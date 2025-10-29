package ivan.zelezinski.lab_supplyer.utils;


public interface Urls {

    String DELIMITER = "/";

    interface BOOK {
        String BASE = "books";
        String BOOK = BASE + DELIMITER;
        String AMOUNT = BASE + DELIMITER + "amount/";
    }

    interface STATISTICS {
        String BASE = "statistics";
        String STATISTICS = BASE + DELIMITER;
    }

    interface SUPPLY {
        String BASE = "supply";
        String SUPPLY = BASE + DELIMITER;
        String QUEUE = "queue" + DELIMITER;
        String CLIENT = "client" + DELIMITER;
    }
}