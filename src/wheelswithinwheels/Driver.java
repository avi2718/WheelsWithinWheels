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
    PriceList pl = new PriceList();
    Boolean isSaved = true;

    public void driver() {
        Scanner input = new Scanner(System.in);

        while (true) {//possible additions: Remove rp, cnum, cname, etc
            if (!driverCycle(input.nextLine())) {
                break;
            }
        }
    }

    private boolean driverCycle(String nextLine) {
        isSaved = false;
        String[] line = s.splitStringIntoParts(nextLine);
        switch (line[0]) {
            case "quit":
                if (shouldQuit()) {
                    return false;
                }
                break;
            case "help":
                help();
                break;
            case "addrp":
                addrp(line);
                break;
            case "addc":
                addc(line);
                break;
            case "addo":
                addo(line);
                break;
            case "addp":
                addp(line);
                break;
            case "comp":
                comp(line);
                break;
            case "printrp":
                printrp();
                break;
            case "printcnum":
                printcnum(line);
                break;
            case "printcname":
                printcname(line);
                break;
            case "printo":
                printo();
                break;
            case "printc":
                printc();
                break;
            case "printp":
                printp();
                break;
            case "printt":
                printt();
                break;
            case "prints":
                prints();
                break;
            case "readc":
                if (line.length == 2) {
                    readc(line[1]);
                } else {
                    System.out.println("Bad Input");
                }
                break;
            case "restorebs":
                System.out.println("Restoring Bike Shop");
                if (line.length == 2) {
                    restorebs(line[1]);
                } else {
                    System.out.println("Bad Input");
                }
                break;
            case "savebs":
                if (line.length == 2) {
                    savebs(line[1]);
                } else {
                    System.out.println("Bad Input");
                }
                break;
            default:
                System.out.println("Invalid Command");
                break;
        }
        return true;
    }

    private boolean shouldQuit() {//could prompt the user to save if they have unsaved changes
        return !isSaved;
    }

    private void help() {
        System.out.println("Welcome to the Wheels Within Wheels bike shop system! "
                + "\nThese are the available commands. "
                + "\nItems in parentheses are values determined by you. "
                + "The parentheses themselves should not be included. "
                + "\nquit \n \t Quits the system without saving \nhelp "
                + "\n \t Displays this help message \naddrp (brand) (level) (price) (days) "
                + "\n \t Adds a new repair pricing option to the system with the given options "
                + "\naddc (firstName) (lastName) "
                + "\n \t Adds a customer with the given options to the system "
                + "\naddo (customerNumber) (date) (brand) (level) (comment) "
                + "\n \t Adds an order to the system with the given options and an optional comment. "
                + "\naddp (customerNumber) (date) (amount) "
                + "\n \t Tells the system that a given customer has made a payment of a certain amount. "
                + "\ncomp (orderNumber) (completionDate) "
                + "\n \t Tells the system that a given order was completed on a given date "
                + "\nprintrp \n \t Prints a list of the repair prices \nprintcnum "
                + "\n \t Prints a list of all customers by customer number \nprintcname "
                + "\n \t Prints a list of all customers by customer name \nprinto "
                + "\n \t Prints a list of all orders \nprintp \n \t Prints a list of all payments "
                + "\nprintt \n \t Prints a list of all transactions \nprintr "
                + "\n \t Prints a list of all receivables \nprints \n \t Prints a list of all statements "
                + "\nreadc (filename) \n \t Instead of typing in commands, this reads them in from a text file with a given filename "
                + "\nsavebs (filename) \n \t Saves the current state of the bikeshop in a file with a given name "
                + "\nrestorebs (filename) \n \tRestores a previously saved version of the bike shop so it can be worked on");
    }

    private boolean isStringFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isStringDate(String s) {
        try {
            Date date = formatter.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void addrp(String[] params) {
        if (params.length != 5) {
            System.out.println("Incorrect number of parameters");
            return;
        }
        if (params[1] instanceof String && params[2] instanceof String && isStringInt(params[3]) && isStringInt(params[4])) {
            System.out.println("Is this information correct: " + "\n\tbrand: " + params[1] +
                    "\n\tlevel: " + params[2] + "\n\tprice: " + params[3] + "\n\tdays: " + params[4] + "\nEnter (y/n)");
            Scanner input = new Scanner(System.in);
            if(input.nextLine().equals("y")){
                pl.addRepairPrice(params[1], params[2], Integer.parseInt(params[3]), Integer.parseInt(params[4]));
                System.out.println("Created new repair price data record.");
                return;
            } else{
                System.out.println("Please re-enter data");
                return;
                }
       
        } else {
            System.out.println("Incorrect type of one or more parameters");
            return;
        }
    }

    private void addc(String[] params) {
        if (params.length != 3) {
            System.out.println("Incorrect number of parameters");
            return;
        }
        System.out.println("Is this information correct: " + "creating new customer with name " + params[1] + " " + params[2]
        + "\n Enter (y/n)");
        Scanner input = new Scanner(System.in);
        if(input.nextLine().equals("y")){
                cd.addCustomer(params[1], params[2]);
                System.out.println("Created new customer.");
                return;
            } else{
                System.out.println("Please re-enter data");
                return;
                }
    }

    private void addo(String[] params) {
        if (!(params.length >= 5)) {
            System.out.println("Incorrect number of parameters");
            return;
        }
        if (cd.isValidCustomerID(params[1]) && isStringDate(params[2]) && pl.isValidBrand(params[3]) && pl.isValidLevel(params[4])) {
            try {
                System.out.println("Is this information correct: " + params[1] + params[2] + params[3] + params[4] + " (y/n)");
            Scanner input = new Scanner(System.in);
            if(input.next().equals("y")){
                td.addOrder(Integer.parseInt(params[1]), formatter.parse(params[2]), params[3], params[4], params[5]);
            } else{
                System.out.println("Incorrect type of one or more parameters");
            }
            } catch (ParseException e) {
                System.out.println("Invalid date");
            }
        }
    }

    private void addp(String[] params) {
        if ((params.length != 4)) {
            System.out.println("Incorrect number of parameters");
            return;
        }
        if (cd.isValidCustomerID(params[1]) && isStringDate(params[2]) && isStringInt(params[3])) {
            try {
                System.out.println("Is this information correct: " + params[1] + params[2] + params[3] + params[4] + " (y/n)");
            Scanner input = new Scanner(System.in);
            if(input.next() == "y"){
                td.addPayment(Integer.parseInt(params[1]), formatter.parse(params[2]), Integer.parseInt(params[3]));
            }else{
                System.out.println("Incorrect type of one or more parameters");
            }
            } catch (ParseException e) {
                System.out.println("Invalid date");
            }
        }
    }

    private void comp(String[] params) {
        if ((params.length != 3)) {
            System.out.println("Incorrect number of parameters");
            return;
        }
        if (td.isValidOrder(params[1]) && isStringDate(params[2])) {
            try {
                System.out.println("Is this information correct: " + params[1] + params[2] + params[3] + params[4] + " (y/n)");
            Scanner input = new Scanner(System.in);
            if(input.next() == "y"){
                td.completeOrder(Integer.parseInt(params[1]), formatter.parse(params[2]));
            }else{
                System.out.println("Incorrect type of one or more parameters");
            }
            } catch (ParseException e) {
                System.out.println("Invalid date");
            }
        }
    }
    
    private void printc() {
        cd.printCustomers();
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
        td.printReceivables();
    }

    private void prints() {
        td.printStatements();
    }

    private void readc(String filename) {

    }

    private void savebs(String filename) {
        String bikeShopAsString = td.savableString() + cd.savableString();
        s.writeTextFile(filename, bikeShopAsString);
        isSaved = true;
    }

    private void restorebs(String filename) {
        String file = s.readTextFile(filename);
        String[] lines = s.splitStringIntoLines(file);
        for (String line : lines) {
            restoreDriverCycle(line);
        }
    }

    private void rncn(String customerNumber) {
        int cn = 0;
        try {
            cn = Integer.parseInt(customerNumber);
        } catch (NumberFormatException e) {
            System.out.println("Invalid customer number");
        }
        Customer.customerNumberCounter = cn;
    }
    
    private void rnon(String orderNumber) {
        int on = 0;
        try {
            on = Integer.parseInt(orderNumber);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid Order Number");
        }
        Transaction.transactionNumberCounter = on;
    }

    private boolean restoreDriverCycle(String nextLine) {
        isSaved = false;
        System.out.println(nextLine);
        String[] line = s.splitStringIntoParts(nextLine);
        
        switch (line[0]) {
            case "quit":
                if (shouldQuit()) {
                    return false;
                }
                break;
            case "help":
                help();
                break;
            case "addrp":
                addrp(line);
                break;
            case "addc":
                addc(line);
                break;
            case "addo":
                addo(line);
                break;
            case "addp":
                addp(line);
                break;
            case "comp":
                comp(line);
                break;
            case "printrp":
                printrp();
                break;
            case "printcnum":
                printcnum(line);
                break;
            case "printcname":
                printcname(line);
                break;
            case "printo":
                printo();
                break;
                case "printc":
                printc();
                break;
            case "printp":
                printp();
                break;
            case "printt":
                printt();
                break;
            case "prints":
                prints();
                break;
            case "readc":
                if (line.length == 2) {
                    readc(line[1]);
                } else {
                    System.out.print("Bad Input\n");
                }
                break;
            case "restorebs":
                //System.out.println("Restoring Bike Shop");
                if (line.length == 2) {
                    restorebs(line[1]);
                } else {
                    System.out.println("Bad Input");
                }
                break;
            case "savebs":
                if (line.length == 2) {
                    savebs(line[1]);
                } else {
                    System.out.println("Bad Input");
                }
                break;
            case "rncn":
                if (line.length == 2) {
                    rncn(line[1]);
                }
                else {
                    System.out.println("Bad Input");
                }
                break;
            case "rnon": 
                if (line.length == 2) {
                    rnon(line[1]);
                } else {
                    System.out.println("Bad Input");
                }
                break;
            default:
                System.out.println("Invalid Command");
                break;
        }
        return true;
    }
}
