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
        prices.add(rp);
    }
    
    public static void printList(){
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
            if(rp.brand == brand && rp.level == level) {
                return rp.days;
            }
        }
        return 0;
    }
}
