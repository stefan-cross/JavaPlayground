package opentuned.groovy

/**
 * Notes from the Pragmatic Bookshelf, Programming Groovy 2
 * Created by stefan.cross on 17/02/2016.
 */

// Groovy is built on the JVM and has all the Java methods at its disposal
 println "Today is " + new Date()

// Groovy lets you loop in very easy ways, note the short hand range syntax with ".."
for(i in 0..10) {print "$i "}
print "\n"

println "Now wasn't that a breeze!"

// But that's not the only way to skin this cat
0.upto(10){ print "$it "}
print "\n"

// Note the use of the special var $it, it represents the index value through the loop
// upto() method accepts a closure as a param

println "So many variations to easily loop!"
print "\n"

// Here's another alternative using the times method
10.times {print "$it "}
print "\n"

// Neat, how's about looping with intervals
0.step(10, 2) {print "$it "}
print "\n"

// That's all well and good but lets move on, hows about something more advanced
// Groovy's GDK builds on top of the JDK, adding convenience methods to various classes
println "ping -c2 google.com".execute().text

// The safe navigation operator is a gem, eliminating the mundane null checks
def foo(str){
    // if (str != null) {str.reverse()}
    str?.reverse()
}
println("Here's a string to chew on")
println(null)
// Note how this last arguement produces a null value rather than the dreaded nullPointerException!


// Now how's about some exception handling, Java forces us to handle checked exceptions
try{
    Thread.sleep(5000)
} catch (InterruptedException ex){
    // eh? I'm losing sleep over what to do here...
}
// Not so in the Groovy world
try{
    openFile = new FileInputStream("nonexistentfile");
} catch(FileNotFoundException ex){
    println "No file found bozo"
}

// You can even omit the type of exception and catch just about any thrown exception, but not Errors, or throwables
// other then exception

// Groovy as Lightweight Java!
// Return statement and semicolons are almost always optional!
// Methods are public by default
// The ?. operator dispatches calls only if the object reference is not null
// We can initialise JavaBeans using named params
// We are not forced to catch exceptions that we don't care to handle
// We can use 'this' within 'static' methods to refer to the Class object
// The 'learn()' method returns the class to chain calls

class Wizard{
    def static learn(trick, action){
        //..
        this
    }
}

Wizard.learn('alohmora', {/*..*/})
    .learn('shazam', {/*..*/})
        .learn('bonza', {/*..*/})

// We've demonstrated the concise and expressive nature of Groovy, now lets look at how to reduce clutter in a fundemental
// part of Java, JavaBeans

// JavaBeans conventionally use getters and setters to expose properties. The intent of a JavaBean is component-based
// development, application assemble and integration practical making possible the IDE and plugin development.

class Car {
    final year;
    private miles = 0;
    Car(theYear) {year = theYear}

    def getMiles(){
        println "getMiles called"
        miles
    }

    private void setMiles(miles){
        throw new IllegalAccessException("alteration of miles not permitted")
    }

    def drive(dist) { if(dist > 0) miles += dist }
}

// If we want to access properties we dont need getters and setters anymore! Much more elegant!

def car = new Car()
println "Year: $car.year"
println "Miles: $car.miles"
println "Driving"
car.drive(10)
println "Miles: $car.miles"


try{
    print "can I set the year?"
    car.year = 1977
} catch(ReadOnlyPropertyException r){
    println r.message
}

try{
    print "can I set the miles?"
    car.miles = 0
} catch(IllegalAccessException ex){
    println ex.message
}

// Groovy gives flexibility when initialising a JavaBean class, we can use comma separated name-value pairs. This is a
// post-construction operation if our class has a no-argument constructor. We can also design our methods to take named
// arguments, the following uses a Map to exemplify this feature.

class Robot {
    def type, height, width
    def access(location, weight, fragile){
        println "Received fragile? $fragile, weight $weight, loc: $location"
    }
}

robot = new Robot(type: 'arm', width: 10, height: 40)
println "$robot.type, $robot.width, $robot.height"

robot.access(x:30, y:20, z:10, 50, true)
// You can change the order
robot.access(true, 50, x:30, y:20, z:10)

// The instance of Robot too type, height and width params as name-value pairs, the flexible constructor created by
// the Groovy compiler is used here.

// The access method receives a Map as a param. If the number of args we send is greater then the number of params
// the method expects and if the excess args are in name-value pairs then Groovy assumes the methods first param
// is a Map and groups all the name-value pairs in the arguments together as values for the first param.

// Although this is powerful, it can become confusing so use sparingly!

// We can avoid confusion explicitly naming the fisrt param as a map:

def accessM(Map location, weight, fragile){
    println "Received fragile? $fragile, weight $weight, loc: $location"
}

// TODO continue from 2.4 optional params!