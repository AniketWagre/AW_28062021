package com.creditcard.app.entity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "credit_card")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bank")
	private String bank;

	@Column(name = "card_number")
	private Long cardNumber;

	@Column(name = "expiry_date")
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	public String getCardNumberString() {
		String newcardNumber = null;
		if (null != cardNumber) {
			newcardNumber = String.valueOf(cardNumber);
			newcardNumber = newcardNumber.substring(0, 4).concat("-XXXX-XXXX-XXXX");
		}
		return newcardNumber;
	}

	public String getExpiryDate() {
		String monthYear = null;
		if (null != expiryDate) {
			Format monthYearFormtter = new SimpleDateFormat("MMM-yyyy");
			monthYear = monthYearFormtter.format(expiryDate);
		}
		return monthYear;
	}
}