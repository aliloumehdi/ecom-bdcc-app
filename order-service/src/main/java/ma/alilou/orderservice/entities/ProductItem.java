package ma.alilou.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ma.alilou.orderservice.models.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productId;
    @ManyToOne()
    @JsonIgnore
    private Order order;
    private double price;
    private int quantity;
    @Transient
    Product product;
}
