package com.api.base;
import static io.restassured.RestAssured.*;

import com.api.filter.LoggingFilter;
import com.api.models.request.AuthRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class BaseService {

    static
    {
        RestAssured.filters(new LoggingFilter());
    }

    //CONSTANT VARIABLE
    private static final String BASE_URL="https://restful-booker.herokuapp.com";
    private RequestSpecification requestSpecification;
    //initializes the requestSpecification with the base URI, so you don’t repeat  write given().baseUriin each method.
    public BaseService(){
        requestSpecification= given().baseUri(BASE_URL);
    }
    //private Not accessible anywhere outside BaseService, public Accessible from anywhere (maybe unnecessarily exposed)
    //**protected** → Just right for a reusable base class
    //standardizes how you send POST requests.Reduces duplicate code in every service class.
    //Automatically applies base URL and content type

//    protected Response postRequest(AuthRequest payload, String endPoint){
//       return requestSpecification.contentType(ContentType.JSON).body(payload).post(endPoint);
//    }

//in above method changed AuthRequest to Object (polymorphism), so that it handles all post request,
// java rule did u create object of AutHRequest if yes thet you cannot pass it as ref, u need to pass its object
    protected Response postRequest(Object payload, String endPoint){
        return requestSpecification.contentType(ContentType.JSON).body(payload).post(endPoint);
    }
//overloading above method- to handle cases where our api have diff url
    protected Response postRequest(String baseUrl,Object payload, String endPoint){
        return requestSpecification.baseUri(baseUrl).contentType(ContentType.JSON).body(payload).post(endPoint);
    }

    protected Response getRequest(String endPoint,String id){
        if(id!=null && !id.isEmpty()){
            return requestSpecification.get(endPoint+"/"+id);
        }
        return requestSpecification.get(endPoint);
    }

    protected Response getRequestWithQueryParams(String endPoint, Map<String, String> queryParams){
        return requestSpecification.queryParams(queryParams).get(endPoint);
    }
}
