package rocks.byivo.passwordmeter.measure;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.VERY_STRONG;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import rocks.byivo.passwordmeter.model.PasswordMeasureRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PasswordMeterControllerIT {

    @LocalServerPort
    int port;

    @Before
    public void setUp() throws Exception {
	RestAssured.port = port;
    }

    @Test
    public void 
    should_have_a_perfect_score_and_complexity_given_the_sort_of_characters_in_password() {
	given()
		.contentType(ContentType.JSON)
		.body(new PasswordMeasureRequest("a$1C5oSt!z"))
	.when()
		.post("/measure")
	.then()
		.statusCode(200)
		.body("score", equalTo(100))
		.body("complexity", equalTo(VERY_STRONG.toString()));
    }

}
