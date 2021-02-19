package com.example.teste.infoService;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.teste.domain.Info;
import com.example.teste.infoController.InfoResources;

import ch.qos.logback.core.status.Status;
import io.restassured.http.ContentType;

@WebMvcTest
public class InfoServiceTest {

	@Autowired
	private InfoResources infoResources;

	@MockBean
	private InfoService infoService;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.infoResources);
	}
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	public void deveRetornarSucesso_QuandoBuscarInfo() {
		when(this.infoService.findById(1)).thenReturn(new Info(1, 23));

		given().accept(ContentType.JSON).when().get("/api/v1/info{id}", 1).then().statusCode(HttpStatus.SC_OK);
	}

	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarInfo() {
		when(this.infoService.findById(10)).thenReturn(null);

		given().accept(ContentType.JSON).when().get("/api/v1/info{id}", 10).then().statusCode(HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarInfos() {
		Info n1 = new Info(1, 45);
		Info n2 = new Info(2, 45);
		List<Info> infos = new ArrayList<>();
		infos.add(n1);
		infos.add(n2);
		
		when(this.infoService.findAll()).thenReturn(infos);
		
		given().accept(ContentType.JSON).when().get("/api/v1/infos").then().statusCode(HttpStatus.SC_OK);
	}
	
	
	@Test
	public void deveRetornarSucesso_QuandoCriarInfo() throws Exception {
		mockMvc.perform(post("/api/v1/info")
				.content("24223;")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void deveRetornarErro_QuandoCriarInfo() throws Exception {
		mockMvc.perform(post("/api/v1/info")
				.content("")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}

}
