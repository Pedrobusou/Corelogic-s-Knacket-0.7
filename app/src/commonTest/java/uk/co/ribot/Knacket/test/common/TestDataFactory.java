package uk.co.ribot.Knacket.test.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import uk.co.ribot.Knacket.data.model.Buyer;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static Buyer makeBuyer() {
        return new Buyer("Andreas Furst", "Hardcoded Buyer", "3-mar 17:05", "Soffa fatolj och sjrivbord skal slangas i grovsopor, bor pa 3tr grovosporna ligger isuefhisuhfiusehifuhseifuhseiufhseiufhiseuh", "300kr", 3);
    }

    public static List<Buyer> makeListBuyers(int number) {
        List<Buyer> buyers = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            buyers.add(makeBuyer());
        }
        return buyers;
    }
}