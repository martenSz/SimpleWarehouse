package model;

import java.io.Serializable;

/**
 * Represents a product with basic fields necessary to describe its characteristics
 */
public class Product implements Serializable {

    private static Integer sequence = 1;

    private Integer id;
    private String name;
    private String category;
    private Integer stock;
    private Double price;

    /**
     * Stores all possible fields of product
     */
    public enum Fields {
        Id, Name, Category, Stock, Price
    }

    /**
     * An empty no-arguments constructor needed to MapStruct work properly
     */
    public Product() {

    }

    /**
     * @param name Represents name of product
     * @param category Represents type of category
     * @param stock Represents quantity of stock
     * @param price Represents price per each product
     */
    public Product(final String name, final String category, final Integer stock, final Double price) {
        this.id = sequence++;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }

    /**
     * @return Sequence that generate unique value for id
     */
    public static Integer getSequence() {
        return sequence;
    }

    /**
     * @return Gets id of product
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return Gets name of product
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Set name for product
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return Get category of product
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category Set category for product
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * @return Get stock of product
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock Set stock for product
     */
    public void setStock(final Integer stock) {
        this.stock = stock;
    }

    /**
     * @return Get price of product
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price Set price for product
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * @param p Copies values of product to another this product
     */
    public void clone(Product p) {
        this.name = p.name;
        this.category = p.category;
        this.stock = p.stock;
        this.price = p.price;
    }

    /**
     * @param field Selector of field that should return his value
     * @return Value of selected field
     */
    public Object getSelected(Fields field) {
        switch(field) {
            case Id: return id;
            case Name: return name;
            case Category: return category;
            case Stock: return stock;
            case Price: return price;
        }
        return null;
    }

    /**
     * @param field Selector of field that should be compared with the same field from this product
     * @param product Sent product to be compared with this one
     * @return Result after comparing same field from sent and this products
     */
    public int compareTo(Fields field, Product product) {
        switch(field) {
            case Id: return id.compareTo(product.id);
            case Name: return name.compareTo(product.name);
            case Category: return category.compareTo(product.category);
            case Stock: return stock.compareTo(product.stock);
            case Price: return price.compareTo(product.price);
        }
        return 0;
    }
}
