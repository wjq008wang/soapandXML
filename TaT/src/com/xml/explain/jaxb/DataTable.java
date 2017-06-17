package com.xml.explain.jaxb;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class DataTable {
	@XmlElement
	private String accountNo;
	@XmlElement
	private Date date;
	@XmlElement
	private String game;
	@XmlElement
	private String currency;
	@XmlElement
	private int numberOfGames;
	@XmlElement
	private int numberOfBet;
	@XmlElement
	private Double betAmount;
	@XmlElement
	private Double payoutAmount;
	@XmlElement
	private Double gGRAmount;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	public int getNumberOfBet() {
		return numberOfBet;
	}

	public void setNumberOfBet(int numberOfBet) {
		this.numberOfBet = numberOfBet;
	}

	public Double getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(Double betAmount) {
		this.betAmount = betAmount;
	}

	public Double getPayoutAmount() {
		return payoutAmount;
	}

	public void setPayoutAmount(Double payoutAmount) {
		this.payoutAmount = payoutAmount;
	}

	public Double getgGRAmount() {
		return gGRAmount;
	}

	public void setgGRAmount(Double gGRAmount) {
		this.gGRAmount = gGRAmount;
	}

}
