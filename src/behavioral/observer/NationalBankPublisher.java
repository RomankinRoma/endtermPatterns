package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class NationalBankPublisher implements Publisher {

    private List<Subscriber> subscriberList;
    private Boolean currencyChanged;
    private double KZUSCurrency=300;
    private double RUUSDCurrency=60;
    private double KZRUCurrency=5;
    private double lKZUSCurrency=300;
    private double lRUUSDCurrency=60;
    private double lKZRUCurrency=5;

    @Override
    public void addSubscriber(Subscriber b) {
        if (subscriberList==null)
            subscriberList=new ArrayList<>();
        subscriberList.add(b);
    }

    @Override
    public void removeSubscriber(Subscriber b) {
    subscriberList.remove(b);
    }

    @Override
    public void notifySubscriber() {
        for (Subscriber s:subscriberList             ) {
            s.update(this);
        }
    }

    public Boolean getCurrencyChanged() {
        return currencyChanged;
    }

    public void setCurrencyChanged(Boolean currencyChanged) {
        this.currencyChanged = currencyChanged;
        notifySubscriber();
    }

    public double getKZUSCurrency() {
        return KZUSCurrency;
    }

    public void setKZUSCurrency(double KZUSCurrency) {
        this.lKZUSCurrency=getKZUSCurrency();
        this.KZUSCurrency = KZUSCurrency;
        notifySubscriber();
    }

    public double getRUUSDCurrency() {
        return RUUSDCurrency;
    }

    public void setRUUSDCurrency(double RUUSDCurrency) {
        this.lRUUSDCurrency=getRUUSDCurrency();
        this.RUUSDCurrency = RUUSDCurrency;
        notifySubscriber();
    }

    public double getKZRUCurrency() {
        return KZRUCurrency;
    }

    public void setKZRUCurrency(double KZRUCurrency) {
        this.lKZRUCurrency=getKZRUCurrency();
        this.KZRUCurrency = KZRUCurrency;
        notifySubscriber();
    }

    public double getlKZUSCurrency() {
        return lKZUSCurrency;
    }

    public double getlRUUSDCurrency() {
        return lRUUSDCurrency;
    }

    public double getlKZRUCurrency() {
        return lKZRUCurrency;
    }
}
