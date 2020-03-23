import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    Map<String,String> map = new HashMap<>();
    ArrayList<String> myList = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        System.out.println(output);

    }

    public String getNamesAndPrices(String text) {
        String input = text;
        String patStr = "\\w*(:|)\\w+[;:*!@#$%^&()_\"]+\\w*(:|)+\\d+[.]+\\d+\\d";
        Pattern pattern = Pattern.compile(patStr);
        Matcher matcher = pattern.matcher(input);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; matcher.find(); i++) {
            builder.append(matcher.group() + "\n");
            myList.add(matcher.group());
        }
        return builder.toString();
    }
    //Match everything with name+price
    // \w*:\w+[;:*!@#$%^&()_"]\w*:+\d+[.]+\d+\d

    //refined
    // \w*(:|)\w+[;:*!@#$%^&()_"]+\w*(:|)+\d+[.]+\d+\d


    public void printList(){
        myList.forEach(System.out::println);
    }

    public String removeName(String input){
        String patStr = "(n|N)+(a|A)+(m|M)+(e|E)+:";
        Pattern pattern = Pattern.compile(patStr);
        Matcher matcher = pattern.matcher(input);
        String newString =  matcher.replaceAll("");
        return newString;
    }

    public String removePrice(String input){
        String patStr = "(p|P)+(r|R)+(i|I)+(c|C)+(e|E)+:";
        Pattern pattern = Pattern.compile(patStr);
        Matcher matcher = pattern.matcher(input);
        String newString =  matcher.replaceAll("");
        return newString;
    }

    public void parseStringToItems(){

    }

    public String countMilkMatches(String text){
        return null;
    }

    public String countBreadMatches(String text){
        return null;
    }

    public String countCookieMatches(String text){
        return null;
    }

    public String countAppleMatches(String text){
        return null;
    }
}
