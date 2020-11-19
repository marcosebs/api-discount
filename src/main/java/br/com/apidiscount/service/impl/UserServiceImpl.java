package br.com.apidiscount.service.impl;

import br.com.apidiscount.http.request.UserDiscountRequest;
import br.com.apidiscount.http.resource.CategoryResource;
import br.com.apidiscount.http.resource.UserResource;
import br.com.apidiscount.http.response.UserDiscountResponse;
import br.com.apidiscount.infra.exception.ApiDiscountException;
import br.com.apidiscount.infra.exception.enumerator.ErrorMappingEnum;
import br.com.apidiscount.repository.CategoryRepository;
import br.com.apidiscount.repository.UserRepository;
import br.com.apidiscount.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private static final Integer INCREMENT_VALUE = 1;

    @Override
    public UserDiscountResponse calculate(UserDiscountRequest request) {
        UserResource user = find(request.getUserId());
        List<CategoryResource> categories = categoryRepository.findAll();
        UserDiscountResponse response = new UserDiscountResponse();
        response.setFinalPrice(request.getPrice());
        user.setOrdersCount(user.getOrdersCount() + 1);
        edit(user);
        categories.forEach(category -> {
            if(user.getOrdersCount() >= category.getMinOrders() && user.getOrdersCount() <= category.getMaxOrders()){
                response.setFinalPrice(((100 - category.getDiscount()) / 100.00 ) * request.getPrice());
            }
        });
        return response;
    }

    @Override
    public UserResource create(UserResource request) {
        request.setId(null);
        request.setOrdersCount(0);
        return userRepository.create(request);
    }

    @Override
    public UserResource edit(UserResource request) {
        if(request.getOrdersCount() == null)
            throw new ApiDiscountException(ErrorMappingEnum.ORDER_NOT_NULL, HttpStatus.BAD_REQUEST);
        return userRepository.edit(request);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public UserResource find(Long id) {
        return userRepository.findById(id);
    }

}
