package ua.com.kneu.coursewebflux.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Products {

    @Id
    private Long id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;

    @Transient
    private Category category;

    @Transient
    private Long categoryId;



}