package com.api.base;

import com.api.models.request.AuthRequest;
import com.api.models.request.BookingRequest;
import io.restassured.response.Response;

import java.util.HashMap;

public class BookingService extends BaseService{
    private static final String BASE_PATH="/booking";
    public Response getBookingIds(){
        return getRequest(BASE_PATH,null);
    }
    public Response getBookingIds(String fname,String lname){
        HashMap<String,String> param= new HashMap<>();
        param.put("fname",fname);
        param.put("lname",lname);
        return getRequestWithQueryParams(BASE_PATH,param);
    }
   public Response createBooking(BookingRequest bookingRequest){
       return postRequest(bookingRequest,BASE_PATH);



   }


}
