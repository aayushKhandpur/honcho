package creativei.enums;

/**
 * Created by Aayush on 7/24/2017.
 */
public enum UserRole {
    ADMIN("ADMIN"),
    KITCHEN("KITCHEN"),
    SERVICE("SERVICE"),
    CANCEL("MANAGEMENT");

    private final String value;

    private UserRole(final String text) {
        this.value = text;
    }
    public String toString() {
        return value;
    }
}
