package com.example.generateinvoices.controllers;

import com.example.generateinvoices.dtos.GenerateInvoiceRequestDto;
import com.example.generateinvoices.dtos.GenerateInvoiceResponseDto;
import com.example.generateinvoices.dtos.ResponseStatus;
import com.example.generateinvoices.exceptions.CustomerSessionNotFoundException;
import com.example.generateinvoices.services.BookingService;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public GenerateInvoiceResponseDto generateInvoice(GenerateInvoiceRequestDto requestDto) {
        GenerateInvoiceResponseDto responseDto = new GenerateInvoiceResponseDto();
        try{
            responseDto.setInvoice(bookingService.generateInvoice(requestDto.getUserId()));
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch(CustomerSessionNotFoundException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
