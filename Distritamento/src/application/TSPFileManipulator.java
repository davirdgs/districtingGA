package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import elements.CoordGraph;
import elements.Coordinate;
import metaheuristics.ga.AbstractGA.Chromosome;
import solutions.Solution;

public class TSPFileManipulator {

	private static File[] finder(String dirName) {
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() { 
			public boolean accept(File dir, String filename)
			{ return filename.endsWith(".tsp"); }
		} );
	}

	public static void LKH() throws IOException {
		
		String filePath = "./Instancias/LKH";
		try {
			ProcessBuilder pb = new ProcessBuilder(filePath, "/Users/davirdgs/Desktop/Repositorios/districtingGA/Distritamento/Instancias/pr2392.par");//"pr2392.par");
			pb.redirectErrorStream(true);
			Process p = pb.start();
			InputStream is = p.getInputStream();
	        int value = -1;
	        
	        while ((value = is.read()) != -1) {
	            System.out.print((char) value);
	        }
	        
	        int exitCode = p.waitFor();

	        System.out.println(filePath + " exited with " + exitCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void reader(File file) throws IOException {
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
	
	public static void createSegmentFile(CoordGraph graph, ArrayList<Integer> sol) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("lkhinput.tsp", "UTF-8");
		writer.println("COMMENT : drilling problem (Ludwig)");
		writer.println("TYPE : TSP");
		writer.println("DIMENSION: " + sol.size());
		writer.println("EDGE_WEIGHT_TYPE : EUC_2D");
		writer.println("NODE_COORD_SECTION");
		for(int i = 0; i < sol.size(); i++) {
			writer.println(sol.get(i) + " " + graph.get(i).x + " " + graph.get(i).y);
		}
		writer.close();
	}

	public static CoordGraph createGraphFromFile(String fileName) throws IOException {

		File[] fileList = TSPFileManipulator.finder("./Instancias");
		int fileIndex = -1;

		CoordGraph cGraph = new CoordGraph();

		for(int i = 0; i < fileList.length; i++) {
			if(fileList[i].getName().compareTo(fileName) == 0) {
				fileIndex = i;
				break;
			}
		}

		if(fileIndex < 0) {
			System.out.println("Arquivo não encontrado");
			return null;
		}
		/*
		try {
			System.out.println(fileList[fileIndex].getName());
			TSPFileManipulator.reader(fileList[fileIndex]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */

		BufferedReader buffRead = new BufferedReader(new FileReader(fileList[fileIndex]));
		String linha = "";
		boolean data = false;
		while (true) {
			linha = buffRead.readLine();
			if (linha != null) {
				if(!data && linha.compareTo("NODE_COORD_SECTION") != 0) {
					continue;
				} else if(!data && linha.compareTo("NODE_COORD_SECTION") == 0) {
					data = true;
					continue;
				} else if(data && linha.compareTo("EOF") != 0) {
					Coordinate coord = parseStringCoordinate(linha);
					cGraph.add(coord);
					if(coord.x > cGraph.maxX) {
						cGraph.maxX = coord.x;
					}
					if(coord.y > cGraph.maxY) {
						cGraph.maxY = coord.y;
					}
					if(coord.x < cGraph.minX) {
						cGraph.minX = coord.x;
					}
					if(coord.y < cGraph.minY) {
						cGraph.minY = coord.y;
					}
				}

			} else
				break;

		}
		buffRead.close();

		return cGraph;
	}

	private static Coordinate parseStringCoordinate(String s) {
		//System.out.println(s);
		boolean index = false;
		boolean xSet = false;
		boolean ySet = false;
		double x = 0;
		double y = 0;

		String[] tokens = s.split(" ");
		for (String t : tokens) {
			if(t.compareTo("") != 0) {
				if(!index) {
					index = true;
					continue;
				} else if(!xSet) {
					xSet = true;
					x = Double.parseDouble(t);
				} else if(!ySet) {
					ySet = true;
					y = Double.parseDouble(t);
				}
			}
		}
		Coordinate coord = new Coordinate(x, y);
		return coord;
	}

}
