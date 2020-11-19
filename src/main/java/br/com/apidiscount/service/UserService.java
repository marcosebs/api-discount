package br.com.apidiscount.service;

import br.com.apidiscount.http.request.UserDiscountRequest;
import br.com.apidiscount.http.resource.UserResource;
import br.com.apidiscount.http.response.UserDiscountResponse;

public interface UserService {

    UserDiscountResponse calculate(UserDiscountRequest request);

    UserResource create(UserResource request);

    UserResource edit(UserResource request);

    void delete(Long id);

    UserResource find(Long id);

}
