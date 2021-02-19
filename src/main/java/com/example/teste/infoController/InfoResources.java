package com.example.teste.infoController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.teste.domain.Info;
import com.example.teste.dtos.InfoDTO;
import com.example.teste.infoService.InfoService;

@RestController
public class InfoResources {

	@Autowired
	private InfoService service;

	@RequestMapping("/api/v1")
	public String index() {
		return "API Desafio Técnico Muxi";
	}

	@RequestMapping(value = "api/v1/infos", method = RequestMethod.GET)
	public ResponseEntity<List<InfoDTO>> findAll() {
		List<Info> list = service.findAll();
		List<InfoDTO> listDto = list.stream().map(x -> new InfoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "api/v1/info{id}", method = RequestMethod.GET)
	public ResponseEntity<InfoDTO> findById(@PathVariable Integer id) {
		Info obj = service.findById(id);
		
		if(obj == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(new InfoDTO(obj));
	}

	@RequestMapping(value = "api/v1/info", method = RequestMethod.POST)
	public ResponseEntity<InfoDTO> insert(@RequestBody String dado) {
	

		InfoDTO infoDTO = new InfoDTO();
		
		if(dado == null) {
			return ResponseEntity.badRequest().build();
		}
		
		String[] d = dado.split(";");

		infoDTO.setLogic(Integer.parseInt(d[0]));
//		infoDTO.setSerial(d[1]);
//		infoDTO.setModel(d[2]);
//		infoDTO.setSam(Integer.parseInt(d[3]));
//		infoDTO.setPtid(d[4]);
//		infoDTO.setPlat(Integer.parseInt(d[5]));
//		infoDTO.setVersion(d[6]);
//		infoDTO.setMxr(Integer.parseInt(d[7]));
//		infoDTO.setMxf(Integer.parseInt(d[8]));
//		infoDTO.setVerfm(d[9]);
		
		Info info = service.fromDTO(infoDTO);
		info = service.insert(info);

		return ResponseEntity.created(null).body(infoDTO);

	}

}
