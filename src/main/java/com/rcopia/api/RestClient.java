package com.rcopia.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class RestClient {

    /**
     * @param endpoint
     * @return
     */
    public static Response get(String endpoint) {
        return requestHeader().get(endpoint);
    }

    /**
     * @return
     */
    public static RequestSpecification requestHeader() {
        return RestAssured.given();
    }

    /**
     * @param bearerToken
     * @return
     */
    public static RequestSpecification requestHeader(String bearerToken) {
        return RestAssured.given()
                .header(Constants.AUTHORIZATION_KEY, "Bearer " + bearerToken);
    }

    /**
     * @return
     */
    public static RequestSpecification request() {
        return requestHeader().accept(Constants.APPLICATION_JSON)
                .contentType(Constants.APPLICATION_JSON);
    }

    /**
     * @param bearerToken
     * @return
     */
    public static RequestSpecification request(String bearerToken) {
        return requestHeader(bearerToken).accept(Constants.APPLICATION_JSON)
                .contentType(Constants.APPLICATION_JSON);
    }

    /**
     * @param endpoint
     * @param requestBody
     * @return
     */
    public static Response post(String endpoint, JSONObject requestBody) {
        return request().body(requestBody)
                .when()
                .post(endpoint);
    }

    /**
     * @param endpoint
     * @param requestBody
     * @param bearerToken
     * @return
     */
    public static Response post(String endpoint, JSONObject requestBody, String bearerToken) {
        return request(bearerToken).body(requestBody)
                .when()
                .post(endpoint);
    }

    /**
     * @param endpoint
     * @param requestBody
     * @return
     */
    public static Response put(String endpoint, JSONObject requestBody) {
        return request().body(requestBody)
                .when()
                .put(endpoint);
    }

    /**
     * @param endpoint
     * @return
     */
    public static Response delete(String endpoint) {
        return request().when()
                .delete(endpoint);
    }

    /**
     * @return
     */
    public static RequestSpecification requestForXMLPayload() {
        return requestHeader().contentType(Constants.APPLICATION_XML);
    }

    /**
     * @param endpoint
     * @param requestBody
     * @return
     */
    public static Response post(String endpoint, String requestBody) {
        return requestForXMLPayload().body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
}
