package service;
import model.Account;
import model.Transaction;
import repository.BankRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class BankService {
    BankRepository bankRepository = new BankRepository();
    public BigDecimal getBalance(int accNo) {
        return bankRepository.accountMap.get(accNo).getBalance();
    }

    Random random = new Random();
    public void withdraw(int accNo, double amount) {
        bankRepository.accountMap.get(accNo).withdraw(amount);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String text = date.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        if(bankRepository.transactions.containsKey(accNo)){
            bankRepository.transactions.get(accNo).add(new Transaction(random.nextInt(), accNo, BigDecimal.valueOf(amount), "Debit", bankRepository.accountMap.get(accNo).getBalance(), parsedDate.minusDays(3)));
        } else {
            bankRepository.transactions.put(accNo, new ArrayList<Transaction>(Arrays.asList(new Transaction(random.nextInt(), accNo, BigDecimal.valueOf(amount), "Debit", bankRepository.accountMap.get(accNo).getBalance(), parsedDate.minusDays(3)))));
        }
    }

    public void deposit(int accNo, double amount) {
        bankRepository.accountMap.get(accNo).deposit(amount);

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String text = date.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        if(bankRepository.transactions.containsKey(accNo)){
            bankRepository.transactions.get(accNo).add(new Transaction(random.nextInt(), accNo, BigDecimal.valueOf(amount), "Credit", bankRepository.accountMap.get(accNo).getBalance(), parsedDate.minusDays(4) ));
        } else {
            bankRepository.transactions.put(accNo, new ArrayList<Transaction>(Arrays.asList(new Transaction(random.nextInt(), accNo, BigDecimal.valueOf(amount), "Credit", bankRepository.accountMap.get(accNo).getBalance(), parsedDate.minusDays(4)))));
        }
    }

    public List<Account> getAllZeroBalanceAccount() {
        return bankRepository.accountMap.values().stream().filter(account -> account.getBalance().compareTo(BigDecimal.valueOf(0)) <= 0).collect(Collectors.toList());
    }

    public void addAccount(Account acc) {
        bankRepository.accountMap.put(acc.accountinfo.no, acc);
    }

    public void isKycDone(int accNo) {
        bankRepository.accountMap.get(accNo).isKycDone();
    }

    public void applyLevyCharge() {
        int MINUTES = 1; // The delay in minutes
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() { // Function runs every MINUTES minutes.
                List<Account> accountsWithZeroBalance = getAllZeroBalanceAccount();
                System.out.println(accountsWithZeroBalance);
                for(Account a: accountsWithZeroBalance) {
                    a.penalty();
                }
            }
        }, 0, 1000 * 6 * MINUTES);
    }

    public void applyInterest() {
        int MINUTES = 30;
        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() { // Function runs every MINUTES minutes.
                for (Integer accNo : bankRepository.transactions.keySet()){
                    BigDecimal interest = BigDecimal.valueOf(0);
                    LocalDate date = LocalDate.now();
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                    String text = date.format(formatters);
                    LocalDate endDate = LocalDate.parse(text, formatters);
                    LocalDate startDate = endDate.minusDays(30);
                    BigDecimal prevBal = BigDecimal.valueOf(0);
                    for(LocalDate date1 = startDate; date1.isBefore(endDate); date1 = date1.plusDays(1)){
                        LocalDate finalDate = date1;
                        LocalDate finalDate1 = date1;
                        if(bankRepository.transactions.get(accNo).stream().filter(transaction -> transaction.dateTimeOfTransaction.equals(finalDate1)).collect(Collectors.toList()).size()!= 0){
                            prevBal = bankRepository.transactions.get(accNo).stream().filter(transaction -> transaction.dateTimeOfTransaction.equals(finalDate)).reduce((first, second) -> second).get().balanceAsOn;
                        }
                        interest = interest.add(prevBal.multiply(BigDecimal.valueOf(0.06/365)));
                    }
                    bankRepository.accountMap.get(accNo).deposit((interest.doubleValue()));
                }
            }
        }, 0, 1000 * MINUTES);
    }
}
