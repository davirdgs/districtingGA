package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import metaheuristics.ga.AbstractGA;

public class TSPFileManipulator {

	public static AbstractGA fileReader(String path) throws IOException{
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		
		return null;
	}
	
}
