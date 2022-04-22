package com.example.demo.controller;

import com.example.demo.entity.request.CustomerChoiceRequest;
import com.example.demo.entity.response.CustomerChoiceResponse;
import com.example.demo.service.BuyingTrackBookingService;
import com.example.demo.service.CustomerChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerChoiceController {

    @Autowired
    private CustomerChoiceService cutomerChoiceService;
    @Autowired
    private BuyingTrackBookingService buyingTrackBookingService;

    @PostMapping("customerchoice")
    public CustomerChoiceResponse createCustomerChoice(@RequestBody @Valid CustomerChoiceRequest customerChoiceRequest) {
        buyingTrackBookingService.assignBuyingToBooking(customerChoiceRequest.getAccid(), customerChoiceRequest.getStartBookingPeriod()
                , customerChoiceRequest.getEndBookingPeriod(), customerChoiceRequest.getBuyingTrack());
        CustomerChoiceResponse customerChoiceResponse = cutomerChoiceService.buildCustomerChoiceResponse(customerChoiceRequest);

        return customerChoiceResponse;
    }


}
