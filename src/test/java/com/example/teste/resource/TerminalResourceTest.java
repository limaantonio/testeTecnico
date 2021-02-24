package com.example.teste.resource;

import com.example.teste.TesteApplicationTests;
import com.example.teste.domain.Terminal;
import org.junit.Test;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class TerminalResourceTest extends TesteApplicationTests {

    @Test
    public void deve_retornar_lista_de_infos()throws Exception {
        given()
                .get("/api/v1/infos")
                .then()
                .log().body().and()
                .and()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deve_procurar_objeto_pelo_atributo_logic() throws  Exception{
        given()
                .pathParam("logic", 44332211)
        .get("/api/v1/info/{logic}")
        .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())
                .body("id", comparesEqualTo(1),
                "serial", comparesEqualTo("123"));
    }

    @Test
    public void deve_retornar_erro_nao_encontrado_quando_buscar_atributo_logic_inexistente() throws Exception {
        given()
                .pathParam("logic", 12)
        .get("/api/v1/info/{logic}")
        .then()
                .log().body().and()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("erro", equalTo("Não existe info com o parametro 12"));
    }

    @Test
    public void deve_salvar_nova_info() throws Exception {
        String info = "1234;MMEE;232";

        given()

                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.TEXT)
                .body(info)
        .when()
        .post("/api/v1/info/")
        .then()
                .log().headers()
            .and()
                .log().body()
                .and()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", equalTo("http://localhost:"+porta+"/api/v1/info/1234"))
                .body("id", equalTo(3),
                        "model", equalTo("232"),
                        "serial", equalTo("MMEE"));
    }

    @Test
    public void deve_retornar_sucesso_ao_atualizar_uma_info() throws Exception {
        final Terminal terminal = new Terminal();
        terminal.setLogic(1234);
        terminal.setModel("232");
        terminal.setSerial("EMMM");

        given()

                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(terminal)
                .when()
                .pathParam("logic", 44332211)
                .put("/api/v1/info/{logic}")
                .then()
                .log().headers()
                .and()
                .log().body()
                .and()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void verifica_a_entrada_segunda_o_JSON_Schema_especificado() throws Exception{
        String info = "1234;MMEE;232";

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.TEXT)
                .body(info)
        .when()
                .post("api/v1/info")
                .then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .contentType(ContentType.JSON).

                body(matchesJsonSchemaInClasspath("schema.json"));
    }

}
