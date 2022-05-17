package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает главный сервис Банка
 *
 * @author Vadim Dedeiko
 * @version 1.0
 */

public class BankService {
    /**
     * Содержит список всех пользователей и счетов в банке
     */

    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавлет пользователя в список
     */

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод добавлет пользователя в список
     *
     * @param passport номер паспорта в строковом представлении
     * @param account  аккаунт клиента банка
     */

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> list = users.get(user);
            if (!list.contains(account)) {
                list.add(account);
            }
        }
    }

    /**
     * Метод ищет пользователя по номеру паспорта
     *
     * @param passport номер паспорта в строковом представлении
     * @return возвращает Пользователя
     */

    public User findByPassport(String passport) {
            return users.keySet().stream()
                    .filter(x -> passport.equals(x.getPassport()))
                    .findFirst()
                    .orElse(null);
    }

    /**
     * Метод ищет пользователя по номеру паспорта и реквизитам аккаунта клиента банка
     *
     * @param passport  номер паспорта в строковом представлении
     * @param requisite номер счета в строковом представлении
     * @return возвращает акканут клиента банка
     */

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user).stream()
                    .filter(i -> requisite.equals(i.getRequisite()))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод совершает транзакцию средств по пасспорту и номера счета клиента банка
     *
     * @param srcPassport   номер паспорта пользователя в строковом представлении от которого переводятся средства
     * @param srcRequisite  номер счета в строковом представлении от которого переводятся средства
     * @param destPassport  номер паспорта в строковом представлении кому переводятся средства
     * @param destRequisite номер счета в строковом представлении кому переводятся средства
     * @param amount        сумма денег для перевода
     * @return возвращает {@code true}, если счета найдены и хватает денег на счете и {@code false} если счета не найдены
     * и денег не хватает на счету.
     */

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            return true;
        }
        return false;
    }
}