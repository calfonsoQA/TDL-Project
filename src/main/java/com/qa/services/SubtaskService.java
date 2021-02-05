package com.qa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.SubtaskDomain;
import com.qa.persistence.dtos.SubtaskDTO;
import com.qa.persistence.repos.SubtaskRepo;

@Service
public class SubtaskService {
	
	private SubtaskRepo repo;
	private ModelMapper mapper;

	@Autowired
	public SubtaskService(SubtaskRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private SubtaskDTO mapToDTO(SubtaskDomain model) {
		return this.mapper.map(model, SubtaskDTO.class);
	}
	
//	CREATE
	public SubtaskDTO create(SubtaskDomain subtask) {
        return this.mapToDTO(this.repo.save(subtask));
    }
	
//	READ
	public List<SubtaskDTO> readAll() {
//        return this.repo.findAll();
        List<SubtaskDomain> dbList = this.repo.findAll();
        List<SubtaskDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
        return resultList;
    }
	
	public SubtaskDTO readOne(Long id) {
		// TODO Auto-generated method stub
		return mapToDTO(this.repo.findById(id).orElseThrow());
		 
	}
	
//	UPDATE
	public SubtaskDTO update(Long id, SubtaskDomain newSubtask) {
//        Optional<User> existingOptional = this.repo.findById(id);
       this.repo.findById(id).orElseThrow();
    
       newSubtask.setId(id);
       

        return this.mapToDTO(this.repo.save(newSubtask));
    }

//	DELETE
	public boolean delete(Long id) {
        // removes the entity
        this.repo.deleteById(id);
        // checks to see if it still exists
        boolean exists = this.repo.existsById(id);
        // returns true if entity no longer exists
        return !exists;
    }

	
}
