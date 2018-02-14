/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

/**
 *
 * @author bhairavi
 */
import java.util.*;
public class Order {
    private static int orderNumberCounter = 0;
    public int customerNumber;
    public Date orderDate;
    public String brand;
    public String level;
    public Float repairPrice;
    public String comment;
    public Date promiseDate;
    public Date completionDate;
    
    public Order(int customerNumber, Date orderDate, String brand, String level, String comment) {
        this.customerNumber = customerNumber;
        this.orderDate = orderDate;
        this.brand = brand;
        this.level = level;
        this.comment = comment;
        promiseDate = plusDays(orderDate, level);
    }
    
    private Date plusDays(Date orderDate, String level) {
        Calendar c = Calendar.getInstance();
        c.setTime(orderDate);
        int numberDays = PriceList.returnDays(level);
        c.add(Calendar.DATE, numberDays);
        return c.getTime();
    }
}
