package elements;

import java.io.File;
import java.util.ArrayList;

public class TSPInstance {
	
	public class Coordinate {
		public int x;
		public int y;
	}
	

	private String name;
	private InstanceType type;
	private String comment;
	private int dimension;
	private int capacity;
	private InstanceEdgeType edgeType;
	private InstanceDisplayDataType dataType;
	private InstanceEdgeDataFormat dataFormat;
	private InstanceNodeCoordType coordType;
	private InstanceWeightFormat weightFormat;
	private ArrayList<Coordinate> coord;
	
	public TSPInstance(File file) {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InstanceType getType() {
		return type;
	}

	public void setType(InstanceType type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public InstanceEdgeType getEdgeType() {
		return edgeType;
	}

	public void setEdgeType(InstanceEdgeType edgeType) {
		this.edgeType = edgeType;
	}

	public InstanceDisplayDataType getDataType() {
		return dataType;
	}

	public void setDataType(InstanceDisplayDataType dataType) {
		this.dataType = dataType;
	}

	public InstanceEdgeDataFormat getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(InstanceEdgeDataFormat dataFormat) {
		this.dataFormat = dataFormat;
	}

	public InstanceNodeCoordType getCoordType() {
		return coordType;
	}

	public void setCoordType(InstanceNodeCoordType coordType) {
		this.coordType = coordType;
	}

	public InstanceWeightFormat getWeightFormat() {
		return weightFormat;
	}

	public void setWeightFormat(InstanceWeightFormat weightFormat) {
		this.weightFormat = weightFormat;
	}
	
	public int euclideanDistance(Coordinate a, Coordinate b) {
		
		int xd = a.x - b.x;
		int yd = a.y - b.y;
		int dij = (int) Math.round(Math.sqrt( xd*xd + yd*yd));
		
		return dij;
	}
	
	public int[][] generateCostMatrix() {
		
		return null;
	}

	public ArrayList<Coordinate> getCoord() {
		return coord;
	}

	public void setCoord(ArrayList<Coordinate> coord) {
		this.coord = coord;
	}
	
}
