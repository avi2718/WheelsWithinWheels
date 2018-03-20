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
        for (Transaction t : transactions.values()) {
            System.out.println(t);
        }
    }
    
    public static void addOrder(int customerNumber, String date, String brand, String level, String comment){
            Order o = new Order(customerNumber, date, brand, level, comment);
            transactions.put(o.orderNumber, o);
    }
    
    public static void addOrderNoComment(int customerNumber, String date, String brand, String level) {
            Order o = new Order(customerNumber, date, brand, level);
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
    
    public static void addPayment(int customerNumber, String date, int amount) {
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
        System.out.println("CUSTOMER NUMBER \t CUSTOMER NAME \t\t AMOUNT OWED");
        for (Customer c: receivablesMap.keySet()) {
            if(receivablesMap.get(c) != 0) {
                System.out.println("\t" + c.customerNumber + "\t\t  " + c.firstName + " " + c.lastName + "\t\t  " + receivablesMap.get(c));
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
            System.out.println(c + "\n");
            for (Transaction a : statementsMap.get(c)) {
                System.out.println(a);
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
