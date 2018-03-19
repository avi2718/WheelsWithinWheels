package wheelswithinwheels;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhairavi
 */
import java.util.Date;

public class Payment extends Transaction{
    public int customerNumber;
    public int amount;
    public Date paymentDate;
    public int paymentNumber; 
    public TransactionType type = TransactionType.PAYMENT;
    
    public Payment(int cn, Date pd, int a) {
        customerNumber = cn;
        amount = a;
        paymentDate = pd;
        paymentNumber = Transaction.transactionNumberCounter;
        Transaction.transactionNumberCounter++;
    }
    
    public String toString() {
        return paymentDate.toString() + customerNumber + amount;
    }
    
    public String savableString() {
        return "addp " + customerNumber + " " + paymentDate + " " + amount;
    }
}

