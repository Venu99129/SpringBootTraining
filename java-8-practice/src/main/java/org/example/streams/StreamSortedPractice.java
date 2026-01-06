package org.example.streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSortedPractice {

    public static void main(String[] args) {
        //sorting list using streams
        List<Product> sortedList =  StreamFilterPractice.productList.stream().sorted(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        }).collect(Collectors.toList());

        sortedList.forEach(System.out::println);

        System.out.println("\n ------------------------------------------------------------------------------------- \n");

        // more optimise way lambda expressions

        StreamFilterPractice.productList.stream().sorted((o1,o2)-> Double.compare(o1.getPrice(),o2.getPrice())).toList().forEach(System.out::println);

        System.out.println("\n ------------------------------------------------------------------------------------- \n");

        StreamFilterPractice.productList.stream().sorted(Comparator.comparingDouble(Product::getPrice)).forEach(System.out::println);

        System.out.println("\n ------------------------------------------------------------------------------------- \n");
        System.out.println("descending order sort \n");
        // descending sort
        StreamFilterPractice.productList.stream().sorted((o1,o2)-> Double.compare(o2.getPrice(),o1.getPrice())).forEach(System.out::println);
    }
}
