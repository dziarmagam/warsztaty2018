package training.user;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import training.Application;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;


@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@SqlGroup({
        @Sql(scripts = "/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class UserResourceTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void should_create_user() throws IOException {
        //given
        byte[] body = loadResource("/user.json");
        RequestSpecification request = RestAssured.given()
                .body(body)
                .header(new Header("content-type","application/json"));
        //when
        Response response = request.post(getUserEndpoint());
        //then
        response.then()
                .statusCode(200);
    }

    @Test
    public void should_return_created_user() throws IOException {
        //given
        byte[] body = loadResource("/user.json");
        String id = RestAssured.given()
                .body(body)
                .header(new Header("content-type", "application/json"))
                .post(getUserEndpoint()).getBody().print();
        //when
        Response response = RestAssured.given()
                .get(getUserEndpoint() + id);
        //then
        response.then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Marcin"))
                .body("surname", Matchers.equalTo("Dziarmaga"))
                .body("email", Matchers.equalTo("marcin.dziarmaga@email.com"));

    }

    private String getUserEndpoint() {
        return "/users/";
    }

    private byte[] loadResource(String resourcePath) throws IOException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream(resourcePath);
        return IOUtils.toByteArray(resourceAsStream);
    }

}