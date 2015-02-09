package com.ualberta.cmput391.W15.slmyers.pageWriter;
import com.ualberta.cmput391.W15.slmyers.column.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Output{
    private static final String OUTPUT = "cdr_page.txt";
    public static void main( String[] args ){
        
    }

    private static void generatePage(){
        ArrayList<ColumnType> cols = new ArrayList<ColumnType>();
    }

    private static void outputPage(ArrayList<String> lines){
        Path path = Paths.get(OUTPUT);
        try{
            Files.write(path,lines,StandardCharsets.UTF_8);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}