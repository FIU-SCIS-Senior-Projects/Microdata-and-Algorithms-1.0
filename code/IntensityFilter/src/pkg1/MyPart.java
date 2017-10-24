/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Owner
 */
public class MyPart {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            //This is the file you are reading from/writing to
            File file = new File("C:/Users/Owner/Desktop/ProjectData.csv");

            //Thisis the file to write to
            File newFile = new File("C:/Users/Owner/Desktop/ProjectOutcome.csv");

            //Creates a reader for the file
            BufferedReader br = new BufferedReader(new FileReader(file));
            
             BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));

            String line;
            //String line;

            String result = JOptionPane.showInputDialog(null, 
                    "Please input gene name, organism name or etc. "
                            + "(Inputs that don't exist in "
                            + "file will result in empty file)", 
                    "Need input box", 
                    JOptionPane.QUESTION_MESSAGE);
            
            String result2 = JOptionPane.showInputDialog(null,
                    "Please enter valid input",
                    "Need input box 2",
                    JOptionPane.QUESTION_MESSAGE);
            
            String result3 = JOptionPane.showInputDialog(null,
                    "Please input valid input",
                    "Input box 3",
                    JOptionPane.QUESTION_MESSAGE);
            //This is your buffer, where you are writing all your lines to
            List<String> fileContents = new ArrayList<String>();

                        //loop through each line
            //while (result != null) {
            
            if((result == null) && (result2==null) && (result3 == null)){//Cancel with no input or close the dialog box
                JOptionPane.showMessageDialog(null, 
                        "Error. Empty file",
                        "Cancel or close Error box",
                        JOptionPane.ERROR_MESSAGE);
            } else if((result.isEmpty())&&(result2.isEmpty())&&(result3.isEmpty())){//Ok with no input
                JOptionPane.showMessageDialog(null,
                        "No. You got it wrong. "
                         + "Same file, no changes",
                        "Ok with no input Error box",
                        JOptionPane.ERROR_MESSAGE);
            }
            
               while ((line = br.readLine()) != null) {

                if ((line.equalsIgnoreCase(result) && line.equalsIgnoreCase(result2) && line.equalsIgnoreCase(result3))) {
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
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
