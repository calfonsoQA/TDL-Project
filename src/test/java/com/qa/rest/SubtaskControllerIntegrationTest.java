package com.qa.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.persistence.domain.SubtaskDomain;
import com.qa.persistence.dtos.SubtaskDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class SubtaskControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private int ID = 1;

	private SubtaskDTO mapToDTO(SubtaskDomain model) {
		return this.mapper.map(model, SubtaskDTO.class);
	}

	@Test
	public void create() throws Exception {
		SubtaskDomain contentBody = new SubtaskDomain("Buy utensils", 40, true, null);
		SubtaskDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(6L);

//		Setup Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/subtask/create/").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody));

//		Setup Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

//		Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readAll() throws Exception {
		List<SubtaskDTO> resultList = new ArrayList<SubtaskDTO>();
		SubtaskDTO expectedResult = new SubtaskDTO(1L, "Buy utensils", 40, true);
		SubtaskDTO expectedResult2 = new SubtaskDTO(2L, "Buy utensils", 40, true);  
		SubtaskDTO expectedResult3 = new SubtaskDTO(3L,"Buy utensils", 40, true); 
		SubtaskDTO expectedResult4 = new SubtaskDTO(4L,"Buy utensils", 40, true);    
		SubtaskDTO expectedResult5 = new SubtaskDTO(5L, "Buy utensils", 40, true);
		resultList.add(expectedResult);
		resultList.add(expectedResult2);
		resultList.add(expectedResult3);
		resultList.add(expectedResult4);
		resultList.add(expectedResult5);

//		Setup Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/subtask/readAll/");

//		Setup Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(resultList));

//		Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readOne() throws Exception {

		SubtaskDTO expectedResult = new SubtaskDTO(1L, "Buy utensils", 40, true);

//		Setup Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/subtask/read/" + ID);

//		Setup Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

//		Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void update() throws Exception{
		SubtaskDTO expectedResult = new SubtaskDTO(1L, "Buy utensils", 40, true);

//		Setup Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT,
				"http://localhost:8080/subtask/update/" + ID);

//		Setup Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

//		Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void delete() throws Exception {
		
//		Setup Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/subtask/delete/" +ID);

//		Setup Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();


//		Perform
		this.mock.perform(mockRequest).andExpect(matchStatus);

	}

}
