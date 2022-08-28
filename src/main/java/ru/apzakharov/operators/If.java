package ru.apzakharov.operators;

import ru.apzakharov.service.Interpreter;
import ru.apzakharov.util.Expression;

import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class If extends Operator {
    public If(String code) {
        super(code);
    }

    @Override
    public void exec(Interpreter interpreter) {
        Pattern pattern = Pattern.compile("(.*) THEN GOTO (.*)");
        Matcher matcher = pattern.matcher(getCode());
        if (!matcher.find()) {
            System.err.println("Matcher cant match expression");
            return;
        } else {
            String condition = matcher.group(1);
            Integer lineNumber = Integer.parseInt(matcher.group(2));
            try {
                interpreter.next();
                Object eval = Expression.eval(interpreter.getVars(), condition);
                if (eval instanceof Boolean && Boolean.TRUE.equals(eval)) {
                    interpreter.goTo(lineNumber);
                }
            } catch (ScriptException e) {
                System.err.println(e.getMessage());
            }

        }

    }
}
