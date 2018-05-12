package HanoiSim;

public class HanoiDisk extends HanoiObject {
    private static int numberOfDisks;
    private int diskNumber;

    public HanoiDisk(){
        numberOfDisks++;
        this.diskNumber = numberOfDisks;
    }

    int getDiskNumber(){
        return diskNumber;
    }

    static int getNumberOfDisks(){
        return numberOfDisks;
    }

    @Override
    public void display() {

    }
}
