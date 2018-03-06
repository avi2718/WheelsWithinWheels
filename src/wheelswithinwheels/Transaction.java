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
abstract class Transaction {
    public TransactionType type;
    
    abstract public String savableString();
}
