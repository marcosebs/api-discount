package br.com.apidiscount.repository.impl;

import br.com.apidiscount.http.resource.UserResource;
import br.com.apidiscount.infra.exception.ApiDiscountException;
import br.com.apidiscount.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    private static final Long USER_PRELOADED_ID = 1L;
    private static final Long FAKE_ID_USER = 999L;

    private UserResource request = new UserResource(null, "User-fake", 0);

    @Test
    public void findByIdTest() {
        UserResource response = userRepository.findById(USER_PRELOADED_ID);
        assertTrue(response.getName() != null && !response.getName().isEmpty());
    }

    @Test
    public void findByIdNotFoundTest() {
        assertThrows(ApiDiscountException.class, () -> {
            userRepository.findById(FAKE_ID_USER);
        });
    }

    @Test
    public void createUserTest() {
        UserResource response = userRepository.create(request);
        assertTrue(response.getId() != null);
        assertTrue(response.getOrdersCount() == 0);
        userRepository.delete(response.getId());
    }

    @Test
    public void editUserTest() {
        UserResource userPreloaded = userRepository.findById(USER_PRELOADED_ID);
        UserResource temp = userPreloaded;
        userPreloaded.setName("teste");
        UserResource response = userRepository.edit(userPreloaded);
        assertEquals(response.getName(), userPreloaded.getName());
        userRepository.edit(temp);
    }

    @Test
    public void editUserNotFoundTest() {
        request.setId(FAKE_ID_USER);
        assertThrows(ApiDiscountException.class, () -> {
            userRepository.edit(request);
        });
    }

    @Test
    public void deleteUserTest() {
        UserResource response = userRepository.create(request);
        userRepository.delete(response.getId());
        assertThrows(ApiDiscountException.class, () -> {
            userRepository.findById(response.getId());
        });
    }

}