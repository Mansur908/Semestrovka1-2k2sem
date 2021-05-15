package ru.itis.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String text;

    private int price;

    private String image;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId")
//    private User user;

    public static Product from(Product product) {
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .type(product.getType())
                .text(product.getText())
                .price(product.getPrice())
                .image(product.getImage())
//                .user(product.getUser())
                .build();
    }

    public static List<Product> from(List<Product> products) {
        return products.stream()
                .map(Product::from)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"name\":\"" + name +
                "\", \"type\":\"" + type +
                "\", \"text\":\"" + text +
                "\", \"price\":\"" + price +
                "\", \"image\":\"" + image +
                "\"" +
//                "\", \"user\":" + user.toJson() +
                "}";
    }
}
