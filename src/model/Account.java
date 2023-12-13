package model;

import java.math.BigDecimal;

public class Account {
    public Accountinfo accountinfo;
    Address addr;
    int ph;
    BigDecimal balance;
    int aadhar;


    public Account(Accountinfo accountinfo, Address addr, int ph, BigDecimal balance) {
        this.accountinfo = accountinfo;
        this.addr = addr;
        this.ph = ph;
        this.balance = balance;
    }

    public Account(Accountinfo accountinfo, Address addr, int ph, BigDecimal balance, int aadhar) {
        this.accountinfo = accountinfo;
        this.addr = addr;
        this.ph = ph;
        this.balance = balance;
        this.aadhar = aadhar;
    }

    public void setAadhar(int aadhar) {
        this.aadhar = aadhar;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void withdraw(double amount){
        synchronized (this) {
            if(balance.compareTo(BigDecimal.valueOf(amount)) >= 0) {
                balance = balance.subtract(BigDecimal.valueOf(amount));
                System.out.println("Remaining balance: " + balance);
            } else {
                System.out.println("Not enough balance");
            }
        }
    }

    public void penalty() {
        synchronized (this) {
            balance = balance.subtract(BigDecimal.valueOf(500));
        }
    }
    public void interest(){
        synchronized (this) {
            balance = balance.add(balance.multiply(BigDecimal.valueOf(0.06/365)));
        }
    }
    public void isKycDone(){
        if(((Integer)aadhar).equals(0)) {
            System.out.println("Not done");
        } else {
            System.out.println("Done");
        }
    }

    public void deposit(double amount){
//        synchronized (this) {
        balance = balance.add(BigDecimal.valueOf(amount));
        System.out.println("Updated balance: " + balance);
//        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountinfo=" + accountinfo +
                ", addr=" + addr +
                ", ph=" + ph +
                ", balance=" + balance +
                ", aadhar=" + aadhar +
                '}';
    }
}