package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import main.java.com.ualberta.cmput391.W15.slmyers.cass.Application;
import main.java.com.ualberta.cmput391.W15.slmyers.cass.Setup;

public class Output {
	
	public static final String DATA_DIR = "data";
	public static final String SHELL_DIR = "shell";

	public static void main(String[] args) {
		

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
