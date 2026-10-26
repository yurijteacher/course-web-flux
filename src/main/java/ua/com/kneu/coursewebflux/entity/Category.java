package ua.com.kneu.coursewebflux.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "category")
public class Category {

    @Id
    private Long id;
    private String name;
    private String description;
    private String image;


//    private List<Products> productsList = new ArrayList<>();


}

