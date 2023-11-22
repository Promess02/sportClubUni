package mikolaj.project.backendapp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sport{
    TENNIS("Tennis"), KARATE("Karate"), JIU_JITSU("Jiu-jitsu"), BOXING("boxing"),
    KICKBOXING("kickboxing"), BASKETBALL("Basketball"), MUAY_THAI("Muay-Thai"), MMA("MMA");
    private final String name;

    Sport(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static Sport fromValue(String value) {
        if(value== null) return null;
        for (Sport sport : Sport.values()) {
            if (sport.name.equalsIgnoreCase(value)) {
                return sport;
            }
        }
        throw new IllegalArgumentException("Invalid BookCategory: " + value);
    }

    @Override
    public String toString() {
        return name;
    }
}