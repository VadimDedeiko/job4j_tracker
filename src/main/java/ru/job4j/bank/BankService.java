package ru.job4j.bank;

import java.util.*;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        if (!users.containsKey(user)) {
            users.put(user, new ArrayList<Account>());
        }
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        List<Account> list = users.get(user);
        if (!list.contains(account)) {
            list.add(account);
        }
    }

    public User findByPassport(String passport) {
        User usr = null;
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                usr = user;
            }
        }
        return usr;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account accountUsr = null;
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> list = users.get(user);
            for (Account account : list) {
                if (requisite.equals(account.getRequisite())) {
                    accountUsr = account;
                }
            }
        }
        return accountUsr;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null || amount >= srcAccount.getBalance()) {
            double srcBalance = srcAccount.getBalance() - amount;
            double destBalance = destAccount.getBalance() + amount;
            User srcUser = findByPassport(srcPassport);
            User destUser = findByPassport(destPassport);
            List<Account> accaaaa = users.get(srcUser);
            List<Account> qwer = users.get(destUser);
           if (accaaaa.contains(srcAccount)) {
               srcAccount.setBalance(srcBalance);
           }
            if (qwer.contains(destAccount)) {
                destAccount.setBalance(destBalance);
            }
            return true;
        }
        return rsl;
    }
}