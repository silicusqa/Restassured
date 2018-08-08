package Sampleprogram;



import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Resttest1 
{
	jdbcconnection db = new jdbcconnection();
	String responsebody = null;
	
	@Test
	public void apitestforgetmethod()
	{
		RestAssured.baseURI ="https://api-dit.azurewebsites.net/";
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.get("companies");
		responsebody = response.getBody().asString();
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode,200,"Status code got chnaged");
		System.out.println("Response body is: "+responsebody);
	}
	@Test 
	public void apitestforpostmethod() throws JSONException, SQLException
	{
		RestAssured.baseURI ="https://api-dit.azurewebsites.net/";
		RequestSpecification httprequest = RestAssured.given();
		httprequest.given().body("{\"type\":\"merchant\","+"\"name\":\"bigbsaket\","+"\"doing_business_as\":\"text764\","+"\"tax_id\":\"123457987\","+"\"created_by\":\"9032d1ff-bd0f-4854-a665-d07eadfd1f35\"}").when().contentType(ContentType.JSON).post("companies");
		Response response = httprequest.get("companies");
		int statuscode = response.getStatusCode();
		System.out.println("Status code is "+statuscode);
		responsebody = response.jsonPath();
		System.out.println("Respose body is "+responsebody);
		Assert.assertEquals(statuscode,200,"Status code got changed");
		//Assert.assertTrue(responsebody.contains("type=merchant"), db.dbquery());
		Assert.assertEquals(responsebody.contains("type"), db.resultset.getString("type"));
		 	
	}
}
