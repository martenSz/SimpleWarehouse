package com.company;

import dto.ProductDto;
import mapper.ProductMapper;
import model.Product;
import model.Warehouse;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        Product product1 = new Product("Java Kompedium programisty IX", "Książka", 62, 149.99);
        Product product2 = new Product("Java Hibernate VI", "Książka", 22, 49.99);
        Product product3 = new Product("Java Spring Boot III", "Książka", 16, 84.0);

        //zastosowanie MapStruct i Lombok
        ProductDto productDto1 = ProductMapper.MAPPER.productToProductDto(product1);


        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        System.out.println("Aktualnie są " + warehouse.getStorageSize() + " produkty w magazynie.");

        List<Product> products = warehouse.sortProducts(Product.Fields.Price, true);
        products.forEach(p -> System.out.println(p.getId() + " " + p.getName() + " " + p.getCategory() + " " + p.getStock() + " " + p.getPrice()));
    }
}
