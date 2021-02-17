package com.example.teste.infoService;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.teste.domain.Info;
import com.example.teste.dtos.InfoDTO;
import com.example.teste.infoRepository.InfoRepository;
import com.example.teste.service.exception.ObjectNotFoundException;

@Service
public class InfoService {
	
	@Autowired
	private InfoRepository repo;
	
	@Transactional
	public List<Info> findAll() {
		return repo.findAll();
	}
	
	public Info findById(Integer id) {
		Optional<Info> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	@Transactional
	public Info insert(Info obj) {
		return repo.save(obj);
	}
	
//	public void delete(String id) {
//		findById(id);
//		repo.deleteById(id);
//	}
//
//	public Info update(Info entity) {
//		Info newObj = repo.findById(entity.getId()).get();
//		updateData(newObj, entity);
//		return repo.save(newObj);
//	}
//
//	private void updateData(Info newObj, Info obj) {
//		newObj.setName(obj.getName());
//		newObj.setEmail(obj.getEmail());
//
//	}

	
	public Info fromDTO(InfoDTO objDto) {
		return new Info(objDto.getLogic(), objDto.getSerial(), 
				objDto.getModel(), objDto.getSam(), objDto.getPtid(), objDto.getPlat(),
				objDto.getVersion(), objDto.getMxr(), objDto.getMxf(), objDto.getVerfm());
	}

	
	
}