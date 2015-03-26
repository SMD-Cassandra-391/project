package main.java.com.ualberta.cmput391.W15.slmyers.cass;

import java.util.ArrayList;

import ObjectGenerator.BigIntGenerator;
import ObjectGenerator.DateGenerator;
import ObjectGenerator.IPGenerator;
import ObjectGenerator.IntGenerator;
import ObjectGenerator.LatGenerator;
import ObjectGenerator.LongitudeGenerator;
import ObjectGenerator.ObjectGenerator;
import ObjectGenerator.PhoneGenerator;

public class ObjectListGenerator {
	private ArrayList<ObjectGenerator> cols;

	public ObjectListGenerator() {
		this.initializeCols();
	}

	private void initializeCols() {
		cols = new ArrayList<ObjectGenerator>();

		cols.add(new IntGenerator());
		cols.add(new IntGenerator());
		for (int i = 0; i < 3; i++) {
			cols.add(new DateGenerator());
		}
		for (int i = 0; i < 6; i++) {
			cols.add(new PhoneGenerator());
		}

		cols.add(new LatGenerator());
		cols.add(new LongitudeGenerator());

		cols.add(new IntGenerator());
		cols.add(new IntGenerator());
		cols.add(new LatGenerator());
		cols.add(new LongitudeGenerator());
		cols.add(new IntGenerator());
		cols.add(new IntGenerator());
		cols.add(new IntGenerator());
		cols.add(new BigIntGenerator());
		for (int i = 0; i < 7; i++)
			cols.add(new IntGenerator());

		cols.add(new BigIntGenerator());
		cols.add(new IPGenerator());
		for (int i = 0; i < 16; i++)
			cols.add(new IntGenerator());

		cols.add(new IPGenerator());
		cols.add(new IPGenerator());
		for (int i = 0; i < 31; i++)
			cols.add(new IntGenerator());

		cols.add(new IPGenerator());
		for (int i = 0; i < 7; i++)
			cols.add(new IntGenerator());

		cols.add(new IPGenerator());
		for (int i = 0; i < 382; i++)
			cols.add(new IntGenerator());
	}
	
	public ArrayList<Object> genRow(){
		ArrayList<Object> row = new ArrayList<Object>();
		for(ObjectGenerator gen : cols){
			row.add(gen.gen());
		}
		return row;
	}
}
