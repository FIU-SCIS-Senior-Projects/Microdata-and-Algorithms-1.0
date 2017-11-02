/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intensityfilter;

/**
 *
 * @author Owner
 */
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class IntensityFilter {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic hereimport java.io.File;

        try {

            JOptionPane.showMessageDialog(null,
                    "Please enter number for input. "
                    + "If your number is too small for "
                    + " our records then you get empty"
                    + " file",
                    "Message disclaimer ",
                    JOptionPane.INFORMATION_MESSAGE);

            File file = new File("C:/Users/Owner/Desktop/ProjectData.csv");

            File newFile = new File("C:/Users/Owner/Desktop/ProjectIntensityOutput.csv");

            BufferedReader csvFileReader = new BufferedReader(new FileReader(file));

            BufferedWriter csvOutput = new BufferedWriter(new FileWriter(newFile));

            String options = JOptionPane.showInputDialog(null,
                    "Please enter a letter a, b, or c "
                    + "for the option to have min, max "
                    + "or range filter respectively."
                    + " No other letters will be accepted."
                    + " Thank you.",
                    "Need Letter Input",
                    JOptionPane.QUESTION_MESSAGE);

            if (options == null || options.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Error. Need letter option",
                        "ErrorBox 3",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } else {

                if (options.equalsIgnoreCase("a")) {

                    JOptionPane.showMessageDialog(null,
                            "You have chosen the min or "
                            + "less than part of the program",
                            "Min box",
                            JOptionPane.INFORMATION_MESSAGE);

                    String min = JOptionPane.showInputDialog(null,
                            "Please enter number that "
                            + "for the min "
                            + "intensity filter",
                            "Need numeric input",
                            JOptionPane.QUESTION_MESSAGE);

                    if (min == null || min.isEmpty()) {//Cancel with no input or close the dialog box or press ok with no input
                        JOptionPane.showMessageDialog(null,
                                "Error. Empty file",
                                "Error box",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        int numResult = Integer.parseInt(min);

                        String[] high;

                        ArrayList<String> list = new ArrayList();

                        int count = 0;

                        String hi;

                        while ((hi = csvFileReader.readLine()) != null) {
                            high = hi.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
//                    boolean flag = false;
//                    String hi2 = csvFileReader.readLine();
//                for(int z = 0; z < high.length;z++){
//                    System.out.print(high[z]+ " ");
//                }
                            //System.out.println();
                            for (int x = 7; x < high.length; x++) {
                                if (high[x].length() != 0 && count != 0) {
                                    int y = Integer.parseInt(high[x]);
                                    if (y < numResult) {
                                        list.add(hi);
                                        break;
                                        //System.out.println(high[x]);
                                    }

                                }

                            }
                            count++;
                        }

                        // System.out.println("Hello");
                        for (String x : list) {
                            csvOutput.write(x);
                            csvOutput.newLine();
                        }

                        csvFileReader.close();
                        csvOutput.close();

                        JOptionPane.showMessageDialog(null,
                                "Bye everybody. Thanks for"
                                + " your business",
                                "Farewell box",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                } else if (options.equalsIgnoreCase("b")) {
                    JOptionPane.showMessageDialog(null,
                            "You have chosen the max or "
                            + "the greater than part of the program",
                            "Max box",
                            JOptionPane.INFORMATION_MESSAGE);

                    String max = JOptionPane.showInputDialog(null,
                            "Please enter number that "
                            + "for the max "
                            + "intensity filter",
                            "Need numeric input",
                            JOptionPane.QUESTION_MESSAGE);

                    if (max == null || max.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Error. Empty file",
                                "Error box 2",
                                JOptionPane.ERROR_MESSAGE);
                    } else {

                        int numResult = Integer.parseInt(max);

                        String[] high;

                        ArrayList<String> list = new ArrayList();

                        int count = 0;

                        String hi;

                        while ((hi = csvFileReader.readLine()) != null) {
                            high = hi.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
//                    boolean flag = false;
//                    String hi2 = csvFileReader.readLine();
//                for(int z = 0; z < high.length;z++){
//                    System.out.print(high[z]+ " ");
//                }
                            //System.out.println();
                            for (int x = 7; x < high.length; x++) {
                                if (high[x].length() != 0 && count != 0) {
                                    int y = Integer.parseInt(high[x]);
                                    if (y > numResult) {
                                        list.add(hi);
                                        break;
                                        //System.out.println(high[x]);
                                    }

                                }

                            }
                            count++;
                        }

                        // System.out.println("Hello");
                        for (String x : list) {
                            csvOutput.write(x);
                            csvOutput.newLine();
                        }

                        csvFileReader.close();
                        csvOutput.close();

                        JOptionPane.showMessageDialog(null,
                                "Bye everybody. Thanks for"
                                + " your business",
                                "Farewell box",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                } else if (options.equalsIgnoreCase("c")) {
                    JOptionPane.showMessageDialog(null,
                            "You have chosen the  min and max or "
                            + "the less than and greater than part of the program",
                            "Max box",
                            JOptionPane.INFORMATION_MESSAGE);

                    String min = JOptionPane.showInputDialog(null,
                            "Please enter number that "
                            + "for the min "
                            + "intensity filter",
                            "Need numeric input",
                            JOptionPane.QUESTION_MESSAGE);

                    String max = JOptionPane.showInputDialog(null,
                            "Please enter number that "
                            + "for the max "
                            + "intensity filter",
                            "Need numeric input",
                            JOptionPane.QUESTION_MESSAGE);

                    int numResult = Integer.parseInt(min);

                    int numResult2 = Integer.parseInt(max);

                    String[] high;

                    ArrayList<String> list = new ArrayList();

                    int count = 0;

                    String hi;

                    while ((hi = csvFileReader.readLine()) != null) {
                        high = hi.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
//                    boolean flag = false;
//                    String hi2 = csvFileReader.readLine();
//                for(int z = 0; z < high.length;z++){
//                    System.out.print(high[z]+ " ");
//                }
                        //System.out.println();
                        for (int x = 7; x < high.length; x++) {
                            if (high[x].length() != 0 && count != 0) {
                                int y = Integer.parseInt(high[x]);
                                if (y > numResult && y < numResult2) {
                                    list.add(hi);
                                    break;
                                    //System.out.println(high[x]);
                                }

                            }

                        }
                        count++;
                    }

                    // System.out.println("Hello");
                    for (String x : list) {
                        csvOutput.write(x);
                        csvOutput.newLine();
                    }

                    csvFileReader.close();
                    csvOutput.close();

                    JOptionPane.showMessageDialog(null,
                            "Bye everybody. Thanks for"
                            + " your business",
                            "Farewell box",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Wrong letter, sir or madam",
                            "WE ARE OUT",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (NumberFormatException e) {
            System.out.print(e.getMessage());
        }
    }
}