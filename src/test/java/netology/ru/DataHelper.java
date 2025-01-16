package netology.ru;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static  AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static CardInfo getFirstCardInfo () {
        return new CardInfo("5559 0000 0000 0001", );
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002", );
    }

    public static int generateValidAmount(int balance) {
        return Math.abs(balance) / 10;
    }

    public static int generateInvalidAmount(int balance) {
        return  Math.abs(balance) + 5;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String testId;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

}
