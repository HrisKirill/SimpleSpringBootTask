package com.example.proxybandtechnicaltask.controllers;

import com.example.proxybandtechnicaltask.entities.User;
import com.example.proxybandtechnicaltask.services.interfaces.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    MockMvc mockMvc;
    IUserService service = mock(IUserService.class);
    UserController userController = new UserController(service);
    private JacksonTester<User> jsonUser;

    private User getTestUser() {
        return User.builder()
                .id(1L)
                .email("test@gmail.com")
                .name("testName")
                .build();
    }

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    void findUserByIdTest() throws Exception {
        given(service.findById(getTestUser().getId())).willReturn(getTestUser());
        MockHttpServletResponse response = mockMvc.perform(
                get("/user/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonUser.write(getTestUser()).getJson());
    }

    @Test
    void createUser() throws Exception {
        given(service.createUser(getTestUser())).willReturn(getTestUser());
        MockHttpServletResponse response = mockMvc.perform(
                        post("/user/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonUser.write(getTestUser()).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonUser.write(getTestUser()).getJson());
    }

    @Test
    void updateUser() throws Exception {
        given(service.updateUser(getTestUser())).willReturn(getTestUser());
        MockHttpServletResponse response = mockMvc.perform(
                        post("/user/updating")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonUser.write(getTestUser()).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonUser.write(getTestUser()).getJson());
    }

    @Test
    void deleteUser() throws Exception {
        when(service.deleteUser(getTestUser().getId()))
                .thenReturn("User with id:" + getTestUser().getId() + " was successfully removed");
        MockHttpServletResponse response = mockMvc.perform(
                delete("/user/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void getUsers() throws Exception {
        given(service.getAllUsers()).willReturn(List.of(getTestUser()));
        MockHttpServletResponse response = mockMvc.perform(
                        post("/user/list").contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}