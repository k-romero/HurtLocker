import org.junit.Test;

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
}
