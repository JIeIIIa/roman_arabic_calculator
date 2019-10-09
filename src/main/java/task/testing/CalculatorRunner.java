package task.testing;

import java.util.Scanner;
import task.testing.calc.Calculator;
import task.testing.calc.exception.CalculatorException;

public class CalculatorRunner {

  private Scanner scanner = new Scanner(System.in);
  private Calculator calculator = new Calculator();

  public static void main(String[] args) {
    new CalculatorRunner().run();
  }

  private void run() {
    System.out.println("Enter empty string to exit\n");
    String line;

    do {
      System.out.print(">>");
      line = scanner.nextLine();
      if (!"".equals(line)) {
        doCalculate(calculator, line);
      }
    } while (!"".equals(line));
  }

  private void doCalculate(Calculator calculator, String line) {
    try {
      String result = calculator.calculate(line);
      System.out.println("<<" + result);
    } catch (CalculatorException e) {
      System.out.println("<<" + e.getMessage());
    }
  }

}
