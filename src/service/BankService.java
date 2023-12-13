package service;
import model.Account;
import repository.BankRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class BankService {
    BankRepository bankRepository = new BankRepository();
    public BigDecimal getBalance(int accNo) {
        return bankRepository.accountMap.get(accNo).getBalance();
    }


    public void withdraw(int accNo, double amount) {
        bankRepository.accountMap.get(accNo).withdraw(amount);
    }

    public void deposit(int accNo, double amount) {
        bankRepository.accountMap.get(accNo).deposit(amount);
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
        int MINUTES = 1;
        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() { // Function runs every MINUTES minutes.
                System.out.println(bankRepository.accountMap.values());
                for(Account a: bankRepository.accountMap.values()) {
                    a.interest();
                }
            }
        }, 0, 1000 * 6 * MINUTES);
    }
}
