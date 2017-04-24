package elements;

public class TSPInstance {

	private String name;
	private InstanceType type;
	private String comment;
	private int dimension;
	private int capacity;

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
	
}
