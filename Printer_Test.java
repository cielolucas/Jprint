package dtr;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Printer_Test {
    public static void main(String[] args) {
    	
    	
    	
        
        ArrayList<String> listFileContents = new ArrayList<String>();
        
               try {
                   // Get the latest file in folder,  Open the file:
                   FileInputStream fstream = new FileInputStream("test.txt");
                   
                   // Get the object of DataInputStream
                   DataInputStream in = new DataInputStream(fstream);
                   BufferedReader br = new BufferedReader(new InputStreamReader(in));
                   String strLine;
                   //Read File Line By Line
                   while ((strLine = br.readLine()) != null) {
                       listFileContents.add(strLine);
                   }
                   //Close the input stream
                   in.close();
               } catch (Exception e) {
                   System.err.println("Error: " + e.getMessage());
                   JOptionPane.showMessageDialog(null, e.getMessage(), "File I/O Error", JOptionPane.ERROR_MESSAGE);
               }
               
        printToPrinter(listFileContents);
               
    }  // End of main()
    
    
// Print to printer method uses OutputPrinter class
// it will print the contents of jtxtAreaDiagnostic text area
               public static void printToPrinter(ArrayList<String> listOfFileContents) {
                   // Calling a method for getting all diag info out of jlstDiagnostic loaded contents:
                 

                   // Make a String out of the Arraylist<String>:
                   String printData = "";
                   Iterator<String> it =  listOfFileContents.iterator();
                   while (it.hasNext()) {
                       printData = printData + it.next();
                       printData = printData + "\r\n";
                   }

                   // Feed the data to be printed to the PrinterJob instance:
                   PrinterJob job = PrinterJob.getPrinterJob();
                   job.setPrintable(new OutputPrinter(printData));
                   boolean doPrint = job.printDialog();
                   if (doPrint) {
                       try {
                           job.print();
                       } catch (PrinterException e) {
                           // Print job did not complete.
                       }
                   }
               }
}