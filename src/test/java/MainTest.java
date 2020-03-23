import org.junit.Test;

public class MainTest {

    @Test
    public void trialRunTest() throws Exception {
    Main test = new Main();
    test.getNamesAndPrices(test.readRawDataToString());
    }
}
