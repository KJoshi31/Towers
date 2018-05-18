package TestHanoiSim;
import HanoiSim.HanoiTower;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Random;

public class TestTower {

    int low = 1;
    int high = 1000;
    Random r = new Random();

    @Test
    public void testTowerName(){
        int randomTowerAmt = r.nextInt(high-low)+low;

        String[] towerNames = {"Left","Middle","Right"};
        ArrayList<HanoiTower> towerList = new ArrayList<HanoiTower>();

        for(int i = 0; i<randomTowerAmt; i++){
            towerList.add(new HanoiTower());
        }

        for (int i = 0; i<towerList.size(); i++){
            Assertions.assertEquals(towerNames[i%3]+" Tower",towerList.get(i).getTowerName());
        }
    }

    @Test
    public void testTowerCount(){
        int randomTowerAmt = r.nextInt(high-low)+low;
        ArrayList<HanoiTower> towerList = new ArrayList<HanoiTower>();

        for(int i = 0; i<randomTowerAmt; i++){
            towerList.add(new HanoiTower());
        }

        Assertions.assertEquals(randomTowerAmt, HanoiTower.getHanoiObjectCount());
    }
}
