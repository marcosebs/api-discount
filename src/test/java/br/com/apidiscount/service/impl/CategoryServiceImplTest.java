package br.com.apidiscount.service.impl;

import br.com.apidiscount.http.resource.CategoryResource;
import br.com.apidiscount.infra.exception.ApiDiscountException;
import br.com.apidiscount.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    CategoryResource request = new CategoryResource(null, 1000, 2000, 10, "Categoria teste");

    @Test
    public void createCategoryWithInvalidMinAndMaxValuesTest() {
        request.setMinOrders(999);
        request.setMaxOrders(123);
        assertThrows(ApiDiscountException.class, () -> {
            categoryService.create(request);
        });
    }

    @Test
    public void createCategoryWithNullMaxOrdersTest() {
        request.setMaxOrders(null);
        assertThrows(ApiDiscountException.class, () -> {
            categoryService.create(request);
        });
    }

    @Test
    public void createCategoryWithNullMinOrdersTest() {
        request.setMinOrders(null);
        assertThrows(ApiDiscountException.class, () -> {
            categoryService.create(request);
        });
    }

    @Test
    public void createCategoryWithNullDiscountTest() {
        request.setDiscount(null);
        assertThrows(ApiDiscountException.class, () -> {
            categoryService.create(request);
        });
    }

}