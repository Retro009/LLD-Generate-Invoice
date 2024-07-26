package com.example.generateinvoices.services;

import com.example.generateinvoices.exceptions.CustomerSessionNotFoundException;
import com.example.generateinvoices.models.*;
import com.example.generateinvoices.repositories.BookingRepository;
import com.example.generateinvoices.repositories.CustomerSessionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingServiceImpl implements BookingService{
    private BookingRepository bookingRepository;
    private CustomerSessionRepository customerSessionRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, CustomerSessionRepository customerSessionRepository){
        this.bookingRepository = bookingRepository;
        this.customerSessionRepository = customerSessionRepository;
    }
    @Override
    public Invoice generateInvoice(long userId) throws CustomerSessionNotFoundException {
        CustomerSession customerSession = customerSessionRepository.findActiveCustomerSessionByUserId(userId).orElseThrow(()-> new CustomerSessionNotFoundException("No Active Customer Session Found"));
        List<Booking> bookings = bookingRepository.findBookingByCustomerSession(customerSession.getId());
        Map<Room,Integer> bookedRooms = new HashMap<>();
        double totalAmount = 0;
        double gst = 0;
        double serviceCharge = 0;
        for(Booking booking: bookings){
            for(Map.Entry<Room,Integer> entry:booking.getBookedRooms().entrySet()){
                Room room = entry.getKey();
                int quantity = entry.getValue();
                totalAmount += room.getPrice() * quantity;
                bookedRooms.put(room, quantity);
            }
        }
        gst = totalAmount * 0.18;
        serviceCharge = totalAmount * 0.1;
        Invoice invoice = new Invoice();
        invoice.setBookedRooms(bookedRooms);
        invoice.setGst(gst);
        invoice.setServiceCharge(serviceCharge);
        invoice.setTotalAmount(totalAmount);
        invoice.setId(customerSession.getId());
        customerSession.setCustomerSessionStatus(CustomerSessionStatus.ENDED);
        return invoice;
    }
}
