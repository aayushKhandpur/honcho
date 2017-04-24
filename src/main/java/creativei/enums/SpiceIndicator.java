package creativei.enums;

/**
 * Created by Administrator on 26-03-2017.
 */
public enum SpiceIndicator {
    SPICY("spicy"),
    NON_SPICY("non-spicy");

    private final String value;

    private SpiceIndicator(final String text) {
        this.value = text;
    }
    public String toString() {
        return value;
    }
}
