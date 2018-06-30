package Evaluator;

import operators.Operator;
import java.util.*;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;

  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/ ";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
    
    Operator.operatorMap.put("+", new AdditionOperator());
    Operator.operatorMap.put("-", new SubtractionOperator());
    Operator.operatorMap.put("*", new MultiplyOperator());
    Operator.operatorMap.put("/", new DivideOperator()); 
    Operator.operatorMap.put("^", new PowerOperator());

  }

  public int eval( String expression ) {
    String token;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators

    // TODO Operator is abstract - this will need to be fixed:
    // operatorStack.push( new Operator( "#" ));

    // When is it a good time to add the "!" operator?

    while ( this.tokenizer.hasMoreTokens() ) {
      // filter out spaces
      if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if (Operand.check(token)) {
           
          operandStack.push(new Operand(token));
        } else {
          if (!Operator.check(token)) {
            System.out.println("*****invalid token******");
            System.exit(1);
          }

          // TODO Operator is abstract - these two lines will need to be fixed:
          // The Operator class should contain an instance of a HashMap,
          // and values will be instances of the Operators.  See Operator class
          // skeleton for an  example.

          // static function checkOperatorType to process the token type and instantiate it's object

          Operator newOperator = Operator.checkOperatorType(token);
          
         

          // if the token to be read is right paranthesis, solve the entire brace.

          if (token.equals(")")) {

            // solve until the left paranthesis is found
            while (!operatorStack.peek().equals(Operator.operatorMap.get("("))) {
                
                

              Operator oldOpr = operatorStack.pop();
              Operand op2 = operandStack.pop();
              Operand op1 = operandStack.pop();
              Operand o = oldOpr.execute(op1, op2);
              operandStack.push(o);
            }

            //pop left paranthesis from the operator stack.

          operatorStack.pop();


          }

          // if the token is not right paranthesis

          else {


             // operation for operands not enclosed by (), and the priority of the top operator should be greater than the token just read, also discarding the "(" since
            // operations with double paranthesis like ((2+1) +(2-1)) could create problem as the condition for the while loop would be true when
            // top of the operator stack "(" and token to be read if "(" and would throw an EmptyStackException when the empty operandStack is popped

            while (!operatorStack.empty() && operatorStack.peek().priority() >= newOperator.priority() && newOperator != Operator.operatorMap.get("(")) {
              // note that when we eval the expression 1 - 2 we will
              // push the 1 then the 2 and then do the subtraction operation
              // This means that the first number to be popped is the
              // second operand, not the first operand - see the following code


                Operator oldOpr = operatorStack.pop();
                Operand op2 = operandStack.pop();
                Operand op1 = operandStack.pop();
                Operand o = oldOpr.execute(op1, op2);
                operandStack.push(o);
              }


         // push operator if it's not right paranthesis

              operatorStack.push(newOperator);

          }



          }


      }

    }

    // clear the operation stack by evaluating the remaining operations.
    

    while(!operatorStack.empty()){
      Operator oldOpr = operatorStack.pop();
      Operand op2 = operandStack.pop();
      Operand op1 = operandStack.pop();
      Operand o = oldOpr.execute(op1, op2);
      operandStack.push(o);
    }

    //return the value of the operand stack.

  return (operandStack.pop().getValue());

  }

    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks (except
    // the init operator on the operator stack); that is, we should keep
    // evaluating the operator stack until it only contains the init operator;
    // Suggestion: create a method that takes an operator as argument and
    // then executes the while loop; also, move the stacks out of the main
    // method


  }




