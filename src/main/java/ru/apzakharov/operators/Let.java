package ru.apzakharov.operators;

import ru.apzakharov.util.Expression;
import ru.apzakharov.service.Interpreter;

import java.util.Map;

public class Let extends Operator {
    public Let(String code) {
        super(code);
    }

    @Override
    public void exec(Interpreter interpreter) {

        final String[] tokens = getCode().split("=");
        final String expression = tokens[1];
        final Map<String, Double> vars = interpreter.getVars();

        final Double result = eval(expression, vars);

        vars.put(tokens[0], result);
        interpreter.next();
    }

    private Double eval(String expression, Map<String, Double> vars) {
        try {
            String eval = Expression.eval(vars, expression).toString();
            return Double.parseDouble(eval);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
