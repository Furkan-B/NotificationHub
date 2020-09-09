package org.example.language;

public class English implements Language {

    @Override
    public String notFoundPackage() {
        return "Not Found Package";
    }

    @Override
    public String blackList() {
        return " You are on the BLACKLIST. You should pay your debt";
    }

    @Override
    public String hasCurrentPackage() {
        return " Has Current package. You can't buy same or like package";
    }
}
