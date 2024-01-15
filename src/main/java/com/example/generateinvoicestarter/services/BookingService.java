package com.example.generateinvoicestarter.services;

import com.example.generateinvoicestarter.exceptions.CustomerSessionNotFoundException;
import com.example.generateinvoicestarter.models.Invoice;

public interface BookingService {
    Invoice generateInvoice(long userId) throws CustomerSessionNotFoundException;
}
