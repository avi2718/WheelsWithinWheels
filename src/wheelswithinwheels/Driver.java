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

import java.util.Scanner;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Driver {

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    Support s = new Support();
    TransactionDatabase td = new TransactionDatabase();
    CustomerDatabase cd = new CustomerDatabase();


    public void driver() {
        Scanner input = new Scanner(System.in);

        while (true) {//possible additions: Remove rp, cnum, cname, etc
            if (!driverCycle(input.nextLine())) {
                break;
            }
        }
    }

    private boolean driverCycle(String nextLine) {
        String[] line = s.splitStringIntoParts(nextLine);
        switch (line[0]) {
            case "quit":
                if (shouldQuit()) {
                    return false;
                }
            case "help":
                help();
                return true;
            case "addrp":
                addrp(line);
                return true;
            case "addc":
                addc(line);
                return true;
            case "addo":
                addo(line);
                return true;
            case "addp":
                addp(line);
                return true;
            case "comp":
                comp(line);
                return true;
            case "printrp":
                printrp();
                return true;
            case "printcnum":
                printcnum();
                return true;
            case "printcname":
                printcname();
                return true;
            case "printo":
                printo();
                return true;
            case "printp":
                printp();
                return true;
            case "printt":
                printt();
                return true;
            case "prints":
                prints();
                return true;
            case "readc":
                readc(line[1]);
                return true;
            case "savebs":
                savebs(line[1]);
                return true;
            case "restorebs":
                restorebs(line[1]);
                return true;
            default:
                System.out.println("Invalid Command");
                return true;
        }
    }

    private boolean shouldQuit() {//could prompt the user to save if they have unsaved changes
        return (true);
    }

    private void help() {
        System.out.println("Welcome to Wheels Within Wheels bike shop system! "
                + "\nThese are the available commands. "
                + "\nItems in parentheses are values determined by you. "
                + "The parentheses themselves should not be included. "
                + "\nquit \nQuits the system without saving \nhelp "
                + "\nDisplays this help message \naddrp (brand) (level) (price) (days) "
                + "\nAdds a new repair pricing option to the system with the given options "
                + "\naddc (firstName) (lastName) "
                + "\nAdds a customer with the given options to the system "
                + "\naddo (customerNumber) (date) (brand) (level) (comment) "
                + "\nAdds an order to the system with the given options and an optional comment. "
                + "\naddp (customerNumber) (date) (amount) "
                + "\nTells the system that a given customer has made a payment of a certain amount. "
                + "\ncomp (orderNumber) (completionDate) "
                + "\nTells the system that a given order was completed on a given date "
                + "\nprintrp \nPrints a list of the repair prices \nprintcnum "
                + "\nPrints a list of all customers by customer number \nprintcname "
                + "\nPrints a list of all customers by customer name \nprinto "
                + "\nPrints a list of all orders \nprintp \nPrints a list of all payments "
                + "\nprintt \nPrints a list of all transactions \nprintr "
                + "\nPrints a list of all recievables \nprints \nPrints a list of all statements "
                + "\nreadc \nInstead of typing in commands, this reads them in from a text file with a given filename "
                + "\nsavebs \nSaves the current state of the bikeshop in a file with a given name "
                + "\nrestorebs \nRestores a previously saved version of the bike shop so it can be worked on");
    }

    private boolean isStringFloat(String s)
{
    try
    {
        Float.parseFloat(s);
        return true;
    } catch (NumberFormatException ex)
    {
        return false;
    }
}
    
    private boolean isStringInt(String s)
{
    try
    {
        Integer.parseInt(s);
        return true;
    } catch (NumberFormatException ex)
    {
        return false;
    }
}
    
     private boolean isStringDate(String s)
{
    try
    {
        Date date = formatter.parse(s);
        return true;
    } catch (ParseException e)
    {
        return false;
    }
}
    
 
  
    private void addrp(String[] params){   
        if (params.length != 5) {System.out.println("Incorrect number of parameters"); return;} 
        if (params[1] instanceof String && params[2] instanceof String && isStringFloat(params[3]) && isStringInt(params[4])){
           RepairPrice newrp = new RepairPrice(params[1], params[2], Float.parseFloat(params[3]), Integer.parseInt(params[4]));
           PriceList.prices[PriceList.prices.length + 1] = (newrp);
        } else{
            System.out.println("Incorrect type of one or more parameters"); return;
        }
    }

    
   
    private void addc(String[] params) {
        if (params.length != 3) {System.out.println("Incorrect number of parameters"); return;} 
        Customer newc = new Customer(params[1], params[2]);
        
    }

    private void addo(String[] params) {
        if (!(params.length >= 5)) {System.out.println("Incorrect number of parameters"); return;} 
        if (isStringInt(params[1]) && isStringDate(params[2]) && isStringFloat(params[3]) && isStringInt(params[4])){
            Order newo = new Order(Integer.parseInt(params[1]), (params[2]), Float.parseFloat(params[3]), params[4], params[5]);
        }
    }

    private void addp(String[] params) {
        if ((params.length != 4)) {System.out.println("Incorrect number of parameters"); return;} 
        if (isStringInt(params[1]) && isStringFloat(params[2]) && isStringDate(params[3])){
            Payment newp = new Payment(Integer.parseInt(params[1]), Float.parseFloat(params[2]), (params[3]));
        }
    }

    private void comp(String[] params) {
        if ((params.length != 3)) {System.out.println("Incorrect number of parameters"); return;} 
        if (isStringInt(params[1]) && isStringDate(params[2])){
            
        }
    }

    private void printrp() {
        PriceList.printList();
    }

    private void printcnum(String[] params) {
        System.out.println(CustomerDatabase.findByNumber(Integer.parseInt(params[1])));
    }

    private void printcname(String[] params) {
        System.out.println(CustomerDatabase.findByName(params[1], params[2]));
    }

    private void printo() {
        TransactionDatabase.printOrders();
    }

    private void printp() {
        TransactionDatabase.printPayments();
    }

    private void printt() {
        TransactionDatabase.printTransactions();
    }

    private void printr() {
        TransactionDatabase.printRecieveables();
    }
   
    private void prints() {
        TransactionDatabase.printStatments();
    }

    private void readc(String filename) {

    }

    private void savebs(String filename) {
        String bikeShopAsString = td.savableString() + cd.savableString();
        s.writeTextFile(filename, bikeShopAsString);
    }

    private void restorebs(String filename) {
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            
            while((line = bufferedReader.readLine()) != null) {
                driverCycle(line);
            }   
            
            bufferedReader.close();
        }
        catch(FileNotFoundException notFount) {
            System.out.println("File Not Found At Given Location");
        }
        catch(IOException badChar) {
            System.out.println("Save File Has Been Corrupted/Is Invalid");
        }
    }
}
