package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;

import elements.CoordGraph;
import elements.Coordinate;

public class TSPFileManipulator {

	private static File[] finder(String dirName) {
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() { 
			public boolean accept(File dir, String filename)
			{ return filename.endsWith(".tsp"); }
		} );
	}

	public static void LKH() {

		try {
			String line;
			Process p = Runtime.getRuntime().exec("./Instancias/LKH pr2392.par");
			BufferedReader in = new BufferedReader(
					new InputStreamReader(p.getInputStream()) );
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
		} catch (IOException e) {
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
