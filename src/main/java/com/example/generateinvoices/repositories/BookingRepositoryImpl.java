package com.example.generateinvoices.repositories;

import com.example.generateinvoices.models.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingRepositoryImpl implements BookingRepository{
    private List<Booking> bookings = new ArrayList<>();
    private static long idCounter = 0;
    @Override
    public Booking save(Booking booking) {
        if(booking.getId()==0)
            booking.setId(++idCounter);
        bookings.add(booking);
        return booking;
    }

    @Override
    public List<Booking> findBookingByCustomerSession(long customerSessionId) {
        return bookings.stream().filter(booking -> booking.getCustomerSession().getId()==customerSessionId).collect(Collectors.toList());
    }
}
