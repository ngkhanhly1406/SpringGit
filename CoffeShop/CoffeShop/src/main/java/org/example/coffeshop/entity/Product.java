package org.example.coffeshop.entity;

import jakarta.persistence.*;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private String price;

    @Column(name="description")
    private String description;

    @Column(name="image")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderDetail_id")
    private OrderDetail orderDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(int id, String name, String price, String description, String image, OrderDetail orderDetail, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.orderDetail = orderDetail;
        this.category = category;
    }

    public Product(String name, String price, String description, String image, OrderDetail orderDetail, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.orderDetail = orderDetail;
        this.category = category;
    }

    public Product() {}
}
