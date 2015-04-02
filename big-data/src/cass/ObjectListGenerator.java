package cass;

import java.util.ArrayList;
import java.util.Random;

import objectGenerator.BigIntGenerator;
import objectGenerator.CharGenerator;
import objectGenerator.DateGenerator;
import objectGenerator.DecimalGenerator;
import objectGenerator.IPGenerator;
import objectGenerator.IntGenerator;
import objectGenerator.ObjectGenerator;
import objectGenerator.PhGenerator;

public class ObjectListGenerator {
	private ArrayList<ObjectGenerator> cols;
	private int tableId;
	private Random random;

	public ObjectListGenerator(int tableId, int seed) {
		this.tableId = tableId;
		this.random = new Random(seed);
		this.initializeCols();
		
	}
	
	private void initializeCols() {
		cols = null;
		switch(tableId){
			case 0:
				cols = getTableOneGen();
				break;
			case 1:
				cols = getTableTwoGen();
				break;
			case 2:
				cols = getTableThreeGen();
				break;
			case 3:
				cols = getTableFourGen();
				break;
			case 4:
				cols = getTableFiveGen();
				break;
		}
	}
	
	private ArrayList<ObjectGenerator> getTableFiveGen() {
		ArrayList<ObjectGenerator> gen  = new ArrayList<ObjectGenerator>();
		gen.add(new IntGenerator(random.nextInt()));
		return gen;
	}

	private ArrayList<ObjectGenerator> getTableFourGen() {
		ArrayList<ObjectGenerator> gen  = new ArrayList<ObjectGenerator>();
		gen.add(new IntGenerator(random.nextInt()));
		return gen;
	}

	/**
	 * each generator has to produce an appropriate type. 
	 * this one is for t1. Check Schemas.java for more info. 
	 * for clarity:  DecimalGenerator returns a Float.
	 */
		
	public ArrayList<ObjectGenerator> getTableOneGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		gens.add(new BigIntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IPGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		return gens;
	}
	
	public ArrayList<ObjectGenerator> getTableTwoGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		gens.add(new BigIntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IPGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IPGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new PhGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		return gens;
	}
	
	public ArrayList<ObjectGenerator> getTableThreeGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		gens.add(new BigIntGenerator(random.nextInt()));
		gens.add(new DateGenerator(random.nextInt()));
		gens.add(new DateGenerator(random.nextInt()));
		gens.add(new DateGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IPGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		return gens;
	}
	
	

	public ArrayList<Object> genRow(){
		ArrayList<Object> row = new ArrayList<Object>();
		for(ObjectGenerator gen : cols){
			row.add(gen.gen());
		}
		return row;
	}
}
