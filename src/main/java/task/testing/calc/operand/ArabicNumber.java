package task.testing.calc.operand;

import java.util.regex.Pattern;
import task.testing.calc.exception.CalculatorException;

public class ArabicNumber extends AbstractNumber {
  private static final Pattern ARABIC = Pattern.compile("\\d");

  public ArabicNumber(String value) {
    super(value);
  }

  @Override
  protected long convert(String value) {
    try {
      return Long.valueOf(value);
    } catch (NumberFormatException e) {
      throw new CalculatorException("Illegal value to convert: " + value, e);
    }
  }

  @Override
  protected String convert(long value) {
    return Long.toString(value);
  }

  public static boolean isValid(String value) {
    return ARABIC.matcher(value).matches();
  }
}
