/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller_Packages;

import Model_Packages.SalesInvoiceHeader;
import Model_Packages.SalesInvoiceHeaderTableModel;
import Model_Packages.SalesInvoiceLine;
import Model_Packages.SalesInvoiceLineTableModel;
import View_Package.SalesInvoiceFrame;
import View_Package.SalesInvoiceHeaderDialog;
import View_Package.SalesInvoiceLineDialog;
//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author lotfy
 */
public class SalesInvoiceActionListener  implements ActionListener{
    
    
    private SalesInvoiceFrame frame;
    private SalesInvoiceHeaderDialog headerDialog;
    private SalesInvoiceLineDialog lineDialog;
    
     
    public SalesInvoiceActionListener(SalesInvoiceFrame frame){
        this.frame =frame ;
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch(e.getActionCommand()){
            case "Load File":
                LoadFiles();
                break;
        
                
            case "Save File": 
                 SaveFiles();
                break;
               
                
            case "Create New Invoice": 
                CreateNewInvoice(); 
                break; 
            
                
            case "Delete": 
                    Delete();
                break;
            
                
            case "Save": 
                SaveNewLine();
                break;
                
                
            case "Cancel": 
                Cancel();
                break; 
                
                
            case "newInvoiceOK":
                newInvoiceDialogOK();
                break;

            case "newInvoiceCancel":
                newInvoiceDialogCancel();
                break;
    
            case "newLineCancel":
                newLineDialogCancel();
                break;

            case "newLineOK":
                newLineDialogOK();
                break;    
                
        }
    }

    private void Cancel() {
         int selectedLineIndex = frame.getInvLTbl().getSelectedRow();
        int selectedInvoiceIndex = frame.getInvHTbl().getSelectedRow();
        if (selectedLineIndex != -1) {
            frame.getLinesArray().remove(selectedLineIndex);
            SalesInvoiceLineTableModel lineTableModel = (SalesInvoiceLineTableModel) frame.getInvLTbl().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getInvTotal().setText("" + frame.getInvoicesArray().get(selectedInvoiceIndex).getInvoiceTotal());
            frame.getHeadertableModel().fireTableDataChanged();
            frame.getInvHTbl().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        }
        
        
    }

    private void SaveNewLine() {
         lineDialog = new SalesInvoiceLineDialog(frame);
        lineDialog.setVisible(true);
    }

