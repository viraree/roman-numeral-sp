package com.fullstack.romanNumerals;

import com.fullstack.romanNumerals.controller.ServiceController;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ServiceController.class)
public class RomanNumeralTests {


	@Autowired
	private MockMvc mockMvc;

/*
	@Test
	public void testMongoTrySave() throws Exception {
		mockMvc.perform(get("/trymongo/"))
				.andExpect(jsonPath("$.message").value("ok"))
				.andExpect(jsonPath("$.errorReason").value("N/A"))
				.andExpect(status().isOk());
	}
*/

	@Test
	public void testArrayTableLookup() throws Exception {
		mockMvc.perform(get("/arr_tbl_lookup/"))
				.andExpect(jsonPath("$.message").value("at 0,0 : I"))
				.andExpect(jsonPath("$.errorReason").value("N/A"))
				.andExpect(status().isOk());
	}

	@Test
	public void testHashLookup() throws Exception {
		mockMvc.perform(get("/hash_lookup/"))
				.andExpect(jsonPath("$.message").value("ok"))
				.andExpect(jsonPath("$.errorReason").value("N/A"))
				.andExpect(status().isOk());
	}


/*
  https://codingdojo.org/solution/KataRomanNumeralsAsReadableAsWeCouldMakeIt/
  >>> number_to_numeral(2399)
    'MMCCCXCIX'
 */
	@Test
	public void testConvertToRoman() throws Exception {
		mockMvc.perform(get("/to_roman/2399"))
				.andExpect(jsonPath("$.message").value("MMCCCXCIX"))
				.andExpect(jsonPath("$.errorReason").value("N/A"))
				.andExpect(status().isOk());
	}


	/*
      https://codingdojo.org/solution/KataRomanNumeralsAsReadableAsWeCouldMakeIt/

        'MMCCCXCIX' -> 2399
     */
	@Test
	public void testConvertFromRoman() throws Exception {
		mockMvc.perform(get("/from_roman/MMCCCXCIX"))
				.andExpect(jsonPath("$.message").value("2399"))
				.andExpect(jsonPath("$.errorReason").value("N/A"))
				.andExpect(status().isOk());
	}



}