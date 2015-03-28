package cass;

import java.util.ArrayList;

import objectGenerator.BigIntGenerator;
import objectGenerator.DateGenerator;
import objectGenerator.IPGenerator;
import objectGenerator.IntGenerator;
import objectGenerator.LatGenerator;
import objectGenerator.LongitudeGenerator;
import objectGenerator.ObjectGenerator;
import objectGenerator.PhGenerator;
import objectGenerator.PhoneGenerator;


public class ObjectListGenerator {
	private ArrayList<ObjectGenerator> cols;

	public ObjectListGenerator() {
		this.initializeCols();
	}
	
	private void initializeCols() {
		cols = new ArrayList<ObjectGenerator>();
		String schema = Application.getApp().getCreateStmnt();
		String[] entries = schema.split(",");
		for(int i = 0; i < entries.length; i++){
			if(entries[i].contains("bigint")){
				cols.add(new BigIntGenerator());
			}else if(i >= 5 && i <= 10){
				cols.add(new PhGenerator());
			}
			else if(entries[i].contains("int")){
				cols.add(new IntGenerator());
			}else if(entries[i].contains("timestamp")){
				cols.add(new DateGenerator());
			}else if(entries[i].contains("LONGITUDE")){
				cols.add(new LongitudeGenerator());
			}else if(entries[i].contains("LATITUDE")){
				cols.add(new LatGenerator());
			}else if(entries[i].contains("varchar")){
				cols.add(new IPGenerator());
			}else if(entries[i].contains("primary key(SEQ_NUM)")){
				break;
			}
		}
	}
	
	
	public ArrayList<Object> genRow(){
		ArrayList<Object> row = new ArrayList<Object>();
		for(ObjectGenerator gen : cols){
			row.add(gen.gen());
		}
		return row;
	}
}
