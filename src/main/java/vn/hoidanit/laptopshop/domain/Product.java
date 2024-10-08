package vn.hoidanit.laptopshop.domain;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private double price;
    private String img;

    @Column(columnDefinition = "Text")
    private String detailDescription;

    private String shortDescription;
    private int quantity;
    private long sold;
    private String factory;
    private String target;

    @OneToMany(mappedBy = "productId")
    private Set<OrderDetail> orderDetails;

    public Product() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getSold() {
        return sold;
    }

    public String getFactory() {
        return factory;
    }

    public String getTarget() {
        return target;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", img=" + img + ", detailDescription="
                + detailDescription + ", shortDescription=" + shortDescription + ", quantity=" + quantity + ", sold="
                + sold + ", factory=" + factory + ", target=" + target + "]";
    }
}
