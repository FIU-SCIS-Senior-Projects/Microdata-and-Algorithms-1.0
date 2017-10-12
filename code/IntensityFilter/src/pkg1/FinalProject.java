/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author Owner
 */

import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;

 
 
public class FinalProject { 
 private static String source; 
     public FinalProject() { 
          
    }
      
     public static void main(String[] args) throws Exception{ 
          javax.swing.JFrame frame = new javax.swing.JFrame("Drag and drop .csv source file here");

        final javax.swing.JTextArea text = new javax.swing.JTextArea();

        frame.getContentPane().add(

            new javax.swing.JScrollPane( text ), 

            java.awt.BorderLayout.CENTER );

        source = "a";

        new FileDrop( System.out, text, new FileDrop.Listener()

        {   public void filesDropped( java.io.File[] files )

            {     

                for( int i = 0; i < files.length; i++ )

                { try {

                        text.append( files[i].getCanonicalPath() + "\n" );

                        source=files[i].getCanonicalPath();

                }

                catch( java.io.IOException e ) {}

                }

            }   // end filesDropped

        }); // end FileDrop.Listener

        frame.setBounds( 100, 100, 300, 400 );

        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );

        frame.setVisible(true);
  
        JTextField geneEntries = new JTextField(10);



        JPanel myPanel = new JPanel();

        myPanel.add(new JLabel("Enter the number so we can remove gene names that appear < "));

        myPanel.add(geneEntries);

        int mino=0;

        int result = JOptionPane.showConfirmDialog(null, myPanel, 

                 "Enter parameters, then drag a .csv source file to the other window.", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            String minos=geneEntries.getText();

            while((!isInteger(minos))&&result==JOptionPane.OK_OPTION){

                result = JOptionPane.showConfirmDialog(null, myPanel, 

                 "Enter parameters, then drag a .csv source file to the other window.", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {

                    minos=geneEntries.getText();

                }

                else System.exit(0);

            }

            if (result == JOptionPane.OK_OPTION) {

                mino=Integer.parseInt(minos);

            }

            else System.exit(0);
        }

        else System.exit(0);

        

        while(source.equals("a")) //while source file has not been set (default is "a")

            System.out.println("");

        BufferedReader s = new BufferedReader(new FileReader(new File(source)));

        BufferedWriter w = new BufferedWriter(new FileWriter(new File("CadaverOutput.csv")));

        String line[];

        HashMap<String,Integer> h = new HashMap<>();

        while(s.ready()){

            line = s.readLine().split(",");

            String geneName=line[1];

            if(h.containsKey(geneName))

                h.replace(geneName, h.get(geneName)+1);

            else

                h.put(geneName, 1);

        }

        s.close(); w.close();

        

        s = new BufferedReader(new FileReader(new File(source)));

        w = new BufferedWriter(new FileWriter(new File("CadaverOutput.csv")));

        int c = 7;

        line = s.readLine().split(",");

        for(int i=0;i<line.length;i++)

        {

            w.write(line[i]);

            if(i<line.length-1)

                w.write(",");

        }

        w.newLine();

        while(s.ready()){

            line = s.readLine().split(",");

            if(h.get(line[1])>=mino)

            {

                int i=0;

                    for(i=0;i<line.length;i++){

                            String l=line[i];

                            if(isDouble(l)){

                                Long rounded = Math.round(Double.parseDouble(line[i]));

                                w.write(rounded.toString());

                            }

                            else

                                w.write(l);

                            if(i<line.length-1)

                                w.write(",");

                    }

                    while(i<19){

                        w.write(",");

                        i++;

                    }

                    w.newLine();

            }

        }
        
        
        s.close(); w.close();

        JOptionPane.showMessageDialog(myPanel, "Finished. Results stored in "+System.getProperty("user.dir")+"\\CadaverOutput.csv.");
        
        geneSubQuery();
        
        System.exit(0);

    }

    public static void geneSubQuery(){
        try {
            //This is the file you are reading from/writing to
            File file = new File(System.getProperty("user.dir")+"\\ProjectData.csv");

            //Thisis the file to write to
            File newFile = new File(System.getProperty("user.dir")+"\\ProjectOutput.csv");

            //Creates a reader for the file
            BufferedReader br = new BufferedReader(new FileReader(file));
            
             BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));

            String line;
            //String line;

            String result = JOptionPane.showInputDialog(null, 
                    "Please input Organism name. "
                            + "(Inputs that don't exist in "
                            + "file will result in empty file)", 
                    "Need Organism name", 
                    JOptionPane.QUESTION_MESSAGE);
            //This is your buffer, where you are writing all your lines to
            List<String> fileContents = new ArrayList<String>();

                        //loop through each line
            //while (result != null) {
            
            if(result == null){//Cancel with no input or close the dialog box
                JOptionPane.showMessageDialog(null, 
                        "Error. Empty file",
                        "Error box",
                        JOptionPane.ERROR_MESSAGE);
            } else if(result.isEmpty()){//Ok with no input
                JOptionPane.showMessageDialog(null,
                        "No. You got it wrong. "
                         + "Same file, no changes",
                        "Error Box 2",
                        JOptionPane.ERROR_MESSAGE);
            }
          
            
                while ((line = br.readLine()) != null) {
                                   

                    if (line.matches("(.*)" + result + "(.*)") || line.matches("(.*)" + result) || line.matches(result + "(.*)")) {
                        fileContents.add(line);
                    
                    } 
                    
            }

                
                //close our reader so we can re-use the file
                br.close();

                //create a writer
               

                //loop through our buffer
                for (String s : fileContents) {
                    //write the line to our file
                    bw.write(s);
                    bw.newLine();
                }

                //close the writer
                bw.close();
                
                JOptionPane.showMessageDialog(null, "Finished. Results stored in "+System.getProperty("user.dir")+"\\ProjectOutput.csv.");
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isString(String input){
        if(!input.matches("[a-zA-Z ,_]*")){
            return false;
        }
        
        return true;
    }
     
    public static boolean isInteger(String str) {

        if (str == null) {

            return false;

        }

        int length = str.length();

        if (length == 0) {

            return false;

        }

        int i = 0;

        if (str.charAt(0) == '-') {

            if (length == 1) {

                return false;

            }

            i = 1;

        }

        for (; i < length; i++) {

            char c = str.charAt(i);

            if (c < '0' || c > '9') {

                return false;

            }

        }

        return true;

    }

    

    public static boolean isDouble(String str) {

        if (str == null) {

            return false;

        }

        int length = str.length();

        if (length == 0) {

            return false;

        }

        int i = 0;

        if (str.charAt(0) == '-') {

            if (length == 1) {

                return false;

            }

            i = 1;

        }

        boolean decimalFlag=false;

        for (; i < length; i++) {

            char c = str.charAt(i);

            if ((c < '0' || c > '9')) {

                if(c=='.'){

                    if(decimalFlag==true)

                        return false;

                    decimalFlag=true;

                }

                else return false;

            }

        }

        return true;

    }

    

}
