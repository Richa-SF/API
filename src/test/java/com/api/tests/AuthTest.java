package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.AuthRequest;
import com.api.models.response.AuthResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.api.listener.TestListener.class)
public class AuthTest {

    @Test(description = "Get booking id",groups = {"auth"})
    public void getToken(){
       /*baseURI="https://restful-booker.herokuapp.com";
       RequestSpecification x=given();
       RequestSpecification y=x.header("Content-Type","application/json");
       RequestSpecification z= y.body("{\"username\" : \"admin\",  \"password\" : \"password123\"}");
       Response response=z.post("/auth");*/

        //below we created request model for auth and created object and passed
        AuthRequest authRequest=new AuthRequest("admin","password123");
        AuthService auth=new AuthService();
        //below is hard coded which is bad so use pojo in line 22
       // Response response=auth.createToken("{\"username\" : \"admin\",  \"password\" : \"password123\"}") ;
        //below pass authRequest obj so changed base class and auth service String type payload to authService obj
        Response response=auth.createToken(authRequest) ;

        AuthResponse authResponse=response.as(AuthResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(),200);
       // Entire json object will deserialize and print as string
        System.out.println("This will get token as string using POJO : "+authResponse.getToken());
        Assert.assertNotNull(authResponse.getToken());
    }
}
