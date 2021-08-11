package com.example.unittest.controller;

import com.example.unittest.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerIntTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void getUserIntTest() throws Exception {
        User expectedUser = new User(1,"Mert", "Çakmak");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getUser");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String userResponseString = result.getResponse().getContentAsString();
        User userResponse = mapper.readValue(userResponseString, User.class);

        assertEquals(expectedUser.getName(), userResponse.getName());
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void saveUserIntTest() throws Exception {
        User expectedUser = new User(1,"Mert", "Çakmak");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveUser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expectedUser));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String userResponseString = mvcResult.getResponse().getContentAsString();
        User userResponse = mapper.readValue(userResponseString, User.class);

        assertEquals(201, mvcResult.getResponse().getStatus());
        assertEquals(expectedUser.getName(), userResponse.getName());
    }

    @Test
    void deleteUserIntTest() throws Exception {
        int expectedId = 1;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteUser?id=1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String responseString = result.getResponse().getContentAsString();
        int actualId = Integer.valueOf(responseString);

        assertEquals(200, result.getResponse().getStatus());
        assertEquals(expectedId, actualId);
    }


}