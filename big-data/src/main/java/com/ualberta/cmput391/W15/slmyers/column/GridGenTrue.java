package main.java.com.ualberta.cmput391.W15.slmyers.column;

public class GridGenTrue extends ColumnType implements Generator{
	
	public GridGenTrue(){
		this.type = "INT";
	}

	@Override
	public String gen() {
		return null;
	}
	
	public String genTrue(){
		return Integer.toString(randomInt(0, 100));
	}
}
