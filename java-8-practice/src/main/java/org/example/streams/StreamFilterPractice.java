package org.example.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFilterPractice {

    static List<Product> productList = new ArrayList<>();
    static{
        productList.add(new Product(1,"lenovo thinpad",55000.00,"laptop"));
        productList.add(new Product(2, "HP Pavilion", 62000.00, "laptop"));
        productList.add(new Product(3, "Samsung Galaxy S21", 54000.00, "mobile"));
        productList.add(new Product(4, "Sony WH-1000XM4", 22000.00, "headphone"));
        productList.add(new Product(5, "Dell Inspiron 3501", 58000.00, "laptop"));
        productList.add(new Product(6, "Apple iPhone 14", 75000.00, "mobile"));
        productList.add(new Product(7, "LG OLED 55-inch TV", 125000.00, "television"));
        productList.add(new Product(8, "Asus VivoBook 15", 54000.00, "laptop"));
        productList.add(new Product(9, "Boat Airdopes 441", 2500.00, "earbuds"));
        productList.add(new Product(10, "Logitech MX Master 3", 6500.00, "mouse"));

        productList.add(new Product(11, "Acer Aspire 7", 60000.00, "laptop"));
        productList.add(new Product(12, "JBL Charge 5", 14999.00, "speaker"));
        productList.add(new Product(13, "Canon EOS 200D II", 56000.00, "camera"));
        productList.add(new Product(14, "Samsung Galaxy Tab S7", 48000.00, "tablet"));
        productList.add(new Product(15, "Apple MacBook Air", 92000.00, "laptop"));
        productList.add(new Product(16, "Mi Smart Band 7", 3500.00, "smartwatch"));
        productList.add(new Product(17, "Sony Bravia 43-inch", 56000.00, "television"));
        productList.add(new Product(18, "Redmi Note 12 Pro", 24000.00, "mobile"));
        productList.add(new Product(19, "HP LaserJet 1020", 14500.00, "printer"));
        productList.add(new Product(20, "Samsung SSD 1TB", 8500.00, "storage"));
        productList.add(new Product(21, "Dell KM117 Keyboard+Mouse Combo", 1200.00, "accessory"));

    }
    public static void main(String[] args) {

        // here do some filter data price grater than 25k with traditional code

        List<Product> filteredList = new ArrayList<>();
        for (Product prod :productList){
            if(prod.getPrice() > 25000){
                filteredList.add(prod);
            }
        }

        for(Product fil: filteredList){
            System.out.println(fil);
        }

        System.out.println("\n___________________________________________________________\n");
        //here we will do same filter using stream
        List<Product> filList = productList.stream().filter(prod -> prod.getPrice()>25000).collect(Collectors.toList());
        filList.forEach(System.out::println);

        //more simplify way
        System.out.println("\n___________________________________________________________\n");
        productList.stream().filter(product -> product.getPrice()>25000).forEach(System.out::println);


    }
}

class Product {
    public Product(int id, String productName, double price, String category) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    int id;
    String productName;
    double price;
    String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}