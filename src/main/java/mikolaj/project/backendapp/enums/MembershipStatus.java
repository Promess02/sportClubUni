package mikolaj.project.backendapp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MembershipStatus {
    ACTIVE("Active"), EXPIRED("Expired"), NEVER_ACQUIRED("Never-Acquired");

    private final String name;

    MembershipStatus(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static MembershipStatus fromValue(String value) {
        if(value== null) return null;
        for (MembershipStatus membershipStatus : MembershipStatus.values()) {
            if (membershipStatus.name.equalsIgnoreCase(value)) {
                return membershipStatus;
            }
        }
        throw new IllegalArgumentException("MembershipStatus: " + value);
    }

    @Override
    public String toString() {
        return name;
    }
}
