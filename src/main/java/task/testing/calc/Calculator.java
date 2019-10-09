package task.testing.calc;

import java.util.Optional;
import java.util.stream.Stream;
import task.testing.calc.exception.CalculatorException;
import task.testing.calc.operand.ArabicNumber;
import task.testing.calc.operand.Operand;
import task.testing.calc.operand.RomanNumber;

public class Calculator {

  public String calculate(String line) {
    ParamsDTO paramsDTO = findOperationPosition(line)
        .map(p -> parseLine(line, p))
        .orElseThrow(() -> new CalculatorException("Illegal expression"));
    return calculate(paramsDTO);
  }

  private String calculate(ArabicNumber a, ArabicNumber b, Operation operation) {
    return String.valueOf(operation.process(a, b));
  }


  private String calculate(RomanNumber a, RomanNumber b, Operation operation) {
    return RomanNumber.convertToString(operation.process(a, b));
  }

  private Optional<Integer> findOperationPosition(String line) {
    return Stream.of("+", "-", "*", "/")
        .filter(line::contains)
        .map(line::indexOf)
        .findAny();
  }

  private ParamsDTO parseLine(String line, int operationPosition) {
    return new ParamsDTO(
        line.substring(0, operationPosition).trim(),
        line.substring(operationPosition + 1).trim(),
        line.substring(operationPosition, operationPosition + 1).trim());
  }

  private String calculate(ParamsDTO paramsDTO) {
    Operation operation = Operation.from(paramsDTO.operation);
    if (Operation.UNKNOWN.equals(operation)) {
      throw new CalculatorException("Unsupported operation: " + paramsDTO.operation);
    }
    if (RomanNumber.isValid(paramsDTO.a) && RomanNumber.isValid(paramsDTO.b)) {
      RomanNumber a = new RomanNumber(paramsDTO.a);
      RomanNumber b = new RomanNumber(paramsDTO.b);

      return RomanNumber.convertToString(operation.process(a, b));
    } else if (ArabicNumber.isValid(paramsDTO.a) && ArabicNumber.isValid(paramsDTO.b)) {
      ArabicNumber a = new ArabicNumber(paramsDTO.a);
      ArabicNumber b = new ArabicNumber(paramsDTO.b);
      return String.valueOf(operation.process(a, b));
    } else {
      throw new CalculatorException("Incompatible operand types");
    }
  }


  private class ParamsDTO {

    final String a;
    final String b;
    final String operation;

    ParamsDTO(String a, String b, String operation) {
      this.a = a;
      this.b = b;
      this.operation = operation;
    }
  }

  private class Params {

    Operation operation;
    Operand a;
    Operand b;
  }
}
