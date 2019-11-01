package bootcamp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yosua_S
 *
 */
public class Account {
	
	private String name;
	private String pin;
	private Integer balance;
	private String accountNumber;
	private List<Transaction> transactionList;
	public Account(String name, String pin, Integer balance, String accountNumber) {
		super();
		this.name = name;
		this.pin = pin;
		this.balance = balance;
		this.accountNumber = accountNumber;
		transactionList = new ArrayList();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public List<Transaction> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	public void addTransaction(Transaction transaction){
		transactionList.add(transaction);
	}
	public Transaction getLastTransaction(){
		if(!transactionList.isEmpty()){
			return transactionList.get(transactionList.size()-1);
		}
		return new Transaction(0, LocalDate.now());
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", pin=" + pin + ", balance=" + balance + ", accountNumber=" + accountNumber
				+ ", transactionList=" + transactionList + "]";
	}
}
