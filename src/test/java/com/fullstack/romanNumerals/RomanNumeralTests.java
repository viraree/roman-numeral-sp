package com.fullstack.romanNumerals;

import com.fullstack.romanNumerals.controller.BaseController;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;






@WebMvcTest(BaseController.class)
public class RomanNumeralTests {


	@Autowired
	private MockMvc mockMvc;

	//@MockBean
	//private GreetingService service;

	/*@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		when(service.greet()).thenReturn("Hello, Mock");
		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, Mock")));
	}*/


	@Test
	public void testLookupTable() throws Exception {
		mockMvc.perform(get("/lookup_table/"))
				.andExpect(jsonPath("$.message").value("at 0,0 : I"))
				.andExpect(jsonPath("$.errorReason").value("N/A"))
				.andExpect(status().isOk());
	}


	@Test
	public void testFizzException() throws Exception {
		mockMvc.perform(get("/controller_advice/fizz"))
				.andExpect(jsonPath("$.message").value("Fizz Exception has been thrown"))
				.andExpect(jsonPath("$.errorReason").value("Internal Server Error"))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testBuzzException() throws Exception {
		mockMvc.perform(get("/controller_advice/buzz"))
				.andExpect(jsonPath("$.message").value("Buzz Exception has been thrown"))
				.andExpect(jsonPath("$.errorReason").value("Bad Request"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testFizzBuzzException() throws Exception {
		mockMvc.perform(get("/controller_advice/fizzbuzz"))
				.andExpect(jsonPath("$.message").value("FizzBuzz Exception has been thrown"))
				.andExpect(jsonPath("$.errorReason").value("Insufficient Storage"))
				.andExpect(status().isInsufficientStorage());
	}

	@Test
	public void testFizzBuzzResponse() throws Exception {
		mockMvc.perform(get("/controller_advice/success"))
				.andExpect(jsonPath("$.message").value("Successfully completed fizzbuzz test"))
				.andExpect(jsonPath("$.statusCode").value("200"))
				.andExpect(status().isOk());
	}

}