package br.com.apidiscount.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "min_orders")
    private Integer minOrders;

    @Column(name = "max_orders")
    private Integer maxOrders;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "description")
    private String description;

}
