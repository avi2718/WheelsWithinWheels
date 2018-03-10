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
    public static Map<Integer, Transaction> transactions;
    
    public static void printTransactions() {
        for (Transaction t : transactions.values()) {
            System.out.println(t);
        }
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
