package creativei.enums;

/**
 * Created by Administrator on 26-03-2017.
 */
public enum OrderState {
    PLACED("placed"),
    PREPARED("prepared"),
    SERVED("served"),
    COMPLETE("complete"),
    PAYMENT("payment"),
    CLOSED("closed"),
    CANCELLED("cancelled");

    private final String value;

    private OrderState(final String text) {
        this.value = text;
    }
    public String toString() {
        return value;
    }
}
