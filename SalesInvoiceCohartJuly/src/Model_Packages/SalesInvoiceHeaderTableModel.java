/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Packages;

import View_Package.SalesInvoiceFrame;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author walaa
 */
public class SalesInvoiceHeaderTableModel extends AbstractTableModel {

    private ArrayList<SalesInvoiceHeader>  invoicesArray;
    
    private String [] columns = {"No.","Date","Customer","Total"};

    public SalesInvoiceHeaderTableModel(ArrayList<SalesInvoiceHeader> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }
    
    
    @Override
    public int getRowCount() {
        return invoicesArray.size();
    }

    @Override
    public int getColumnCount() {
      return columns.length ;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SalesInvoiceHeader inv = invoicesArray.get(rowIndex);
        switch(columnIndex) {
            case 0 : 
                return inv.getInvNum();
            
            case 1 :
                return SalesInvoiceFrame.dateFormat.format(inv.getInvDate());
                
            case 2 : 
                return inv.getInvCustomer();
                
           case 3 :
               return inv.getInvoiceTotal();
        
        
        }
     return "";   
    }
    
    public String getColumnName(int column){
        return columns [column];
    }
}