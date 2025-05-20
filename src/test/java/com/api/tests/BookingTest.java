package com.api.tests;

import com.api.base.BookingService;
import com.api.models.request.BookingRequest;
import com.api.models.BookingDates;
import com.api.models.response.BookingResponse;
import com.api.models.response.CreateBookingResponse;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.api.models.BookingDates;


import static com.api.models.request.BookingRequest.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
@Listeners(com.api.listener.TestListener.class)
public class BookingTest {
    BookingService bookingService = new BookingService();
    @Test(description = "Get All booking id", groups = {"booking"})
    public void getBookingId() {
        Response response=bookingService.getBookingIds();
        System.out.println("All Bookings :\n" + response.asPrettyString());
        assertEquals(response.getStatusCode(), 200, "Expected HTTP 200");
    }
    @Test(description = "Get booking id with firstname and lastname",groups = {"booking"})
    public void getBookingIdByName() {
        Response response=bookingService.getBookingIds("Sally","Brown");
        System.out.println("Bookings By name:\n" + response.asPrettyString());
        assertEquals(response.getStatusCode(), 200, "Expected HTTP 200");
    }
    @Test(description = "Create Booking usint post",groups = {"booking"})
    public void createBookingTest(){
       // BookingRequest.BookingDates bookingDates=new BookingRequest.BookingDates("2025-06-01","2025-06-06");
        //  reused short form  in setBookingdates method
        BookingRequest bookingRequest = BookingRequest.builder()
                .firstname("John")
                .lastname("Doe")
                .totalprice(150)
                .depositpaid(true)
                .bookingdates(new BookingDates("2025-06-01", "2025-06-06"))
                .additionalneeds("Breakfast")
                .build();

        Response response=bookingService.createBooking(bookingRequest);
        System.out.println("Bookings By name:\n" + response.asPrettyString());
        //deserialize response-response.as(ClassName.class) converts JSON response to your Java class.
        //Converts the HTTP JSON response into a Java CreateBookingResponse object.Using response.as(Class) is a neat RestAssured shortcut to deserialize JSON into POJOs.
        //It saves you from writing explicit ObjectMapper code.
        CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
        assertEquals(response.getStatusCode(), 200);  // Status code check
        //assertEquals(createBookingResponse.getBookingid(), 1234);

        BookingResponse booking = createBookingResponse.getBooking();
        assertEquals(booking.getFirstname(), "John");
        assertEquals(booking.getLastname(), "Doe");
        //assertEquals(booking.getTotalprice(), 150);
        assertTrue(booking.isDepositpaid());

        BookingDates bookingDates = booking.getBookingdates();
      //  assertEquals(bookingDates.getCheckin(), "2023-05-01");
       // assertEquals(bookingDates.getCheckout(), "2023-05-05");
        assertEquals(booking.getAdditionalneeds(), "Breakfast");

    }

}
