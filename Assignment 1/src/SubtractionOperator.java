public class SubtractionOperator extends Operator {

    public int priority(){
        return 1;
    }

    public Operand execute(Operand op1, Operand op2) {

        int value = op1.getValue() - op2.getValue();
        Operand op = new Operand(value);
        return op;
    }


}

