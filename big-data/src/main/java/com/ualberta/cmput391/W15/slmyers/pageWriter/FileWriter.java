package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriter{
    private static final String OUTPUT = "cdr_page.csv";
    private static void outputPage(ArrayList<String> lines){
        Path path = Paths.get(OUTPUT);
        try{
            Files.write(path,lines,StandardCharsets.UTF_8);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}