package br.com.apidiscount.http.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResource {

    private Long id;

    private Integer minOrders;

    private Integer maxOrders;

    private Integer discount;

    private String description;

}
