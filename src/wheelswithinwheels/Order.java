/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

/**
 *
 * @author Shai
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Order extends Transaction {
    public int orderNumber; 
    public int customerNumber;
    public String saveableDate;
    public Date orderDate;
    public String brand;
    public String level;
    public int repairPrice;
    public String comment = "";
    public Date promiseDate;
    public Date completionDate;
    
    SimpleDateFormat dateFormatter = new SimpleDateFormat("MMddyyyy");
    
    public Order(int customerNumber, String saveableDate, String brand, String level, String comment) {
        orderNumber = Transaction.transactionNumberCounter; 
        Transaction.transactionNumberCounter++;
        this.customerNumber = customerNumber;
        this.saveableDate = saveableDate;
        try {
            this.orderDate = dateFormatter.parse(saveableDate);
        } catch (ParseException p) {
            System.out.println("Invalid Date");
        }
        this.brand = brand;
        this.level = level;
        this.comment = comment;
        this.repairPrice = PriceList.returnPrice(brand, level);
        promiseDate = plusDays(orderDate, brand, level);
        super.type = TransactionType.ORDER;
    }
    
    public Order(int customerNumber, String saveableDate, String brand, String level) {
        orderNumber = Transaction.transactionNumberCounter; 
        Transaction.transactionNumberCounter++;
        this.customerNumber = customerNumber;
        this.customerNumber = customerNumber;
        this.saveableDate = saveableDate;
        try {
            this.orderDate = dateFormatter.parse(saveableDate);
        } catch (ParseException p) {
            System.out.println("Invalid Date");
        }
        this.brand = brand;
        this.level = level;
        promiseDate = plusDays(orderDate, brand, level);
        super.type = TransactionType.ORDER;
    }
    
    private Date plusDays(Date orderDate, String brand, String level) {
        Calendar c = Calendar.getInstance();
        c.setTime(orderDate);
        int numberDays = PriceList.returnNumberDays(brand, level);
        c.add(Calendar.DATE, numberDays);
        return c.getTime();
    }
    
    public String toString() {
        return orderDate.toString() + customerNumber + brand + level + repairPrice + promiseDate.toString() + comment;
    }
    
    public String savableString() {
        return "addo " + customerNumber + " " + saveableDate + " " + brand + " " + level + " " + comment;
    }
}
