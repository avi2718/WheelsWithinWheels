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

public class Payment {
    public int customerNumber;
    public Float amount;
    public Date paymentDate;
    
    public Payment(int cn, Float a, Date pd) {
        customerNumber = cn;
        amount = a;
        paymentDate = pd;
    }
}

