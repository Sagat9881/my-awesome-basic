package ru.apzakharov.operators;

import ru.apzakharov.service.Interpreter;

abstract public class Operator {
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName().toUpperCase() + " " + code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public Operator(String code) {
        this.code = code;
    }

    abstract public void exec(Interpreter interpreter);


}
