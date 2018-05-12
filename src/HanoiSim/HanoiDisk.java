package HanoiSim;

public class HanoiDisk extends HanoiObject {
    private int numberOfDisks;
    private int diskNumber;

    public HanoiDisk(){
        ++numberOfDisks;
        this.diskNumber = numberOfDisks;
    }

    int getDiskNumber(){
        return diskNumber;
    }

    int getNumberOfDisks(){
        return numberOfDisks;
    }

    @Override
    public void display() {

    }
}
