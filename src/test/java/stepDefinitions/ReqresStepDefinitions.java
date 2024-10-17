package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;
import static io.restassured.RestAssured.given;
public class ReqresStepDefinitions {


    String endpoint;
    Response response ;
    JsonPath responseJP;
    JSONObject requestBody;



    @Given("Kullanici {string} base URL ini kullanir")
    public void kullanici_base_url_ini_kullanir(String reqresUrl) {

     endpoint= ConfigReader.getProperty(reqresUrl);


    }
    @Then("Path parametrelerine {string} kullanir")
    public void path_parametrelerine_kullanir(String pathParam) {

        endpoint=endpoint +pathParam;



    }
    @Then("Kullanici endpointe bir GET request gondererek Response degeri kaydeder")
    public void kullanici_endpointe_bir_get_request_gondererek_response_degeri_kaydeder() {

        response=given().when().get(endpoint);
        response.prettyPrint();




    }
    @Then("Donen Response in status degerinin {int}")
    public void donen_response_in_status_degerinin(Integer status) {

        response.then().assertThat().statusCode(status);


    }
    @Then("Donen Response in JSON bodysinde yer alan id degerin {int} oldugunu")
    public void donen_response_in_json_bodysinde_yer_alan_id_degerin_oldugunu(Integer id) {


        responseJP=response.jsonPath();
        Assert.assertEquals(id,responseJP.get("data.id"));




    }
    @Then("Response in yanit suresinin {int} sn den kisa oldugunu test eder")
    public void response_in_yanit_suresinin_sn_den_kisa_oldugunu_test_eder(Integer sec) {

                Assert.assertTrue(response.getTime()>sec);
    }

    @When("Kullanici request Body yi {string} icin {string}, {string} icin {string} olusturur")
    public void kullanici_request_body_yi_icin_icin_olusturur(String name, String nameValue, String job, String jobValue) {
        requestBody=new JSONObject();
        requestBody.put("name",nameValue);
        requestBody.put("job",jobValue);
        System.out.println(requestBody);
    }

    @Then("Kullanici endpointe header değeri {string}, {string} olan bir POST isteği gondererek Response degeri kaydeder")
    public void kullanici_endpointe_header_degeri_olan_bir_post_istegi_gondererek_response_degeri_kaydeder(String headerKey, String headerValue) {

        response=given()
                .when()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .header(headerKey,headerValue)
                .post(endpoint);
        response.prettyPrint();


    }
    @Then("ResponseBody deki {string} attribute unun {string} oldugunu test eder")
    public void response_body_deki_attribute_unun_oldugunu_test_eder(String name, String nameValue) {

       responseJP= response.jsonPath();

        Assert.assertEquals(nameValue, responseJP.getString(name));



    }

}
