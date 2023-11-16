package com.example.restservice.enums;

public enum Kind {
    CAT,
    DOG,
    HAMSTER,
    GERBIL,
    FISH,
    STEER,
    GOAT,
    SHEEP,
    HORSE,
    DONKEY,
    REPTILE,
    BIRD,
    ;

    public static boolean isValidKind(Kind kind) {
        for (Kind enumKind : Kind.values()) {
            if (enumKind.name().equalsIgnoreCase(kind.toString())) {
                return false;
            }
        }
        return true;
    }
}