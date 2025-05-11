import org.example.Service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

public class ServiceTest {
    @Test
    void testProcessData() throws Exception {
        String pathOrders = "src/test/resources/orders.json";
        String pathPayment = "src/test/resources/paymentmethods.json";

        HashMap<String, Double> result = Service.ProcessData(pathOrders, pathPayment);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(165.00, result.get("mZysk"));
        assertEquals(190.00, result.get("BosBankrut"));
        assertEquals(100.00, result.get("PUNKTY"));

    }
}
