package br.com.apidiscount.http;

import br.com.apidiscount.ApiDiscountApplication;
import br.com.apidiscount.http.request.UserDiscountRequest;
import br.com.apidiscount.http.resource.UserResource;
import br.com.apidiscount.http.response.UserDiscountResponse;
import br.com.apidiscount.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {ApiDiscountApplication.class})
public class UserHttpTest {

    private MockMvc mockMvc;

    private UserResource response = new UserResource(1L, "User-fake", 10);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void returningOkWhenFindPreloadedUser() throws Exception {
        when(userService.find(response.getId())).thenReturn(response);

        mockMvc.perform(get("/api/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.orders_count").value(response.getOrdersCount()));
    }

    @Test
    public void returningOkWhenDeleteUser() throws Exception {
        doNothing().when(userService).delete(response.getId());

        mockMvc.perform(delete("/api/v1/user/1")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void returningCreatedWhenCreateUser() throws Exception {
        UserResource request = new UserResource(null, "User-fake", null);
        when(userService.create(request)).thenReturn(response);

        mockMvc.perform(post("/api/v1/user")
                .content("{\"name\":\"User-fake\"}")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void returningOkWhenEditUser() throws Exception {
        UserResource request = new UserResource(1L, "User-fake", 10);
        when(userService.edit(request)).thenReturn(response);

        mockMvc.perform(put("/api/v1/user")
                .content("{\"id\":1,\"name\":\"User-fake\", \"orders_count\":10}")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.orders_count").value(response.getOrdersCount()));
    }

    @Test
    public void returninOkWhenCalculate() throws Exception {
        UserDiscountRequest discountRequest = new UserDiscountRequest(1L, 100.00);
        UserDiscountResponse discountResponse = new UserDiscountResponse(95.00);
        when(userService.calculate(discountRequest)).thenReturn(discountResponse);

        mockMvc.perform(post("/api/v1/user/calculate")
                .content("{\"user_id\":1,\"price\": 100.00}")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.final_price").value(discountResponse.getFinalPrice()));
    }

}