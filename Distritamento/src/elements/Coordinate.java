package elements;

public class Coordinate {
	public double x;
	public double y;
	
	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Coordinate newCoordinate(double x, double y) {
		Coordinate coord = new Coordinate(x,y);
		return coord;
	}
}
