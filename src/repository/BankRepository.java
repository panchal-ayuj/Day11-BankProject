package repository;

import model.Account;
import model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankRepository {
    public static Map<Integer, Account> accountMap = new HashMap<>();

    public static Map<Integer, List<Transaction>> transactions = new HashMap<>();
}
