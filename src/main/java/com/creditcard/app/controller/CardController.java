package com.creditcard.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.app.requests.CreditCardRequest;
import com.creditcard.app.services.CreditCardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/credit-card")
public class CardController {

	@Autowired
	private CreditCardService creditCardService;

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CreditCardRequest> getEmployees() {
		return creditCardService.getAllCreditCardsSortedByExpiry();
	}

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addEmployees(@RequestBody List<CreditCardRequest> cards) {
		try {
			creditCardService.addCreditCards(cards);
			return HttpStatus.OK.getReasonPhrase();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
		}
	}
}
