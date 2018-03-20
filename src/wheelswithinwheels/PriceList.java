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

import java.util.*;
public class PriceList {
    public static ArrayList<RepairPrice> prices = new ArrayList<RepairPrice>();
    private static Set<String> validBrands = new HashSet(Arrays.asList("trek", "cannondale", "salsa",
            "jamis", "specialized", "surly", "giant", "bianchi", "soma", "cervelo"));
    private static Set<String> validLevels = new HashSet(Arrays.asList("silver", "gold", "platinum"));
   
    
    public static void addRepairPrice(String brand, String level, int price, int days) {
        RepairPrice rp = new RepairPrice(brand, level, price, days);
        for (RepairPrice p : prices) {
            System.out.println("for loop");
            if (p.brand.equals(rp.brand) && p.level.equals(rp.level)) {
                prices.remove(p);
                break;
            }
        }
        prices.add(rp);
        validBrands.add(rp.brand);
        validLevels.add(rp.level);
    }
    
    public static void printList(){
        System.out.println("BRAND\t\tLEVEL\tPRICE\tDAYS");
        for (RepairPrice p : prices) {
            System.out.println(p);
        }
    }
    
    public static String savableString() {
        String s = "";
        for (RepairPrice rp: prices) {
            s += rp.savableString() + "\n";
        }
        return s;
    }
    
    public static boolean isValidBrand(String brandString) {
        return validBrands.contains(brandString);
    }
    
    public static boolean isValidLevel(String levelString) {
        return validLevels.contains(levelString);
    }
    
    public static int returnNumberDays(String brand, String level) {
        for (RepairPrice rp: prices) {
            if(rp.brand.equals(brand) && rp.level.equals(level)) {
                return rp.days;
            }
        }
        return 0;
    }
    
    public static int returnPrice(String brand, String level) {
        for (RepairPrice rp: prices) {
            if(rp.brand.equals(brand) && rp.level.equals(level)) {
                return rp.price;
            }
        }
        return 0;
    }
}
