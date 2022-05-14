package ru.job4j.bank;

import java.util.Objects;
/**
 * Класс описывает объект User (Пользователя),
 * который имеет пасспорт и имя (поля passport, username)
 *
 * @author Vadim Dedeyko
 * @version 1.0
 */

public class User {
    /**
     * Поле passport тип String
     */
    private String passport;
    /**
     * Поле username тип String
     */
    private String username;

    /**
     * Конструктор принимает аргументы
     * пасспорт пользователя в строковом выражении
     * и имя пользователя в строковом выражении
     */

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Метод позволяет получить номер пасспорта
     *
     * @return возвращает номер паспорта в строковом выражении или null, если паспорта нет.
     */

    public String getPassport() {
        return passport;
    }

    /**
     * Метод позволяет назначить номер паспорта объекту типа User
     *
     * @param passport номер паспорта в строковом выражении
     */

    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Метод позволяет получить значение name объекта типа User
     *
     * @return возвращает имя Пользователя
     */

    public String getUsername() {
        return username;
    }

    /**
     * Метод позволяет дать имя объекту типа User
     *
     * @return возвращает имя Пользователя
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Переопределяет метод equals(Object o)
     *
     * @return возвращает имя Пользователя
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}