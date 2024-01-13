package mikolaj.project.backendapp.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StarRating {
    ONE_STAR("1 Star"), TWO_STARS("2 Stars"), THREE_STARS("3 Stars"), FOUR_STARS("4 Stars"), FIVE_STARS("5 Stars");
    private final String name;

    StarRating(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public byte getGrade(){
        switch (this){
            case ONE_STAR -> {
                return 1;
            }
            case TWO_STARS -> {
                return 2;
            }
            case THREE_STARS -> {
                return 3;
            }
            case FOUR_STARS -> {
                return 4;
            }
            case FIVE_STARS -> {
                return 5;
            }
        }
        return 0;
    }

    //@JsonCreator
    public static StarRating fromValue(String value) {
        if(value== null) return null;
        for (StarRating starRating : StarRating.values()) {
            if (starRating.name.equalsIgnoreCase(value)) {
                return starRating;
            }
        }
        throw new IllegalArgumentException("Invalid star rating: " + value);
    }

    @Override
    public String toString() {
        return name;
    }
}
