/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wheelswithinwheels;

import java.util.Scanner;

/**
 *
 * @author maxwelllittle
 */
public class Driver {
    Support s = new Support();

    
    
    public void driver() {
        Scanner input = new Scanner(System.in);
        
        while (true) {//possible additions: Remove rp, cnum, cname, etc
            String next = input.nextLine();
            String[] line = s.splitStringIntoParts(next);
            
            switch (line[0]) {
                case "quit": if (shouldQuit()) {break;}
                case "help": help();
                case "addrp": addrp(line);
                case "addc": addc(line);
                case "addo": addo(line);
                case "addp": addp(line);
                case "comp": comp(line);
                case "printrp": printrp();
                case "printcnum": printcnum();
                case "printcname": printcname();
                case "printo": printo();
                case "printp": printp();
                case "printt": printt();
                case "prints": prints();
                case "readc": readc(line[1]);
                case "savebs": savebs(line[1]);
                case "restorebs": restorebs(line[1]);
                default: System.out.println("Invalid Command");
            }
        }
        
        
        
        
        
        
    }
    
    private boolean shouldQuit() {//could prompt the user to save if they have unsaved changes
        return (true);
    }
    
    private void help() {
        System.out.println("Welcome to Wheels Within Wheels bike shop system! \nThese are the available commands. \nItems in parentheses are values determined by you. The parentheses themselves should not be included. \nquit \nQuits the system without saving \nhelp \nDisplays this help message \naddrp (brand) (level) (price) (days) \nAdds a new repair pricing option to the system with the given options \naddc (firstName) (lastName) \nAdds a customer with the given options to the system \naddo (customerNumber) (date) (brand) (level) (comment) \nAdds an order to the system with the given options and an optional comment. \naddp (customerNumber) (date) (amount) \nTells the system that a given customer has made a payment of a certain amount. \ncomp (orderNumber) (completionDate) \nTells the system that a given order was completed on a given date \nprintrp \nPrints a list of the repair prices \nprintcnum \nPrints a list of all customers by customer number \nprintcname \nPrints a list of all customers by customer name \nprinto \nPrints a list of all orders \nprintp \nPrints a list of all payments \nprintt \nPrints a list of all transactions \nprintr \nPrints a list of all recievables \nprints \nPrints a list of all statements \nreadc \nInstead of typing in commands, this reads them in from a text file with a given filename \nsavebs \nSaves the current state of the bikeshop in a file with a given name \nrestorebs \nRestores a previously saved version of the bike shop so it can be worked on");
    }
    
    private void addrp(String[] params) {
        
    }
    
    private void addc(String[] params) {
        
    }
    
    private void addo(String[] params) {
        
    }
    
    private void addp(String[] params) {
        
    }
    
    private void comp(String[] params) {
        
    }
    
    private void printrp() {
        
    }
    
    private void printcnum() {
        
    }
    
    private void printcname() {
        
    }
    
    private void printo() {
        
    }
    
    private void printp() {
        
    }
    
    private void printt() {
        
    }
    
    private void prints() {
        
    }
    
    private void readc(String filename) {
        
    }
    
    private void savebs(String filename) {
        
    }
    
    private void restorebs(String filename) {
        
    }
}
