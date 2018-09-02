package rocks.byivo.passwordmeter.measure;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.STRONG;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.TOO_SHORT;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.VERY_STRONG;
import static rocks.byivo.passwordmeter.model.PasswordComplexity.VERY_WEAK;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import rocks.byivo.passwordmeter.model.PasswordComplexity;
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
    should_have_the_worst_score_and_complexity_when_all_letters_are_equals() {
	given()
		.contentType(ContentType.JSON)
		.body(new PasswordMeasureRequest("aaaaaaaaaa"))
	.when()
		.post("/measure")
	.then()
		.statusCode(200)
		.body("score", equalTo(0))
		.body("complexity", equalTo(VERY_WEAK.toString()));
    }
    
    @Test
    public void 
    should_have_the_20_score_and_weak_complexity_with_the_provided_password() {
	given()
		.contentType(ContentType.JSON)
		.body(new PasswordMeasureRequest("55423181"))
	.when()
		.post("/measure")
	.then()
		.statusCode(200)
		.body("score", equalTo(20))
		.body("complexity", equalTo(PasswordComplexity.WEAK.toString()));
    }
    
    @Test
    public void 
    should_have_the_48_score_and_good_complexity_with_the_provided_password() {
	given()
		.contentType(ContentType.JSON)
		.body(new PasswordMeasureRequest("55231@"))
	.when()
		.post("/measure")
	.then()
		.statusCode(200)
		.body("score", equalTo(48))
		.body("complexity", equalTo(PasswordComplexity.GOOD.toString()));
    }
    
    @Test
    public void 
    should_have_the_73_score_and_strong_complexity_with_the_provided_password() {
	given()
		.contentType(ContentType.JSON)
		.body(new PasswordMeasureRequest("aaa23#$5"))
	.when()
		.post("/measure")
	.then()
		.statusCode(200)
		.body("score", equalTo(73))
		.body("complexity", equalTo(STRONG.toString()));
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
    
    @Test
    public void 
    should_have_a_perfect_score_and_complexity_with_25_spaces() {
	given()
		.contentType(ContentType.JSON)
		.body(new PasswordMeasureRequest("                         "))
	.when()
		.post("/measure")
	.then()
		.statusCode(200)
		.body("score", equalTo(100))
		.body("complexity", equalTo(VERY_STRONG.toString()));
    }
    
    @Test
    public void 
    should_have_the_zero_score_and_a_too_short_complexity_when_user_does_not_provide_a_password() {
	given()
		.contentType(ContentType.JSON)
		.body(new PasswordMeasureRequest(null))
	.when()
		.post("/measure")
	.then()
		.statusCode(200)
		.body("score", equalTo(0))
		.body("complexity", equalTo(TOO_SHORT.toString()));
    }
    
    @Test
    public void 
    should_have_the_zero_score_and_a_too_short_complexity_when_user_provides_an_empty_password() {
	given()
		.contentType(ContentType.JSON)
		.body(new PasswordMeasureRequest(""))
	.when()
		.post("/measure")
	.then()
		.statusCode(200)
		.body("score", equalTo(0))
		.body("complexity", equalTo(TOO_SHORT.toString()));
    }
    

}
