/**
 * Created by Thomas on 6/24/17.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Lexer {

    private Keywords KeywordTable = new Keywords();

    private int row = 1;                                                    //Tracks row number in file
    private int column = -1;                                                //Tracks column number in file
    private char currentChar;                                               //Stores current character being looked at
    private BufferedReader reader;


    private static final char END_OF_FILE = (char) (-1);

    public Lexer(File file) {                                               //Constructor accepts input file to pass into BufferedReader
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            System.out.println("File not found.");
        }

        currentChar = scan();
    }


    private char scan() {
        try {
            column++;
            return (char) (reader.read());
        } catch (IOException e){
            return END_OF_FILE;
        }
    }


    private boolean isNumber(char c){
        if (c >= '0' && c <= '9')
            return true;
        else
            return false;
    }


    private boolean isLetter(char c){
        if (c >= 'a' && c<= 'z')
            return true;
        else if (c >= 'A' && c<= 'Z')
            return true;
        else
            return false;
    }


    public Token nextChar(){

        int controller = 1;         //Defaults to 1. This helps track tokens with multiple characters such as >=.
        String stringHolder = "";   //Holds and builds multiple characters to check for strings
        int numberHolder = 0;       //Holds and builds numbers
        boolean stop = false;

        while (true) {

            if (currentChar == END_OF_FILE && !stop){
                stop = true;
            }

            else if (stop){
                try{
                    reader.close();
                } catch (Exception e){
                    System.out.println("Error.");
                }
                return null;
            }

            switch (controller) {
                case 1:
                    switch (currentChar) {
                        case '\n':
                            row ++;
                            column = -1;
                            currentChar = scan();
                            continue;
                        case ' ':
                        case '\t':
                        case '\b':
                        case '\f':
                        case '\r':
                            currentChar = scan();
                            continue;

                        case '+':
                            currentChar = scan();
                            return new Token("+", 2001, column, row);

                        case '-':
                            currentChar = scan();
                            return new Token("-", 2002, column, row);

                        case '*':
                            currentChar = scan();
                            return new Token("*", 2003, column, row);

                        case '/':
                            currentChar = scan();
                            return new Token("/", 2004, column, row);

                        //These next 4 cases change the controller of the switch statement to check if the characters
                        //are followed by a =
                        case '<':
                            currentChar = scan();
                            controller = 6;
                            continue;

                        case '>':
                            currentChar = scan();
                            controller = 7;
                            continue;

                        case '=':
                            currentChar = scan();
                            controller = 8;
                            continue;

                        case '~':
                            currentChar = scan();
                            controller = 9;
                            continue;

                        //Default moves onto numbers if none of the above characters are detected.
                        default:
                            controller = 2;
                            continue;
                    }

                //Case 2 handles the beginning of integers
                case 2:
                    if (isNumber(currentChar)){
                        numberHolder = 0;
                        numberHolder += (currentChar - '0');

                        //Moves to next digit in the number.
                        controller = 3;

                        currentChar = scan();
                    }

                    else {
                        controller = 4; //If not a number, continue to Case 4
                    }
                    continue;

                //Case 3 continues building integers if the following characters are numbers
                case 3:
                    if (isNumber(currentChar)){

                        numberHolder *= 10; //Moves the current digit over a tens place.
                        numberHolder += (currentChar - '0');


                        currentChar = scan();
                    }
                    else {
                        return new Token("" + numberHolder, 2013, column - String.valueOf(numberHolder).length()+1, row);
                    }
                    continue;

                //Case 4 begins building a string if the character is a letter
                case 4:
                    if (isLetter(currentChar)){
                        stringHolder = "";
                        stringHolder += currentChar;
                        controller = 5;
                        currentChar = scan();
                    }
                    else {
                        throw new IllegalArgumentException("Invalid character");
                    }
                    continue;

                //Continues building the string and checks if the string is a keyword
                case 5:
                    if (isLetter(currentChar) || isNumber(currentChar)){
                        stringHolder += currentChar;
                        currentChar = scan();
                    }
                    else{
                        if (KeywordTable.isKeyword(stringHolder)){              //If string is a keyword, return token as a keyword

                            return new Token(stringHolder, KeywordTable.lookup(stringHolder), column - stringHolder.length()+1, row);

                        }
                        else if (stringHolder.equalsIgnoreCase("true") || stringHolder.equalsIgnoreCase("false")){
                            return new Token(stringHolder, 2014, column - stringHolder.length()+1, row);
                        }
                        return new Token(stringHolder, 2012, column - stringHolder.length()+1, row);           //If not a keyword, returns as an identifier
                    }
                    continue;

                //This series of cases checks if operators <, >, and = are followed by a =
                //If they are, then return the combination <=, >=, ==, or ~=, if not, return the single character.
                case 6:
                    if (currentChar == '='){
                        currentChar = scan();
                        return new Token("<=", 2006, column-1, row);
                    }
                    else
                        return new Token("<", 2005, column, row);

                case 7:
                    if (currentChar == '='){
                        currentChar = scan();
                        return new Token(">=", 2008, column-1, row);
                    }
                    else
                        return new Token(">", 2007, column, row);

                case 8:
                    if (currentChar == '='){
                        currentChar = scan();
                        return new Token("==", 2010, column-1, row);
                    }
                    else
                        return new Token("=", 2009, column, row);

                case 9:
                    if (currentChar == '=') {
                        currentChar = scan();
                        return new Token("~=", 2011, column-1, row);
                    }
                    else
                        throw new IllegalArgumentException("Invalid Character Input");
            }
        }
    }
}
