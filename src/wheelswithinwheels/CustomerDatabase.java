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

import java.util.Map;

public class CustomerDatabase {
    public static Map<Integer, Customer> customers;
    
    public static Customer findByNumber(int customerNumber) {
        return customers.get(customerNumber);
    }
    
    public static Customer findByName(String customerFirstName, String customerLastName) {
        for (Customer c : customers.values()) {
            if (c.firstName == customerFirstName && c.lastName == customerLastName) {
                return c;
            }
        }
        return null;
    }
    
    public String savableString() {
        String s = "";
        for (Customer c : customers.values()) {
            s += c.savableString() + "\n";
        }
        return s;
    }
}
