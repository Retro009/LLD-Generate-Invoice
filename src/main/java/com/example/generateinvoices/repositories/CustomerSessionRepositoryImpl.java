package com.example.generateinvoices.repositories;

import com.example.generateinvoices.models.CustomerSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerSessionRepositoryImpl implements CustomerSessionRepository{
    private List<CustomerSession> customerSessions = new ArrayList<>();
    private static long idCounter = 0;
    @Override
    public CustomerSession save(CustomerSession customerSession) {
        if(customerSession.getId()==0)
            customerSession.setId(++idCounter);
        customerSessions.add(customerSession);
        return customerSession;
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        return customerSessions.stream().filter(customerSession -> customerSession.getUser().getId()==userId && customerSession.isActive()).findFirst();
    }
}
