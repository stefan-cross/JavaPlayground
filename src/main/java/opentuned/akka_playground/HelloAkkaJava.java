package opentuned.akka_playground;

import akka.actor.*;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import scala.concurrent.duration.Duration;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by stefan.cross on 19/02/2016.
 */
public class HelloAkkaJava {

    public static class Greet implements Serializable {}

    public static class WhoToGreet implements Serializable {
        public final String who;
        public WhoToGreet(String who){
            this.who = who;
        }
    }

    public class Greeting implements Serializable{
        public final String message;
        public Greeting(String message){
            this.message = message;
        }
    }

    public class Greeter extends UntypedActor {

        String greeting = "";

        public void onReceive(Object message) throws Exception {
            if(message instanceof WhoToGreet)
                greeting = "Hello, " + ((WhoToGreet) message).who;
            else if (message instanceof Greet)
                getSender().tell(new Greeting(greeting), getSelf());
            else unhandled(message);
        }
    }

    public static void main(String[] args){
        try {
            // Create the helloakka actor system
            final ActorSystem system = ActorSystem.create("helloakkka");
            // Create our actors greeter, actor-in-a-box
            final ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeter");
            final Inbox inbox = Inbox.create(system);

            // Tell the greeter to change its greeting msg
            greeter.tell(new WhoToGreet("akka"), ActorRef.noSender());
            // Ask the greeter for the latest greeting...
            inbox.send(greeter, new Greet());

             // Delay 5 secs for the reply
            final Greeting greeting1 = (Greeting)inbox.receive(Duration.create(5, TimeUnit.SECONDS));
            System.out.println("Greeting: " + greeting1.message);

            // Change the greeting and ask for it again...
            greeter.tell(new WhoToGreet("typesafe"), ActorRef.noSender());
            inbox.send(greeter, new Greet());

            final Greeting greeting2 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
            System.out.println("Greeting: " + greeting2.message );

            // after zero secs send a Greet message every sec to the greeter with a sender to the GreetPrinter
            final ActorRef greetPrinter = system.actorOf(Props.create(GreetPrinter.class));
            system.scheduler().schedule(Duration.Zero(), Duration.create(1, TimeUnit.SECONDS), greeter, new Greet(), system.dispatcher(), greetPrinter);

        } catch (TimeoutException e) {
            System.out.println("Got a timeout waiting on an Actor");
            e.printStackTrace();
        }


    }

    public static class GreetPrinter extends UntypedActor{
        public void onReceive(Object message) {
            if(message instanceof Greeting)
                System.out.println(((Greeting) message).message);
        }
    }

}
