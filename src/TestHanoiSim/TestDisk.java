package TestHanoiSim;

import HanoiSim.HanoiDisk;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class TestDisk {

    int low = 1;
    int high = 1000;
    Random r = new Random();
    int randomDiskNum;
    ArrayList<HanoiDisk> diskArray;


    private void setUp(){
        HanoiDisk.resetStartDiskNum();

        randomDiskNum = r.nextInt(high - low) + low;
        diskArray = new ArrayList<>();

        for (int i = 0; i < randomDiskNum; i++) {
            diskArray.add(new HanoiDisk());
        }
        HanoiDisk.resetStartDiskNum();

    }

    @Test
    public void testNumberOfDisks() {
        setUp();
        if(HanoiDisk.getTotalNumberOfDisks()>0){
            assert true;
        }else{
            assert false;
        }
    }

    @Test
    public void testDiskNum() {
        setUp();
        int counter = 1;
        for (HanoiDisk d : diskArray) {
            assertEquals(d.getDiskNumber(), counter);
            counter++;
        }
        HanoiDisk.resetStartDiskNum();

    }

}
