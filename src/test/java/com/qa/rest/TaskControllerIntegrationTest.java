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
import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.SubtaskDTO;
import com.qa.persistence.dtos.TaskDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TaskControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private int ID = 1;

	private TaskDTO mapToDTO(TaskDomain model) {
		return this.mapper.map(model, TaskDTO.class);
	}

	@Test
	public void create() throws Exception {
		TaskDomain contentBody = new TaskDomain("Task", null);
		TaskDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(4L);

//		Setup Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/task/create/")
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody));

//		Setup Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

//		Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readAll() throws Exception {
		
		List<SubtaskDTO> subResultList = new ArrayList<SubtaskDTO>();
		List<SubtaskDTO> subResultList2 = new ArrayList<SubtaskDTO>();
		List<SubtaskDTO> subResultList3 = new ArrayList<SubtaskDTO>();
		SubtaskDTO expectedSubResult = new SubtaskDTO(1L, "Buy utensils", 40, true);
		SubtaskDTO expectedSubResult2 = new SubtaskDTO(2L, "Buy vacuum", 40, true);
		SubtaskDTO expectedSubResult3 = new SubtaskDTO(3L, "Clean the kitchen", 40, true);
		SubtaskDTO expectedSubResult4 = new SubtaskDTO(4L, "Sign documents", 40, true);
		SubtaskDTO expectedSubResult5 = new SubtaskDTO(5L, "Update calendar", 40, true);
		subResultList.add(expectedSubResult);
		subResultList2.add(expectedSubResult2);
		subResultList2.add(expectedSubResult3);
		subResultList3.add(expectedSubResult4);
		subResultList3.add(expectedSubResult5);
		
		List<TaskDTO> resultList = new ArrayList<TaskDTO>();
		TaskDTO expectedResult = new TaskDTO(1L,"Cooking", subResultList);
		TaskDTO expectedResult2 = new TaskDTO(2L,"Cleaning", subResultList2);
		TaskDTO expectedResult3 = new TaskDTO(3L,"Admin Work", subResultList3);
	
		resultList.add(expectedResult);
		resultList.add(expectedResult2);
		resultList.add(expectedResult3);

//		Setup Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/task/readAll/");

//		Setup Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(resultList));

//		Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readOne() throws Exception {
//		TaskDomain contentBody = new TaskDomain(1L,"Cooking", null);
//		TaskDTO expectedResult = mapToDTO(contentBody);
		SubtaskDTO subtaskDTO = new SubtaskDTO(1L, "Buy utensils", 30, true);
		List<SubtaskDTO> subtaskList = new ArrayList<SubtaskDTO>();
		subtaskList.add(subtaskDTO);
		TaskDTO expectedResult = new TaskDTO(1L,"Cooking", subtaskList);

//		Setup Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/task/read/" + ID);

//		Setup Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

//		Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void update() throws Exception {
		TaskDomain contentBody = new TaskDomain(1L,"Task", null);
		TaskDTO expectedResult = mapToDTO(contentBody);

//		Setup Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/task/update/" + ID)
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody));

//		Setup Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));

//		Perform
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void delete() throws Exception {

//		Setup Request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/task/delete/" + ID);

//		Setup Expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

//		Perform
		this.mock.perform(mockRequest).andExpect(matchStatus);

	}

}
