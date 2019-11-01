package bootcamp.dao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import bootcamp.entity.Account;

/**
 * @author Yosua_S
 * 
 */
public class AccountDAO {

    private static final String CSV_FILE = "./users.csv";
	
	private static Map<String, Account> accountList = null;
	
	private static Account activeAccount = null;
	
	private AccountDAO() {
		readData();
	}
	
	/**
	 * read data from CSV and assign it to Map
	 */
	private static void readData(){
		accountList = new HashMap<>();
		CSVParser records = readDataFromCSV();

        for (CSVRecord csvRecord : records) {
            String pin = csvRecord.get("PIN");
            String name = csvRecord.get("Name");
            String balance = csvRecord.get("Balance");
            String accountNumber = csvRecord.get("accountNumber");
            
            Account account = new Account(name, pin, Integer.parseInt(balance), accountNumber);
            
            if(!validateDuplicate(accountList, account)){
            	System.out.println("There can't be 2 different accounts with the same Account Number");
            	return;
            }
            
            if(!validate(accountList, accountNumber)){
            	System.out.println("There can't be duplicated records");
            	return;
            }
            
            accountList.put(accountNumber, account);
        }
	}
	
	/**
	 * @param accountList
	 * @param accountNumber
	 * @return
	 * Validate CSV data to avoid duplicate account number
	 */
	private static boolean validate(Map<String, Account> accountList, String accountNumber){
		boolean isValid = true;
		Account account = accountList.get(accountNumber);
        if(account != null) {
        	isValid = false;
        }
        return isValid;
	}
	
	/**
	 * @param accountList
	 * @param account
	 * @return
	 * Validate CSV data to reduce duplicate data
	 */
	private static boolean validateDuplicate(Map<String, Account> accountList, Account account){
		boolean isValid = true;
		for (Map.Entry<String, Account> entry : accountList.entrySet()) {
			if(entry.getValue().equals(account)){
				isValid = false;
			}
		}
        return isValid;
	}
	
	
	/**
	 * Persist the Map
	 */
	private static void persistData(){

    	List<Account> data = accountList.values().stream()
    			.collect(Collectors.toList());
		saveDataToCSV(data);
		readData();
	}
	
	
	/**
	 * @return list of the account in Map
	 */
	public static Map<String, Account> getAccounts(){
		if(accountList == null) {
			new AccountDAO();
		}
		return accountList;
	}
	
	/**
	 * @param account save account into Map and Persist it into CSV file
	 */
	public static void saveAccount(Account account){
		Account sample = accountByAccountNumber(account.getAccountNumber());
		if(sample != null) {
			accountList.put(account.getAccountNumber(), account);
			persistData();
		}
	}
	
	
	/**
	 * @param key 
	 * @return an object filter by the key Map
	 */
	public static Account accountByAccountNumber(String key){
		readData();
		Account res = accountList.get(key);
		if(res != null) {
			return res;
		}
		return null;
	}
	
	/**
	 * @param acc set the current user
	 */
	public static void setActiveUser(Account acc){
		activeAccount = acc;
	}
	
	
	/**
	 * @return the current user
	 */
	public static Account getActiveUser(){
		readData();
		return activeAccount;
	}

    
	
    /**
     * @return read the accounts.csv file
     */
    private static CSVParser readDataFromCSV() {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
            
            return csvParser;
        } catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    
    /**
     * @param data persist the list of account into CSV file
     */
    private static void saveDataToCSV(List<Account> data) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(CSV_FILE));

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("Name", "PIN", "Balance", "AccountNumber"));
            
            data.forEach(d->{
                try {
					csvPrinter.printRecord(d.getName(), d.getPin(), d.getBalance(), d.getAccountNumber());
				} catch (Exception e) {
					e.printStackTrace();
				}
            });

            csvPrinter.flush();   
	    } catch (IOException e) {         
			e.printStackTrace();
	    }
    }

}
