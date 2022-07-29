/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Packages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author walaa
 */
public class SalesInvoiceHeader {
    
    private int invNum ;
    private String invCustomer; 
    private Date invDate ;
    private ArrayList <SalesInvoiceLine> Lines ;
     private DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

   
    
    
    
     public SalesInvoiceHeader(){
    
    }

    
    public SalesInvoiceHeader(int invNum , String invCustomer , Date invDate){
     
        this.invNum = invNum;
        this.invCustomer = invCustomer; 
        this.invDate = invDate;
    }
    
    
    
    
       public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }
    
    
    
    public int getInvNum() {
        return invNum;
    }

    public void setInvNum(int invNum) {
        this.invNum = invNum;
    }

    public String getInvCustomer() {
        return invCustomer;
    }

    public void setInvCustomer(String invCustomer) {
        this.invCustomer = invCustomer;
    }
   
    
    
    
    
    
     public ArrayList<SalesInvoiceLine> getLines() 
     {
     if (Lines == null){
         Lines = new ArrayList<>();
     
     }
         
         return Lines;
    }

    public void setLines(ArrayList<SalesInvoiceLine> Lines) {
        this.Lines = Lines;
    }
    
 
    
    public double getInvoiceTotal(){
        
        double total = 0.0;
        
        for(int i =0;i< getLines().size();i++ ){
        
            total += Lines.get(i).getLinesTotal() ;
        }
        return total;
    }

    @Override
    public String toString() {
        return invNum + "," + dateformat.format(invDate) + "," +  invCustomer ;
    }
    
    
    
}
