package uk.co.ribot.Knacket.test.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import uk.co.ribot.Knacket.data.model.Ad;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static Ad makeBuyer() {
        return new Ad("Andreas Furst", "Hardcoded Ad", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr", 3);
    }

    public static List<Ad> makeListBuyers(int number) {
        List<Ad> ads = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            ads.add(makeBuyer());
        }
        return ads;
    }
}