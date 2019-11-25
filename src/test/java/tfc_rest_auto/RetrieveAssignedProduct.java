package tfc_rest_auto;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.ITestContext;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class RetrieveAssignedProduct  extends RestAssuredBased
{
	 protected static  String  customerId ="";
	 protected static String productId ;
	 //private static String CONTEXT_PATH=" /ecommerce/customer" + "/" +customerId + "/product" + "/" + productId;
	 
	 public static  void getAssignedProduct(String productId , ITestContext context ){
		 
		 //SET URL
		 customerId= (context.getAttribute("customerId")).toString();//need to add validation for customerId
		 String CONTEXT_PATH="mcss/ecommerce/customer/" +customerId + "/product/" + productId;
		 
		//Execute REST
		 Response response =get( CONTEXT_PATH );
     
		 

		 //Validations:
		 ResponseBody body = response.getBody();
		 assertEquals( 200  ,response.getStatusCode(),"Status Code is not  as expected");
		 assertEquals( "AC" ,response.body().path("Product.status"),"AP status is not correct ");
		 assertEquals( productId ,response.body().path("Product.id"),"AP status is not correct ");
		 
		 //Product validations 
		 assertNotEquals( null, response.body().path("Product.serviceId"), "ServiceId is null");
		 assertNotEquals( null, response.body().path("Product.productOfferingProductSpecID"), "productOfferingProductSpecID is null");
		 assertNotEquals( null, response.body().path("Product.productTypeX9"), "productTypeX9 is null");//EQ,IPTV
		 assertNotEquals( null, response.body().path("Product.hasPendingOrders"), "hasPendingOrders is null");//optional
		 assertNotEquals( null, response.body().path("Product.ceaseActionAllowed"), "ceaseActionAllowed is null");
		 assertNotEquals( null, response.body().path("Product.changeActionAllowed"), "changeActionAllowed is null");
		 assertNotEquals( null, response.body().path("Product.catalogOfferingId"), "catalogOfferingId is null");
		 assertNotEquals( null, response.body().path("Product.lineOfBusiness"), "lineOfBusiness is null");
		 assertNotEquals( null, response.body().path("Product.spoName"), "spoName is null");
		 assertNotEquals( null, response.body().path("Product.resumeActionAllowed"), "resumeActionAllowed is null");
		 assertNotEquals( null, response.body().path("Product.catalogItemID"), "catalogItemID");
		// plan validations  only if product is not Equipment for Equipment can be if it has Repeator
		 if ( !(response.body().path("Product.productTypeX9")).equals("EQ")  ){
		     assertNotEquals( null, response.body().path("Product.plan"), "plan is null"); // optional 
		     assertNotEquals( null, response.body().path("Product.plan.catalogItemName"), "plan.catalogItemName is null");
		     assertNotEquals( null, response.body().path("Product.plan.catalogItemImages"), "plan.catalogItemImages is null");//optional
		     assertNotEquals( null, response.body().path("Product.plan.planRank"), "plan.planRank is null");
		     assertNotEquals( null, response.body().path("Product.plan.catalogItemID"), "plan.catalogItemID is null");
		     assertNotEquals( null, response.body().path("Product.plan.catalogItemDescription"), "plan.catalogItemDescription is null");//optional
		     assertNotEquals( null, response.body().path("Product.plan.id"), "plan.id is null");
		    }
		 //services validations 
		 assertNotEquals( null, response.body().path("Product.services"), "services are  null!"); // optional 
		 assertNotEquals( 0, response.body().path("Product.services.size()"), " Services Array is empty !"); // optional 
		// assertNotEquals( null, ((RestAssuredBased) response.body()).get("/Product").jsonPath().getList("Product.services.[0].businessType"), "Services.businessType is null!"); 
	/*	 assertNotEquals( null, response.body().path("Product.services.[0].catalogItemID"), "Services.catalogItemID is null!"); 
		 assertNotEquals( null, response.body().path("Product.services.[0].catalogItemName"), "Services.catalogItemName is null!"); 
		 assertNotEquals( null, response.body().path("Product.services.[0].catalogItemDescription"), "catalogItemDescription is null!"); //optional
		 assertNotEquals( null, response.body().path("Product.services.[0].initialActivationDate"), "Services.initialActivationDate is null!");// 
		 assertNotEquals( null, response.body().path("Product.services.[0].id"), "Services.id is null!");// 
		 assertNotEquals( null, response.body().path("Product.services.[0].serviceType"), "Services.serviceType is null!");// 
		 assertNotEquals( null, response.body().path("Product.services.[0].status"), "Services.status is null!");// */
		 
		 
		 //Savinq  Info 
		 /*
        context.setAttribute( "customerId", body.path("ClientUserContext.customerId" ));
        context.setAttribute( "personId", body.path("ClientUserContext.personId" ));
        context.setAttribute( "personId", body.path("ClientUserContext.personId" ));
        context.setAttribute( "personObjIdX", body.path("ClientUserContext.personObjIdX" ));
        */
	 }

}