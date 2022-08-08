package com.example.company;

import java.util.Objects;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CUSTOMER_ORDER")				//this is used to change the name of the table
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;	//apparently you can put annotations where ever you want;
    private String description;
    private OrderStatus orderStatus;

    public Order() {}

    Order(String description, OrderStatus orderStaus) {

        this.description = description;
        this.orderStatus = orderStaus;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public OrderStatus getStatus() {
        return this.orderStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(OrderStatus orderStaus) {
        this.orderStatus = orderStaus;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Order))
            return false;
        Order order = (Order) o;
        return Objects.equals(this.id, order.id) && Objects.equals(this.description, order.description)
                && this.orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.orderStatus);
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status=" + this.orderStatus + '}';
    }
}
