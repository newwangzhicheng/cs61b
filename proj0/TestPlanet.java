public class TestPlanet {
    public static void main(String[] args) { checkPlanet(); }

    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + "and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + "and you gave " + actual);
        }
    }

    private static void checkPlanet() {
        System.out.println("Checking update...");
        final Planet p1 = new Planet(0, 0, 3, 4, 20, "jupiter.gif");
        final Planet p2 = new Planet(3, 4, 5, 6, 10, "jupiter.gif");
        final double force = p1.calcForceExertedBy(p2);
        final double G = 6.67 * Math.pow(10, -11);

        checkEquals(8 * G, force, "calcNetForceExerted", 0.01);
    }
}
