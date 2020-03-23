import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class MainTest {

    @Test
    public void trialRunTest() throws Exception {
    Main test = new Main();
        System.out.println(test.getNamesAndPrices(test.readRawDataToString()));
    }

    @Test
    public void printListTest() throws Exception {
        Main test = new Main();
        test.getNamesAndPrices(test.readRawDataToString());
        test.printList();
    }

    @Test
    public void removeNamePriceTest() throws Exception {
        Main test = new Main();
        String testString = test.getNamesAndPrices(test.readRawDataToString());
        System.out.println(test.removePrice(test.removeName(testString)));
    }

    @Test
    public void findGroupsTest() throws Exception {
        Main test = new Main();
        String testString = test.getNamesAndPrices(test.readRawDataToString());
        String useThisString = test.removePrice(test.removeName(testString));
        test.parseStringToItems(useThisString);
    }

    @Test
    public void parseStringIntoItems() throws Exception {
        Main test = new Main();
        String testString = test.getNamesAndPrices(test.readRawDataToString());
        String useThisString = test.removePrice(test.removeName(testString));
        test.parseStringToItems(test.replaceAll(useThisString));
        ArrayList<Item> testItems = test.getItems();
        testItems.stream().forEach(i -> System.out.println(i.name + " " + i.price));
    }

    @Test
    public void putIntoMapTest() throws Exception {
        Main test = new Main();
        String testString = test.getNamesAndPrices(test.readRawDataToString());
        String useThisString = test.removePrice(test.removeName(testString));
        test.parseStringToItems(test.replaceAll(useThisString));
        test.putIntoMap();
        test.map.forEach((b,c) ->{
            System.out.println(b + " seen :" + (c));
        });

    }
}
