import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Thomas on 6/24/17.
 */

//Main instances an input and output file to work with, then loops through the input file while there are still characters.
//The Parser checks for any expressions found in the token list and also checks for any assignment statements.
//The Parser also uses an Interpreter to run any expressions found and adds them to their respective lists.

public class Main {

    public static void main(String[] args) {

        //ArrayLists for tokens, identifiers, arithmetic and boolean expressions
        ArrayList<Token> tokenList = new ArrayList<>();
        ArrayList<Identifier> idList = new ArrayList<>();
        ArrayList<ArithmeticExpression> arithmeticList = new ArrayList<>();
        ArrayList<BooleanExpression> booleanList = new ArrayList<>();

        File inFile = new File("src/input.txt");
        File outFile = new File("src/output.txt");

        Lexer lexer = new Lexer(inFile);
        Parser parser = new Parser();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

            Token token;


            writer.write("LEXEMES:                    TOKEN CODES:\n" +
                    "+                           2001\n" +
                    "-                           2002\n" +                          //Menu for lexemes and their token codes
                    "*                           2003\n" +
                    "/                           2004\n" +
                    "<                           2005\n" +
                    "<=                          2006\n" +
                    ">                           2007\n" +
                    ">=                          2008\n" +
                    "=                           2009\n" +
                    "==                          2010\n" +
                    "~=                          2011\n" +
                    "Identifier                  2012\n" +
                    "Integer                     2013\n" +
                    "Boolean                     2014\n" +
                    "Keywords                    1001 - 1029\n" +
                    "-----------------------------------------");

            while ((token = lexer.nextChar()) != null){

                tokenList.add(token);
                writer.write(token.toString());

            }

            writer.write("\n\n");

            //Parser checks for any expressions within the tokenList
            for (int i = 0; i < tokenList.size()-1; i++) {
                parser.parse(tokenList, idList, arithmeticList, booleanList, i);
            }


            writer.write("\n");

            //Prints identifier array
            for (Identifier id : idList){
                writer.write("\n" + id.toString());
            }

            writer.write("\n\n");

            //Prints ArithmeticExpression array
            for (ArithmeticExpression exp : arithmeticList){
                writer.write("\n" + exp.toString());
            }

            //Prints BooleanExpression array
            for(BooleanExpression exp : booleanList)
                writer.write("\n" + exp.toString());

            writer.close();

        } catch (IOException e){
            System.out.println("Error.");
        }
    }
}
