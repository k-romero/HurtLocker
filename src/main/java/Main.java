import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    Map<String,Map<Double,Integer>> map = new HashMap<>();
    ArrayList<String> myList = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
//        String output = (new Main()).readRawDataToString();
//        System.out.println(output);
        Main main = new Main();
        main.printMap();
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

    public void parseStringToItems(String input){
        //(\w*);((\d.\d\d))
        String patStr = "(\\w*);((\\d.\\d\\d))";
        Pattern pattern = Pattern.compile(patStr);
        Matcher matcher = pattern.matcher(input);
        for (int i = 0; matcher.find(); i++) {
           items.add(Item.itemFactory(matcher.group(1),Double.valueOf(matcher.group(2))));
        }
    }

    public String replaceCookies(String input){
        // (c|C)(o|O|0)(o|O|0)(k|K)(i|I)(e|E)(s|S)
        String cookies = "Cookies";
        String patStr = "(c|C)(o|O|0)(o|O|0)(k|K)(i|I)(e|E)(s|S)";
        Pattern pattern = Pattern.compile(patStr);
        Matcher matcher = pattern.matcher(input);
        String newString =  matcher.replaceAll(cookies);
        return newString;
    }

    public String replaceMilk(String input){
        // (m|M)(i|I)(l|L)(k|K)
        String cookies = "Milk";
        String patStr = "(m|M)(i|I)(l|L)(k|K)";
        Pattern pattern = Pattern.compile(patStr);
        Matcher matcher = pattern.matcher(input);
        String newString =  matcher.replaceAll(cookies);
        return newString;
    }

    public String replaceBread(String input){
        // (b|B)(r|R)(e|E)(a|A)(d|D)
        String cookies = "Bread";
        String patStr = "(b|B)(r|R)(e|E)(a|A)(d|D)";
        Pattern pattern = Pattern.compile(patStr);
        Matcher matcher = pattern.matcher(input);
        String newString =  matcher.replaceAll(cookies);
        return newString;
    }

    public String replaceApples(String input){
        // (a|A)(p|P)(p|P)(l|L)(e|E)(s|S)
        String cookies = "Apples";
        String patStr = "(a|A)(p|P)(p|P)(l|L)(e|E)(s|S)";
        Pattern pattern = Pattern.compile(patStr);
        Matcher matcher = pattern.matcher(input);
        String newString =  matcher.replaceAll(cookies);
        return newString;
    }

    public String replaceAll(String input){
        return replaceCookies(replaceApples(replaceBread(replaceMilk(input))));
    }

    public void putIntoMap(){
        items.stream().forEach(i -> {
            if(i.name.length()<=1){
                if(map.containsKey("Error")){
                    Map<Double,Integer> tempMap = map.get("Error");
                    tempMap.replace(0.0,tempMap.get(0.0),tempMap.get(0.0)+1);
                    map.replace("Error",map.get("Error"),tempMap);
                }
                else{
                    Map<Double,Integer> tempMap = new HashMap<>();
                    tempMap.put(0.0,1);
                    map.put("Error",tempMap);
                }
            } else if(map.containsKey(i.name)){
                Map<Double,Integer> tempMap = map.get(i.name);
                if(tempMap.containsKey(i.price)){
                    Map<Double,Integer> innerMap = map.get(i.name);
                    innerMap.replace(i.price,innerMap.get(i.price),innerMap.get(i.price)+1);
                    map.replace(i.name,map.get(i.name),innerMap);
                } else{
                    map.get(i.name).put(i.price,1);
                }
            } else{
                Map<Double,Integer> newItemMap = new HashMap<>();
                newItemMap.put(i.price,1);
                map.put(i.name,newItemMap);
            }
        });
    }

    public void printMap() throws Exception {
        String testString = getNamesAndPrices(readRawDataToString());
        String useThisString = removePrice(removeName(testString));
        parseStringToItems(replaceAll(useThisString));
        putIntoMap();
        map.forEach((b,c) ->{
            int count = 0;
            for (Integer i : c.values()) {
                count += i;
            }
            System.out.println("name : " + b + " seen : " + count + " times");
            c.forEach((g,h) ->{
                System.out.println("price : " + g + " seen : " + h + " times.");
            });
        });
    }



    public ArrayList<Item> getItems() {
        return items;
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
