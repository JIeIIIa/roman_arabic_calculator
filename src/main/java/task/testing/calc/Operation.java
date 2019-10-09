package task.testing.calc;

import task.testing.calc.exception.CalculatorException;
import task.testing.calc.operand.Operand;

public enum Operation {
  UNKNOWN,
  PLUS {
    @Override
    public long process(Operand a, Operand b) {
      return a.longValue() + b.longValue();
    }
  },

  MINUS {
    @Override
    public long process(Operand a, Operand b) {
      return a.longValue() - b.longValue();
    }
  },

  MULTIPLE {
    @Override
    public long process(Operand a, Operand b) {
      return a.longValue() * b.longValue();
    }
  },

  DIVIDE {
    @Override
    public long process(Operand a, Operand b) {
      if (b.longValue() == 0L) {
        throw new CalculatorException("Division by zero");
      }
      return a.longValue() / b.longValue();
    }
  };

  public long process(Operand a, Operand b) {
    throw new UnsupportedOperationException();
  }

  public static Operation from(String value) {
    switch (value.trim()) {
      case "+": return PLUS;
      case "-": return MINUS;
      case "*": return MULTIPLE;
      case "/": return DIVIDE;
      default: return UNKNOWN;
    }
  }
}
