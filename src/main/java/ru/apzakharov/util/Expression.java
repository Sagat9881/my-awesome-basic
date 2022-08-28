package ru.apzakharov.util;

import javax.script.*;
import java.util.Map;

public class Expression {

    public static final String JAVA_SCRIPT = "JavaScript";

    public static Object eval(Map<String, Double> vars, String code) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engineByName = scriptEngineManager.getEngineByName(JAVA_SCRIPT);
       for(String key:vars.keySet()){
           Double doubleValue = vars.get(key);
           String stringValue = String.valueOf(doubleValue);
           code = code.replace(key, stringValue);
       }
        return engineByName.eval(code);
    }
}
