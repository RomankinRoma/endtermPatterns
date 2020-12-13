package behavioral.state;

import java.util.Date;

public class StateImpl implements State {
    String state;


    @Override
    public State created() {
        state="Created."+new Date().getDay()+"."+new Date().getMonth()+"."+new Date().getYear();
        return this;
    }

    @Override
    public State getState() {
        return this;
    }

    @Override
    public State closed() {
        state="Closed."+new Date().getDay()+"."+new Date().getMonth()+"."+new Date().getYear();
        return this;
    }

    @Override
    public String getStateString() {
        return state;
    }
}
