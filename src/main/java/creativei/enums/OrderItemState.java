package creativei.enums;

/**
 * Created by Aayush on 5/29/2017.
 */
public enum OrderItemState {
    ADDED("ADDED"),
    PREPARING("PREPARING"),
    READY("READY"),
    CANCEL("CANCEL");

    private final String value;

    private OrderItemState(final String text) {
        this.value = text;
    }
    public String toString() {
        return value;
    }

}
