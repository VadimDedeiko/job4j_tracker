package ru.job4j.oop;

public class Error {

    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void print() {
        System.out.println(active);
        System.out.println(status);
        System.out.println(message);
    }

    public static void main(String[] args) {
        Error error = new Error(true, -1, "Error");
        Error er = new Error(false, 0, "Process finished with exit code 0");
        Error er2 = new Error();
        error.print();
        er.print();
        er2.print();
    }
}
