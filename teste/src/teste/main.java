package teste;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws java.lang.Exception {
		/*
		Scanner in = new Scanner(System.in);
		int count = in.nextInt();
		int inputNumber = in.nextInt();
		int[] vector = new int[count];
		int[] inputs = new int[inputNumber];
		boolean[] output = new boolean[inputNumber];
		
		for(int i = 0; i < count; i ++) {
			vector[i] = in.nextInt();
		}
		
		for(int i = 0; i < inputNumber; i ++) {
			inputs[i] = in.nextInt();
		}
		
		for(int i = 0; i < inputNumber; i++) {
			for(int j = 0; j < count; j++) {
				if(inputs[i] == vector[j]) {
					output[i] = true;
				}
			}
		}
		
		for(int i = 0; i < inputNumber; i++) {
			if(output[i]) {
				System.out.println("True");
			} else {
				System.out.println("False");
			}
		}
		*/
		
		
		
		
		Scanner in = new Scanner(System.in);
	    
	    int count = in.nextInt();
	    int[] input = new int[count];
	    
	    for(int i = 0; i < count; i++) {
	    	input[i] = in.nextInt();
	    }
	    
	    
		float sum = 0;
		float average = 0;
		
		int output = 0;
		float dif = Float.MAX_VALUE;
		
		for(int i = 0; i < count; i++) {
			sum = sum + input[i];
		}
		average = sum / count;
		
		
		for(int i = 0; i < count; i++) {
			if(Math.abs(average - input[i]) < dif) {
				dif = average - input[i];
				output = input[i];
			}
		}
		
		System.out.println(output);
		
		
		
		/*
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String in = br.readLine();
	    
	    int count = Integer.parseInt(in);
	    int[] output = new int[count];
		
	    for(int i = 0; i < count; i++) {
	    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		    String inS = bf.readLine();
		    output[i] = nextFibo(Integer.parseInt(inS));
	    }
	   
		
		for(int i = 0; i < count; i ++) {
			System.out.println(output[i]);
		}
		*/
	}
	
	public static int nextFibo(int nextTo) {
		
		int fibo = 0;
		int lastFibo = 0;
		while(true) {
			if(fibo > nextTo) {
				return fibo;
			}
			if(fibo == 0) {
				fibo = 1;
				continue;
			}
			
			int aux = fibo;
			fibo = fibo + lastFibo;
			lastFibo = aux;
		}
	}

}
