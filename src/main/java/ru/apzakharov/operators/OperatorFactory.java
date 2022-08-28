package ru.apzakharov.operators;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {
    private static Map<String, Class<? extends Operator>> operators = new HashMap<>();

    static {
        operators.put("PRINT", Print.class);
        operators.put("LET", Let.class);
        operators.put("IF", If.class);
        operators.put("GOTO", GoTo.class);
    }

    public static Operator createOperatorByName(String operatorName, String substring) {
        Class<? extends Operator> operatorClass = operators.get(operatorName.toUpperCase());
        Operator operator = null;
        try {
            operator = operatorClass.getConstructor(String.class).newInstance(substring);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return operator;
    }
}
