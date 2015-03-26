package output;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cass.Application;
import cass.SSTwriter;
import cass.Setup;


public class Output {
	public static final int COL_NUM = 470;
	public static final String DATA_DIR = "data";
	public static final String SHELL_DIR = "shell";
	public static Application app;
	public static String tableDesc = new String();
	
	public static void main(String[] args) {
		initApp();
		String questionString = buildQuestionString(COL_NUM);
		SSTwriter writer = new SSTwriter(createStatement, tableDesc, questionString); 
	}

	public static void initApp(){
		app = Application.getApp();
		tableDesc = app.getTableDesc();
	}
	
	public static String buildQuestionString(int colNum){
		// 470 ?
		// 469 ,
		// 469 " "
		// means 1408 chars going to 1420 for extra safety
		StringBuilder sb = new StringBuilder(1420);
		for(int i = 0; i < 469; i++){
			sb.append("?");
			sb.append(",");
			sb.append(" ");
		}
		sb.append("?");
		return new String(sb.toString());
	}
	
	public static void printStartMsg() {
		System.out.println("**********************************");
		System.out.println("*  begining data generation      *");
		System.out.println("**********************************");
	}

	/*
	 * long size = FileUtils.sizeOfDirectory(new File("C:/Windows/folder"));
	 * 
	 * System.out.println("Folder Size: " + size + " bytes");
	 */

	public static void printEndMsg(long totalTime) {
		System.out.println("**********************************");
		System.out.println("*     ending data generation     *");
		System.out.println("**********************************");
	}

	public static long folderSize(File directory) {
		long length = 0;
		for (File file : directory.listFiles()) {
			if (file.isFile())
				length += file.length();
			else
				length += folderSize(file);
		}
		return length;
	}
}
