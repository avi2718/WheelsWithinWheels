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

    SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");
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
        String[] line = s.splitStringIntoParts(nextLine);
        if (line.length > 0) {
            switch (line[0]) {
                case "quit":
                    if (isSaved) {
                        return false;
                    } else {
                        return shouldSave();
                    }
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
                    printcnum();
                    break;
                case "printcname":
                    printcname();
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
                case "printr":
                    printr();
                    break;
                case "readc":
                    if (line.length == 2) {
                        restorebs(line[1]);
                    } else {
                        System.out.println("Incorrect Number of Specifications");
                    }
                    break;
                case "restorebs":
                    System.out.println("Restoring Bike Shop");
                    if (line.length == 2) {
                        restorebs(line[1]);
                    } else {
                        System.out.println("Incorrect Number of Specifications");
                    }
                    break;
                case "savebs":
                    if (line.length == 2) {
                        savebs(line[1]);
                    } else {
                        System.out.println("Incorrect Number of Specifications");
                    }
                    break;
                case "isSaved?": 
                    System.out.println(isSaved);
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        } else {
            System.out.println("Invalid Command");
        }
        return true;
    }

    /* private boolean shouldQuit() {//could prompt the user to save if they have unsaved changes
        return !isSaved;
    }*/
    
    private boolean shouldSave() {
        System.out.println("Are you sure you would like to quit without saving? (y/n)");
        while (true) {//possible additions: Remove rp, cnum, cname, etc
            Scanner input = new Scanner(System.in);
            String nextLine = input.nextLine();
            switch (nextLine) {
                case "y":
                    return false;
                case "n":
                    return true;
                default:
                    System.out.println("Please enter y or n");
            }
        }
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
        isSaved = false;
        if (params.length != 5) {
            System.out.println("Incorrect Number of Specifications");
            return;
        }
        if (params[1] instanceof String && params[2] instanceof String && isStringInt(params[3]) && isStringInt(params[4])) {
            pl.addRepairPrice(params[1], params[2], Integer.parseInt(params[3]), Integer.parseInt(params[4]));
        } else {
            System.out.println("Something you have entered is not a number or date");
            return;
        }
    }

    private void addc(String[] params) {
        isSaved = false;
        if (params.length != 3) {
            System.out.println("Incorrect Number of Specifications");
            return;
        }
        cd.addCustomer(params[1], params[2]);
    }

    private void addo(String[] params) {
        isSaved = false;
        if (!(params.length >= 5)) {
            System.out.println("Incorrect Number of Specifications");
            return;
        }

        //System.out.println("ID: " + cd.isValidCustomerID(params[1]) + "Date: " + isStringDate(params[2]) + "Brand: " + pl.isValidBrand(params[3]) + "Level: " + pl.isValidLevel(params[4]));
        if (cd.isValidCustomerID(params[1]) && isStringDate(params[2]) && pl.isValidBrand(params[3]) && pl.isValidLevel(params[4])) {
            try {
                formatter.parse(params[2]);

                if (params.length == 5) {
                    td.addOrderNoComment(Integer.parseInt(params[1]), params[2], params[3], params[4]);
                } else {
                    td.addOrder(Integer.parseInt(params[1]), params[2], params[3], params[4], putCommentBackTogether(params, 5));
                }
            } catch (ParseException | NumberFormatException n) {
                System.out.println("Invalid date");
            }
        } else {
            System.out.println("Something you have entered is not a number or date");
        }
    }

    private void addp(String[] params) {
        isSaved = false;
        if ((params.length != 4)) {
            System.out.println("Incorrect Number of Specifications");
            return;
        }
        if (cd.isValidCustomerID(params[1]) && isStringDate(params[2]) && isStringInt(params[3])) {
            try {
                formatter.parse(params[2]);
                td.addPayment(Integer.parseInt(params[1]), params[2], Integer.parseInt(params[3]));
            } catch (ParseException e) {
                System.out.println("Invalid date");
            }
        }
    }

    private void comp(String[] params) {
        isSaved = false;
        if ((params.length != 3)) {
            System.out.println("Incorrect Number of Specifications");
            return;
        }
        if (td.isValidOrder(params[1]) && isStringDate(params[2])) {
            try {
                td.completeOrder(Integer.parseInt(params[1]), formatter.parse(params[2]));
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

    private void printcnum() {
        cd.printcnum();
        //System.out.println(CustomerDatabase.findByNumber(Integer.parseInt(params[1])));
    }

    private void printcname() {
        cd.printcname();
        //System.out.println(CustomerDatabase.findByName(params[1], params[2]));
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
        TransactionDatabase.printReceivables();
    }

    private void prints() {
        td.printStatements();
    }

    private String putCommentBackTogether(String[] params, int start) {
        String cs = "";
        for (int p = start; p < params.length; p++) {
            cs += params[p] + " ";
        }
        return cs;
    }

    private void savebs(String filename) {
        String bikeShopAsString = cd.savableString() + pl.savableString() + td.savableString();
        s.writeTextFile(filename, bikeShopAsString);
        isSaved = true;
    }

    private void restorebs(String filename) {
        isSaved = false;
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
        } catch (NumberFormatException e) {
            System.out.println("Invalid Order Number");
        }
        Transaction.transactionNumberCounter = on;
    }

    private boolean restoreDriverCycle(String nextLine) {
        System.out.println(nextLine);
        String[] line = s.splitStringIntoParts(nextLine);

        switch (line[0]) {
            case "quit":
                if (isSaved) {
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
                    restorebs(line[1]);
                } else {
                    System.out.println("Incorrect Number of Specifications");
                }
                break;
            case "restorebs":
                if (line.length == 2) {
                    restorebs(line[1]);
                } else {
                    System.out.println("Incorrect Number of Specifications");
                }
                break;
            case "savebs":
                if (line.length == 2) {
                    savebs(line[1]);
                } else {
                    System.out.println("Incorrect Number of Specifications");
                }
                break;
            case "rncn":
                if (line.length == 2) {
                    rncn(line[1]);
                } else {
                    System.out.println("Incorrect Number of Specifications");
                }
                break;
            case "rnon":
                if (line.length == 2) {
                    rnon(line[1]);
                } else {
                    System.out.println("Incorrect Number of Specifications");
                }
                break;
            default:
                System.out.println("Invalid Command");
                break;
        }
        return true;
    }
}
