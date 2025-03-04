package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull(message = "Название не должно быть пустым")
    private String name;

    @Column(name = "slug")
    @NotNull
    private String slug;

    @Column(name = "price")
    @NotNull
    private int price;

    @Column(name = "quantity")
    @NotNull
    private int quantity;

    @Column(name = "image")
    @NotNull
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private CategoryEntity category;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                ", category=" + (category != null ? category.getId() : "null") +
                ", updateAt=" + updateAt +
                '}';
    }
}
