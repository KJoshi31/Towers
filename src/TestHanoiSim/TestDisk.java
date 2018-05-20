package TestHanoiSim;

import HanoiSim.HanoiDisk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class TestDisk {

    int low = 1;
    int high = 1000;
    Random r = new Random();
    int randomDiskNum;
    ArrayList<HanoiDisk> diskArray;


    @BeforeEach
    private void setUp(){
        randomDiskNum = r.nextInt(high - low) + low;
        diskArray = new ArrayList<>();

        for (int i = 0; i < randomDiskNum; i++) {
            diskArray.add(new HanoiDisk());
        }
    }

    @Test
    public void testNumberOfDisks() {
        Assertions.assertEquals(randomDiskNum, HanoiDisk.getTotalNumberOfDisks());
        HanoiDisk.resetStartDiskNum();
    }

    @Test
    public void testDiskNum() {
        int counter = 1;
        for (HanoiDisk d : diskArray) {
            Assertions.assertEquals(d.getDiskNumber(), counter);
            counter++;
        }
        HanoiDisk.resetStartDiskNum();
    }

}
