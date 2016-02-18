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

// TODO continue on page 20, JavaBeans section
