package entity;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
	private int amount;
	private LocalDate date;
	
	public Transaction(int amount, LocalDate date) {
		super();
		this.amount = amount;
		this.date = date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
