package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Transaction {
    int id;
    int accNo;
    BigDecimal Amount;
    public LocalDate dateTimeOfTransaction;
    String type;
    public BigDecimal balanceAsOn;


    public Transaction(int id, int accNo, BigDecimal amount, String type, BigDecimal balanceAsOn, LocalDate dateTimeOfTransaction) {
        this.id = id;
        this.accNo = accNo;
        Amount = amount;
        this.type = type;
        this.balanceAsOn = balanceAsOn;
        this.dateTimeOfTransaction = dateTimeOfTransaction;
    }
}
