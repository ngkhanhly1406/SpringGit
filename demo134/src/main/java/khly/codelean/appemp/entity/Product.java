package khly.codelean.appemp.entity;

import jakarta.persistence.*;


@Entity
@Table(name="product")
public class Product {

    // define fields

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="product_details")
    private String productDetails;

    @Column(name="price")
    private double price;

    @Column(name="image")
    private String image;

    // define constructors

    public Product() {

    }

    public Product(int id, String name, String productDetails, double price, String image) {
        this.id = id;
        this.name = name;
        this.productDetails = productDetails;
        this.price = price;
        this.image = image;
    }


    public Product(String name, String productDetails, double price, String image) {
        this.name = name;
        this.productDetails = productDetails;
        this.price = price;
        this.image = image;
    }

    // define getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // define toString

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", productDetails=" + productDetails +
                ", price=" + price + ", image=" + image + "]";
    }
}
