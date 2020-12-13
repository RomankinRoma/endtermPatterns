package creational;

import behavioral.observer.NationalBankPublisher;

public class Singleton {
    private static Singleton instance;
    public NationalBankPublisher value;

    public Singleton(NationalBankPublisher value) {
        this.value = value;
    }

    public static Singleton getInstance(NationalBankPublisher value) {
        if (instance == null) {
            instance = new Singleton(value);
        }
        return instance;
    }
}
