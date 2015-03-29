package cass;

import java.util.ArrayList;

import objectGenerator.BigIntGenerator;
import objectGenerator.CharGenerator;

import objectGenerator.DecimalGenerator;
import objectGenerator.IPGenerator;
import objectGenerator.IntGenerator;
import objectGenerator.ObjectGenerator;
import objectGenerator.PhGenerator;



public class ObjectListGenerator {
	private ArrayList<ObjectGenerator> cols;
	private int threadId;

	public ObjectListGenerator(int threadId) {
		this.threadId = threadId;
		this.initializeCols();
	}
	
	private void initializeCols() {
		cols = null;
		switch(threadId){
			case 1:
				cols = getThreadOneGen();
				break;
			case 2:
				cols = getThreadTwoGen();
				break;
			case 3:
				cols = getThreadThreeGen();
				break;
			case 4:
				cols = getThreadFourGen();
				break;
		}
	}
	
	public ArrayList<ObjectGenerator> getThreadOneGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		gens.add(new BigIntGenerator());
		for(int i = 0; i < 4; i++){
			gens.add(new IntGenerator());
		}
		gens.add(new PhGenerator());
		gens.add(new PhGenerator());
		
		for(int i = 0; i < 3; i++)
			gens.add(new DecimalGenerator());
		
		for(int i = 0; i < 4; i++)
			gens.add(new IntGenerator());
		
		gens.add(new DecimalGenerator());
		
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		
		for(int i = 0; i < 4; i++)
			gens.add(new IntGenerator());
		
		gens.add(new IPGenerator());
		gens.add(new IPGenerator());
		return gens;
	}
	
	
	public ArrayList<ObjectGenerator> getThreadTwoGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		gens.add(new BigIntGenerator());
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		
		for(int i = 0; i < 4; i++)
			gens.add(new IntGenerator());
		
		gens.add(new DecimalGenerator());
		gens.add(new DecimalGenerator());
		
		for(int i = 0; i < 4; i++)
			gens.add(new IntGenerator());
		
		gens.add(new PhGenerator());
		gens.add(new PhGenerator());
		gens.add(new CharGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new DecimalGenerator());
		for(int i = 0; i < 4; i++)
			gens.add(new IntGenerator());
		return gens;
	}
	
	
	
	public ArrayList<ObjectGenerator> getThreadThreeGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		gens.add(new BigIntGenerator());
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new IntGenerator());
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new IntGenerator());
		gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new CharGenerator());
		gens.add(new PhGenerator());
		gens.add(new CharGenerator());
		gens.add(new PhGenerator());
		gens.add(new CharGenerator());
		gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new CharGenerator());
		for(int i = 0 ; i < 3; i++){
			gens.add(new IntGenerator());
		}
		gens.add(new PhGenerator());
		gens.add(new CharGenerator());
		for(int i = 0; i < 3; i++){
			gens.add(new DecimalGenerator());
		}
		for(int i = 0; i< 5; i++){
			gens.add(new IntGenerator());
		}
		gens.add(new CharGenerator());
		gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		return gens;
	}
	/*
	 * + 
			
			
			
			+ "origVideoCap_Codec_Channel2 decimal, "
			+ "origVideoCap_Bandwidth_Channel2 int, "
			+ "origVideoCap_Resolution_Channel2 decimal, "
			+ "origVideoTransportAddress_IP_Channel2 int, "
			+ "origVideoTransportAddress_Port_Channel2 int, "
			+ "origVideoChannel_Role_Channel2 int, "
	 */

	public ArrayList<ObjectGenerator> getThreadFourGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		gens.add(new BigIntGenerator());
		for(int i = 0; i< 5; i++)
			gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new CharGenerator());
		gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		for(int i = 0; i< 6; i++)
			gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new PhGenerator());
		gens.add(new PhGenerator());
		gens.add(new IPGenerator());
		gens.add(new IPGenerator());
		
		gens.add(new DecimalGenerator());
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		for(int i = 0; i< 3; i++)
			gens.add(new IntGenerator());
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
