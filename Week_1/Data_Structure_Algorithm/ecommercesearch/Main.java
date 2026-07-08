package ecommercesearch;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        Product[] products = {

                new Product(101, "Laptop", "Electronics"),
                new Product(58, "Phone", "Electronics"),
                new Product(103, "Shoes", "Fashion"),
                new Product(456, "Mouse", "Electronics"),
                new Product(105, "Keyboard", "Electronics")
        };

        // Linear Search

        Product result1 = SearchAlgo.linearSearch(products, "Mouse");

        if(result1 != null)
            System.out.println("Linear Search Found : " + result1);
        else
            System.out.println("Not Found");


        // Sorting before Binary Search

        Arrays.sort(products,
                Comparator.comparing(Product::getProductName));

        Product result2 = SearchAlgo.binarySearch(products, "Mouse");

        if(result2 != null)
            System.out.println("Binary Search Found : " + result2);
        else
            System.out.println("Not Found");
    }
}
