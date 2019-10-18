/**
 * Created by Thomas on 7/21/17.
 */
public class BooleanExpression {
    private int value1, value2;
    private Token operator;
    private String result;

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public String getResult() {
        return result;
    }

    public Token getOperator() {
        return operator;
    }

    //Creates an object of a BooleanExpression using the two values in the expression and the operator used
    public BooleanExpression(int value1, Token operator, int value2){
        if (operator == null)
            throw new IllegalArgumentException("Null operator argument");

        this.value1 = value1;
        this.operator = operator;
        this.value2 = value2;
        this.result = getResult();
    }

    //Function to determine the result of the expression
    public String runExpression(){

        //Operator <
        if (operator.getTokenCode() == 2005){
            if (value1 < value2)
                result = "True";
            else
                result = "False";
        }

        //Operator <=
        else if (operator.getTokenCode() == 2006){
            if (value1 <= value2)
                result = "True";
            else
                result = "False";
        }

        //Operator >
        else if (operator.getTokenCode() == 2007){
            if (value1 > value2)
                result = "True";
            else
                result = "False";
        }

        //Operator >=
        else if (operator.getTokenCode() == 2008){
            if (value1 >= value2)
                result = "True";
            else
                result = "False";
        }

        //Operator ==
        else if (operator.getTokenCode() == 2010){
            if (value1 == value2)
                result = "True";
            else
                result = "False";
        }

        //Operator ~=
        else if (operator.getTokenCode() == 2011){
            if (value1 != value2)
                result = "True";
            else
                result = "False";
        }
        else
            throw new IllegalArgumentException("Error at BooleanExpression.runExpression()");

        return result;
    }

    @Override
    public String toString(){
        return "\nBoolean Expression: " + getValue1() + " " + getOperator().getTokenLexeme() + " " + getValue2() + " = " + getResult();
    }
}
