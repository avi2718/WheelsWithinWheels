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
public class PriceList {
    private RepairPrice[] prices;
    
    public static int returnDays(String level) {
        switch(level) {
            case "Silver": return 6;
            case "Gold": return 5;
            case "Platinum": return 3;
            case "Rush": return 2;
            default: System.out.println("invalid level");
            return 0;
        }
    }
}