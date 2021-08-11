package com.example.unittest.controller;

import com.example.unittest.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void getUserUnitTest() {
        User expectedUser = new User(1,"Mert", "Çakmak");

        UserController userController = new UserController();
        User userResponse = userController.getUser();

        assertEquals(expectedUser.getName(), userResponse.getName());
        assertEquals(expectedUser,userResponse);
    }

    @Test
    void saveUserUnitTest() {
        User expectedUser = new User(1,"Mert", "Çakmak");

        UserController userController = new UserController();
        User userResponse = userController.saveUser(expectedUser);

        assertEquals(expectedUser, userResponse);
    }

    @Test
    void deleteUserUnitTest() {
        int expectedId = 1;

        UserController userController = new UserController();
        int id = userController.deleteUser(1);

        assertEquals(expectedId, id);
    }
}