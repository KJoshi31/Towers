package HanoiSim;

public abstract class HanoiObject {
    private static int totalHanoiObjects;
    static final int TOTALNUMTOWER = 3;

    abstract void display();

    static void increaseHanoiObjects(){
        totalHanoiObjects++;
    }

    public static int getHanoiObjectCount(){
        return totalHanoiObjects;
    }
}
