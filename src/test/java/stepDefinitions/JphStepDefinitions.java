package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;


public class JphStepDefinitions {


    String endPoint;
    Response response;

    JsonPath responseJP;

    @Given("Kullanici {string} base URL'ini kullanir")
    public void kullanici_base_url_ini_kullanir(String string) {
        //      Kullanici "jPHBaseUrl" base URL'ini kullanir


        endPoint= ConfigReader.getProperty("jPHBaseUrl");


    }
    @Then("Path parametreleri icin {string} kullanir")
    public void path_parametreleri_icin_kullanir(String pathParams) {

        //Path parametreleri icin "posts/44" kullanir

        endPoint=endPoint +"/"+pathParams;


    }
    @Then("jPH server a GET request gonderir ve testleri yapmak icin response degerini kaydeder")
    public void j_ph_server_a_get_request_gonderir_ve_testleri_yapmak_icin_response_degerini_kaydeder() {
        //    jPH server a GET request gonderir ve testleri yapmak icin response degerini kaydeder

        response=given().when().get(endPoint);
        response.prettyPrint();



    }
    @Then("jPH response'da status degerinin {int}")
    public void j_ph_response_da_status_degerinin(Integer statusCode) {
        //jPH response'da status degerinin 200

        Assert.assertEquals(statusCode,(Integer)response.statusCode());


    }
    @Then("jPH response'da content type degerinin {string}")
    public void j_ph_response_da_content_type_degerinin(String contentType) {
        //jPH response'da content type degerinin "application/json; charset=utf-8"
        Assert.assertEquals(contentType,response.contentType());

    }
    @Then("jPH responseBody'sindeki attributeleri test etmek icin response i JsonPath objesine cast eder")
    public void j_ph_response_body_sindeki_attributeleri_test_etmek_icin_response_i_json_path_objesine_cast_eder() {

        //jPH responseBody'sindeki attributeleri test etmek icin response i JsonPath objesine cast eder
        responseJP=response.jsonPath();




    }
    @Then("jPH GET response body'sinde {string} degerinin Integer {int}")
    public void j_ph_get_response_body_sinde_degerinin_integer(String attribute, Integer expectedValue) {

        //jPH GET response body'sinde "userId" degerinin Integer 5
        Assert.assertEquals(expectedValue, (Integer)responseJP.getInt(attribute));



    }
    @Then("jPH GET response body'sinde {string} degerinin String {string}")
    public void j_ph_get_response_body_sinde_degerinin_string(String attribute, String expectedValue) {
        //    jPH GET response body'sinde "title" degerinin String "optio dolor molestias sit"

        Assert.assertEquals(expectedValue,responseJP.getString(attribute));


    }
}
