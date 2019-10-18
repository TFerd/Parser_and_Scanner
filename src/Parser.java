import java.util.ArrayList;

/**
 * Created by Thomas on 7/8/17.
 */
public class Parser {

    //Start checking the token codes of the tokens in the ArrayList tokenList
    //If statement begins if token is an identifier, then checks following tokens to determine what type of state it is.
    public void parse(ArrayList<Token> tokenList, ArrayList<Identifier> idList,
                      ArrayList<ArithmeticExpression> arithmeticList, ArrayList<BooleanExpression> booleanList, int i) {

        if (isAssignment(tokenList.get(i+1))) {

            //Assignment statement (Integer)
            if (isInteger(tokenList.get(i+2))) {

                //Checks for arithmetic operators
                if (isArithmetic(tokenList.get(i+3))){

                    //If an Identifier followed by an assignment operator followed by a full arithmetic expression, assign the result
                    //of the arithmetic expression to the Identifier
                    if (isInteger(tokenList.get(i+4))){

                        ArithmeticExpression exp = new ArithmeticExpression(Integer.parseInt(tokenList.get(i+2).getTokenLexeme()), tokenList.get(i+3),
                                Integer.parseInt(tokenList.get(i+4).getTokenLexeme()));

                        Identifier id = new Identifier(tokenList.get(i).getTokenLexeme(), Integer.toString(exp.runExpression()), "Int", 2012);
                        idList.add(id);
                    }
                    else
                        throw new IllegalArgumentException("Illegal Argument at Arithmetic Expression");
                }
                //Checks for boolean operators followed by an integer
                else if (isBool(tokenList.get(i+3))){

                    if (isInteger(tokenList.get(i+4))){

                        BooleanExpression exp = new BooleanExpression(Integer.parseInt(tokenList.get(i+2).getTokenLexeme()), tokenList.get(i+3),
                                Integer.parseInt(tokenList.get(i+4).getTokenLexeme()));

                        Identifier id = new Identifier((tokenList.get(i).getTokenLexeme()), exp.runExpression(), "Boolean", 2014);
                        idList.add(id);
                    }
                    else
                        throw new IllegalArgumentException("Illegal Argument at Boolean Expression");

                }
                else
                    idList.add(new Identifier(tokenList.get(i).getTokenLexeme(), tokenList.get(i + 2).getTokenLexeme(), "Int", 2012));
            }
            //Assignment statement (String)
            else if (isIdentifier(tokenList.get(i+2))) {

                idList.add(new Identifier(tokenList.get(i).getTokenLexeme(), tokenList.get(i + 2).getTokenLexeme(), "String", 2012));

            } else {
                throw new IllegalArgumentException("Invalid argument");
            }
        }//End of assignment statement


        //Check if the next token is an arithmetic operator and creates an ArithmeticExpression object
        //NOTE: Arithmetic operations using strings as variables will not be accepted
        else if (isArithmetic(tokenList.get(i+1))) {

            if (isInteger(tokenList.get(i)) && isInteger(tokenList.get(i+2))) {

                ArithmeticExpression exp =
                        new ArithmeticExpression(Integer.parseInt(tokenList.get(i).getTokenLexeme()), tokenList.get(i + 1), Integer.parseInt(tokenList.get(i + 2).getTokenLexeme()));

                exp.runExpression();
                arithmeticList.add(exp);
            }
            else
                throw new IllegalArgumentException("Error at: Arithmetic Operation");

        } //End of arithmetic operator

        //Check if the next token is a boolean statement
        //NOTE: Boolean expressions using string variables will not be accepted
        else if (isBool(tokenList.get(i+1))) {

            //If both tokens are integers
            if (isInteger(tokenList.get(i)) && isInteger(tokenList.get(i+2))){

                BooleanExpression exp =
                        new BooleanExpression(Integer.parseInt(tokenList.get(i).getTokenLexeme()), tokenList.get(i + 1), Integer.parseInt(tokenList.get(i + 2).getTokenLexeme()));

                exp.runExpression();
                booleanList.add(exp);
            }
            else
                throw new IllegalArgumentException("Error at: Boolean Operation");
        } //End of boolean expression statement
    }


    //Functions to check each token for different types of operators
    private boolean isAssignment(Token t){
        return t.getTokenCode() == 2009;
    }

    private boolean isBool(Token t){
        return (t.getTokenCode() >= 2005 && t.getTokenCode() <= 2008) || t.getTokenCode() == 2010 || t.getTokenCode() == 2011;
    }

    private boolean isArithmetic(Token t){
        return (t.getTokenCode() >= 2001 && t.getTokenCode() <= 2004);
    }

    private boolean isInteger(Token t){
        return t.getTokenCode() == 2013;
    }

    private boolean isIdentifier(Token t){
        return t.getTokenCode() == 2012;
    }
}
