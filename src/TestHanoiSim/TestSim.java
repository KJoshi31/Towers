package TestHanoiSim;

import HanoiSim.HanoiSim;
import HanoiSim.HanoiTower;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class TestSim {

    int low = 1;
    int high = 250;
    Random r = new Random();

    int randomSimAmt;
    int randomDiskAmt;


    @Test
    public void testTowerSimulation() {
        HanoiTower.resetLocalTowerCount();

        HanoiSim testSim = new HanoiSim(2);
        testSim.runSimulation();
        String testSteps = "Move Disk #1 from Left Tower to Middle Tower\n" +
                "Move Disk #2 from Left Tower to Right Tower\n" +
                "Move Disk #1 from Middle Tower to Right Tower";
        Assertions.assertEquals(testSteps, testSim.getSteps());
    }

    @Test
    public void testMultiSims() {
        ArrayList<HanoiSim> simList = new ArrayList<>();

        for (int i = 0; i < randomSimAmt; i++) {
            simList.add(new HanoiSim(i));
        }

        for (int i = 0; i < simList.size() - 1; i++) {
            Assertions.assertNotEquals(simList.get(i)
                    .getSteps(), simList.get(i + 1).getSteps());
        }

    }

}
