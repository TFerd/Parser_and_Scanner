/**
 * Created by Thomas on 7/7/17.
 */

//Identifier class holds the lexeme, value, type, and token code of the identifier.

public class Identifier {

    private String name;
    private String value;
    private String type;

    private int tokenCode = 2012;



    public Identifier(String name, String value, String type, int code) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.tokenCode = code;
    }

    public Identifier(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public int getTokenCode() {
        return tokenCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTokenCode(int tokenCode) {
        this.tokenCode = tokenCode;
    }

    @Override
    public String toString(){
        return "Identifier name: " + getName() + " | Type: " + getType() + " | Value: " + getValue();
    }

}
