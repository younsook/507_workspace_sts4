package com.rubypaper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubypaper.domain.BoardVO;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT) //(webEnvironment= WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@WebMvcTest
public class BoardControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	//private MockMvc mockMvc;
	
	@Test
	public void testHello() throws Exception{
//		String result = restTemplate.getForObject("/hello?name=둘리", String.class);
//		assertEquals("Hello : 둘리", result);
		
		BoardVO board = restTemplate.getForObject("/getBoard", BoardVO.class);
		assertEquals("테스터", board.getWriter());
		
		//mockup
//		mockMvc.perform(get("/hello").param("name", "둘리"))
//		 .andExpect(status().isOk())
//		 .andExpect(content().string("Hello : 둘리"))
//		 .andDo(print()); 
	}
	
//	@Test
//	public void testHelloJson2() throws Exception {
//		ObjectMapper objectMapper = new ObjectMapper();
//		MvcResult mvcResult = mockMvc.perform(get("/getBoard").param("seq", "100"))
//			 .andExpect(status().isOk())
//			 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//			 .andExpect(jsonPath("$.writer").value("테스터"))
//			 .andDo(print())
//			 .andReturn();
//				
//		 String jsonString = mvcResult.getResponse().getContentAsString();
//		 BoardVO board = objectMapper.readValue(jsonString, BoardVO.class);
//		 assertEquals(board.getSeq(), 100);			
//	}
	
	

}
