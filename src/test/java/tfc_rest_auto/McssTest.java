package tfc_rest_auto;

import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;

import tfc_rest_auto.LoginMCSS;
import tfc_rest_auto.RestAssuredBased;

  
     
public class McssTest {
 
  @BeforeSuite( groups ={"COP","Anonymouse"})
  @Parameters({"baseURI","port"})
  public void setup(@Optional("") String baseURI ,@Optional("") String port, ITestContext context) throws Exception  {
	  context.setAttribute("Authorization", "");
	  RestAssuredBased setup = new  RestAssuredBased(baseURI, port, context );
	  
  }
    @Parameters({ "docType", "docId", "password"})
    @Test(priority=1, groups ={"COP"})
    public void test_Login (@Optional("") String docType,@Optional("")  String docId ,@Optional("")  String password ,ITestContext context) throws Exception  { 
    	LoginMCSS.login(docType, docId, password,context);
    }
    
    @Test(priority=10 , groups ={"COP"} ,dependsOnMethods ={"test_Login"})
   // @Parameters({"docType","docId","password"})
    public void test_GetUserInfo (ITestContext context) throws Exception { 
    	GetUserInfo.getUserInfo(context );
    }
    
    @Test(priority=20 , groups ={"COP", "Anonymouse"} )
    // @Parameters({"docType","docId","password"})
     public void test_getBrowsingCategory (ITestContext context) throws Exception { 
     	RetrieveBrowsingCategory.getBrowsingCategory(context );
     	System.out.println("test started");
     }
    
    @Test(priority=30 , groups ={"COP","CHQ"} ,dependsOnMethods ={"test_Login"})
    @Parameters({"productId"})
     public void test_getAssignedProduct (@Optional("") String productId ,ITestContext context) throws Exception { 
    	RetrieveAssignedProduct.getAssignedProduct( productId, context );
     }
    
    
    
    
    @AfterSuite
    public void end (ITestContext context ) throws Exception {
    	System.out.println("Endof Suite");	
  	  
    }
}
