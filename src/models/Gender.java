package models;

public enum Gender {
    MALE(0),
    FEMALE(1);

    private final int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Gender getGender(int value) {
        return switch (value) {
            case 0 -> Gender.MALE;
            case 1 -> Gender.FEMALE;
            default -> throw new RuntimeException("Error");
        };
    }
}
