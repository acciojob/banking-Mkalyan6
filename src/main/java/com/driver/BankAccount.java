package com.driver;
import java.lang.Exception;



public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
      this.name=name;
      this.balance=balance;
      this.minBalance=minBalance;
    }
//   Getters

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(sum>(9*digits)) throw new Exception("Account Number can not be generated");
        StringBuilder accountNum=new StringBuilder();
        while(digits>0){
            if(sum>=9){
//                allocate 9 to digits place and decrement sum
                accountNum.append(9);
                sum-=9;
            }else {
                accountNum.append(sum);
                sum = 0;
            }
             digits--;
        }

        return accountNum.toString();
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance+=amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(getBalance()-amount<minBalance){
            throw new Exception("Insufficient Balance");
        }
        setBalance(getBalance()-amount) ;

    }

}