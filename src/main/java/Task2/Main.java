package Task2;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You need to type in five absolute paths separated by whitespaces.\nYour first bugban analysis output file, you second bugban analysis output file, the program output file for the problems only detected in the first analysis, the program output file for the problems only detected in the second analysis and the program output file for the problems detected in both analyses");
        String[] directories;
        File[] files = new File[5];
        directories = scanner.nextLine().split("\\s+");
        if(directories.length != 5){
            System.out.println("We need FIVE absolute paths to work with");
            System.out.println("Did you even type in paths?");
            return;
        }
        Task2 task2 = new Task2();
        for(int i = 0; i < 5; i++){
            files[i] = new File(directories[i]);
            if(!task2.isValidJson(files[i])){
                System.out.println("Input file " + i + " is no valid json file");
                return;
            }
        }
        task2.task2(files);
    }
}