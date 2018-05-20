package InputOutputLogic;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class HanoiFileLogic {

    public static void saveSteps(String stepsParam){
        Scanner saveInput = new Scanner(System.in);
        System.out.println("Please Enter the directory to save the file:");
        String directory = saveInput.nextLine();
        System.out.println("Please Enter the file name:");
        String fileName = saveInput.nextLine();

        saveStepFile(fileName,directory,stepsParam);
    }

    private static void saveStepFile(String fileNameParam, String directoryParam, String steps){

        Formatter output = null;

        if(directoryParam.endsWith("\\")){
            directoryParam = directoryParam.substring(0,directoryParam.length()-1);
            System.out.println(directoryParam);
        }

        try{
            output = new Formatter(directoryParam+"\\"+fileNameParam);
        } catch (FileNotFoundException ex){
            System.out.println("Steps could not be saved!");
            System.out.println("Directory+File Name combo invalid.");
        }

        output.format(steps);
        output.close();
        System.out.println("Save Successful");

    }

}
