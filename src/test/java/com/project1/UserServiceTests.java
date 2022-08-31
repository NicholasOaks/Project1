package com.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.project1.dao.UserDAO;
import com.project1.models.User;
import com.project1.services.UserService;

public class UserServiceTests {

    UserDAO udao = Mockito.mock(UserDAO.class);
    UserService us = new UserService(udao);

    @Test
    void testValidateUser() {

        User inputedUser = new User("test", "password");

        Mockito.when(udao.getUserByUsername(inputedUser.getUsername())).thenReturn(new User("test", "password"));

        boolean actualOuput = us.validateUser(inputedUser);

        Assertions.assertTrue(actualOuput);

    }
    
}
