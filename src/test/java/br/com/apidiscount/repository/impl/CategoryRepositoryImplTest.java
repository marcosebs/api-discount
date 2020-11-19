package br.com.apidiscount.repository.impl;

import br.com.apidiscount.http.resource.CategoryResource;
import br.com.apidiscount.infra.exception.ApiDiscountException;
import br.com.apidiscount.repository.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryRepositoryImplTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private static final int CATEGORIES_PRELOAD_COUNT = 3;

    private static final Long FAKE_ID = 999L;

    CategoryResource request = new CategoryResource(null, 1000, 2000, 10, "Categoria teste");

    @Test
    public void listCategoriesTest() {
        List<CategoryResource> categories = categoryRepository.findAll();
        assertTrue(categories.size() > 0);
    }

    @Test
    public void createCategoryTest() {
        CategoryResource response = categoryRepository.create(request);
        assertTrue(response.getId() != null);
        categoryRepository.delete(response.getId());
    }

    @Test
    public void deleteCategoryTest() {
        CategoryResource response = categoryRepository.create(request);
        categoryRepository.delete(response.getId());
        List<CategoryResource> categories = categoryRepository.findAll();
        assertTrue(categories.size() == CATEGORIES_PRELOAD_COUNT);
    }

    @Test
    public void deleteCategoryIdNotFoundTest() {
        assertThrows(ApiDiscountException.class, () -> {
            categoryRepository.delete(FAKE_ID);
        });
    }

}