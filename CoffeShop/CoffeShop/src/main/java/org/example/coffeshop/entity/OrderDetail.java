package org.example.coffeshop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="orderdetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private double price;

    @OneToMany(mappedBy = "orderDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderDetail(int id, int quantity, double price, Order order) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }
    public OrderDetail(int quantity, double price, Order order) {
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    public OrderDetail() {}
}
