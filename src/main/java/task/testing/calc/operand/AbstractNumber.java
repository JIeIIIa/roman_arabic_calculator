package task.testing.calc.operand;

public abstract class AbstractNumber implements Operand {
  private final String value;
  private final long convertedValue;

  public AbstractNumber(String value) {
    this.value = value;
    this.convertedValue = convert(value);
  }

  public AbstractNumber(long convertedValue) {
    this.value = convert(convertedValue);
    this.convertedValue = convertedValue;
  }

  protected abstract long convert(String value);

  protected abstract String convert(long value);

  public long longValue() {
    return convertedValue;
  }

  public String originalValue() {
    return value;
  }
}
