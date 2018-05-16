package HanoiSim;

import HanoiSim.HanoiSim;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

public class TestSim {

    int low = 1;
    int high = 1000;
    Random r = new Random();

    @Test
    public void testCreateDisks(){

        int randomDiskNum = r.nextInt(high-low)+low;

        HanoiSim simTest = new HanoiSim(randomDiskNum);

        Object o[] = simTest.createDisks();

        Assertions.assertEquals(o.length,randomDiskNum);
    }

    @Test
    public void testInitDisk(){
        int randomDiskNum = r.nextInt(high-low)+low;
        HanoiTower a = new HanoiTower();
        HanoiTower b = new HanoiTower();
        HanoiTower c = new HanoiTower();

        HanoiSim simTest = new HanoiSim(randomDiskNum);
        HanoiDisk[] disks =  simTest.createDisks();
        HanoiSim.initializeDiskSet(a,disks);

        Assertions.assertEquals(a.diskSet.size(),randomDiskNum);
    }

    @Test
    public void testResetSim(){
        int high = 15;
        int randomDiskNum = r.nextInt(high-low)+low;
        HanoiTower a = new HanoiTower();
        HanoiTower b = new HanoiTower();
        HanoiTower c = new HanoiTower();

        HanoiSim simTest = new HanoiSim(randomDiskNum);
        HanoiDisk[] disks =  simTest.createDisks();
        HanoiSim.initializeDiskSet(a,disks);
        simTest.towerSimulation(randomDiskNum,a,c,b);

        simTest.resetSimulation(a,b,c);

        Assertions.assertEquals(a.diskSet.size(),randomDiskNum);
        Assertions.assertEquals(b.diskSet.size(),0);
        Assertions.assertEquals(c.diskSet.size(),0);


    }

    @Test
    public void testTowerSimulation(){
        int high = 15;
        int randomDiskNum = r.nextInt(high-low)+low;
        HanoiTower a = new HanoiTower();
        HanoiTower b = new HanoiTower();
        HanoiTower c = new HanoiTower();

        HanoiSim simTest = new HanoiSim(randomDiskNum);
        HanoiDisk[] disks =  simTest.createDisks();
        HanoiSim.initializeDiskSet(a,disks);

        simTest.towerSimulation(randomDiskNum,a,c,b);

        Assertions.assertEquals(c.diskSet.size(),randomDiskNum);
        Assertions.assertEquals(a.diskSet.size(),0);
        Assertions.assertEquals(b.diskSet.size(),0);
    }

}
