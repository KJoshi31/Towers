package TestHanoiSim;

import HanoiSim.HanoiTower;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class TestTower {

    int low = 1;
    int high = 1000;
    Random r = new Random();
    ArrayList<HanoiTower> towerList;
    int randomTowerAmt;

    @BeforeEach
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
        System.out.println(randomTowerAmt);
        String[] towerNames = {"Left", "Middle", "Right"};
        for (int i = 0; i < towerList.size(); i++) {
            System.out.println("i: " + i);
            System.out.println(towerList.get(0).getTowerName());
            Assertions.assertEquals(towerNames[i % 3] + " Tower",
                    towerList.get(i).getTowerName());
        }
        HanoiTower.resetGlobalTowerCount();
        HanoiTower.resetLocalTowerCount();
    }

    @Test
    public void testGlobalTowerCount() {
        HanoiTower.resetGlobalTowerCount();
        Assertions.assertEquals(randomTowerAmt, HanoiTower.getGlobalTowerCount());
        HanoiTower.resetGlobalTowerCount();
    }

    @Test
    public void testLocalTowerCount() {
        int[] localTowerCount = new int[randomTowerAmt];
        for (int i = 0; i < randomTowerAmt; i++) {
            localTowerCount[i] = HanoiTower.getLocalTowerCount();
        }
        Assertions.assertEquals(localTowerCount[randomTowerAmt - 1], HanoiTower.getLocalTowerCount());
        HanoiTower.resetGlobalTowerCount();
    }
}
