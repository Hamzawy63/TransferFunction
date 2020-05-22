
import org.junit.Assert;
import org.junit.Test;

public class UnitTests {
    @Test
    public void test1() {
        GraphAdapter graphAdapter = new GraphAdapter(6);
        graphAdapter.addEdge(1, 2, 1);
        graphAdapter.addEdge(2, 5, 3);
        graphAdapter.addEdge(2, 3, 5);
        graphAdapter.addEdge(3, 2, -7);
        graphAdapter.addEdge(3, 4, 11);
        graphAdapter.addEdge(4, 5, 23);
        graphAdapter.addEdge(5, 4, -13);
        graphAdapter.addEdge(5, 2, -17);
        graphAdapter.addEdge(5, 6, 19);
        MasonMetaInformation masonMetaInformation = graphAdapter.fillMasonInformation();
        double delta = 1 - ((-35.0) + (-21505.0) + (-51.0) + (-299.0)) + (10465.0);
        double m1 = 24035;
        double m2 = 57;
        Assert.assertEquals((m1 + m2) /delta,masonMetaInformation.getTransferFunction(), 0.00000000001f);

    }
    @Test
    public void test2() {
        GraphAdapter graphAdapter = new GraphAdapter(7);
        graphAdapter.addEdge(1, 2, 1);
        graphAdapter.addEdge(2, 3, 5);
        graphAdapter.addEdge(2, 6, 10);
        graphAdapter.addEdge(3, 4, 10);
        graphAdapter.addEdge(4, 5, 2);
        graphAdapter.addEdge(4, 3, -1);
        graphAdapter.addEdge(5, 4, -2);
        graphAdapter.addEdge(5, 7, 1);
        graphAdapter.addEdge(5, 2, -1);
        graphAdapter.addEdge(6, 5, 2);
        graphAdapter.addEdge(6, 6, -1);
        MasonMetaInformation masonMetaInformation = graphAdapter.fillMasonInformation();
        Assert.assertEquals(14/(double)15,masonMetaInformation.getTransferFunction(), 0.00000000001f);

    }

}
