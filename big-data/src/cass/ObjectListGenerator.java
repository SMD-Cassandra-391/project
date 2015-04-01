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
				cols = getThreadOneGen();
				break;
			case 1:
				cols = getThreadTwoGen();
				break;
			case 2:
				cols = getThreadThreeGen();
				break;
		}
	}
	
	public ArrayList<ObjectGenerator> getThreadOneGen() {
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
		gens.add(new IPGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IPGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IPGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		return gens;
	}
	
	public ArrayList<ObjectGenerator> getThreadTwoGen() {
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
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new DecimalGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		gens.add(new CharGenerator(random.nextInt()));
		gens.add(new IntGenerator(random.nextInt()));
		return gens;
	}
	
	public ArrayList<ObjectGenerator> getThreadThreeGen() {
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
