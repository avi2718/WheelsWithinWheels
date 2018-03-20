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
import java.util.*;
public class Order extends Transaction {
    public int orderNumber; 
    public int customerNumber;
    public Date orderDate;
    public String brand;
    public String level;
    public int repairPrice;
    public String comment = "";
    public Date promiseDate;
    public Date completionDate;
    public TransactionType type = TransactionType.ORDER;
    
    public Order(int customerNumber, Date orderDate, String brand, String level, String comment) {
        orderNumber = Transaction.transactionNumberCounter; 
        Transaction.transactionNumberCounter++;
        this.customerNumber = customerNumber;
        this.orderDate = orderDate;
        this.brand = brand;
        this.level = level;
        this.comment = comment;
        this.repairPrice = PriceList.returnPrice(brand, level);
        promiseDate = plusDays(orderDate, brand, level);
    }
    public Order(int customerNumber, Date orderDate, String brand, String level) {
        orderNumber = Transaction.transactionNumberCounter; 
        Transaction.transactionNumberCounter++;
        this.customerNumber = customerNumber;
        this.orderDate = orderDate;
        this.brand = brand;
        this.level = level;
        promiseDate = plusDays(orderDate, brand, level);
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
        return "addo " + customerNumber + " " + orderDate + " " + brand + " " + level + " " + comment;
    }
}
