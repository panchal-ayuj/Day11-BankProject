package manager;

import model.Account;
import service.BankService;

import java.math.BigDecimal;
import java.util.List;

public class BankManager {
    BankService bankService = new BankService();

    public BigDecimal getBalance(int accNo){
        return bankService.getBalance(accNo);
    }

    public void withdraw(int accNo, double amount){
        bankService.withdraw(accNo, amount);
    }

    public void deposit(int accNo, double amount){
        bankService.deposit(accNo, amount);
    }

    public List<Account> getAllZeroBalanceAccount(){
        return bankService.getAllZeroBalanceAccount();
    }

    public void addAccount(Account acc){
        bankService.addAccount(acc);
    }

    public void isKycDone(int accNo){
        bankService.isKycDone(accNo);
    }

    public void applyLevyCharge(){
        bankService.applyLevyCharge();
    }

    public void applyInterest(){
        bankService.applyInterest();
    }
}
