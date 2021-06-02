package com.example.pizzeriataskmain;

import com.example.pizzeriataskmain.controller.ShowController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;

import static org.hamcrest.Matchers.containsString;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("homeless_narcissus")
class IngredientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addIngredientView() throws Exception{
        this.mockMvc.perform(get("/ingredients/add"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(containsString("Add ingredient")));
    }


    @Test
    public void changeIngredientView() throws Exception{
        this.mockMvc.perform(get("/ingredients/change/0411e54f-14a1-42a9-802c-bdf44b18e4e9"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(containsString("Change ingredient")));
    }

    @Test
    public void addIngredient() throws Exception{
        this.mockMvc.perform(post("/ingredients/add")
                .param("name", "chili")
                .param("price", "45"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void changeIngredient() throws Exception{
        this.mockMvc.perform(post("/ingredients/change/33a39d2d-3f0d-4ff1-b871-7cfe07111d71")
                .param("name", "pepper chili")
                .param("price", "65"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void deleteIngredient() throws Exception{
        this.mockMvc.perform(get("/ingredients/delete/33a39d2d-3f0d-4ff1-b871-7cfe07111d71"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"));
    }
}
