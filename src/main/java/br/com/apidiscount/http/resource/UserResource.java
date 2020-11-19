package br.com.apidiscount.http.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {

    private Long id;

    private String name;

    private Integer ordersCount;

}
