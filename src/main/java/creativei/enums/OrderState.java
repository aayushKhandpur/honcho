package creativei.enums;

/**
 * Created by Administrator on 26-03-2017.
 */
public enum OrderState {
    PLACED("PLACED"),
    INITIATE("INITIATE"),
    PAY("PAY"),
    COMPLETE("COMPLETE"),
    CANCEL("CANCEL"),
    DELIVERY("DELIVERY");

    private final String value;

    private OrderState(final String text) {
        this.value = text;
    }
    public String toString() {
        return value;
    }
}
