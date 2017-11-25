/**
 * @author UCSD MOOC development team
 * 
 * Utility class to add vertices and edges to a graph
 *
 */
package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;

import graph.KEdge;
import graph.KGraph;

public class GraphLoader {
    /**
     * Loads graph with data from a file.
     * The file should consist of lines with 2 integers each, corresponding
     * to a "from" vertex and a "to" vertex.
     */ 
    public static void loadGraph(KGraph g, String filename) {
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
            while(sc.hasNextLine()){
                String str = sc.nextLine();
                if (str.charAt(0) != 's') parseLine(g, str);
              }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Iterate over the lines in the file, adding new
        // vertices as they are found and connecting them with edges.
  
        sc.close();
    }
    private static void parseLine(KGraph g, String str){
    	  Scanner sc = new Scanner(str);
    	  sc.useDelimiter(",");
    	  // Check if there is another line of input
    	  while(sc.hasNext()){
    	   int a = Integer.parseInt(sc.next());
    	   int b = Integer.parseInt(sc.next());
    	   g.addEdge(a, b);
    	  }
    	  sc.close();
    }
    
    public static boolean exportCsv(File file, List<KEdge> dataList){
        boolean isSucess=false;
        
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            bw.write("source,target");
            bw.write("\r\n");
            if(dataList!=null && !dataList.isEmpty()){
                for(KEdge e : dataList){
                	bw.write(Integer.toString(e.getFirstNode().getIndex()));
                	bw.write(',');
                	bw.write(Integer.toString(e.getSecondNode().getIndex()));
                	bw.write("\r\n");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return isSucess;
    }

}	