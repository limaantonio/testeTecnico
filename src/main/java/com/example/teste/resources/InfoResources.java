package com.example.teste.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.teste.domain.Info;
import com.example.teste.dtos.InfoDTO;
import com.example.teste.services.InfoService;


@RestController
@RequestMapping(value="/infos")
public class InfoResources {
	
	@Autowired
	private InfoService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<InfoDTO>> findAll(){
		List<Info> list = service.findAll();
		List<InfoDTO> listDto = list.stream().map(x -> new InfoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Info> insert(@RequestBody InfoDTO infoDto){
		Info info = service.fromDTO(infoDto);
		service.insert(info);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(info.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
