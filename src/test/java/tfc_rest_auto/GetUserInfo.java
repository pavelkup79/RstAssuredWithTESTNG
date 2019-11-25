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

public class GetUserInfo extends RestAssuredBased {
	
	 private static  String CONTEXT_PATH="/mcss/utility/user_information";
	 
	 public static  void getUserInfo( ITestContext context ){
		 
		 //Execute REST
		 Response response =get( CONTEXT_PATH );
		 
		
		 
		 
		 
		 
		 
		 //Validations:
		 assertEquals( 200  ,response.getStatusCode(),"Status Code is not  as expected");
		 ResponseBody body = response.getBody();
		 
		
		 assertEquals( context.getAttribute("docId") +"_"+ context.getAttribute("docType")  ,body.path("ClientUserContext.username"),"Username is not correct correct");
		 assertNotEquals(null, response.body().path("ClientUserContext.customerId" ), "customerId is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.personId" ), "personId is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.personObjIdX9" ), "personObjIdX is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.registrationStatus" ), "registrationStatus is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.paymentCategory" ), "paymentCategory is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.userContactPersonName" ), "userContactPersonName is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.associatedCustomersX9" ), "associatedCustomersX9 is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.work" ), "work is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.primaryResource" ), "primaryResource is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.allProducts" ), "allProducts is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.subscriptionIds" ), "subscriptionIds is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.subscriptionNumbers" ), "subscriptionNumbers is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.bundledProducts" ), "bundledProducts  is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.customerTypeCodeX9" ), "customerTypeCodeX9 is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.ceasedOnlyX9" ), "ceasedOnlyX9 is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.creditScoreX9" ), "creditScoreX9 is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.customerValueX9" ), "customerValueX9 is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.hostNameX9" ), "hostNameX9 is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.subscriberToCustomerX9" ), "subscriberToCustomerX9is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.allCustomerProducts" ), "allCustomerProductsis null");
		 assertNotEquals(null, response.body().path("ClientUserContext.uniqueIdX9" ), "uniqueIdX9 is null");
		 assertNotEquals(null, response.body().path("ClientUserContext.contractRUT" ), "contractRUT is null");

		 
			 
		 //Savinq  Info 
         context.setAttribute( "customerId", body.path("ClientUserContext.customerId" ));
         context.setAttribute( "personId", body.path("ClientUserContext.personId" ));
         context.setAttribute( "personObjIdX", body.path("ClientUserContext.personObjIdX9" ));

	 }
       
}



