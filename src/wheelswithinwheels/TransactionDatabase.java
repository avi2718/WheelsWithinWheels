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
    public static ArrayList<Transaction> transactions;
    
    public static void printTransactions() {
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
    
    public static void addOrder(int customerNumber, Date date, String brand, String level, String comment) {
        Order o = new Order(customerNumber, date, brand, level, comment); 
        transactions.add(o);
    }
    
    public static void addPayment(int customerNumber, Date date, int amount) {
        Payment p = new Payment(customerNumber, date, amount);
    }
    public static void printPayments() {
        for (Transaction t : transactions) {
            if (t.type == TransactionType.PAYMENT) {
                System.out.println(t);
            }
        }
    }
    
    public static void printOrders() {
        for (Transaction t : transactions) {
            if (t.type == TransactionType.ORDER) {
                System.out.println(t);
            }
        }
    }
    
    public String savableString() {
        String s = "";
        for (Transaction t : transactions) {
            s += t.savableString() + "\n";
        }
        return s;
    }
}
