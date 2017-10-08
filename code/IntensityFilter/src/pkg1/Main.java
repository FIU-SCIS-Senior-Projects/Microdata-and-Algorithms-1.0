package pkg1;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;
import java.util.HashMap;

public class Main {
private static String source;
    public Main() {
        
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
        myPanel.add(new JLabel("Remove records with gene names appearing < this many times:"));
        myPanel.add(minOrganismEntries);
        int mino=0;
        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Enter parameters, then drag a .csv source file to the other window.", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String minos=minOrganismEntries.getText();
            while((!isInteger(minos))&&result==JOptionPane.OK_OPTION){
                result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Enter parameters, then drag a .csv source file to the other window.", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    minos=minOrganismEntries.getText();
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