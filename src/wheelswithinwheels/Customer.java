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
public class Customer {
    public static int customerNumberCounter = 0;
    public String firstName;
    public String lastName;
    public int customerNumber;
    
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        customerNumber = customerNumberCounter;
        customerNumberCounter++;
    }
    
    public String savableString() {
        return "addc " + this.firstName + " " + this.lastName;
    }
    
    public String toString() {
        return customerNumber + "\t\t\t" + firstName + lastName; 
    }
}
