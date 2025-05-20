package com.api.models.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor  // Generates no-arg constructor
@AllArgsConstructor
public class CreateBookingResponse {
    private int bookingid;
    private BookingResponse booking;
/*

    // Getters and Setters
    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingResponse getBooking() {
        return booking;
    }

    public void setBooking(BookingResponse booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "CreateBookingResponse{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
*/
}
