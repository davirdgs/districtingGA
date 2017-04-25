package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;

import metaheuristics.ga.AbstractGA;

public class TSPFileManipulator {

	public static File[] finder(String dirName) {
		File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith(".tsp"); }
        } );
	}
	
	public static void reader(File file) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(file));
        String linha = "";
        while (true) {
            if (linha != null) {
                System.out.println(linha);
 
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
    }
	
}
