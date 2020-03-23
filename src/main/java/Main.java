import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        System.out.println(output);

    }

    public void getNamesAndPrices(String text){
        String input = text;
        String patStr = "\\w*(:|)\\w+[;:*!@#$%^&()_\"]+\\w*(:|)+\\d+[.]+\\d+\\d";
        Pattern pattern = Pattern.compile(patStr);
        Matcher matcher = pattern.matcher(input);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; matcher.find() ; i++) {
            builder.append(matcher.group() + "\n");
        }

        System.out.println(builder.toString());
    }


    //Match everything with name+price
    // \w*:\w+[;:*!@#$%^&()_"]\w*:+\d+[.]+\d+\d

    //refined
    // \w*(:|)\w+[;:*!@#$%^&()_"]+\w*(:|)+\d+[.]+\d+\d
}
