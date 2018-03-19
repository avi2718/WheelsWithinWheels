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
public class RepairPrice {
    public String brand;
    public String level;
    public int price;
    public int days;
    
    public RepairPrice(String brand, String level, int price, int days ) {
        this.brand = brand;
        this.level = level;
        this.price = price;
        this.days = days;
    }
    
    public String savableString() {
        return "addrp " + brand + " " + level + " " + price + " " + days;
    }
}
