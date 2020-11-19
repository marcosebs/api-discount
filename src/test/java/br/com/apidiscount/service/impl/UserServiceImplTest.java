package br.com.apidiscount.service.impl;

import br.com.apidiscount.http.request.UserDiscountRequest;
import br.com.apidiscount.http.resource.UserResource;
import br.com.apidiscount.http.response.UserDiscountResponse;
import br.com.apidiscount.infra.exception.ApiDiscountException;
import br.com.apidiscount.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private UserDiscountRequest request = new UserDiscountRequest(1L, 100.00);
    private UserResource userRequest = new UserResource(1L, "User-fake", null);

    @Test
    public void calculateForPreloadedUserTest() {
        UserDiscountResponse response = userService.calculate(request);
        assertEquals(response.getFinalPrice(), new Double(95.00));
    }

    @Test
    public void editUserWithNullOrdersCountTest() {
        assertThrows(ApiDiscountException.class, () -> {
            userService.edit(userRequest);
        });
    }

}