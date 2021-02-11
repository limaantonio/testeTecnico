package com.example.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.teste.domain.Info;
import com.example.teste.dtos.InfoDTO;
import com.example.teste.repository.InfoRepository;

@Service
public class InfoService {
	
	@Autowired
	private InfoRepository repo;
	
	public List<Info> findAll() {
		return repo.findAll();
	}
	
	public Info insert(Info obj) {
		return repo.insert(obj);
	}
	
	public Info fromDTO(InfoDTO objDto) {
		return new Info(objDto.getId(), objDto.getLogic(), objDto.getSerial(), 
				objDto.getModel(), objDto.getSam(), objDto.getPtid(), objDto.getPlat(),
				objDto.getVersion(), objDto.getMxr(), objDto.getMxf(), objDto.getVerfm());
	}

	
	
}
