package wheelswithinwheels;

import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhairavi
 */
public class CustomerDatabase {
    public Map<Integer, Customer> customers;
    
    public Customer findByNumber(int customerNumber) {
        return customers.get(customerNumber);
    }
    
    public Customer findByName(String customerFirstName, String customerLastName) {
        for (Customer c : customers.values()) {
            if (c.firstName == customerFirstName && c.lastName == customerLastName) {
                return c;
            }
        }
        return null;
    }
    
}
