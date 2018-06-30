import java.util.HashMap;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.

    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );


    static HashMap<String, Operator> operatorMap = new HashMap<String, Operator>();



    public abstract int priority();

    public abstract Operand execute(Operand op1, Operand op2);

    public static boolean check(String token) {

        if(token.equals("+") || token.equals("-") || token.equals("*") ||
                token.equals("/") || token.equals("^") || token.equals(")") || token.equals("(")){
            return true;
        }
        return false;
    }

public static Operator checkOperatorType(String token){

        switch(token){
            case "+":
                return  operatorMap.get(token);
            case "-": 
                return operatorMap.get(token);
            case "*":
                return operatorMap.get(token);
            case "/":
                return operatorMap.get(token);
            case "^":
                return operatorMap.get(token);
            case "(":
                return operatorMap.get(token);
            case ")":
                return operatorMap.get(token);
        }

return null;

    }





}

