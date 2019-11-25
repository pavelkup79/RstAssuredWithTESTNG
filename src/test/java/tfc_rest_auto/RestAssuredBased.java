package tfc_rest_auto;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.SSLConfig;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;


public class RestAssuredBased 
{
	
    static final String cacheControl ="no-cache"; 
    static final String lo ="es_CL";
    static final String sc ="SS";

    //static String contentType ="Application/json" ;  
    
    
 	RestAssuredBased()
 	{
     
 	}
	
	 public RestAssuredBased(String baseURI ,String port,ITestContext context)
	{
	  System.out.println("RestAssuredBased Constructor Started");	 
      RestAssured.baseURI = baseURI ;
      RestAssured.port= Integer.parseInt(port) ;
      //defining  request Spec requestSpec 
      setRequestSpecification(context);
	}
	 
	 
	 
	 

	public static Response post(Map<String, Object> RequestBody,String CONTEXT_PATH ) 
	{
     Response response =RestAssured.given().
				   body(RequestBody).
				   when().
				   post(CONTEXT_PATH).
				   then().
				   extract().
				   response();
				   System.out.println("Response Body is =>  " + response.asString());	
				   	
     return response;
    }

	public static Response get(String CONTEXT_PATH ) 
	{
     Response response =RestAssured.given().
				   when().
				   get(CONTEXT_PATH).
				   then().
				   extract().
				   response();
				   System.out.println("Response Body is =>  " + response.asString());	
				   	
     return response;
    }
	
	//NotCompleted 
	public static Response getWithParams (String CONTEXT_PATH , Map<String,Object> Queryparams) 
	{
     Response response =RestAssured.given().
				   when().
				   get(CONTEXT_PATH).
				   then().
				   extract().
				   response();
				   System.out.println("Response Body is =>  " + response.asString());	
				   	
     return response;
    }
	
	
	
	protected static void setRequestSpecification(ITestContext context)
	{
		
	      RequestSpecification requestSpec = new RequestSpecBuilder()
	    		    //.setBaseUri( baseURI)
	    		    //.setPort(Integer.parseInt(port))
	    		    .setRelaxedHTTPSValidation()
	    		    .addHeader("cache-control",cacheControl)
	    		    .addHeader("Authorization",(context.getAttribute("Authorization")).toString())
	    		    .setAccept(ContentType.JSON)
	    		    .setContentType(ContentType.JSON)
	    		    .addQueryParam("lo", lo)
	    		    .addQueryParam("sc", sc)
	    		    .log(LogDetail.ALL)//log().all().// log().body   log().cookies()
	    		    .build();
	      RestAssured.requestSpecification = requestSpec;
	      
	      
	      ///defining  response Spec      
	      ResponseSpecification responseSpec = new ResponseSpecBuilder()
	    		    .expectContentType(ContentType.JSON)
	    		    .expectStatusCode(200)
	    		    .build();
	      RestAssured.requestSpecification = requestSpec;	
	}
	
}
   