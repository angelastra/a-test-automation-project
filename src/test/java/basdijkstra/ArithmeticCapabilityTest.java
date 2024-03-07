package basdijkstra;

import org.junit.Assert;
import org.junit.Test;

public class ArithmeticCapabilityTest {


    @Test
    public void additionPass(){
        int add = 2+2;
        Assert.assertEquals(4, add);
    }

    @Test
    public void additionFail(){
        int add = 3+7;
        Assert.assertEquals(7, add);
    }
}
