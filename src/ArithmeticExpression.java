/**
 * Created by Thomas on 7/19/17.
 */
public class ArithmeticExpression {

    private int value1, value2, result;
    private Token operator;


    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public Token getOperator() {
        return operator;
    }

    public int getResult() {
        return result;
    }


    ////Creates an object of an ArithmeticExpression using the two values in the expression and the operator used
    public ArithmeticExpression(int value1, Token operator, int value2){
        if (operator == null){
            throw new IllegalArgumentException("Null operator argument");
        }
        this.value1 = value1;
        this.operator = operator;
        this.value2 = value2;
        this.result = getResult();
    }


    //Function to determine the result of the expression
    public int runExpression(){
        result = 0;

        if (operator.getTokenCode() == 2001){
            result = value1 + value2;
        }

        else if (operator.getTokenCode() == 2002){
            result = value1 - value2;
        }

        else if (operator.getTokenCode() == 2003){
            result = value1 * value2;
        }

        else if (operator.getTokenCode() == 2004){
            if (value2 == 0){
                throw new ArithmeticException("Cannot divide by zero");
            }
            else
                result = value1 / value2;
        }
        else
            throw new IllegalArgumentException("Error at: runExpression()");

        return result;
    }

    @Override
    public String toString(){
        return "\nArithmetic Expression: " + getValue1() + " " + getOperator().getTokenLexeme() + " " + getValue2() + " = " + getResult();
    }

}
