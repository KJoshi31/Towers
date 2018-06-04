package IO_Utils;

import HanoiSim.HanoiSim;

import java.io.*;
import java.util.*;


public class HanoiFileLogic {

    public static void exportSimConfig(List<Integer> configList, File filePath){

        try(ObjectOutputStream outfile = new ObjectOutputStream(
                new FileOutputStream(filePath)
        );){
            for (int i = 0; i<configList.size(); i++){
                outfile.writeObject(configList.get(i));
            }

            outfile.close();
        }catch (IOException ex){

        }


        //System.out.println("export done");

    }

    public static int[] importSimConfig(File filepath){
        //String fileName = "simConfig.dat";
        //String fullPath = fileName;

        ArrayList<Integer> configList = new ArrayList<>();

        try(ObjectInputStream infile= new ObjectInputStream(
                new FileInputStream(filepath));){

            while(true){
                configList.add((Integer) infile.readObject());
            }

        }catch (EOFException ex) {
            System.out.println("Finished Reading");

        }catch(IOException ex){
            System.out.println("IO Exception");

        }catch (ClassNotFoundException ex){

        }


        int[] configArray = new int[configList.size()];

        for(int j = 0; j< configArray.length; j++){
            configArray[j] =configList.get(j);
        }

        return configArray;
    }

    public static String readSteps(){
        Scanner readInput = new Scanner(System.in);
        System.out.println("Please Enter the directory where the file is located:");
        String directory = readInput.nextLine();
        System.out.println("Please Enter the file name:");
        String fileName = readInput.nextLine();

        return readStepFile(fileName,directory).toString().trim();
    }

    private static StringBuilder readStepFile(String fileNameParam, String directoryParam){
        String fullPath;
        StringBuilder stepsFromFile = new StringBuilder();

        if(directoryParam.length() == 0 || directoryParam.equals("")){
            fullPath = fileNameParam;
        }else{
            if(directoryParam.endsWith("\\")){
                fullPath = directoryParam+fileNameParam;
            }else{
                fullPath = directoryParam+"\\"+fileNameParam;
            }
        }

        Scanner stepsFile = null;

        try{
            stepsFile =  new Scanner(new File(fullPath));
        } catch (FileNotFoundException ex){
            System.out.println("File could not be opened!");
            System.out.println("Directory+File Name combo invalid.");
        }


            while(stepsFile.hasNext()){
                stepsFromFile.append(stepsFile.nextLine().trim()+"\n");
            }
            stepsFromFile.setLength(stepsFromFile.length()-1);

        stepsFile.close();
        return stepsFromFile;
    }

    public static void saveSteps(String stepsParam, File filepath){

        saveStepFile(stepsParam, filepath);
    }

    private static void saveStepFile(String steps, File filepath){

        try (DataOutputStream  outfile = new DataOutputStream (new FileOutputStream(filepath));)
        {
           outfile.writeUTF(steps);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void analyzeFileData(String fileStringParam) {
        String[] wordArray = fileStringParam.split("\\s+");
        int fileStepCounter = 0;

        for(String w: wordArray){
            if(w.equals("Move")||w.equals("move")){
                fileStepCounter++;
            }
        }

        int projectNumberOfDisks = getProjectedDisks(fileStepCounter);
        //System.out.println("Number of projected disks: "+projectNumberOfDisks);

        HanoiSim analysis = new HanoiSim(projectNumberOfDisks);
        analysis.runSimulation();

        //System.out.println(analysis.getSteps().equals(fileStringParam));

        if(analysis.getSteps().equals(fileStringParam)) {
            System.out.print("Number of Disks: " + projectNumberOfDisks + "\n" +
                    "Step Iterations: " + fileStepCounter);
        }else{
            System.out.println("Mismatch with steps & disks in data");
            System.out.println("Please upload a file with valid steps/data");
        }

    }

    private int getProjectedDisks(int projectedStepCount){
        int numDisks = 0;
        for(int disks =1; true; disks++){
            if((Math.pow(2,disks))-1==projectedStepCount){
                numDisks = disks;
                break;
            }
        }
        return numDisks;
    }

}
