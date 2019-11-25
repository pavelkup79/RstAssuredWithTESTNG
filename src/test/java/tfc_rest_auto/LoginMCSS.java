package tfc_rest_auto;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import  tfc_rest_auto.RestAssuredBased; 
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestContext;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public  class LoginMCSS extends RestAssuredBased{ 

    private static  String CONTEXT_PATH="/mcss/mobile/login";
	
    //LoginMCSS(){	}
	

    public static  void login( String docType,String docId ,String password ,ITestContext context){	
    	
        /*Body Preparation according tp example : 
    	  {
    		"ImplMobileLoginParams": {
    			"docType": "{{docType}}",
    			"docId":"{{docId}}",
    			"password": "{{UserPassword}}"		
    		 }
    	   }
    	 */
		Map<String, Object> loginBody = new HashMap<String, Object>();
        Map<String, Object> userDetails = new HashMap<String, Object>();
	    userDetails.put("password",password);
	    userDetails.put("docId",docId);
	    userDetails.put("docType",docType);
	    loginBody.put("ImplMobileLoginParams", userDetails);
	    
	    //Execute REST  
        Response response =post( loginBody, CONTEXT_PATH ); 
        
	    //Handling Response +Validations  
        assertEquals( 200  ,response.getStatusCode(),"Status Code is not  as expected");
        String Authorization =response.getHeader("uxfauthorization");
        ResponseBody body = response.getBody();
        Assert.assertNotNull(Authorization);         
        assertEquals( true  ,body.path("AccessResponse.authenticated"),"Login failed ");	
        
        //Saving Data 
    	context.setAttribute("docId", docId);
    	context.setAttribute("docType", docType);
    	context.setAttribute("Authorization", Authorization);
    	setRequestSpecification( context);// need to be after  context.setAttribute("Authorization", Authorization);
    	


	 }

}
