package opentuned.lambda;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stefan.cross on 17/02/2016.
 *
 * Lambdas allow us as programmers to make instances of anonymous classes easier to write and read.
 * The format of a lambda can change depending on the number of lines it spans and the arguments taken.
 *
 * In Java lambdas of is a functional interface type. A functional interface is an interface with only
 * one abstract method. Methods from the Object class do not count. Functional interfaces can be annotated.
 *
 * Lambda expressions have the useful feature that they can be passed as a variable, this means that they can
 * be taken as a method parameter and can also be returned by a method.
 *
 * Lambdas are not objects in the traditional sense, they are considered objects but without an identity
 *
 * There are many new functional interfaces included in java.util.function including:
 *  Supplier
 *  Consumer/BiConsumer
 *  Predicates/BiPredicates
 *  Function... and many more
 */
public class FirstLambda {

    public static void main(String[] args){

        // The old way
        FileFilter fileFiler = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".class");
            }
        };

        // The new lambda way!
        // Note the concise inner anonymous class
        FileFilter fileFilterLambda = (File pathname) ->
                pathname.getName().endsWith(".class");

        File dir = new File("target/classes/opentuned/lambda");

        File[] files = dir.listFiles(fileFilterLambda);

        for(File f : files){
            System.out.println(f);
        }

        // We can also use them to process data
        // TODO But hang on! Where did this new forEach method come from!?!?
        Arrays.asList(files).forEach(f -> System.out.println(f));

    }

}
