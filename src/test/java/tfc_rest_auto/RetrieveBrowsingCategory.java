package tfc_rest_auto;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.HashMap;
import  tfc_rest_auto.RestAssuredBased; 
import java.util.HashMap;
import java.util.Map;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import org.testng.Assert;
import org.testng.ITestContext;

public class RetrieveBrowsingCategory extends RestAssuredBased  {
	
	 private static  String CONTEXT_PATH="/mcss/ecommerce/catalog/categories";
	 
	 
	 public static void getBrowsingCategory( ITestContext context ){
		 boolean isAnonymouse =(context.getAttribute("Authorization").equals(""))? true : false ;
		 		 
		 //Execute REST
		 Response response =get( CONTEXT_PATH );
		 
		 //Handle response
		 ResponseBody body = response.getBody();
		 System.out.println("Response bodyis:" + body.asString() );
		// Set Authorization in case if anonymouse flow  and no login 
		 String Authorization =response.getHeader("uxfauthorization");
		 Assert.assertNotNull(Authorization);    		 		 
	     if ( isAnonymouse  )    
	       {
	     	context.setAttribute("Authorization", Authorization);
	    	setRequestSpecification( context);
	      }
	             
		 
	     //Validations
	     assertEquals( 200  ,response.getStatusCode(),"Status Code is not  as expected");
	     assertNotEquals(null, response.body().path("Categories" ), "Categories is null");
	     assertNotEquals(null, response.body().path("Categories.categories" ), "Categories.categories is null");
	 
	 }

}
