//Form 1: Gene #,species/organism #,intensity range, # intensity points
//Form 2: 2-Filter (prioritze: Superkingom, Organism/Species, gene name, gene category),
//arbitrary number
//-tell user to separate each record
package pkg1;
import java.awt.GridLayout;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;
import java.util.HashMap;

public class Main {
private static String source;
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
            for (File file : files) {
                try {
                    text.append(file.getCanonicalPath() + "\n");
                    source = file.getCanonicalPath().toUpperCase();
                }catch( java.io.IOException e ) {}
            }
            }   // end filesDropped
        }); // end FileDrop.Listener
        frame.setBounds( 100, 100, 300, 400 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
        
        while(source.equals("a")) //while source file has not been set (default is "a")
            System.out.print("");
        BufferedReader s = new BufferedReader(new FileReader(new File(source)));
        String line[];
        int llength=0;
        HashMap<String,Integer> geneNameHash = new HashMap<>();
        HashMap<String,Integer> OrganismHash = new HashMap<>();
	HashMap<Integer,int[]> dph = new HashMap<>();
        /*dph identifies where record indexes begin and end. Each record
        consists of 2 numbers:
        1. The record starting index
        2. The record ending index*/
        if(s.ready()){
            String[] read = s.readLine().split(",");
            llength=read.length;
            line=new String[llength];
            System.arraycopy(read, 0, line, 0, read.length);
            int c1=0;
            int i=0;
            while(c1<line.length){
                int currentIndex=c1;
                while(c1<line.length-1&&!line[c1+1].substring(0,1).equals("#")){
                    c1++;
                }
                dph.put(i, new int[]{currentIndex,c1});
                i++;
                c1++;
            }
        }
        JTextField[] columns=new JTextField[dph.get(0)[1]+1];
        String[] columnQuery=new String[columns.length];
        for(int a=0; a<columns.length; a++){
            columns[a]=new JTextField(5);
        }
        JTextField separate = new JTextField(5);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Enter search term separator:"));
        myPanel.add(separate);
        String searchSeparator="";
        int result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Enter search term separator:", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
            searchSeparator = separate.getText();
        else
            System.exit(0);
        
        myPanel = new JPanel();
        myPanel.setLayout(new GridLayout(12,1));
        for(int a=0; a<columns.length; a++){
            myPanel.add(new JLabel("Column "+(a+1)+" Search (Insert \""+searchSeparator+"\" between each search term):"));
            myPanel.add(columns[a]);
        }
        
        JTextField minField = new JTextField(5);
        JTextField maxField = new JTextField(5);
        JTextField minPoints = new JTextField(5);
        JTextField minGeneNames = new JTextField(5);
        JTextField minOrganisms = new JTextField(5);
        
        myPanel.add(new JLabel("Minimum intensity:"));
        myPanel.add(minField);
        myPanel.add(new JLabel("Maximum intensity:"));
        myPanel.add(maxField);
        myPanel.add(new JLabel("Remove records with < this many data points:"));
        myPanel.add(minPoints);
        myPanel.add(new JLabel("Remove records with gene names appearing < this many times:"));
        myPanel.add(minGeneNames);
        myPanel.add(new JLabel("Remove records with organism names appearing < this many times:"));
        myPanel.add(minOrganisms);
        double min=-1; //intensity range minimum
        double max=-1; //intensity range maximum
        int ming=0; //min # gene names
        int mino=0; //min # organism names
        int minp=0; //min # data points per record
        result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Enter search parameters", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            for(int a=0; a<columns.length; a++)
                columnQuery[a]=columns[a].getText();
            
            String mi=minField.getText();
            String ma=maxField.getText();
            String mings=minGeneNames.getText();
            String minos=minOrganisms.getText();
            String minps=minPoints.getText();
            while((!isDouble(mi)||!isDouble(ma)||!isInteger(mings)||!isInteger(minos)||!isInteger(minps))&&result==JOptionPane.OK_OPTION){
                result = JOptionPane.showConfirmDialog(null, myPanel, 
                 "Enter search parameters", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION){
                    for(int a=0; a<columns.length; a++)
                        columnQuery[a]=columns[a].getText();
                    
                    mi=minField.getText();
                    ma=maxField.getText();
                    mings=minGeneNames.getText();
                    minos=minOrganisms.getText();
                    minps=minPoints.getText();
                }
                else System.exit(0);
            }
            if (result == JOptionPane.OK_OPTION){
                for(int a=0; a<columns.length; a++)
                    columnQuery[a]=columns[a].getText();
                    
                min=Double.parseDouble(mi);
                max=Double.parseDouble(ma);
                ming=Integer.parseInt(mings);
                mino=Integer.parseInt(minos);
                minp=Integer.parseInt(minps);
            }
            else System.exit(0);
        }
        else System.exit(0);
        
        long start = System.currentTimeMillis();
        line=new String[llength];
        while(s.ready()){
            String[] read = s.readLine().split(",");
            int i=0;
            int count=0;
            for(i=0;i<read.length;i++){
                if(read[i].length()>1&&read[i].substring(0,1).equals("\"")){
                    String read2="";
                    while(!read[i].substring(read[i].length()-1,read[i].length()).equals("\"")){
                        read2+=read[i]+",";
                        i++; count++;
                    }
                    read2+=read[i];
                    line[i-count]=read2;
                }
                else
                    line[i-count]=read[i];
            }
            while(i<line.length){
                line[i-count]="";
                i++;
            }
            String geneName=line[1];
            String organism=line[5];
            if(geneNameHash.containsKey(geneName))
                geneNameHash.replace(geneName, geneNameHash.get(geneName)+1);
            else
                geneNameHash.put(geneName, 1);
            if(OrganismHash.containsKey(organism))
                OrganismHash.replace(organism, OrganismHash.get(organism)+1);
            else
                OrganismHash.put(organism, 1);
        }
        s.close();
        
        s = new BufferedReader(new FileReader(new File(source)));
        BufferedWriter w = new BufferedWriter(new FileWriter(new File("output.csv")));
        line = s.readLine().split(",");
        for(int i=0;i<line.length;i++)  /*header start*/
        {
            w.write(line[i]);
            if(i<line.length-1)
                w.write(",");
        }
        w.newLine();                    /*header end*/
        while(s.ready()){
            String[] read = s.readLine().split(",");
            int i=0;
            int count=0;
            for(i=0;i<read.length;i++){
                if(read[i].length()>1&&read[i].substring(0,1).equals("\"")){
                    String read2="";
                    while(!read[i].substring(read[i].length()-1,read[i].length()).equals("\"")){
                        read2+=read[i]+",";
                        i++; count++;
                    }
                    read2+=read[i];
                    line[i-count]=read2;
                }
                else
                    line[i-count]=read[i];
            }
            while(i<line.length){
                line[i-count]="";
                i++;
            }
            boolean[] in = new boolean[columnQuery.length];
            i=0;
            while(i<columnQuery.length){
                String[] a = columnQuery[i].split(searchSeparator);
                int i2=0;
                while(!in[i]&&i2<a.length){
                    if(containsIgnoreCase(line[i],(a[i2])))
                        in[i]=true;
                    i2++;
                }
                i++;
            }
            boolean allSearchTermsMatch=true;
            i=0;
            while(allSearchTermsMatch&&i<in.length){
                if(!in[i])
                    allSearchTermsMatch=false;
                i++;
            }
            if(allSearchTermsMatch&&geneNameHash.get(line[1])>=ming&&OrganismHash.get(line[5])>=mino)
            {
                /*nonEmpty is used to check whether at least 1 sub-record (such as a cadaver)
                contains at least 1 point matching the criteria entered by the user and, if so, the entire line is written.*/
                boolean nonEmpty=false;
                String toWrite="";
                for(int it=1;it<dph.size();it++){
                    int index=dph.get(it)[0];
                    int nonEmptyCount=0;
                    String li; double d;
                    while(index<=dph.get(it)[1]){
                        li=line[index];
                        if(isDouble(li)){
                            d=Double.parseDouble(li);
                            if(d>=min&&d<=max)
                                nonEmptyCount++;
                        }
                        index++;
                    }
                    index=dph.get(it)[0];
                    if(nonEmptyCount>=minp){
                        nonEmpty=true;
                        while(index<=dph.get(it)[1]){
                            li=line[index];
                            if(isDouble(li)){
                                d=Double.parseDouble(li);
                                if(d>=min&&d<=max)
                                    toWrite+=line[index]+",";
                                else
                                    toWrite+="*,";
                            }
                            else
                                toWrite+=",";
                            index++;
                        }
                    }
                    else{
                        while(index<=dph.get(it)[1]){
                            li=line[index];
                            if(isDouble(li)){
                                d=Double.parseDouble(li);
                                if(d>=min&&d<=max)
                                    toWrite+="p,";
                                else
                                    toWrite+="*p,";
                            }
                            else
                                toWrite+=",";
                            index++;
                        }
                    }
                }
                if(nonEmpty){
                    for(int a=0;a<dph.get(1)[0];a++)
                        w.write(line[a]+",");
                    w.write(toWrite.substring(0,toWrite.length()-1));
                    w.newLine();
                }
            }
        }
        s.close(); w.close();
        JOptionPane.showMessageDialog(myPanel, "Finished. Results stored in "+System.getProperty("user.dir")+"\\output.csv.");
        System.out.println(System.currentTimeMillis()-start);
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
    
    public static boolean containsIgnoreCase(String src, String what) {
        final int length = what.length();
        if (length == 0)
            return true; // Empty string is contained

        final char firstLo = Character.toLowerCase(what.charAt(0));
        final char firstUp = Character.toUpperCase(what.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;
            if (src.regionMatches(true, i, what, 0, length))
                return true;
        }
        return false;
    }
    
}