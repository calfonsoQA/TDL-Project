package com.qa.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.persistence.domain.TaskDomain;
import com.qa.persistence.dtos.TaskDTO;
import com.qa.persistence.repos.TaskRepo;

@SpringBootTest
public class TaskServiceUnitTest {

	@MockBean
	private ModelMapper mockedMapper;

	@MockBean
	private TaskRepo mockedRepo;

	@Autowired
	private TaskService service;

	@Test
	public void justRun() {
		assertTrue(true);
	}

	@Test
	public void create() {
		TaskDomain testTask = new TaskDomain(1L,"Task", null);
		TaskDTO testDTO = new TaskDTO(1L, "Task", null);
		// RULES
		Mockito.when(this.mockedRepo.save(Mockito.any(TaskDomain.class))).thenReturn(testTask);
		Mockito.when(this.mockedMapper.map(testTask, TaskDTO.class)).thenReturn(testDTO);

		// ACTIONS
		TaskDTO result = this.service.create(testTask);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testDTO);
		Assertions.assertThat(result).isNotNull();
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(TaskDomain.class));
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testTask, TaskDTO.class);
	}

	@Test
	public void readAll() {
		
		TaskDomain testTask = new TaskDomain(1L,"Task", null);
		TaskDomain testTask2 = new TaskDomain(2L,"Task2", null);
		TaskDomain testTask3 = new TaskDomain(3L,"Task3", null);
		List<TaskDomain> testList = new ArrayList<TaskDomain>();
		testList.add(testTask);
		testList.add(testTask2);
		testList.add(testTask3);
		TaskDTO testDTO= new TaskDTO(1L,"Task", null);
		TaskDTO testDTO2= new TaskDTO(2L,"Task2", null);
		TaskDTO testDTO3= new TaskDTO(3L,"Task3", null);
		List<TaskDTO> testDTOList = new ArrayList<TaskDTO>();
		testDTOList.add(testDTO);
		testDTOList.add(testDTO2);
		testDTOList.add(testDTO3);
		
		// RULES
		Mockito.when(this.mockedRepo.findAll()).thenReturn(testList);

		int j = 0;
		for (TaskDomain i: testList) {
			Mockito.when(this.mockedMapper.map(i, TaskDTO.class)).thenReturn(testDTOList.get(j));
			j++;
		}
		
		// ACTIONS
		List<TaskDTO> result = this.service.readAll();

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTOList);
		Assertions.assertThat(result).isNotNull();
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findAll();

	}

	@Test
	public void readOne() {
		
		TaskDomain testTask = new TaskDomain(1L,"Task", null);
//		TaskDTO testDTO = this.mockedMapper.map(testTask, TaskDTO.class);
		TaskDTO testDTO = new TaskDTO(1L,"Task", null);
		
		// RULES
		Mockito.when(this.mockedRepo.findById(testTask.getId())).thenReturn(Optional.of(testTask));
		Mockito.when(this.mockedMapper.map(testTask, TaskDTO.class)).thenReturn(testDTO);

		// ACTIONS
		TaskDTO result = this.service.readOne(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).isNotNull();
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testTask, TaskDTO.class);

	}

	@Test
	public void update() {
		TaskDomain oldtestTask = new TaskDomain(1L,"Task", null);
		TaskDTO oldTestDTO = new TaskDTO(1L,"Task", null);
		TaskDomain testTask = new TaskDomain(1L,"New Task", null);
		TaskDTO testDTO = new TaskDTO(1L,"New Task", null);
		
		
		// RULES
		Mockito.when(this.mockedRepo.findById(testTask.getId())).thenReturn(Optional.of(testTask));
		Mockito.when(this.mockedRepo.save(Mockito.any(TaskDomain.class))).thenReturn(testTask);
		Mockito.when(this.mockedMapper.map(testTask, TaskDTO.class)).thenReturn(testDTO);
		Mockito.when(this.mockedMapper.map(oldtestTask, TaskDTO.class)).thenReturn(oldTestDTO);
		
		// ACTIONS
		TaskDTO result = this.service.update(1L, testTask);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isNotEqualTo(oldTestDTO);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(testTask);
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testTask, TaskDTO.class);
	}

	@Test
	public void delete() {

		// ACTIONS
		Boolean result = this.service.delete(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(true);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).deleteById(1L);

	}

}
