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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class CustomerDatabase {

    public static Map<Integer, Customer> customers = new HashMap<Integer, Customer>();

    public static Customer findByNumber(int customerNumber) {
        return customers.get(customerNumber);
    }

    public static Customer findByName(String customerLastName) {
        for (Customer c : customers.values()) {
            if (c.lastName == customerLastName) {
                return c;
            }
        }
        return null;
    }

    public void printcnum() {
        TreeSet<Integer> orderedSet = new TreeSet<Integer>();
        Iterator<Integer> iter = customers.keySet().iterator();
        while (iter.hasNext()) {//Places Keys in a treeset which automatically sorts them, then prints the customers using that set
            orderedSet.add(iter.next());
        }

        Iterator<Integer> treeSetIter = orderedSet.iterator();
        while (treeSetIter.hasNext()) {
            System.out.println(customers.get(treeSetIter.next()));
        }
    }

    public void printcname() {
        TreeSet<String> orderedSet = new TreeSet<String>();
        for (Customer c : customers.values()) {//Places values in a treeset which automatically sorts them, then prints that set
            orderedSet.add(c.lastName);
        }
        Iterator<String> treeSetIter = orderedSet.iterator();
        while (treeSetIter.hasNext()) {
            System.out.println(findByName(treeSetIter.next()));
        }
    }

    public void addCustomer(String firstName, String lastName) {
        Customer c = new Customer(firstName, lastName);
        customers.put(c.customerNumber, c);
    }

    public boolean isValidCustomerID(String customerID) {
        try {
            return customers.containsKey(Integer.parseInt(customerID));
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public void printCustomers() {
        System.out.println("CUSTOMER NUMBER" + "\t\tCUSTOMER NAME");
        for (Customer c : customers.values()) {
            System.out.println(c);
        }
    }

    public String savableString() {
        String s = "";
        for (Customer c : customers.values()) {
            s += "rncn " + c.customerNumber + "\n";
            s += c.savableString() + "\n";
        }
        return s;
    }
}
