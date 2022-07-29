/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller_Packages;

import Model_Packages.SalesInvoiceHeader;
import Model_Packages.SalesInvoiceLine;
import Model_Packages.SalesInvoiceLineTableModel;
import View_Package.SalesInvoiceFrame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author walaa
 */
public class SalesInvoiceTableSelectionListener implements ListSelectionListener{
    
    
      private SalesInvoiceFrame frame;

    public SalesInvoiceTableSelectionListener(SalesInvoiceFrame frame) {
        this.frame = frame;
    }
      
      

    @Override
    public void valueChanged(ListSelectionEvent e) {
         int selectedInvIndex = frame.getInvHTbl().getSelectedRow();

         if (selectedInvIndex != -1) {
            SalesInvoiceHeader selectedInv = frame.getInvoicesArray().get(selectedInvIndex);
            ArrayList<SalesInvoiceLine> lines = selectedInv.getLines();
            SalesInvoiceLineTableModel lineTableModel = new SalesInvoiceLineTableModel(lines);
            frame.setLinesArray(lines);
            frame.getInvLTbl().setModel(lineTableModel);
            frame.getInvName().setText(selectedInv.getInvCustomer());
            frame.getInvNum().setText("" + selectedInv.getInvNum());
            frame.getInvTotal().setText("" + selectedInv.getInvoiceTotal());
            frame.getInvDate().setText(SalesInvoiceFrame.dateFormat.format(selectedInv.getInvDate()));
        }



    }
    
}
