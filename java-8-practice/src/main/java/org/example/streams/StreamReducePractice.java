package org.example.streams;


import java.util.Arrays;
import java.util.List;

public class StreamReducePractice {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);

        //reduce method return on;y single value it reduces collection of values reduces to single values

        list.stream().reduce((a,b)-> a+b).ifPresent(System.out::println);

        System.out.println("----------------------------------------------");
        //reduce method for max
        list.stream().reduce((a,b) -> a>b?a:b).ifPresent(System.out::println);

        System.out.println("------------------------------------------------");
        //reduce method for min
        list.stream().reduce((a,b) -> a<b?a:b).ifPresent(System.out::println);

        System.out.println("------------------------------------------------");
        //reduce for average
        list.stream().reduce((a,b)-> a+b/2).ifPresent(System.out::println);
    }
}
