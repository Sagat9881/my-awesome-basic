package ru.apzakharov.service;

import ru.apzakharov.operators.Operator;
import ru.apzakharov.operators.OperatorFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Interpreter {
    public static final String RUN = "RUN";
    public static final String LIST = "LIST";
    public static final String EMPTY_SPACE = " ";
    public static final String run = "run";
    public static final String list = "list";
    private TreeMap<Integer, Operator> code = new TreeMap<>(); // AST типа
    private Map<String, Double> vars = new HashMap<>(); // стек, типа
    private Integer currLine;

    public void run() {
        currLine = code.firstKey();
        while (true) {
            Operator operator = code.get(currLine);
            operator.exec(this);

            if (currLine == null) break;
        }
    }

    public Map<String, Double> getVars() {
        return vars;
    }
    public void next() {
        currLine = code.higherKey(currLine);
    }

    public void goTo(int line) {
        currLine = line;
    }

    public void parse(String line) {
        switch (line) {
            case RUN:
            case run:
                this.run();
                break;
            case LIST:
            case list:
                this.printOperatorList();
                break;
            default:
                this.parseExpression(line);
        }
    }

    private void parseExpression(String line) {
        final String[] tokens = line.split(EMPTY_SPACE, 3);

        final int lineNumber = Integer.parseInt(tokens[0]);
        final String operatorName = tokens[1];
        final String expression = tokens[2];

        final Operator operator = OperatorFactory.createOperatorByName(operatorName, expression);

        code.put(lineNumber, operator);
    }

    private void printOperatorList() {
        for (int l : code.keySet()) {
            System.out.println(l + " - " + code.get(l));
        }
    }
}
