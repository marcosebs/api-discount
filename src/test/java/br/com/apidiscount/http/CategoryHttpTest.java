package br.com.apidiscount.http;

import br.com.apidiscount.ApiDiscountApplication;
import br.com.apidiscount.http.resource.CategoryResource;
import br.com.apidiscount.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {ApiDiscountApplication.class})
public class CategoryHttpTest {

    private MockMvc mockMvc;

    private CategoryResource request = new CategoryResource(null, 0, 9, 10, "Categoria teste");
    private CategoryResource response = new CategoryResource(null, 0, 9, 10, "Categoria teste");

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CategoryService categoryService;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void returningOkWhenCreateCategory() throws Exception {
        when(categoryService.create(request)).thenReturn(response);

        mockMvc.perform(post("/api/v1/category")
                .content("{\"min_orders\": 0, \"max_orders\": 9, \"discount\": 10, \"description\": \"Categoria teste\"}")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void returningOkWhenDeleteCategory() throws Exception {
        doNothing().when(categoryService).delete(response.getId());

        mockMvc.perform(delete("/api/v1/category/1")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void returningOkWhenListCategories() throws Exception {
        when(categoryService.list()).thenReturn(Arrays.asList(response));

        mockMvc.perform(get("/api/v1/category")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }


}