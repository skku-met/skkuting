package skkumet.skkuting.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest
class UserAccountControllerTest {

    private final MockMvc mvc;

    public UserAccountControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

}