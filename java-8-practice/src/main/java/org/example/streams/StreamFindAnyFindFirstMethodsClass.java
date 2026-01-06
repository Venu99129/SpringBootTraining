package org.example.streams;

import java.util.Comparator;
import java.util.List;

public class StreamFindAnyFindFirstMethodsClass {
    public static void main(String[] args) {
        List<Integer> numList = List.of(8,5,2,3,6,9,1,4,11,7);


        // find first it returns
        System.out.print("findFirst -> ");
        numList.stream().findFirst().ifPresent(System.out::println);

        System.out.println("\n-----------------------------------------------------\n");

        //findAny it returns any value is there in List
        System.out.print("findAny -> ");
        numList.stream().findAny().ifPresent(System.out::println);

        System.out.println("\n-----------------------------------------------------\n");
        //count
        System.out.print("count -> ");
        System.out.println(numList.stream().count());

        System.out.println("\n-----------------------------------------------------\n");
        //min
        System.out.print("min -> ");
        System.out.println(numList.stream().min(Comparator.naturalOrder()).get());

        System.out.println("\n-----------------------------------------------------\n");
        //min
        System.out.print("max -> ");
        System.out.println(numList.stream().max(Comparator.naturalOrder()).get());
    }
}
