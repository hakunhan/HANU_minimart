package sqa.hanu_minimart.model;

import javax.persistence.*;

@Entity
public class Sale {
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @Column(precision = 3, scale = 2)
    private float sale;
    @Id
    private Integer id;

    public Sale(Product product, float sale) {
        this.product = product;
        this.sale = sale;
    }

    public Sale() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "product=" + product +
                ", sale=" + sale +
                '}';
    }


}
