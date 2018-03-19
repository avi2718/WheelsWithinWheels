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

public class TransactionDatabase {
    public static Map<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
    
    public static void printTransactions() {
        for (Transaction t : transactions.values()) {
            System.out.println(t);
        }
    }
    
    public static void addOrder(int customerNumber, Date date, String brand, String level, String comment) {
        Order o = new Order(customerNumber, date, brand, level, comment); 
        transactions.put(o.orderNumber, o);
    }
    
    public static boolean isValidOrder(String orderNumber) {
        try {
            return transactions.get(Integer.parseInt(orderNumber)).type == TransactionType.ORDER;
        } catch (NumberFormatException | NullPointerException ex) {
            return false;
        }
    }
    
    public static void completeOrder(int orderNumber, Date completionDate) {
        Order o = (Order) transactions.get(orderNumber);
        o.completionDate = completionDate;
    }
    
    public static void addPayment(int customerNumber, Date date, int amount) {
        Payment p = new Payment(customerNumber, date, amount);
        transactions.put(p.paymentNumber, p);
    }
    public static void printPayments() {
        for (Transaction t : transactions.values()) {
            if (t.type == TransactionType.PAYMENT) {
                System.out.println(t);
            }
        }
    }
    
    public static void printOrders() {
        for (Transaction t : transactions.values()) {
            if (t.type == TransactionType.ORDER) {
                System.out.println(t);
            }
        }
    }
    
    public String savableString() {
        String s = "";
        for (Transaction t : transactions.values()) {
            s += t.savableString() + "\n";
        }
        return s;
    }
}
