package org.example.lambdaExpressions;

public class lambda_practice {

    public static void main(String[] args) {

        // creating objects using traditional oops and functional interfaces
        Tata tata = new Tata();
        tata.move();
        Mahindra mahindra = new Mahindra();
        mahindra.move();
        Suzuki suzuki = new Suzuki();
        suzuki.move();

        System.out.println("\n");
        // creating objects using lambda expressions
        Car tataCar = ()-> System.out.println("tata car running");
        Car suzukiCar = ()-> System.out.println("suzuki car running");
        Car mahindarCar = ()-> System.out.println("mahindra car running");

        tataCar.move();
        suzukiCar.move();
        mahindarCar.move();

        System.out.println("\n");
        // passing lambda expression as method parameter
        print(() -> System.out.println("tata car going"));
        print(()-> System.out.println("suzuki car going"));
        print(()-> System.out.println("mahindra car going"));

        //traditional way of using functional interface using Calculator class

        System.out.println("\n");
        Calculator cal = new Calculator();
        int add = cal.doMath(10,20);
        System.out.println(add);

        System.out.println("\n");
        // implementing using lambda expression and write multiple lines in one method
        Math addition = (a, b)-> (a+b);
        System.out.println(addition.doMath(10,20));

        // implementing multiple commands  in lambda methods

        Math power = (a,b)->{
            int result =1;
            for(int i = 1;i<=b;i++){
                result = result*a;
            }
            return result;
        };

        System.out.println(power.doMath(10,2));



    }

    public static void print(Car car){
        car.move();
    }
}

interface Math{
    int doMath(int a , int b);
}

class Calculator implements Math{

    @Override
    public int doMath(int a , int b){
        return a+b;
    }
}



interface Car{
    void move();
}

class Tata implements Car{

    @Override
    public void move() {
        System.out.println("tata car moving");
    }
}

class Mahindra implements Car{

    @Override
    public void move(){
        System.out.println("mahindra car moving");
    }
}

class Suzuki implements Car{

    @Override
    public void move(){
        System.out.println("suzuki car moving");
    }
}
