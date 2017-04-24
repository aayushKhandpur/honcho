package creativei.enums;

/**
 * Created by Administrator on 26-03-2017.
 */
public enum DishType {
    VEG("veg"),
    NON_VEG("non-veg");

    private final String value;

    private DishType(final String text) {
        this.value = text;
    }
    public String toString() {
        return value;
    }
}
