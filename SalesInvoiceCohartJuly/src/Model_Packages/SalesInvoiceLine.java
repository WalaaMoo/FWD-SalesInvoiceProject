/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Packages;

/**
 *
 * @author walaa
 */
public class SalesInvoiceLine {

   
    
    private String itemName ;

  
    private double itemPrice ; 
    private int count ; 
    
   private SalesInvoiceHeader header;
   
   
    public SalesInvoiceLine(String itemName, double itemPrice, int count, SalesInvoiceHeader header) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.header = header;
    }
   
    
      public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SalesInvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(SalesInvoiceHeader header) {
        this.header = header;
    }
    
    
    public double getLinesTotal(){
    return itemPrice * count ;
    }

    @Override
    public String toString() {
        return header.getInvNum() + "," + itemName +  itemPrice + "," + count;
    }
    
    
    
}
