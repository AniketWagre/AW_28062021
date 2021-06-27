package com.creditcard.app.models;

import java.util.List;

import com.creditcard.app.requests.CreditCardRequest;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;

@Builder
@JsonSerialize
public class CreditCardResponse {
	private int status;
	private String message;
	private List<CreditCardRequest> cardrequests;
}