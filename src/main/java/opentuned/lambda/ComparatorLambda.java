package opentuned.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by stefan.cross on 17/02/2016.
 */
public class ComparatorLambda {

    public static void main(String[] args){

        // Prior to Java 8 if we had wanted to implement a Comparator, one would have
        // to have done so in the following manner..
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };

        // In the brave new world we can leverage Lambda functions for a more concise syntax

        Comparator<String> lambdaComparator = (String s1, String s2) ->
                Integer.compare(s1.length(), s2.length());

        List<String> list = Arrays.asList("Sometext", "text", "tiddlywinks");
        Collections.sort(list, lambdaComparator);

        for (String s : list){
            System.out.println(s);
        }

    }
}
