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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends Transaction{
    public int amount;
    private String saveableDate;
    public Date paymentDate;
    public int paymentNumber; 
    private Support s = new Support();
    
    SimpleDateFormat dateFormatter = new SimpleDateFormat("MMddyyyy");
    
    public Payment(int cn, String saveableDate, int a) {
        super.customerNumber = cn;
        amount = a;
        
        this.saveableDate = saveableDate;
        try {
            this.paymentDate = dateFormatter.parse(saveableDate);
        } catch (ParseException p) {
            System.out.println("Invalid Date");
        }
        
        paymentNumber = Transaction.transactionNumberCounter;
        Transaction.transactionNumberCounter++;
        super.type = TransactionType.PAYMENT;
    }
    
    public String toString() {
        return s.fitD(paymentDate, 12, true) + s.fitI(customerNumber,10, true) + "$" + s.fitI(amount,9,true);
    }
    
    public String savableString() {
        return "addp " + customerNumber + " " + saveableDate + " " + amount;
    }
}

