package csula.cs4660.examples;

/**
 * Created by ceejay562 on 9/9/2016.
 */
public class Test {

    public static void main(String[] args){
        String a = "a:a";
        String[] a1 = a.split(":");

        for(String word: a1){
            System.out.println(word);
        }
    }


}
