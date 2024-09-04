package com.github.leonardosdias.ifood.cadastro;

// import static io.restassured.RestAssured.given;

// import org.approvaltests.JsonApprovals;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
// import jakarta.ws.rs.core.Response.Status;
import org.junit.*;

@QuarkusTest
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestauranteResourceTest {

    @Test
    @DataSet("restaurantes-cenario-1.yml")
    public void testBuscarRestaurantes() {
        String resultado = given()
                .when().get("/restaurantes")
                .then()
                .statusCode(200)
                .extract().asString();
        // JsonApprovals.verifyJson(resultado);
    }

    private RequestSpecification given() {
        return RestAssured.given().contentType(ContentType.JSON);
    }

    @Test
    @DataSet("restaurantes-cenario-1.yml")
    public void testAlterarRestaurante() {
        Restaurante dto = new Restaurante();
        dto.nome = "Novo nome";
        Integer parameterValue = 123;
        given()
                .with().pathParam("id", parameterValue)
                .body(dto)
                .when().put("/restaurantes/{id}")
                .then()

                .extract().asString();

        Restaurante findId = Restaurante.findById(parameterValue);

        Assert.assertEquals(parameterValue, findId);
    }

}
