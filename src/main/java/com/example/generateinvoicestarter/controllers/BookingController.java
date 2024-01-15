package com.example.generateinvoicestarter.controllers;

import com.example.generateinvoicestarter.dtos.GenerateInvoiceRequestDto;
import com.example.generateinvoicestarter.dtos.GenerateInvoiceResponseDto;
import com.example.generateinvoicestarter.dtos.ResponseStatus;
import com.example.generateinvoicestarter.models.Invoice;
import com.example.generateinvoicestarter.services.BookingService;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public GenerateInvoiceResponseDto generateInvoice(GenerateInvoiceRequestDto requestDto) {
        return null;
    }
}
