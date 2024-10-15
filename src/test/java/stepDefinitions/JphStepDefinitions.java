package stepDefinitions;





import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class JphStepDefinitions {


    String endPoint;
    Response response;

    JsonPath responseJP;
    JSONObject requestBody=new JSONObject();
    JSONObject expectedDataResponseBody= new JSONObject();



    @Given("Kullanici {string} base URL'ini kullanir")
    public void kullanici_base_url_ini_kullanir(String jPHBaseUrl) {
        //      Kullanici "jPHBaseUrl" base URL'ini kullanir


        endPoint= ConfigReader.getProperty(jPHBaseUrl);


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




    @Then("POST request icin {string},{string},{int},{int} bilgileri ile request body olusturur")
    public void post_request_icin_bilgileri_ile_request_body_olusturur(String title, String body, Integer userId, Integer id) {
        //POST request icin "Ahmet","Merhaba",10 bilgileri ile request body olusturur


        requestBody.put("title",title);
        requestBody.put("body",body);
        requestBody.put("userId",userId);
        requestBody.put("id",id);


    }
    @Then("jPH server a POST request gonderir ve testleri yapmak icin response degerini kaydeder")
    public void j_ph_server_a_post_request_gonderir_ve_testleri_yapmak_icin_response_degerini_kaydeder() {

        //jPH server a POST request gonderir ve testleri yapmak icin response degerini kaydeder

        response=given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(endPoint);



    }
    @Then("jPH respons daki {string} header degerinin {string}")
    public void j_ph_respons_daki_header_degerinin(String headerKey, String headerValue) {


        //jPH respons daki "Connection" header degerinin "keep-alive"

        response.then().assertThat().header(headerKey, response.header(headerKey));
    }
    @Then("response attribute degerlerinin {string},{string},{int} ve {int} oldugunu test eder")
    public void response_attribute_degerlerinin_ve_oldugunu_test_eder(String title, String body, Integer userId, Integer id) {

        // response attribute degerlerinin "Ahmet","Merhaba",10 ve 101 oldugunu test eder

        Assert.assertEquals(title,responseJP.getString("title"));
        Assert.assertEquals(body,responseJP.getString("body"));
        Assert.assertEquals(userId,(Integer)responseJP.getInt("userId"));
        Assert.assertEquals(id,(Integer)responseJP.getInt("id"));

    }



    @Then("jPH server'a PUT request gonderir ve response'i kaydeder")
    public void j_ph_server_a_put_request_gonderir_ve_response_i_kaydeder() {
        //jPH server'a PUT request gonderir ve response'i kaydeder
        response=given().contentType(ContentType.JSON)
                            .body(requestBody.toString())
                            .when()
                            .put(endPoint);

    }
    @Then("expected response body ile actual response'daki attribute degerlerinin ayni oldugunu test eder")
    public void expected_response_body_ile_actual_response_daki_attribute_degerlerinin_ayni_oldugunu_test_eder() {
       // expected response body ile actual response'daki attribute degerlerinin ayni oldugunu test eder


        Assert.assertEquals(expectedDataResponseBody.getString("title"),responseJP.getString("title"));
        Assert.assertEquals(expectedDataResponseBody.getString("body"),responseJP.getString("body"));
        Assert.assertEquals(expectedDataResponseBody.getInt("userId"),
                responseJP.getInt("userId"));


    }



    @And("Test icin  {string}, {string} {int} ve {int} degerleri ile expected response body olusturur")
    public void testIcinVeDegerleriIleExpectedResponseBodyOlusturur(String title, String body, Integer userId, Integer id) {



        expectedDataResponseBody.put("title",title );
        expectedDataResponseBody.put("body",body );
        expectedDataResponseBody.put("userId",userId );
        expectedDataResponseBody.put("id",id );

        System.out.println(expectedDataResponseBody);

    }



    @When("PUT request icin {string} {string} ve {int} degerleri ile request body olusturur")
    public void put_request_icin_ve_degerleri_ile_request_body_olusturur(String title, String body, Integer userId) {


        requestBody.put("title",title);
        requestBody.put("body",body);
        requestBody.put("userId",userId);
        System.out.println(requestBody);
    }
}
