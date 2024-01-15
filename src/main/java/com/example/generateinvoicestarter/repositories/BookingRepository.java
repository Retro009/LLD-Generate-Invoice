package com.example.generateinvoicestarter.repositories;

import com.example.generateinvoicestarter.models.Booking;

import java.util.List;

public interface BookingRepository {
    Booking save(Booking booking);

    List<Booking> findBookingByCustomerSession(long customerSessionId);
}
