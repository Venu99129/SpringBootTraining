package org.example.methodReference;

import java.lang.Math;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
interface Printable{
    void print(String str);
}

public class MethodReference {
    public static void main(String[] args) {

        //creating object with lambda expression of static method
        Function<Integer , Double> fun = (value)-> Math.sqrt(value);
        System.out.println(fun.apply(16));

        //with method reference
        Function<Integer , Double> methodReference = Math::sqrt;
        System.out.println(methodReference.apply(4));

        //calling custom method with method reference
        BiFunction<Integer,Integer,Integer> biFunction = MethodReference::addition;
        System.out.println(biFunction.apply(2,4));

        //calling using object method reference
        MethodReference meth = new MethodReference();
        BiFunction<Integer,Integer,Integer> biFun = meth::multiply;
        System.out.println(biFun.apply(4,9));

        Printable printable = meth::print;
        printable.print("hello madhav and aishwarya");

        //REFERENCE  to instance of an arbitrary object
        //sometimes, we call a method of argument in the lambda expression
        //In the case, we can use method reference to call an instance
        //method of an arbitrary object of specific of a specific type

        Function<String,String> function = (s1)-> s1.toLowerCase();
        System.out.println(function.apply("HELLO FROM AISHWARYA"));


        //using method reference
        Function<String,String> funMeth = String::toLowerCase;
        System.out.println(funMeth.apply("HELLO FROM VENU MADHAV"));

        String[] arr = {"q", "w" , "e", "p","o","l","k"};
        //using lambda expression

        Arrays.sort(arr, (s1,s2)-> s1.compareTo(s2));

        System.out.println(Arrays.toString(arr));

        // using method reference
        String[] strArray = {"q", "w" , "e", "p","o","l","k"};

        Arrays.sort(strArray, String::compareTo);
        System.out.println(Arrays.toString(strArray));

        List<String> list = new ArrayList<>();
        list.add("lemon");
        list.add("water melon");
        list.add("banana");
        list.add("lemon");
        list.add("graps");

        //lambda expression
        Function<List<String>, Set<String>> functionLambda = (furtsList)-> new HashSet<>(furtsList);
        System.out.println(functionLambda.apply(list).toString());

        list.add("harbal");
        list.add("banana");
        //method reference of constructor reference
        Function<List<String>, Set<String>> funMethodReference = HashSet::new;
        System.out.println(funMethodReference.apply(list));


    }

    public void print(String str){
        System.out.println(str.toUpperCase());
    }

    public static Integer addition(int a, int b){
        return a+b;
    }

    public Integer multiply(int a ,int b){
        return a*b;
    }

}


