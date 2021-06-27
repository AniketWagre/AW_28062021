package com.creditcard.app.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.creditcard.app.entity.CreditCard;
import com.creditcard.app.repositories.CreditCardRepository;
import com.creditcard.app.requests.CreditCardRequest;

@Service
public class CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	public List<CreditCard> getAllCreditCards() {
		return creditCardRepository.findAll();
	}

	public List<CreditCardRequest> getAllCreditCardsSortedByExpiry() {
		List<CreditCard> cardEntities = creditCardRepository.findByOrderByExpiryDateDesc();
		List<CreditCardRequest> cardRequests = new ArrayList<>();
		cardEntities.stream().forEach(card -> {
			CreditCardRequest cardrequest = CreditCardRequest.builder().bank(card.getBank())
					.cardNumber(card.getCardNumberString()).expiryDate(card.getExpiryDate()).build();
			cardRequests.add(cardrequest);
		});
		return cardRequests;
	}

	public void addCreditCards(List<CreditCardRequest> cards) {
		if (!CollectionUtils.isEmpty(cards)) {
			List<CreditCard> cardEntities = getCreditCardEntity(cards);
			creditCardRepository.saveAll(cardEntities);
		}
	}

	private List<CreditCard> getCreditCardEntity(List<CreditCardRequest> cards) {
		List<CreditCard> cardEntities = new ArrayList<>();
		cards.stream().forEach(card -> {
			CreditCard cardEntity = CreditCard.builder().bank(card.getBank())
					.cardNumber(Long.parseLong(card.getCardNumber())).expiryDate(getDate(card.getExpiryDate())).build();
			cardEntities.add(cardEntity);
		});

		return cardEntities;
	}

	private Date getDate(String dateString) {
		DateFormat format = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
