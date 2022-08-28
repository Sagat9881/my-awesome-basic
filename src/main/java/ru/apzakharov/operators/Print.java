package ru.apzakharov.operators;

import ru.apzakharov.util.Expression;
import ru.apzakharov.service.Interpreter;

import javax.script.ScriptException;

public class Print extends Operator{
    public Print(String code) {
        super(code);
    }

    @Override
    public void exec(Interpreter interpreter) {
        Object eval = null;

        try {
            eval = Expression.eval(interpreter.getVars(), getCode());
        } catch (ScriptException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(eval);
        interpreter.next();
    }
}
