import model.Product;
import model.Warehouse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.*;

class WarehouseTest {

    @Test
    public void selectingProductTest() {
        Warehouse warehouse = new Warehouse();
        Product product = new Product("Java Programmer Compendium", "Book", 16, 142.99);

        warehouse.insertProduct(product);

        Assertions.assertEquals(product, warehouse.selectProduct(product.getId()));
        Assertions.assertNull(warehouse.selectProduct(null));
        Assertions.assertNull(warehouse.selectProduct(2));
    }

    @Test
    public void insertingProductTest() {
        Warehouse warehouse = new Warehouse();
        Product product1 = new Product("Java Hibernate framework", "Book", 32, 89.88);
        Product product2 = new Product("Java Spring Boot", "Book", 11, 125.0);

        Assertions.assertTrue(warehouse.insertProduct(product1));
        Assertions.assertTrue(warehouse.insertProduct(product2));
        Assertions.assertFalse(warehouse.insertProduct(null));

        Assertions.assertEquals(2, warehouse.getStorageSize());
    }

    @Test
    public void updatingProductTest() {
        Warehouse warehouse = new Warehouse();
        Product product = new Product("Java Best Practsies", "Book", 8, 65.99);

        warehouse.insertProduct(product);

        product.setName("Java Best Practices");
        product.setPrice(78.82);

        Assertions.assertTrue(warehouse.updateProduct(product));
        Assertions.assertEquals(Double.valueOf(78.82), warehouse.selectProduct(product.getId()).getPrice());
        Assertions.assertEquals("Java Best Practices", warehouse.selectProduct(product.getId()).getName());
        Assertions.assertNotEquals(65.99, warehouse.selectProduct(product.getId()).getPrice());
    }

    @Test
    public void deletingProductTest() {
        Warehouse warehouse = new Warehouse();
        Product product = new Product("Java Algorithms and data structure", "Book", 7, 78.11);

        warehouse.insertProduct(product);

        Assertions.assertTrue(warehouse.deleteProduct(product));
        Assertions.assertEquals(0, warehouse.getStorageSize());
    }

//    @Test
    public void filteringProductByIdTest() {
        Warehouse warehouse = new Warehouse();
        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 16, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        List<Product> filteredProducts;

        filteredProducts = warehouse.filterProducts(Product.Fields.Id, 2);
        Assertions.assertEquals(1, filteredProducts.size());
        Assertions.assertEquals(product2, filteredProducts.get(0));
    }

    @Test
    public void filteringProductByNameTest() {
        Warehouse warehouse = new Warehouse();
        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 16, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        List<Product> filteredProducts;

        filteredProducts = warehouse.filterProducts(Product.Fields.Name, "Thinking in Java");
        Assertions.assertEquals(1, filteredProducts.size());
        Assertions.assertEquals(product1, filteredProducts.get(0));
    }

    @Test
    public void filteringProductByCategoryTest() {
        Warehouse warehouse = new Warehouse();
        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 16, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        List<Product> filteredProducts;

        Assertions.assertNull(warehouse.filterProducts(null, null));

        filteredProducts = warehouse.filterProducts(Product.Fields.Category, "Book");
        Assertions.assertEquals(3, filteredProducts.size());
    }

    @Test
    public void filteringProductByStockTest() {
        Warehouse warehouse = new Warehouse();
        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 24, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        List<Product> filteredProducts;

        filteredProducts = warehouse.filterProducts(Product.Fields.Stock, 24);
        Assertions.assertEquals(2, filteredProducts.size());
        Assertions.assertEquals(filteredProducts.get(1).getStock(), filteredProducts.get(0).getStock());
    }

    @Test
    public void filteringProductByPriceTest() {
        Warehouse warehouse = new Warehouse();
        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 24, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        List<Product> filteredProducts;

        filteredProducts = warehouse.filterProducts(Product.Fields.Price, 45.99);
        Assertions.assertEquals(1, filteredProducts.size());
        Assertions.assertEquals(product2, filteredProducts.get(0));
    }

    @Test
    public void sorteringProductsAscendingTest() {
        Warehouse warehouse = new Warehouse();
        List<Product> filteredProducts;
        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 16, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        Assertions.assertNull(warehouse.sortProducts(null, true));

        filteredProducts = warehouse.sortProducts(Product.Fields.Name, true);
        Assertions.assertEquals(product3, filteredProducts.get(0));
        Assertions.assertEquals(product2, filteredProducts.get(1));
        Assertions.assertEquals(product1, filteredProducts.get(2));

        filteredProducts = warehouse.sortProducts(Product.Fields.Stock, true);
        Assertions.assertEquals(product2, filteredProducts.get(0));
        Assertions.assertEquals(product3, filteredProducts.get(1));
        Assertions.assertEquals(product1, filteredProducts.get(2));

        filteredProducts = warehouse.sortProducts(Product.Fields.Price, true);
        Assertions.assertEquals(product2, filteredProducts.get(0));
        Assertions.assertEquals(product1, filteredProducts.get(1));
        Assertions.assertEquals(product3, filteredProducts.get(2));
    }

