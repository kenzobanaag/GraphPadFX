package test.model;

public abstract class TestingSpecifics {

    private static boolean isTest = false;

    public static boolean isTesting() {
        return isTest;
    }

    public static void setTesting(boolean test) {
        isTest = test;
    }
}
