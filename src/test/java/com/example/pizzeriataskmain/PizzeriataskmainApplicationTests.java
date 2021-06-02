package com.example.pizzeriataskmain;

import com.example.pizzeriataskmain.controller.ShowController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
class PizzeriataskmainApplicationTests {

	@Autowired
	ShowController controller;

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() throws  Exception{
//		assertThat(controller).isNotNull();
		this.mockMvc.perform(get("/")) // get-запрос на главную страницу проекта
				.andDo(print()) // вывод полученного результата в консоль, это поможет если тесты упали и по логам можно понять что случилось
				.andExpect(status().isOk()) // запрос вернет код 200
				.andExpect(content().string(containsString("Pizzeria"))); //
	}

	@Test
	public void login() throws Exception{
		this.mockMvc.perform(get("/login"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Login")))
				.andExpect(content().string(containsString("Password")))
				.andExpect(content().string(containsString("Sign in")));
	}

	@Test
	public void registration() throws Exception{
		this.mockMvc.perform(get("/registration"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Login")))
				.andExpect(content().string(containsString("Password")))
				.andExpect(content().string(containsString("Sign up")));
	}

	@Test
	public void correctLogin() throws Exception{
		this.mockMvc.perform(formLogin().user("homeless_narcissus").password("123456")) // смотрит как мы в контексте определили login-page и вызывает обращается к этой страничке
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));
	}

	@Test
	public void correctRegistration() throws Exception{
		this.mockMvc.perform(post("/registration")
				.param("login", "new_user" + new Random().nextInt(1000))
				.param("password", "123456"))
				.andDo(print())
				.andExpect(content().string(containsString("Login")))
				.andExpect(content().string(containsString("Password")))
				.andExpect(content().string(containsString("Sign up")));
	}

}
