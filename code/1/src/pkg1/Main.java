

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
import javax.swing.JOptionPane;

public class Main {
private static String source[];
    public Main() {
        
    }
    
    public static void main(String[] args) throws Exception{
        javax.swing.JFrame frame = new javax.swing.JFrame("Drag and drop source data file here");
        final javax.swing.JTextArea text = new javax.swing.JTextArea();
        frame.getContentPane().add(
            new javax.swing.JScrollPane( text ), 
            java.awt.BorderLayout.CENTER );
        source = new String[]{"a","b"};
        new FileDrop( System.out, text, new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {     
                for( int i = 0; i < files.length; i++ )
                { try {
                        text.append( files[i].getCanonicalPath() + "\n" );
                        source[i]=files[i].getCanonicalPath();
                }
                catch( java.io.IOException e ) {}
                }
            }   // end filesDropped
        }); // end FileDrop.Listener
        frame.setBounds( 100, 100, 300, 400 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
        
        int min=Integer.MIN_VALUE; //intensity threshold minimum
        int max=Integer.MAX_VALUE; //intensity threshold maximum
        
        String st=JOptionPane.showInputDialog("Enter the minimum intensity to search for.");
        boolean check=isInteger(st);
        if(check)
            min=Integer.parseInt(st);
        st=JOptionPane.showInputDialog("Enter the maximum intensity to search for.");
        check=isInteger(st);
        if(check)
            max=Integer.parseInt(st);
        
        while(source[0].equals("a")||source[1].equals("b"))
            System.out.println("");
        BufferedReader s = new BufferedReader(new FileReader(new File(source[0])));
        BufferedWriter w = new BufferedWriter(new FileWriter(new File(source[1])));
        int count=0;
        String line[];
        while(s.ready()){
            line = s.readLine().split(",");
            count ++;
            if((line.length>7&&line[7].length()>0&&isInteger(line[7])&&Integer.parseInt(line[7])>=min&&Integer.parseInt(line[7])<=max)||
            (line.length>8&&line[8].length()>0&&isInteger(line[8])&&Integer.parseInt(line[8])>=min&&Integer.parseInt(line[8])<=max)||
            (line.length>9&&line[9].length()>0&&isInteger(line[9])&&Integer.parseInt(line[9])>=min&&Integer.parseInt(line[9])<=max)||
            (line.length>10&&line[10].length()>0&&isInteger(line[10])&&Integer.parseInt(line[10])>=min&&Integer.parseInt(line[10])<=max)||
            (line.length>11&&line[11].length()>0&&isInteger(line[11])&&Integer.parseInt(line[11])>=min&&Integer.parseInt(line[11])<=max)||
            (line.length>12&&line[12].length()>0&&isInteger(line[12])&&Integer.parseInt(line[12])>=min&&Integer.parseInt(line[12])<=max)||
            (line.length>13&&line[13].length()>0&&isInteger(line[13])&&Integer.parseInt(line[13])>=min&&Integer.parseInt(line[13])<=max)||
            (line.length>14&&line[14].length()>0&&isInteger(line[14])&&Integer.parseInt(line[14])>=min&&Integer.parseInt(line[14])<=max)||
            (line.length>15&&line[15].length()>0&&isInteger(line[15])&&Integer.parseInt(line[15])>=min&&Integer.parseInt(line[15])<=max)||
            (line.length>16&&line[16].length()>0&&isInteger(line[16])&&Integer.parseInt(line[16])>=min&&Integer.parseInt(line[16])<=max)||
            (line.length>17&&line[17].length()>0&&isInteger(line[17])&&Integer.parseInt(line[17])>=min&&Integer.parseInt(line[17])<=max)||
            (line.length>18&&line[18].length()>0&&isInteger(line[18])&&Integer.parseInt(line[18])>=min&&Integer.parseInt(line[18])<=max)){
                w.write(count+" ");//System.out.print(count+" ");
                for(String str: line)//{
                    w.write(str+" ");//System.out.print(str+" ");}
                w.newLine();//System.out.println("");
            }
        }
        s.close(); w.close();
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
    
    
}