package com.qa.services;

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

import com.qa.persistence.domain.SubtaskDomain;
import com.qa.persistence.dtos.SubtaskDTO;
import com.qa.persistence.repos.SubtaskRepo;

@SpringBootTest
public class SubtaskServiceTest {

	@MockBean
	private ModelMapper mockedMapper;

	@MockBean
	private SubtaskRepo mockedRepo;

	@Autowired
	private SubtaskService service;

	@Test
	public void justRun() {

	}

	@Test
	public void create() {
		SubtaskDomain testSubtask = new SubtaskDomain(1L,"Buy utensils", 40, true,null);
		SubtaskDTO testDTO = new SubtaskDTO(1L, "Buy utensils", 40, true);
		// RULES
		Mockito.when(this.mockedRepo.save(Mockito.any(SubtaskDomain.class))).thenReturn(testSubtask);
		Mockito.when(this.mockedMapper.map(testSubtask, SubtaskDTO.class)).thenReturn(testDTO);

		// ACTIONS
		SubtaskDTO result = this.service.create(testSubtask);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testDTO);
		Assertions.assertThat(result).isNotNull();
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(SubtaskDomain.class));
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testSubtask, SubtaskDTO.class);
	}

	@Test
	public void readAll() {
		
		SubtaskDomain testSubtask = new SubtaskDomain(1L,"Buy utensils", 30, true,null);
		SubtaskDomain testSubtask2 = new SubtaskDomain(2L,"Buy broom", 30, true,null);
		SubtaskDomain testSubtask3 = new SubtaskDomain(3L,"Buy another", 30, true,null);
		List<SubtaskDomain> testList = new ArrayList<SubtaskDomain>();
		testList.add(testSubtask);
		testList.add(testSubtask2);
		testList.add(testSubtask3);
		SubtaskDTO testDTO= new SubtaskDTO(1L, "Buy utensils", 30, true);
		SubtaskDTO testDTO2= new SubtaskDTO(1L, "Buy broom", 30, true);
		SubtaskDTO testDTO3= new SubtaskDTO(1L, "Buy another", 30, true);
		List<SubtaskDTO> testDTOList = new ArrayList<SubtaskDTO>();
		testDTOList.add(testDTO);
		testDTOList.add(testDTO2);
		testDTOList.add(testDTO3);
		
		// RULES
		Mockito.when(this.mockedRepo.findAll()).thenReturn(testList);

		int j = 0;
		for (SubtaskDomain i: testList) {
			Mockito.when(this.mockedMapper.map(i, SubtaskDTO.class)).thenReturn(testDTOList.get(j));
			j++;
		}
		
		// ACTIONS
		List<SubtaskDTO> result = this.service.readAll();

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTOList);
		Assertions.assertThat(result).isNotNull();
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findAll();

	}

	@Test
	public void readOne() {
		
		SubtaskDomain testSubtask = new SubtaskDomain(1L, "Buy utensils", 40, true, null);
//		SubtaskDTO testDTO = this.mockedMapper.map(testSubtask, SubtaskDTO.class);
		SubtaskDTO testDTO = new SubtaskDTO(1L, "Buy utensils", 40, true);
		
		// RULES
		Mockito.when(this.mockedRepo.findById(testSubtask.getId())).thenReturn(Optional.of(testSubtask));
		Mockito.when(this.mockedMapper.map(testSubtask, SubtaskDTO.class)).thenReturn(testDTO);

		// ACTIONS
		SubtaskDTO result = this.service.readOne(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).isNotNull();
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testSubtask, SubtaskDTO.class);

	}

	@Test
	public void update() {
		SubtaskDomain oldTestSubtask = new SubtaskDomain(1L, "Buy utensils", 30, true, null);
		SubtaskDTO oldTestDTO = new SubtaskDTO(1L, "Buy utensils", 30, true);
		SubtaskDomain testSubtask = new SubtaskDomain(1L, "Buy broom", 31, false, null);
		SubtaskDTO testDTO = new SubtaskDTO(1L, "Buy broom", 31, false);
		
		
		// RULES
		Mockito.when(this.mockedRepo.findById(testSubtask.getId())).thenReturn(Optional.of(testSubtask));
		Mockito.when(this.mockedRepo.save(Mockito.any(SubtaskDomain.class))).thenReturn(testSubtask);
		Mockito.when(this.mockedMapper.map(testSubtask, SubtaskDTO.class)).thenReturn(testDTO);
		Mockito.when(this.mockedMapper.map(oldTestSubtask, SubtaskDTO.class)).thenReturn(oldTestDTO);
		
		// ACTIONS
		SubtaskDTO result = this.service.update(1L, testSubtask);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isNotEqualTo(oldTestDTO);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(testSubtask);
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testSubtask, SubtaskDTO.class);
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
