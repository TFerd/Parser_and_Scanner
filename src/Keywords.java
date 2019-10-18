import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thomas on 6/25/17.
 */

//This class holds the keywords and functions to look up the token codes of each keyword.
//A hashmap holds the token code for each keyword and is used when looking up the code.
public class Keywords {


    //Keywords
    private static final String specificationsString = "specifications";
    private static final String symbolString = "symbol";
    private static final String forwardString = "forward";
    private static final String referencesString = "references";
    private static final String functionString = "function";
    private static final String pointerString = "pointer";
    private static final String arrayString = "array";
    private static final String typeString = "type";
    private static final String structString = "struct";
    private static final String integerString = "integer";
    private static final String enumString = "enum";
    private static final String globalString = "global";
    private static final String declarationsString = "declarations";
    private static final String implementationsString = "implementations";
    private static final String mainString = "main";
    private static final String parametersString = "parameters";
    private static final String constantString = "constant";
    private static final String beginString = "begin";
    private static final String endfunString = "endfun";
    private static final String ifString = "if";
    private static final String thenString = "then";
    private static final String elseString = "else";
    private static final String endifString = "endif";
    private static final String repeatString = "repeat";
    private static final String untilString = "until";
    private static final String endrepeatString = "endrepeat";
    private static final String displayString = "display";
    private static final String setString = "set";
    private static final String returnString = "return";






    //Checks if the string is a keyword
    public boolean isKeyword(String string){
        if (string.equals(specificationsString)
                || string.equalsIgnoreCase(symbolString)
                || string.equalsIgnoreCase(forwardString)
                || string.equalsIgnoreCase(referencesString)
                || string.equalsIgnoreCase(functionString)
                || string.equalsIgnoreCase(pointerString)
                || string.equalsIgnoreCase(arrayString)
                || string.equalsIgnoreCase(typeString)
                || string.equalsIgnoreCase(structString)
                || string.equalsIgnoreCase(integerString)
                || string.equalsIgnoreCase(enumString)
                || string.equalsIgnoreCase(globalString)
                || string.equalsIgnoreCase(declarationsString)
                || string.equalsIgnoreCase(implementationsString)
                || string.equalsIgnoreCase(mainString)
                || string.equalsIgnoreCase(parametersString)
                || string.equalsIgnoreCase(constantString)
                || string.equalsIgnoreCase(beginString)
                || string.equalsIgnoreCase(endfunString)
                || string.equalsIgnoreCase(ifString)
                || string.equalsIgnoreCase(thenString)
                || string.equalsIgnoreCase(elseString)
                || string.equalsIgnoreCase(endifString)
                || string.equalsIgnoreCase(repeatString)
                || string.equalsIgnoreCase(untilString)
                || string.equalsIgnoreCase(endrepeatString)
                || string.equalsIgnoreCase(displayString)
                || string.equalsIgnoreCase(setString)
                || string.equalsIgnoreCase(returnString))
            return true;

        else
            return false;
    }

    //HashMap for storing keywords and their respective token codes
    private static final Map<String, Integer> KeywordMap;

    static{
        KeywordMap = new HashMap<>();
        KeywordMap.put(specificationsString, 1001);
        KeywordMap.put(symbolString, 1002);
        KeywordMap.put(forwardString, 1003);
        KeywordMap.put(referencesString, 1004);
        KeywordMap.put(functionString, 1005);
        KeywordMap.put(pointerString, 1006);
        KeywordMap.put(arrayString, 1007);
        KeywordMap.put(typeString, 1008);
        KeywordMap.put(structString, 1009);
        KeywordMap.put(integerString, 1010);
        KeywordMap.put(enumString, 1011);
        KeywordMap.put(globalString, 1012);
        KeywordMap.put(declarationsString, 1013);
        KeywordMap.put(implementationsString, 1014);
        KeywordMap.put(mainString, 1015);
        KeywordMap.put(parametersString, 1016);
        KeywordMap.put(constantString, 1017);
        KeywordMap.put(beginString, 1018);
        KeywordMap.put(endfunString, 1019);
        KeywordMap.put(ifString, 1020);
        KeywordMap.put(thenString, 1021);
        KeywordMap.put(elseString, 1022);
        KeywordMap.put(endifString, 1023);
        KeywordMap.put(repeatString, 1024);
        KeywordMap.put(untilString, 1025);
        KeywordMap.put(endrepeatString, 1026);
        KeywordMap.put(displayString, 1027);
        KeywordMap.put(setString, 1028);
        KeywordMap.put(returnString, 1029);

    }


    int lookup(String string){
        string = string.toLowerCase();  //Ignores case

        return KeywordMap.get(string);

        }
}
