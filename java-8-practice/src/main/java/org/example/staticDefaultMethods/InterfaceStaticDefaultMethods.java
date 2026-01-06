package org.example.staticDefaultMethods;



public class InterfaceStaticDefaultMethods {
    public static void main(String[] args) {

        // creating object using lambda expression
        CustomInterface cus = (a, b) -> System.out.println(a + b);
        cus.math(52, 63);

        //calling default method using object cus
        cus.mul(82, 6);

        //calling static main method using interface name
        CustomInterface.main();

        // calling abstract method and default override method using implementation class
        CustomInterfaceImpl cusImpl = new CustomInterfaceImpl();
        cusImpl.math(20, 30);

        //override mul method called not from parent class its calling from child
        cusImpl.mul(82, 6);

        //trying to call static method using implemented class object
        //cusImpl.main();
        // it showing no methods found using main in CustomImpl class
    }
}

interface CustomInterface{
    default void mul(int a , int b){
        System.out.println(a*b);
    }

    void math(int a , int b);

    static void main(){
        System.out.println("hello from main static method");
    }
}

class CustomInterfaceImpl implements CustomInterface{

    @Override
    public void mul(int a, int b) {
        System.out.println(a*a*b);
    }

    @Override
    public void math(int a, int b) {
        System.out.println("from impl class");
        System.out.println(a+b);
    }
}

