package prog2.ha1.testing;

// behaviour inspired by https://www.online-calculator.com/
public class Calculator {

    private String screen = "0";

    private double latestValue;

    private String latestOperation = "";

    private double memory = 0.0; //Klassenvariable um in mehreren Methoden zu verwenden

    private double result = 0.0;

    public String readScreen() { // was steht jetzt auf dem Bildschirm
        return screen;
    }

    public void pressDigitKey(int digit) { // also die Tasten 0-9
        if(digit > 9 || digit < 0) throw new IllegalArgumentException();

        if(screen.equals("0")) screen = "";

        if(latestOperation.isEmpty()) {
            screen = screen + digit;
        } else {
            latestValue = Double.parseDouble(screen);
            screen = Integer.toString(digit);
        }

    }

    public void pressClearKey() { // die Taste CE
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
    }

    public void pressBinaryOperationKey(String operation)  { // also die Tasten /,x,-,+
        latestOperation = operation;
    }

    public void pressUnaryOperationKey(String operation) { // also die Tasten Wurzel, %, 1/x
        latestOperation = operation;
    }

    public void pressDotKey() { // die Komma- bzw. Punkt-Taste
        if(!screen.endsWith(".")) screen = screen + ".";
    }

    public void pressNegativeKey() { // die +/- Taste
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;
    }

    public void pressEqualsKey() { // die Taste =
        result = switch(latestOperation) { //var rausgenommen, als Klassenvariabel deklariert um bei pressMemoryPlus nutzen zu können
            case "+" -> latestValue + Double.parseDouble(screen);
            case "-" -> latestValue - Double.parseDouble(screen);
            case "x" -> latestValue * Double.parseDouble(screen);
            case "/" -> latestValue / Double.parseDouble(screen);
            case "w" -> Math.sqrt(Double.parseDouble(screen)); //Quelle: http://java-zwischendurch.blogspot.com/2012/08/wurzelziehen-und-potezieren-in-java.html
            default -> throw new IllegalArgumentException();
        };
        screen = Double.toString(result);
        if(screen.endsWith(".0")) screen = screen.substring(0,screen.length()-2);
    }

    public void pressMemoryPlus() { //addiert Ergebnis auf den gespeicherten Wert
        memory = memory + result;
        screen = Double.toString(memory);
    }



}