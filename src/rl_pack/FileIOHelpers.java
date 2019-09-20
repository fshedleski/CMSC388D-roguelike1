package rl_pack;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileIOHelpers {
    public static ArrayList<String> readAllLines(String filename) {
        File file = new File(filename);
        ArrayList<String> output = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                String st;
                while ((st = br.readLine()) != null) {
                    output.add(st);
                }
            } catch(IOException e) {
                return null;
            }
        } catch(FileNotFoundException e) {
            return null;
        }

        return output;
    }

    public static ArrayList<ArrayList<String>> readCSV(String filename) {
        ArrayList<String> lines = readAllLines(filename);
        if(lines == null) { return null; }
        ArrayList<ArrayList<String>> vals = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < lines.size(); i++) {
            vals.add(new ArrayList<String>(Arrays.asList(lines.get(i).split(","))));
        }
        return vals;
    }
}

