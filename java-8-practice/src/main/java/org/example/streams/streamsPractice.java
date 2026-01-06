package org.example.streams;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class streamsPractice {
    public static void main(String[] args) {

        //creating stream using of method
        Stream<String> stringStream = Stream.of("a","b","c");
        stringStream.forEach(System.out::println);

        System.out.println();

        // creating stream from list
        List<String> firstList = List.of("banana", "carrot", "beetroot", "mango");
        Stream<String> firstStream = firstList.stream();
        firstStream.forEach(System.out::println);

        System.out.println();

        //creating stream from array
        String[] strArr = new String[]{"number", "digit","kama","populist","column","margin"};
        Stream<String> arrStream = Arrays.stream(strArr);
        arrStream.forEach(System.out::println);

        System.out.println();

        //creating stream from set
        Set<String> strSet = Set.of("hello", "welcome","melon","lemon","jack","joy");
        strSet.add("hello"); strSet.add("melon");
        Stream<String> setStream = strSet.stream();
        setStream.forEach(System.out::println);
    }
}
