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
public class SalesInvoiceLineTableModel extends AbstractTableModel {

    private ArrayList<SalesInvoiceLine>  linesArray;
    
    private String [] columns = {"Item Name", "Item Price", "Count", "Item Total"};

    public SalesInvoiceLineTableModel(ArrayList<SalesInvoiceLine> linesArray) {
       this.linesArray = linesArray;
    }
    
    
    @Override
    public int getRowCount() {
        return linesArray == null ? 0 : linesArray.size();
    }

    @Override
    public int getColumnCount() {
      return columns.length ;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       if (linesArray == null) {
            return "";
        } else {
            SalesInvoiceLine line = linesArray.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return line.getItemName();
                case 1:
                    return line.getItemPrice();
                case 2:
                    return line.getCount();
                case 3:
                    return line.getLinesTotal();
                default:
                    return "";
    }
       }
    }
    public String getColumnName(int column){
        return columns [column];
    }
}