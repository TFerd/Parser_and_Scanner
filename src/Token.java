/**
 * Created by Thomas on 6/18/17.
 */

//Token class holds the lexeme, token, and column of each token scanned.

public class Token {
    private String tokenLexeme;
    private int tokenCode;
    private int column, row;


    public Token(String lex, int code, int column, int row){
        this.tokenLexeme = lex;
        this.tokenCode = code;
        this.column = column;
        this.row = row;
    }

    public String getTokenLexeme() {
        return tokenLexeme;
    }

    public int getTokenCode() {
        return tokenCode;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "\nLexeme is: " + tokenLexeme + "    |   Token Code is: " + tokenCode + "    |   at Column: " + column + "    |   at Row: " + row;
    }
}
