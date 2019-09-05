package com.ecommerce.product.product;


import com.ecommerce.product.event.product.ProductCreatedEvent;
import com.ecommerce.product.event.product.ProductNameUpdatedEvent;
import com.ecommerce.shared.model.BaseAggregate;

import java.math.BigDecimal;
import java.time.Instant;

public class Product extends BaseAggregate {
    private ProductId id;
    private String name;
    private String description;
    private BigDecimal price;
    private Instant createdAt;
    private int inventory;

    private Product() {
    }

    private Product(String name, String description, BigDecimal price) {
        this.id = ProductId.newId();
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = Instant.now();
        this.inventory = 0;
    }

    public static Product create(String name, String description, BigDecimal price) {
        Product product = new Product(name, description, price);
        product.raiseEvent(new ProductCreatedEvent(product.getId().toString(), name, description, price, product.getCreatedAt()));
        return product;
    }

    public void updateName(String newName) {
        raiseEvent(new ProductNameUpdatedEvent(this.getId().toString(), name, newName));
        this.name = newName;
    }

    public void updateInventory(int inventory) {
        this.inventory = inventory;
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public int getInventory() {
        return inventory;
    }
}