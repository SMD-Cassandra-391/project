package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileUtils{
    
	public static void outputPage(ArrayList<String> lines, String fileName, String Dir){
        Path path = Paths.get(Dir + File.separatorChar + fileName);
        try{
            Files.write(path,lines,StandardCharsets.UTF_8);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
	
	public static void deleteDir(String dir){
		Path directory = Paths.get(File.pathSeparatorChar + dir);
		   try {
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
				   @Override
				   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					   Files.delete(file);
					   return FileVisitResult.CONTINUE;
				   }

				   @Override
				   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					   Files.delete(dir);
					   return FileVisitResult.CONTINUE;
				   }

			   });
		} catch (IOException e) {
			// empty catch directory doesn't exist it's cool
		}
	}
	
	public static void createDir(String dirName){
		File theDir = new File(dirName);
		
		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + dirName);
		    boolean result = false;
		
		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(!result) {    
		        System.out.println("Unable to create directory: " + dirName);  
		    }
		}
    }
	
	public static ArrayList<String> readFileDesc() throws IOException {
		Path path = Paths.get("resources/bigdata_setup1.sql");
	    List<String> file = java.nio.file.Files.readAllLines(
	            path, Charset.defaultCharset());

	    return new ArrayList<String>(file);
	}
	
	public static String getTableDesc(){
		StringBuilder sb = new StringBuilder(2000);
		ArrayList<String> fileIn = null;
		String[] words;
		sb.append("(");
		try {
			fileIn = readFileDesc();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s : fileIn){
			words = s.split(" ");
			sb.append(words[0]);
			if(fileIn.indexOf(s) != fileIn.size()){
				sb.append(",");
				sb.append(" ");
			}
		}
		sb.append(")");
		return sb.toString();
	}
}