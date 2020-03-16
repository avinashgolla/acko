package com.acko.base;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseAPI {

	RequestSpecBuilder builder = new RequestSpecBuilder();
	RequestSpecification requestSpec = builder.build();

	public Response getAPIcall(String URL) {
		Response response = given().spec(requestSpec).when().get(URL);
		return response;
	}

}
