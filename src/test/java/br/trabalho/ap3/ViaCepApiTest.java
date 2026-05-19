package br.trabalho.ap3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import br.trabalho.ap3.model.Endereco;

public class ViaCepApiTest {

    private static final String BASE_URL = "https://viacep.com.br/ws/";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testBuscarCepValido() {
        String cep = "01001000";
        Endereco endereco = given()
            .pathParam("cep", cep)
        .when()
            .get("{cep}/json/")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .extract().as(Endereco.class);

        assertEquals("01001-000", endereco.getCep());
        assertEquals("Praça da Sé", endereco.getLogradouro());
        assertEquals("lado ímpar", endereco.getComplemento());
        assertEquals("Sé", endereco.getBairro());
        assertEquals("São Paulo", endereco.getLocalidade());
        assertEquals("SP", endereco.getUf());
        assertEquals("3550308", endereco.getIbge());
        assertEquals("1004", endereco.getGia());
        assertEquals("11", endereco.getDdd());
        assertEquals("7107", endereco.getSiafi());
    }

    @Test
    public void testBuscarCepValidoComHifen() {
        String cep = "01001-000";
        given()
            .pathParam("cep", cep)
        .when()
            .get("{cep}/json/")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("cep", equalTo("01001-000"))
            .body("logradouro", equalTo("Praça da Sé"));
    }

    @Test
    public void testBuscarCepInvalido() {
        String cep = "99999999";
        given()
            .pathParam("cep", cep)
        .when()
            .get("{cep}/json/")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("erro", equalTo("true"));
    }

    @Test
    public void testBuscarCepComFormatoInvalido() {
        String cep = "123"; 
        given()
            .pathParam("cep", cep)
        .when()
            .get("{cep}/json/")
        .then()
            .statusCode(400)
            .body(containsString("Bad Request"));
    }

    @Test
    public void testBuscarCepComCaracteresNaoNumericos() {
        String cep = "abcde123";
        given()
            .pathParam("cep", cep)
        .when()
            .get("{cep}/json/")
        .then()
            .statusCode(400)
            .body(containsString("Bad Request"));
    }

    @Test
    public void testBuscarCepComParametroFaltando() {
        given()
        .when()
            .get("/json/")
        .then()
            .statusCode(400);
    }

    @Test
    public void testBuscarCepComResponseTime() {
        String cep = "01001000";
        given()
            .pathParam("cep", cep)
        .when()
            .get("{cep}/json/")
        .then()
            .statusCode(200)
            .time(lessThan(8000L));
    }

    @Test
    public void testBuscarCepComMetodoNaoPermitido() {
        String cep = "01001000";
        given()
            .pathParam("cep", cep)
        .when()
            .post("{cep}/json/")
        .then()
            .statusCode(405);
    }

    @Test
    public void testBuscarCepComFormatoXML() {
        String cep = "01001000";
        given()
            .pathParam("cep", cep)
        .when()
            .get("{cep}/xml/")
        .then()
            .statusCode(200)
            .contentType(ContentType.XML)
            .body("xmlcep.cep", equalTo("01001-000"));
    }

    @Test
    public void testValidarContratoCepValido() {
        String cep = "01001000";
        given()
            .pathParam("cep", cep)
        .when()
            .get("{cep}/json/")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("viacep-schema.json"));
    }
}
