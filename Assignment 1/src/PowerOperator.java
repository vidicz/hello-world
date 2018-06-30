public class PowerOperator extends Operator {

    public int priority(){
        return 3;
    }
    public Operand execute(Operand op1, Operand op2) {

        //MATH.POW() returns double value casting done to remove incompatible type
        //in the Operand constructor.

        int value = (int)Math.pow(op1.getValue(),op2.getValue());
        Operand op = new Operand(value);
        return op;
    }
}
