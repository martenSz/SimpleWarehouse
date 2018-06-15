package model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a warehouse that can store products in map data structure
 */
public class Warehouse {

    /**
     * Storage as map data structure to store products as value and thier id as key
     */
    private Map<Integer, Product> storage;

    /**
     * Default constructor for initializing storage
     */
    public Warehouse() {
        this.storage = new HashMap<Integer, Product>();
    }

    /**
     * @param storage To be assigned to storage while initializing warehouse
     */
    public Warehouse(HashMap<Integer, Product> storage) {
        this.storage = storage;
    }

    /**
     * @param storage Set storage to store products
     */
    public void setStorage(Map<Integer, Product> storage) {
        this.storage = storage;
    }

    /**
     * @return Get storage to hold reference with it
     */
    public Map<Integer, Product> getStorage() {
        return storage;
    }

    /**
     * @param id Id of product
     * @return Returns product if exists in storage, if not then returns null
     */
    public Product selectProduct(Integer id) {
        return storage.get(id);
    }

    /**
     * @return Returns list of all products that are stored in the storage
     */
    public List<Product> selectAllProducts() {
        return new ArrayList<>(storage.values());
    }

    /**
     * @param product Product to be inserted into storage
     * @return Returns true if product has been inserted, if not then false will be returned
     */
    public boolean insertProduct(Product product) {
        if(product != null && !storage.containsKey(product.getId())) {
            storage.put(product.getId(), product);
            return true;
        }

        return false;
    }

    /**
     * @param products Products to be inserted into storage
     * @return Returns true if products has been inserted, if not then false will be returned
     */
    public boolean insertAllProducts(List<Product> products) {
        if(products != null) {
            for (Product product : products) {
                if (storage.containsKey(product.getId()))
                    return false;
            }

            storage.putAll(products.stream().collect(Collectors.toMap(Product::getId, p -> p)));
            return true;
        }
        return false;
    }

    /**
     * @param product Product which should be updated in storage by comparing id
     * @return Returns true if product has been updated, if not then false will be returned
     */
    public boolean updateProduct(Product product) {
        if (product != null && storage.containsKey(product.getId())) {
            storage.get(product.getId()).clone(product);
            return true;
        }
        return false;
    }

    /**
     * @param product Product which should be deleted in storage by comparing id
     * @return Returns true if product has been deleted, if not then false will be returned
     */
    public boolean deleteProduct(Product product) {
        if(product != null && storage.containsKey(product.getId())) {
            storage.remove(product.getId());
            return true;
        }
        return false;
    }

    /**
     * @param field Field by which products should be filtered
     * @param keyword Keyword by which comparison is to be made
     * @return List of products after filtration
     */
    public List<Product> filterProducts(Product.Fields field, Object keyword) {
        if(field != null && keyword != null) {
            return storage.values().stream().filter(p -> p.getSelected(field).equals(keyword)).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * @param field Field by which products should be sorted
     * @param ascending Keyword by which comparison is to be made
     * @return List of products after sorting
     */
    public List<Product> sortProducts(Product.Fields field, Boolean ascending) {
        if(field != null) {
            List<Product> products = new ArrayList<>(storage.values());

            if(ascending) {
                products.sort((p1, p2) -> p1.compareTo(field, p2));
            } else {
                products.sort(Collections.reverseOrder((p1, p2) -> p1.compareTo(field, p2)));
            }

            return products;
        }
        return null;
    }

    /**
     * @param fileName The name of the file to which products have to be saved
     * @throws IOException A returned exception that may occur during writing data
     */
    public void writeStorageToFile(String fileName) throws IOException {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(storage);

        } finally {
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        }
    }

    /**
     * @param fileName The name of the file from which products have to be readed
     * @throws ClassNotFoundException A returned exception that may occur during casting class
     * @throws FileNotFoundException A returned exception that may occur when file has not be found
     * @throws IOException A returned exception that may occur during reading data
     */
    public void readStorageFromFile(String fileName) throws ClassNotFoundException, FileNotFoundException, IOException {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);

            storage = (Map<Integer, Product>) objectInputStream.readObject();
        } finally {
            objectInputStream.close();
            fileInputStream.close();
        }
    }

    /**
     * @return Returns the current size of storage
     */
    public int getStorageSize() {
        return storage.size();
    }

    /**
     * @return Returns flag that inform if storage is empty or not
     */
    public boolean isStorageEmpty() {
        return storage.isEmpty();
    }

    /**
     * Clears all data from storage
     */
    public void clearStorage() {
        storage.clear();
    }
}
