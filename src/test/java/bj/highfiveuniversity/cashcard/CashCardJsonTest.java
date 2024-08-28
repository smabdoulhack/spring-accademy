package bj.highfiveuniversity.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

// @JsonTest // pour les test json
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CashCardJsonTest {

    // @Test
    // void myFirstTest() {
    // assertThat(42).isEqualTo(42); // tester l'égalité entre deux assertions
    // }

    // @Autowired // @Autowired is an annotation that directs Spring to create an
    // object of the
    // // requested type.
    // private JacksonTester<CashCard> json;

    // @Test
    // void cashCardSerializationTest() throws IOException { // le test échoue en
    // l'absence du fichier expected.json et des
    // // bonnes données.
    // CashCard cashCard = new CashCard(99L, 123.45);
    // assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
    // assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
    // assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
    // .isEqualTo(99);
    // assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
    // assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
    // .isEqualTo(123.45);
    // }

    // @Test
    // void cashCardDeserializationTest() throws IOException {
    // String expected = """
    // {
    // "id":99,
    // "amount":123.45
    // }
    // """;
    // assertThat(json.parse(expected))
    // .isEqualTo(new CashCard(99L, 123.45));
    // assertThat(json.parseObject(expected).id()).isEqualTo(99);
    // assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
    // }

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldNotReturnACashCardWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();

        // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // DocumentContext documentContext = JsonPath.parse(response.getBody());
        // Number id = documentContext.read("$.id");
        // assertThat(id).isEqualTo(99);

        // Double amount = documentContext.read("$.amount");
        // assertThat(amount).isEqualTo(123.45);
    }
}
