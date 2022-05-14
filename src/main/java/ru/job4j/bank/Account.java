package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель Банковского счета, с его реквизитами и балансом на счету.
 */

public class Account {

    /**
     * Поле класса реквизиты банка принимает строковый параметр
     */
    private String requisite;
    /**
     * Поле класса определяет баланс на счету банка в строковом выражении
     */
    private double balance;

    /**
     * Конструктор класса
     *
     * @param requisite номер счета в строковом выражении
     * @param balance   количетство средств на счете клиента банка
     */

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Метод позволяет узнать реквизиты счета
     *
     * @return возвращает реквизиты в строковом представлении
     */

    public String getRequisite() {
        return requisite;
    }

    /**
     * Метод позволяет внести в счет в банке его реквизиты
     */

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Метод позволяет получить информацию о балансе на счету
     *
     * @return возвращает количество средств на счету в банке
     */

    public double getBalance() {
        return balance;
    }

    /**
     * Метод позволяет внести количество денег в баланс счета
     */

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}