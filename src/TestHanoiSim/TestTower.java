package TestHanoiSim;

import HanoiSim.HanoiTower;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class TestTower {

    int low = 1;
    int high = 1000;
    Random r = new Random();
    ArrayList<HanoiTower> towerList;
    int randomTowerAmt;


    private void setUp() {
        randomTowerAmt = r.nextInt(high - low) + low;
        towerList = new ArrayList<>();

        for (int i = 0; i < randomTowerAmt; i++) {
            towerList.add(new HanoiTower());
            //System.out.println(towerList.get(i).getTowerName());
        }
    }

    @Test
    public void testTowerName() {
        //System.out.println(randomTowerAmt);
        setUp();
        String[] towerNames = {"Left", "Middle", "Right"};
        for (int i = 0; i < towerList.size(); i++) {
            //System.out.println("i: " + i);
            //System.out.println(towerList.get(0).getTowerName());
            assertEquals(towerNames[i % 3] + " Tower",
                    towerList.get(i).getTowerName());
        }
        HanoiTower.resetGlobalTowerCount();
        HanoiTower.resetLocalTowerCount();
    }

    @Test
    public void testGlobalTowerCount() {
        setUp();
        assertEquals(randomTowerAmt, HanoiTower.getGlobalTowerCount());
        HanoiTower.resetGlobalTowerCount();
    }

    @Test
    public void testLocalTowerCount() {
        setUp();
        int[] localTowerCount = new int[randomTowerAmt];
        for (int i = 0; i < randomTowerAmt; i++) {
            localTowerCount[i] = HanoiTower.getLocalTowerCount();
        }
        assertEquals(localTowerCount[randomTowerAmt - 1], HanoiTower.getLocalTowerCount());
        HanoiTower.resetGlobalTowerCount();
    }
}
