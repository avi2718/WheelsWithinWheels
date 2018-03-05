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

public class TransactionDatabase {
    public Map<Integer, Transaction> transactions;
    
    public void printTransactions() {
        for (Transaction t : transactions.values()) {
            System.out.println(t);
        }
    }
    
    public void printPayments() {
        for (Transaction t : transactions.values()) {
            if (t.type == TransactionType.PAYMENT) {
                System.out.println(t);
            }
        }
    }
    
    public void printOrders() {
        for (Transaction t : transactions.values()) {
            if (t.type == TransactionType.ORDER) {
                System.out.println(t);
            }
        }
    }
}
