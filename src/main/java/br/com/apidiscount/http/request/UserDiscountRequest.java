package br.com.apidiscount.http.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDiscountRequest {

    private Long userId;
    private Double price;

}
