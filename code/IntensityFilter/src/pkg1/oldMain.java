

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the edmaxor.
 */
package pkg1;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;
import java.util.HashMap;

public class oldMain {
private static String source;
    public oldMain() {
        
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

        JTextField minField = new JTextField(5);
        JTextField maxField = new JTextField(5);
        JTextField n = new JTextField(5);
        JTextField minOrganismEntries = new JTextField(5);

        JPanel myPanel = new JPanel();
//        myPanel.add(new JLabel("Minimum intensity:"));
//        myPanel.add(minField);
//        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
//        myPanel.add(new JLabel("Maximum intensity:"));
//        myPanel.add(maxField);
//        myPanel.add(new JLabel("Minimum number of time data points per record:"));
//        myPanel.add(n);
        myPanel.add(new JLabel("Remove records with gene names appearing < this many times:"));
        myPanel.add(minOrganismEntries);

//        int min=-1;
//        int max=-1;
//        int nt=13;
        int mino=0;
        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Enter parameters, then drag a .csv source file to the other window.", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
//            String mi=minField.getText();
//            String ma=maxField.getText();
//            String nts=n.getText();
            String minos=minOrganismEntries.getText();
            while((/*!isInteger(mi)||!isInteger(ma)||!isInteger(nts)*/!isInteger(minos))&&result==JOptionPane.OK_OPTION){
                result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Enter parameters, then drag a .csv source file to the other window.", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
//                    mi=minField.getText();
//                    ma=maxField.getText();
//                    nts=n.getText();
                    minos=minOrganismEntries.getText();
                }
                else System.exit(0);
            }
            if (result == JOptionPane.OK_OPTION) {
//                min=Integer.parseInt(mi);
//                max=Integer.parseInt(ma);
//                nt=Integer.parseInt(nts);
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
//            while(c<line.length)
//                if(line.length>c&&line[c].length()>0&&isDouble(line[c]))
            if(h.get(line[1])>=mino)
            {
//            (line.length>7&&line[7].length()>0&&isDouble(line[7]))/*&&Double.parseDouble(line[7])>=min&&Double.parseDouble(line[7])<=max)*/||
//            (line.length>8&&line[8].length()>0&&isDouble(line[8]))/*&&Double.parseDouble(line[8])>=min&&Double.parseDouble(line[8])<=max)*/||
//            (line.length>9&&line[9].length()>0&&isDouble(line[9]))/*&&Double.parseDouble(line[9])>=min&&Double.parseDouble(line[9])<=max)*/||
//            (line.length>10&&line[10].length()>0&&isDouble(line[10]))/*&&Double.parseDouble(line[10])>=min&&Double.parseDouble(line[10])<=max*/||
//            (line.length>11&&line[11].length()>0&&isDouble(line[11]))/*&&Double.parseDouble(line[11])>=min&&Double.parseDouble(line[11])<=max*/||
//            (line.length>12&&line[12].length()>0&&isDouble(line[12]))/*&&Double.parseDouble(line[12])>=min&&Double.parseDouble(line[12])<=max*/||
//            (line.length>13&&line[13].length()>0&&isDouble(line[13]))/*&&Double.parseDouble(line[13])>=min&&Double.parseDouble(line[13])<=max*/||
//            (line.length>14&&line[14].length()>0&&isDouble(line[14]))/*&&Double.parseDouble(line[14])>=min&&Double.parseDouble(line[14])<=max*/||
//            (line.length>15&&line[15].length()>0&&isDouble(line[15]))/*&&Double.parseDouble(line[15])>=min&&Double.parseDouble(line[15])<=max*/||
//            (line.length>16&&line[16].length()>0&&isDouble(line[16]))/*&&Double.parseDouble(line[16])>=min&&Double.parseDouble(line[16])<=max*/||
//            (line.length>17&&line[17].length()>0&&isDouble(line[17]))/*&&Double.parseDouble(line[17])>=min&&Double.parseDouble(line[17])<=max*/||
//            (line.length>18&&line[18].length()>0&&isDouble(line[18]))/*&&Double.parseDouble(line[18])>=min&&Double.parseDouble(line[18])<=max*/))
            
//                int i=7, ntc=nt;
//                while(ntc>0&&i<line.length){
//                    if(!line[i].isEmpty())
//                        ntc--;
//                    i++;
//                }
//                if(ntc<1){
                    for(int i=0;i<line.length;i++){
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
                    w.newLine();
//                }
            }
        }
        s.close(); w.close();
        JOptionPane.showMessageDialog(myPanel, "Finished. Results stored in "+System.getProperty("user.dir")+"\\CadaverOutput.csv.");
        System.exit(0);
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