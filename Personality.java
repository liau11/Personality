import java.util.*;
import java.io.*;

public class Personality {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);

        intro();
        System.out.print("input file name? ");
        String inputFile = console.nextLine();
        Scanner input = new Scanner(new File(inputFile));

        // System.out.print("output file name? ");
        // String outputFile = console.nextLine();
        // PrintStream output = new PrintStream(new File(outputFile));
        String testResponse = "";
        String name = "";
        while(input.hasNextLine()){
            name = input.nextLine();
            testResponse = input.nextLine();
            int[] bArr = new int[4]; 
            int [] aArr = new int [4];
            countSingleUser(name, testResponse, aArr, bArr);
        }



    }

    public static void intro(){
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter. It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }

    public static void countSingleUser(String name, String testResponse, int[] aArr, int [] bArr){
        for (int i = 0; i < testResponse.length(); i++) {
            char response = testResponse.charAt(i);
            if (i % 7 == 0){
                if (response == 'B') {
                    bArr[0]++;
                } else if (response == 'A') {
                    aArr[0]++; 
                } 
            } else if (i % 7 == 1 || i % 7 == 2){
                if (response == 'B') {
                    bArr[1]++;
                } else if (response == 'A') {
                    aArr[1]++; 
                }
            } else if (i % 7 == 3 || i % 7 == 4){
                if (response == 'B') {
                    bArr[2]++;
                } else if (response == 'A') {
                    aArr[2]++; 
                }
            } else if (i % 7 == 5 || i % 7 == 6){
                if (response == 'B') {
                    bArr[3]++;
                } else if (response == 'A') {
                    aArr[3]++; 
                }
            }
        }
        System.out.print(name);
        System.out.print(Arrays.toString(aArr));
        System.out.print(Arrays.toString(bArr));
        System.out.println();
    }
}