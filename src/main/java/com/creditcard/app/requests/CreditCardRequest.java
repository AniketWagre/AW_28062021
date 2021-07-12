package com.creditcard.app.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRequest {

	private String bank;
	private String cardNumber;
	private String expiryDate;
}