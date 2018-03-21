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

public class TransactionDatabase {
    public static Map<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
    
    public static void printTransactions() {
        System.out.println("ALL TRANSACTIONS");
        for (Transaction t : transactions.values()) {
            if (t.type == TransactionType.ORDER) {
                System.out.println(Support.fit("ORDER", 10, true) + t);
            } else if (t.type == TransactionType.PAYMENT) {
                System.out.println(Support.fit("PAYMENT", 10, true) + t);
            }
        }
    }
    
    public static void addOrder(int customerNumber, String date, String brand, String level, String comment){
            Order o = new Order(customerNumber, date, brand, level, comment);
            transactions.put(o.orderNumber, o);
    }
    
    //checks to see if a given order number applies to an order in the system
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
    
    public static void addPayment(int customerNumber, String date, int amount) {
        Payment p = new Payment(customerNumber, date, amount);
        transactions.put(p.paymentNumber, p);
    }
    
    public static void printPayments() {
        System.out.println(Support.fit("DATE", 12, true) + Support.fit("CUSTOMER", 10, true) + Support.fit("PAYMENT", 10, true));
        for (Transaction t : transactions.values()) {
            if (t.type == TransactionType.PAYMENT) {
                System.out.println(t);
            }
        }
    }
    
    public static void printOrders() {
        System.out.println(Support.fit("ORDER DATE", 12, true) + Support.fit("CUSTOMER", 10, true) + Support.fit("BRAND", 15, true)
        + Support.fit("LEVEL", 12, true) + Support.fit("PRICE", 6, true) + Support.fit("PROMISE DATE", 12, true));
        for (Transaction t : transactions.values()) {
            if (t.type == TransactionType.ORDER) {
                System.out.println(t);
            }
        }
    }
    
    public static void printReceivables() {
        Map<Customer, Integer> receivablesMap = new HashMap();
        for (Transaction t : transactions.values()) {
            if (t.type == TransactionType.ORDER) {
                Order o = (Order) t;
                Customer c = CustomerDatabase.findByNumber(o.customerNumber);
                receivablesMap.putIfAbsent(c, 0);
                receivablesMap.put(c, receivablesMap.get(c) + o.repairPrice);
            } else if (t.type == TransactionType.PAYMENT) {
                Payment p = (Payment) t;
                Customer c = CustomerDatabase.findByNumber(p.customerNumber);
                receivablesMap.putIfAbsent(c, 0);
                receivablesMap.put(c, -1 * p.amount);
            }
        }
        System.out.println(Support.fit("CUSTOMER NUMBER", 24, true) + 
                Support.fit("CUSTOMER NAME", 20, true) + Support.fit("AMOUNT OWED", 15, true));
        for (Customer c: receivablesMap.keySet()) {
            if(receivablesMap.get(c) != 0) {
                System.out.println(c + "$" + Support.fitI(-1 * receivablesMap.get(c), 14, true));
            }
        }
    }
    
    public static void printStatements() {
        Map<Customer, ArrayList<Transaction>> statementsMap = new HashMap();
        for (Transaction t : transactions.values()) {
            Customer c  = CustomerDatabase.findByNumber(t.customerNumber);
            if (statementsMap.containsKey(c)) {
                statementsMap.get(c).add(t);
            } else {
                ArrayList<Transaction> alt = new ArrayList<Transaction>();
                alt.add(t);
                statementsMap.put(c, alt);
            }
        }
        System.out.println("CUSTOMER NUMBER \t CUSTOMER NAME ");
        for (Customer c: statementsMap.keySet()) {
            System.out.println(c.customerNumber + "\t" + c.firstName + " " + c.lastName + "\n");
            for (Transaction a : statementsMap.get(c)) {
                if (a.type == TransactionType.ORDER) {
                    System.out.println(Support.fit("ORDER", 10, true) + a);
                } else if (a.type == TransactionType.PAYMENT) {
                    System.out.println(Support.fit("PAYMENT", 10, true) + a);
                }
            }
            System.out.println("\n");
        }
    }
    
    public String savableString() {
        String s = "";
        for (Transaction t : transactions.values()) {
            if (t.type == TransactionType.ORDER) {
                Order o = (Order) t;
                s += "rnon " + o.orderNumber + "\n";
            }
            s += t.savableString() + "\n";
        }
        return s;
    }
}
