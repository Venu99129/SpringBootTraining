package org.example.optionalPackage;

import java.util.Optional;

public class OptionalClass {

    public static void main(String[] args) {

        //creating optional obj using static methods

        //static empty function
        Optional<Object> optionalObj = Optional.empty();
        System.out.println(optionalObj);

        String email = null;

        //ofNullable static method
        Optional<String> optionalNullable = Optional.ofNullable(email);
        System.out.println(optionalNullable);

        String name = "venuAishu";
        //of static method only use if you have object to convert optional class or else it throws exception
        Optional<String> emailObj = Optional.of(name);
        System.out.println(emailObj);


        //get method use if optional object have value
        System.out.println(emailObj.get());

        //isPresent method
        String value = new String();

        if(optionalNullable.isPresent()){
            value = optionalNullable.get();
        }else{
            System.out.println("no value present");
        }


        //using or else method
        String val = optionalNullable.orElse("default null");
        System.out.println(val);

        //orElseGet method input type lambda method
        String optionalValue = optionalNullable.orElseGet(()-> "or else get method call");
        System.out.println(optionalValue);

        try {
            String nullObject = null;
            //orElseThrow method using get value or else throw the error
            Optional<String> optionalThrowObj = Optional.ofNullable(nullObject);
            String thrownVal = optionalThrowObj.orElseThrow(() -> new IllegalArgumentException("no such value is present"));

            System.out.println(thrownVal);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //if present method is only execute the inner body option object have the value
            Optional<String> optionalGender = Optional.of("MALE");
            optionalGender.ifPresent((s)->{
                System.out.println("value is present");
            });

            Optional<String> nullOptObj = Optional.empty();
            nullOptObj.ifPresent((s) -> System.out.println("value is there in nullOptObj"));

            //if present or else method work like if and else block if object is present
            //it execute first lambda expression or method will execute second lambda expression
            nullOptObj.ifPresentOrElse((s) -> System.out.println("value is there in nullOptObj") ,
                    ()-> System.out.println("nullOptObj is doest have any value"));


            String strObj = "  abcdf";
            Optional<String> filObject = Optional.of(strObj);

            // filter use to filter the obj allows the next flow to continue
            filObject.filter(s-> s.contains("abc"))
                    .ifPresent(s-> System.out.println(s+" filtered and value is there"));



            //map function is used to map the object into one form to another form
            filObject.map(String::trim)
                    .filter(s-> s.contains("abc"))
                    .ifPresent(System.out::println);

        }
    }
}
