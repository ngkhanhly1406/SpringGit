package org.example.coffeshop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="order")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @Column(name="order_date")
    private String order_date;

    @Column(name="total_amount")
    private String total_amount;

    @Column(name="status")
    private String status;

    public Order (int id, Customer customer, String order_date, String total_amount, String status) {
        this.id = id;
        this.customer = customer;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.status = status;
    }

    public Order (Customer customer, String order_date, String total_amount, String status) {
        this.customer = customer;
        this.order_date = order_date;
        this.total_amount = total_amount;
        this.status = status;
    }
    public Order() {}


}
