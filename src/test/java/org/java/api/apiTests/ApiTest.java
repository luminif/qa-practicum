package org.java.api.apiTests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.java.api.dto.RequestEntity;
import org.java.api.dto.ResponseEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;

import static org.java.api.specifications.Specifications.requestSpecification;
import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.SAME_THREAD)
@Feature("API")
@Epic("Тест API")
public class ApiTest {
    private RequestEntity requestEntity;
    private int id;
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @BeforeEach
    void setUp() {
        requestEntity = RequestEntity.builder()
            .addition(RequestEntity.Addition.builder()
                .additionalInfo("Java")
                .additionalNumber(321)
                .build())
            .importantNumbers(List.of(1, 2, 3))
            .title("Заголовок")
            .verified(false)
            .build();

        id = Integer.parseInt(RestAssured.given()
            .spec(requestSpecification())
            .body(requestEntity)
            .post("create")
            .then()
            .extract()
            .asString());
    }

    @AfterEach
    void tearDown() {
        RestAssured.given()
            .spec(requestSpecification())
            .delete("delete/{id}", id);
    }

    @Test
    @DisplayName("TC1")
    @Story("GET запрос")
    @Step("Получение сущности")
    @Description("Получение сущности по ее существующему идентификатору")
    void getRequestByKnownId() {
        ResponseEntity responseEntity = RestAssured.given()
            .spec(requestSpecification())
            .get("get/{id}", id)
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .as(ResponseEntity.class, ObjectMapperType.GSON);

        assertAll(
            () -> assertNotNull(responseEntity.getId()),
            () -> assertEquals(requestEntity.getTitle(), responseEntity.getTitle()),
            () -> assertEquals(requestEntity.getVerified(), responseEntity.getVerified()),
            () -> assertNotNull(responseEntity.getAddition().getId()),
            () -> assertEquals(requestEntity.getAddition().getAdditionalInfo(), responseEntity.getAddition().getAdditionalInfo()),
            () -> assertEquals(requestEntity.getAddition().getAdditionalNumber(), responseEntity.getAddition().getAdditionalNumber()),
            () -> assertEquals(requestEntity.getImportantNumbers(), responseEntity.getImportantNumbers())
        );
    }

    @Test
    @DisplayName("TC2")
    @Story("GET запрос")
    @Step("Получение несуществующей сущности")
    @Description("Получение сущности по ее несуществующему идентификатору")
    void getRequestByUnknownId() {
        String message = RestAssured.given()
            .spec(requestSpecification())
            .get("get/{id}", UNKNOWN_ID)
            .then()
            .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
            .extract()
            .path("error");

        assertEquals("no rows in result set", message);
    }

    @Test
    @DisplayName("TC3")
    @Story("GET запрос")
    @Step("Получение всех сущностей")
    @Description("Получение всех сущностей")
    void getRequestAll() {
        RestAssured.given()
            .spec(requestSpecification())
            .get("getAll")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .body("size()", Matchers.greaterThanOrEqualTo(1));
    }

    @Test
    @DisplayName("TC4")
    @Story("POST запрос")
    @Step("Создание сущности")
    @Description("Создание сущности с валидными данными")
    void postEntityWIthValidData() {
        RestAssured.given()
            .spec(requestSpecification())
            .body(requestEntity)
            .post("create")
            .then()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("TC5")
    @Story("POST запрос")
    @Step("Создание сущности без всех обязательных полей")
    @Description("Создание сущности без всех обязательных полей")
    void postEntityWItInvalidData() {
        RequestEntity invalidRequestEntity = RequestEntity.builder()
            .addition(RequestEntity.Addition.builder()
                .additionalInfo("Java")
                .additionalNumber(321)
                .build())
            .build();

        String message = RestAssured.given()
            .spec(requestSpecification())
            .body(invalidRequestEntity)
            .post("create")
            .then()
            .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
            .extract()
            .response()
            .body()
            .path("error");

        assertEquals("title field is required", message);
    }


    @Test
    @DisplayName("TC6")
    @Story("PATCH запрос")
    @Step("Редактирование сущности")
    @Description("Редактирование сущности по ее существующему идентификатору")
    void patchEntityByKnownId() {
        RequestEntity newRequestEntity = RequestEntity.builder()
            .addition(RequestEntity.Addition.builder()
                .additionalInfo("Kotlin")
                .additionalNumber(123)
                .build())
            .importantNumbers(List.of(1, 2, 3))
            .title("Заголовок")
            .verified(false)
            .build();

        RestAssured.given()
            .spec(requestSpecification())
            .body(newRequestEntity)
            .patch("patch/{id}", id)
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("TC7")
    @Story("PATCH запрос")
    @Step("Редактирование несуществующей сущности")
    @Description("Редактирование сущности по ее несуществующему идентификатору")
    void patchEntityByUnknownId() {
        String message = RestAssured.given()
            .spec(requestSpecification())
            .body(requestEntity)
            .patch("patch/{id}", UNKNOWN_ID)
            .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .extract()
            .path("error");

        assertEquals("unable to check for existence of such id [no rows in result set]", message);
    }

    @Test
    @DisplayName("TC8")
    @Story("DELETE запрос")
    @Step("Удаление сущности")
    @Description("Удаление сущности по ее существующему идентификатору")
    void deleteEntityByKnownId() {
        RestAssured.given()
            .spec(requestSpecification())
            .body(requestEntity)
            .delete("delete/{id}", id)
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("TC9")
    @Story("DELETE запрос")
    @Step("Удаление несуществующей сущности")
    @Description("Удаление сущности по ее несуществующему идентификатору")
    void deleteEntityByUnknownId() {
        String message = RestAssured.given()
            .spec(requestSpecification())
            .body(requestEntity)
            .delete("delete/{id}", UNKNOWN_ID)
            .then()
            .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
            .extract()
            .path("error");

        assertEquals("no rows found for this id", message);
    }
}