    private void Delete() {
        
        int selectedInvoiceIndex = frame.getInvHTbl().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            frame.getInvoicesArray().remove(selectedInvoiceIndex);
            frame.getHeadertableModel().fireTableDataChanged();

            frame.getInvLTbl().setModel(new SalesInvoiceLineTableModel(null));
            frame.setLinesArray(null);
            frame.getInvName().setText("");
            frame.getInvNum().setText("");
            frame.getInvTotal().setText("");
            frame.getInvDate().setText("");
        }
        
    }

    private void CreateNewInvoice() {
         headerDialog = new SalesInvoiceHeaderDialog(frame);
        headerDialog.setVisible(true);
        
    }

    private void SaveFiles() {
         ArrayList<SalesInvoiceHeader> invoicesArray = frame.getInvoicesArray();
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                String headers = "";
                String lines = "";
                for (SalesInvoiceHeader invoice : invoicesArray) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (SalesInvoiceLine line : invoice.getLines()) {
                        lines += line.toString();
                        lines += "\n";
                    }
                }
               
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                result = fc.showSaveDialog(frame);
                File lineFile = fc.getSelectedFile();
                FileWriter lfw = new FileWriter(lineFile);
                hfw.write(headers);
                lfw.write(lines);
                hfw.close();
                lfw.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }

    private void LoadFiles(){
        
        JFileChooser fileChooser = new JFileChooser();
        
        try{
             int result = fileChooser.showOpenDialog(frame);
          
                  if(result == JFileChooser.APPROVE_OPTION){
            
                         File headerFile = fileChooser.getSelectedFile();
                         Path headerPath = Paths.get(headerFile.getAbsolutePath());
                         List<String> headerLines= Files.readAllLines(headerPath);
                          ArrayList<SalesInvoiceHeader> invoiceHeaders = new ArrayList<>();
                               for(String headerLine : headerLines){
                                   String [] splitHeader =  headerLine.split(",");
               
                                         String Num = splitHeader[0];
                                         String Date = splitHeader[1];
                                         String name = splitHeader[2];
               //transform from String t integeR
                                             int codeNum = Integer.parseInt(Num);
                                              Date invoiceDate   = SalesInvoiceFrame.dateFormat.parse(Date);     
                                              SalesInvoiceHeader header = new SalesInvoiceHeader(codeNum,name,invoiceDate);
                                             invoiceHeaders.add(header);             
                                         }
                  frame.setInvoicesArray(invoiceHeaders);
              result = fileChooser.showOpenDialog(frame);
          
                  
                if(result == JFileChooser.APPROVE_OPTION){
            
                 File lineFile = fileChooser.getSelectedFile();
          
          Path linePath = Paths.get(lineFile.getAbsolutePath());
          
          List<String> lineLines= Files.readAllLines(linePath);
          
           ArrayList<SalesInvoiceLine> invoiceLines = new ArrayList<>();
           
           for(String lineLine :lineLines){
                 String [] splitLine =  lineLine.split(",");
                 
               String invoiceID = splitLine[0];
               String itemName = splitLine[1];
               String price = splitLine[2];
               String count = splitLine[3];
               
                int invID = Integer.parseInt(invoiceID);
                double invPrice = Double.parseDouble(price);
                int invCount = Integer.parseInt(count);
           
               SalesInvoiceHeader inv = frame.getInvObject(invID);
               
               SalesInvoiceLine line = new SalesInvoiceLine(itemName,invPrice,invCount,inv);
                   
               inv.getLines().add(line);
                        
                 
           }
           
          
        }
         SalesInvoiceHeaderTableModel headerTableModel = new  SalesInvoiceHeaderTableModel (invoiceHeaders);
         frame.setHeadertableModel(headerTableModel);
         frame.getInvHTbl().setModel(headerTableModel);
         System.out.println("Files read");
        }
       
        }    catch(IOException ex){
    JOptionPane.showMessageDialog(frame,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
}
   catch (ParseException ex){
    JOptionPane.showMessageDialog(frame,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    } 
}

    private void newInvoiceDialogOK() {
  headerDialog.setVisible(false);
  String custName = headerDialog.getCustNameField().getText();
        String str = headerDialog.getInvDateField().getText();
        Date date = new Date();
        try {
            date = SalesInvoiceFrame.dateFormat.parse(str);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot parse date, resetting to today.", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }

        int invNum = 0;
        for (SalesInvoiceHeader inv : frame.getInvoicesArray()) {
            if (inv.getInvNum() > invNum) {
                invNum = inv.getInvNum();
            }
        }
        invNum++;
        SalesInvoiceHeader newInv = new SalesInvoiceHeader(invNum, custName, date);
        frame.getInvoicesArray().add(newInv);
        frame.getHeadertableModel().fireTableDataChanged();
        headerDialog.dispose();
        headerDialog = null;

    }

    private void newInvoiceDialogCancel() {
  headerDialog.setVisible(false);
        headerDialog.dispose();
        headerDialog = null;

    }

    private void newLineDialogCancel() {
        
         lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    private void newLineDialogOK() {
        
        lineDialog.setVisible(false);

        String name = lineDialog.getItemNameField().getText();
        String itemcount = lineDialog.getItemCountField().getText();
        String itemPrice = lineDialog.getItemPriceField().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(itemcount);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }

        try {
            price = Double.parseDouble(itemPrice);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = frame.getInvHTbl().getSelectedRow();
        if (selectedInvHeader != -1) {
            SalesInvoiceHeader invHeader = frame.getInvoicesArray().get(selectedInvHeader);
            SalesInvoiceLine line = new SalesInvoiceLine(name, price, count, invHeader);
            frame.getLinesArray().add(line);
            SalesInvoiceLineTableModel lineTableModel = (SalesInvoiceLineTableModel) frame.getInvLTbl().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getHeadertableModel().fireTableDataChanged();
        }
        frame.getInvHTbl().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        lineDialog.dispose();
        lineDialog = null;
    }

    }
    
    
    
