package org.example.streams;

import java.util.*;

public class forEachMethodPracticeClass {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("venu",26));
        personList.add(new Person("madhu",26));
        personList.add(new Person("Aishu",26));
        personList.add(new Person("Thulasi",42));
        personList.add(new Person("gopal",38));

        //for each method traditional
        for(Person per : personList){
            System.out.println(per);
        }

        System.out.println("\n----------------------------------------------\n");

        //iterator foreach method
        personList.forEach(System.out::println);

        System.out.println("\n----------------------------------------------\n");

        //foreach method in streams
        personList.stream().forEach(System.out::println);


        // foreach method for set
        System.out.println("\n----------------------------------------------\n");
        System.out.println("set foreach print");

        Set<Person> personSet = new HashSet<>();
        personSet.add(new Person("venu",26));
        personSet.add(new Person("madhu",26));
        personSet.add(new Person("Aishu",26));
        personSet.add(new Person("Thulasi",42));
        personSet.add(new Person("Thulasi",42));
        personSet.add(new Person("gopal",38));

        for (Person per: personSet){
            System.out.println(per);
        }

        System.out.println("\n----------------------------------------------\n");

        personSet.forEach(System.out::println);

        System.out.println("\n----------------------------------------------\n");

        personSet.stream().forEach(System.out::println);

        //map for each method

        Map<Integer, Person> personMap = new HashMap<>();

        System.out.println("\n----------------------------------------------\n");
        System.out.println("map practice print");

        personMap.put(1,new Person("venu",26));
        personMap.put(2,new Person("madhu",26));
        personMap.put(3,new Person("Thulasi",42));
        personMap.put(4,new Person("gopal",38));

        //tradition foreach for map
        for (Map.Entry<Integer,Person> personEntry : personMap.entrySet()){
            System.out.print(personEntry.getKey()+", ");
            System.out.println(personEntry.getValue());
        }

        //foreach from iterator
        System.out.println("\n----------------------------------------------\n");
        personMap.forEach((key , value) ->{
            System.out.println(key +" , "+value);
        });

        //form streams
        System.out.println("\n----------------------------------------------\n");

        personMap.entrySet().stream().forEach(entry -> System.out.println(entry.getKey()+" , "+ entry.getValue()));

    }
}

class Person{
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
