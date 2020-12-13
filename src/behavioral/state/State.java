package behavioral.state;

public interface State {
    State created();
    State getState();
    State closed();
    String getStateString();
}