    @Test
    public void sortingProductDescendingTest() {
        Warehouse warehouse = new Warehouse();
        List<Product> filteredProducts;
        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 16, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        Assertions.assertNull(warehouse.sortProducts(null, false));

        filteredProducts = warehouse.sortProducts(Product.Fields.Name, false);
        Assertions.assertEquals(product3, filteredProducts.get(2));
        Assertions.assertEquals(product2, filteredProducts.get(1));
        Assertions.assertEquals(product1, filteredProducts.get(0));

        filteredProducts = warehouse.sortProducts(Product.Fields.Stock, false);
        Assertions.assertEquals(product2, filteredProducts.get(2));
        Assertions.assertEquals(product3, filteredProducts.get(1));
        Assertions.assertEquals(product1, filteredProducts.get(0));

        filteredProducts = warehouse.sortProducts(Product.Fields.Price, false);
        Assertions.assertEquals(product2, filteredProducts.get(2));
        Assertions.assertEquals(product1, filteredProducts.get(1));
        Assertions.assertEquals(product3, filteredProducts.get(0));
    }

    @Test
    public void warehouseIsEmptyTest() {
        Warehouse warehouse = new Warehouse();

        Assertions.assertTrue(warehouse.isStorageEmpty());

        Product product = new Product("Learning Java", "Book", 16, 45.99);
        warehouse.insertProduct(product);

        Assertions.assertFalse(warehouse.isStorageEmpty());
    }

    @Test
    public void gettingWarehouseInstanceTest() {
        Warehouse warehouse = new Warehouse();

        Assertions.assertNotNull(warehouse.getStorage());

        Map<Integer, Product> storage = new HashMap<>(0);
        warehouse.setStorage(storage);

        Assertions.assertSame(storage, warehouse.getStorage());
    }

    @Test
    public void gettingWarehouseSizeTest() {
        Warehouse warehouse = new Warehouse();

        Assertions.assertSame(0, warehouse.getStorageSize());

        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 16, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        Assertions.assertSame(3, warehouse.getStorageSize());
    }

    @Test
    public void selectingAllProductsTest() {
        Warehouse warehouse = new Warehouse();

        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 16, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        List<Product> products = warehouse.selectAllProducts();

        Assertions.assertEquals(3, products.size());
        Assertions.assertEquals(product1, products.get(0));
        Assertions.assertEquals(product2, products.get(1));
        Assertions.assertEquals(product3, products.get(2));
    }

    @Test
    public void clearingWarehouseTest() {
        Warehouse warehouse = new Warehouse();

        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 16, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        Assertions.assertEquals(3, warehouse.getStorageSize());

        warehouse.clearStorage();

        Assertions.assertEquals(0, warehouse.getStorageSize());
    }

    @Test
    public void insertingAllProductsByListTest() {
        Warehouse warehouse = new Warehouse();

        Product product1 = new Product("Thinking in Java", "Book", 34, 65.55);
        Product product2 = new Product("Learning Java", "Book", 16, 45.99);
        Product product3 = new Product("Java Concurrent Programming", "Book", 24, 78.99);

        warehouse.insertProduct(product1);
        warehouse.insertProduct(product2);
        warehouse.insertProduct(product3);

        Product product4 = new Product("Hibernate framework", "Book", 8, 42.23);
        Product product5 = new Product("Java Design Patterns", "Book", 7, 39.11);
        Product product6 = new Product("Spring Boot framework", "Book", 11, 112.52);

        List<Product> products = new ArrayList<Product>(0);
        Collections.addAll(products, product4, product5, product6);

        Assertions.assertTrue(warehouse.insertAllProducts(products));
        Assertions.assertEquals(6, warehouse.getStorageSize());
    }

    @Test
    public void writingWarehouseToFileTest() {
        Warehouse warehouse = new Warehouse();

        Product product1 = new Product("Hibernate framework", "Book", 8, 42.23);
        Product product2 = new Product("Java Design Patterns", "Book", 7, 39.11);
        Product product3 = new Product("Spring Boot framework", "Book", 11, 112.52);

        List<Product> products = new ArrayList<>(0);
        Collections.addAll(products, product1, product2, product3);

        warehouse.insertAllProducts(products);

        try {
            warehouse.writeStorageToFile("WarehouseTest.dat");
        } catch(Exception ignored) {
            throw new AssertionFailedError();
        }
    }

    @Test
    public void readingWarehouseFromFileTest() {
        Warehouse warehouse = new Warehouse();

        Product product1 = new Product("Hibernate framework", "Book", 8, 42.23);
        Product product2 = new Product("Java Design Patterns", "Book", 7, 39.11);
        Product product3 = new Product("Spring Boot framework", "Book", 11, 112.52);

        List<Product> products = new ArrayList<Product>(0);
        Collections.addAll(products, product1, product2, product3);

        warehouse.insertAllProducts(products);

        try {
            warehouse.writeStorageToFile("WarehouseTest.dat");
            warehouse.clearStorage();

            warehouse.readStorageFromFile("WarehouseTest.dat");

        } catch(Exception ignored) {
            throw new AssertionFailedError();
        }

        Assertions.assertEquals(3, warehouse.getStorageSize());
        Assertions.assertThrows(Exception.class, () -> warehouse.readStorageFromFile("FileNotExists.dat"));
    }
}