package TestIO_Utils;

import HanoiSim.HanoiDisk;
import HanoiSim.HanoiObject;
import HanoiSim.HanoiTower;
import IO_Utils.DisplayUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class testDisplayUtil {

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
    public void testDisplayDisks(){
        DisplayUtil<HanoiObject> a = new DisplayUtil<>();
        HanoiDisk[] disks = diskArray.toArray(new HanoiDisk[diskArray.size()]);

        a.setH(disks);
        String displayString = a.displayTool();

        StringBuilder diskString = new StringBuilder();
        for (int i = 1; i<= randomDiskNum; i++){
            diskString.append("Disk #"+i+"\n");
        }

        Assertions.assertEquals(diskString.toString(), displayString);
    }

    @Test
    public void testDisplayTowers(){

    }

    @Test
    public void testDisplayDiskAndTowers(){

    }
}
