package TestHanoiSim;
import HanoiSim.HanoiDisk;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Random;

public class TestDisk {

    int low = 1;
    int high = 1000;
    Random r = new Random();

    @Test
    public void testNumberOfDisks(){
        int randomDiskNum = r.nextInt(high-low)+low;

        for(int i =0; i<randomDiskNum;i++){
            new HanoiDisk();
        }

        Assertions.assertEquals(randomDiskNum,HanoiDisk.getTotalNumberOfDisks());
    }

    @Test
    public void testDiskNum(){

    }

}
