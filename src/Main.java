import model.Account;
import model.Transaction;
import repository.BankRepository;
import manager.BankManager;
import service.BankService;
import model.Accountinfo;
import model.Address;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

public class Main {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Account a1 = new Account(new Accountinfo(1, sdf.parse("12/02/2023"), "A"), new Address("Ahm", "Guj", 1), 123, BigDecimal.valueOf(0));
        Account a2 = new Account(new Accountinfo(2, sdf.parse("13/01/2021"), "B"), new Address("Sur", "Guj", 2), 456, BigDecimal.valueOf(0));
        Account a3 = new Account(new Accountinfo(3, sdf.parse("09/06/2020"), "C"), new Address("Vad", "Guj", 3), 789, BigDecimal.valueOf(0));

        BankManager bankManager = new BankManager();
        bankManager.addAccount(a1);
        bankManager.addAccount(a2);
        bankManager.addAccount(a3);
        bankManager.deposit(1, 100);
        bankManager.deposit(2, 30);
        bankManager.deposit(3,70);
        bankManager.withdraw(1, 20);
        bankManager.withdraw(2, 30);
        bankManager.withdraw(3,70);

        bankManager.getBalance(1);
        bankManager.getBalance(2);
        bankManager.getBalance(3);

        bankManager.applyLevyCharge();
        bankManager.applyInterest();
    }
}