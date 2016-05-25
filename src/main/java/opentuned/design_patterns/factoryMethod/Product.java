package opentuned.design_patterns.factoryMethod;

// THE FACTORY METHOD

// Known as the virtual constructor, uses abstract classes for defining and maintaining relationships between objects
// Responsibilities such as creating objects, where it the library know where an object needs to be created but not the type


// INTENT
// Defines an interface for creating an object, but leaves the choice of type to subclasses, deferred to run-time.
// Refers to the newly created object through a common interface

// Defines the interface for Objects that factory creates
interface Product {
    void displayInfo();
}

// Also referred to as the factory as it creates the product objects declares the FactoryMethod
abstract class Creator{

    public void anOperation(){
        Product product = factoryMethod();
    }

    protected abstract Product factoryMethod();
}

// implements the product interface
class ConcreteProduct1 implements Product{
    @Override
    public void displayInfo() {
        System.out.println("ConcreteProduct1 displayInfo method called");
    }
}

// Overrides the generating method for creating ConcreteProduct objects
class ConcreteCreator extends Creator{
    @Override
    protected Product factoryMethod() {
        return new ConcreteProduct1();
    }
}

class Client {
    public static void main(String[] args){
        Creator creator = new ConcreteCreator();
        creator.anOperation();
    }
}