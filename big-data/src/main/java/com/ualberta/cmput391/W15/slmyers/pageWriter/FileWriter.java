package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriter{
    public static final String OUTPUT = "cdr_page.csv";
    public
	static void outputPage(ArrayList<String> lines, String fileName){
        Path path = Paths.get(Output.DIR_NAME + File.separatorChar + fileName);
        try{
            Files.write(path,lines,StandardCharsets.UTF_8);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}