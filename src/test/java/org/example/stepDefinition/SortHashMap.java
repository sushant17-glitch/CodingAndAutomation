package org.example;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import io.cucumber.java.*;


import static io.restassured.RestAssured.given;

public class SortHashMap {

    @Test
    public void sortHashMapByKey() {
        HashMap<String, Integer> myMap = new HashMap<>();
        myMap.put("Sushant", 100);
        myMap.put("Aryan", 50);
        myMap.put("Vinod", 200);
        myMap.put("Gaurav", 110);
        myMap.put("Krish", 89);

        System.out.println(myMap);

        ArrayList<String> myList = new ArrayList<>(myMap.keySet());
        Collections.sort(myList);
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        for (String key : myList) {
            sortedMap.put(key, myMap.get(key));
        }

        System.out.println(sortedMap);
    }

    @Test
    public void sortHashMapByValue() {
        HashMap<String, Integer> myMap = new HashMap<>();
        myMap.put("Sushant", 100);
        myMap.put("Aryan", 50);
        myMap.put("Vinod", 200);
        myMap.put("Gaurav", 110);
        myMap.put("Krish", 89);

        System.out.println(myMap);
        ArrayList<Integer> myList = new ArrayList<>(myMap.values());
        Collections.sort(myList);

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        for (Integer value : myList) {
            for (String key : myMap.keySet()) {
                if (myMap.get(key).equals(value)) {
                    sortedMap.put(key, value);
                }
            }
        }

        System.out.println(sortedMap);
    }

    @Test
    public void RestAssuredBasicGet() {
        Response myResponse = given()
                .baseUri("https://resolver-svc-int-stage.lending.paytm.com/v1/user/journey")
                .header("product-type","PL")
                .header("customer-id","1701760010")
                .when()
                .get();

        myResponse.then().statusCode(200).log().all();
        Assert.assertEquals("RISK", myResponse.jsonPath().get("successResponse.journey"));
    }

    @Test
    public void RestAssuredBasicPost(){
        Response myResponse = given()
                .baseUri("https://goldengate-staging10.paytm.com/MerchantService/v1/workflow/lead")
                .header("Content-Type","application/json")
                .header("custId","1702240745")
                .header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnRJZCI6IkxFTkRJTkdfQkZGIiwiaXNzIjoiT0UiLCJjdXN0SWQiOiIxNzAyMjQwNzQ1IiwidGltZXN0YW1wIjoiMjAyNC0wMS0wNFQwOToxNToxMC4zNTYrMDU6MzAifQ.8mvXjbqJD3Me1p-KTGGhn1kQGP6jh6-PkaRaLAgTUxs")
                .queryParam("solution","personal_loan_v3")
                .queryParam("entityType","INDIVIDUAL")
                .queryParam("channel","PAYTM_APP")
                .queryParam("leadId","82fd05ed-4819-4dea-a029-27ac0859dc87")
                .body(new File("src/main/java/utils/requestBody.json"))
                .when()
                .post();

        myResponse.then().statusCode(200).log().all();
        Assert.assertEquals("LIS_DATA_UPDATE_SUCCESS", myResponse.jsonPath().get("stage"));

    }

    @Test
    @Given("Perform API Call")
    public void RestAssuredBasicPost2(){

        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json");
        headersMap.put("custId", "1702240745");
        headersMap.put("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnRJZCI6IkxFTkRJTkdfQkZGIiwiaXNzIjoiT0UiLCJjdXN0SWQiOiIxNzAyMjQwNzQ1IiwidGltZXN0YW1wIjoiMjAyNC0wMS0wNFQwOToxNToxMC4zNTYrMDU6MzAifQ.8mvXjbqJD3Me1p-KTGGhn1kQGP6jh6-PkaRaLAgTUxs");

        // Create a HashMap for query parameters
        HashMap<String, String> queryParamsMap = new HashMap<>();
        queryParamsMap.put("solution", "personal_loan_v3");
        queryParamsMap.put("entityType", "INDIVIDUAL");
        queryParamsMap.put("channel", "PAYTM_APP");
        queryParamsMap.put("leadId", "82fd05ed-4819-4dea-a029-27ac0859dc87");


        Response myResponse = given()
                .baseUri("https://goldengate-staging10.paytm.com/MerchantService/v1/workflow/lead")
                .headers(headersMap)
                .queryParams(queryParamsMap)
                .body(new File("src/main/java/utils/requestBody.json"))
                .log().all()
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

        myResponse.then().statusCode(200).log().all();
        Assert.assertEquals("LIS_DATA_UPDATE_SUCCESS", myResponse.jsonPath().get("stage"));

    }

    @Given("User performed request for type: {}, for base url: {}, endpoint: {}, requestFile: {} and Expected key: {} & value: {}, Expected status code: {}")
    public void RestAssuredBasicCall(String type, String baseUrl, String endpoint, String requestFile, String key, String value, int statusCode){

        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("Content-Type", "application/json");
        headersMap.put("custId", "1702240745");
        headersMap.put("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnRJZCI6IkxFTkRJTkdfQkZGIiwiaXNzIjoiT0UiLCJjdXN0SWQiOiIxNzAyMjQwNzQ1IiwidGltZXN0YW1wIjoiMjAyNC0wMS0wNFQwOToxNToxMC4zNTYrMDU6MzAifQ.8mvXjbqJD3Me1p-KTGGhn1kQGP6jh6-PkaRaLAgTUxs");

        // Create a HashMap for query parameters
        HashMap<String, String> queryParamsMap = new HashMap<>();
        queryParamsMap.put("solution", "personal_loan_v3");
        queryParamsMap.put("entityType", "INDIVIDUAL");
        queryParamsMap.put("channel", "PAYTM_APP");
        queryParamsMap.put("leadId", "82fd05ed-4819-4dea-a029-27ac0859dc87");
        Response apiResponse=null;

       if(type.equalsIgnoreCase("post")) {
            apiResponse = given()
                   .baseUri(baseUrl+endpoint)
                   .headers(headersMap)
                   .queryParams(queryParamsMap)
                   .body(new File(requestFile))
                   .log().all()
                   .when()
                   .post()
                   .then()
                   .statusCode(statusCode)
                   .extract().response();
       }
       else if(type.equalsIgnoreCase("get")){
            apiResponse = given()
                   .baseUri(baseUrl+endpoint)
                   .headers(headersMap)
                   .queryParams(queryParamsMap)
                   .body(new File(requestFile))
                   .log().all()
                   .when()
                   .get()
                   .then()
                   .statusCode(statusCode)
                   .extract().response();
       }

        apiResponse.then().statusCode(200).log().all();
        Assert.assertEquals(value, apiResponse.jsonPath().get(key));
    }

}
