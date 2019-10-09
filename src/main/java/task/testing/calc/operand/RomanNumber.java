package task.testing.calc.operand;

import java.util.TreeMap;
import java.util.regex.Pattern;
import task.testing.calc.exception.CalculatorException;

public class RomanNumber extends AbstractNumber {

  private static final Pattern ROMAN = Pattern.compile(
      "^(?=.)M*(C[MD]|D?C{0,3})"
          + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

  private final static TreeMap<Long, String> map = new TreeMap<>();

  static {
    map.put(1000L, "M");
    map.put(900L, "CM");
    map.put(500L, "D");
    map.put(400L, "CD");
    map.put(100L, "C");
    map.put(90L, "XC");
    map.put(50L, "L");
    map.put(40L, "XL");
    map.put(10L, "X");
    map.put(9L, "IX");
    map.put(5L, "V");
    map.put(4L, "IV");
    map.put(1L, "I");
  }

  public RomanNumber(String value) {
    super(value);
  }

  @Override
  protected long convert(String value) {
    if ("".equals(value)) {
      return 0;
    } else if (value.startsWith("M")) {
      return 1000 + convert(value.substring(1));
    } else if (value.startsWith("CM")) {
      return 900 + convert(value.substring(2));
    } else if (value.startsWith("D")) {
      return 500 + convert(value.substring(1));
    } else if (value.startsWith("CD")) {
      return 400 + convert(value.substring(2));
    } else if (value.startsWith("C")) {
      return 100 + convert(value.substring(1));
    } else if (value.startsWith("XC")) {
      return 90 + convert(value.substring(2));
    } else if (value.startsWith("L")) {
      return 50 + convert(value.substring(1));
    } else if (value.startsWith("XL")) {
      return 40 + convert(value.substring(2));
    } else if (value.startsWith("X")) {
      return 10 + convert(value.substring(1));
    } else if (value.startsWith("IX")) {
      return 9 + convert(value.substring(2));
    } else if (value.startsWith("V")) {
      return 5 + convert(value.substring(1));
    } else if (value.startsWith("IV")) {
      return 4 + convert(value.substring(2));
    } else if (value.startsWith("I")) {
      return 1 + convert(value.substring(1));
    } else {
      throw new CalculatorException("Illegal value to convert: " + value);
    }
  }

  @Override
  protected String convert(long value) {
    return convert(value);
  }

  public static boolean isValid(String value) {
    return ROMAN.matcher(value).matches();
  }

  public static String convertToString(long value) {
    long l = map.floorKey(value);
    if (value == l) {
      return map.get(value);
    }
    return map.get(l) + convertToString(value - l);
  }
}
