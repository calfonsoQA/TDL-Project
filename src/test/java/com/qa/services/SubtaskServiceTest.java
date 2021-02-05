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
		SubtaskDomain testUser = new SubtaskDomain(1L,"Buy utensils", 40, true,null);
		SubtaskDTO testDTO = new SubtaskDTO(1L, "Buy utensils", 40, true);
		// RULES
		Mockito.when(this.mockedRepo.save(Mockito.any(SubtaskDomain.class))).thenReturn(testUser);
		Mockito.when(this.mockedMapper.map(testUser, SubtaskDTO.class)).thenReturn(testDTO);

		// ACTIONS
		SubtaskDTO result = this.service.create(testUser);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testDTO);
		Assertions.assertThat(result).isNotNull();
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(SubtaskDomain.class));
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testUser, SubtaskDTO.class);
	}

//	@Test
//	public void readAll() {
//		
//		UserDomain testUser = new UserDomain(1L, "John", "Doe", 25, null);
//		List<UserDomain> testList = new ArrayList<UserDomain>();
//		testList.add(testUser);
//		UserDTO testDTO = this.mockedMapper.map(testList, UserDTO.class);
//		
//		// RULES
//		
//		Mockito.when(this.mockedRepo.findAll().thenReturn(Optional.of(testList)));
//
//		// ACTIONS
//		List<UserDTO> result = this.service.readAll();
//
//		// ASSERTIONS
//		Assertions.assertThat(result).isEqualTo(testDTO);
//		
//		Mockito.verify(this.mockedRepo, Mockito.times(1)).findAll();
//
//	}

	@Test
	public void readOne() {
		
		SubtaskDomain testUser = new SubtaskDomain(1L, "Buy utensils", 40, true, null);
		SubtaskDTO testDTO = this.mockedMapper.map(testUser, SubtaskDTO.class);
		
		// RULES
		Mockito.when(this.mockedRepo.findById(testUser.getId())).thenReturn(Optional.of(testUser));

		// ACTIONS
		SubtaskDTO result = this.service.readOne(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTO);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);

	}

	@Test
	public void update() {
		
		SubtaskDomain testUser = new SubtaskDomain(1L, "Buy utensils", 40, true, null);
		SubtaskDomain newTestUser = new SubtaskDomain(1L, "Buy utensils", 40, true, null);
		
		SubtaskDTO testDTO = this.mockedMapper.map(newTestUser, SubtaskDTO.class);
		
		// RULES
		Mockito.when(this.mockedRepo.findById(testUser.getId())).thenReturn(Optional.of(testUser));

		// ACTIONS
		SubtaskDTO result = this.service.update(1L, newTestUser);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(testDTO);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
	}

	@Test
	public void delete() {
		
		SubtaskDomain testUser = new SubtaskDomain(1L, "Buy utensils", 40, true, null);
		SubtaskDTO testDTO = this.mockedMapper.map(testUser, SubtaskDTO.class);
		
		// RULES
//		Mockito.when(this.mockedRepo.deleteById(testUser.getId())).thenReturn(true);
		//Mockito.when(this.mockedRepo.deleteById(testUser.getId())).thenReturn(Optional.of(testUser));

		// ACTIONS
		Boolean result = this.service.delete(1L);

		// ASSERTIONS
		Assertions.assertThat(result).isEqualTo(true);
		
		Mockito.verify(this.mockedRepo, Mockito.times(1)).deleteById(1L);

	}

}
