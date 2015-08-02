/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Chamin
 */
public class Player {
    
    private String name;
    private String noOfWins;
    private String noOfLosses;
    private String noOfDraws;
    

    public Player(String name) {  // construtor with name 
        this.name = name;
        this.noOfWins="0";
        this.noOfLosses="0";
        this.noOfDraws="0";
        
        
        
    }

    public Player(String name, String noOfWins, String noOfLosses, String noOfDraws ) {
        this.name = name;
        this.noOfWins = noOfWins;
        this.noOfLosses = noOfLosses;
        this.noOfDraws = noOfDraws;
        
    }
    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoOfWins() {
        return noOfWins;
    }

    public void setNoOfWins(String noOfWins) {
        this.noOfWins = noOfWins;
    }

    public String getNoOfLosses() {
        return noOfLosses;
    }

    public void setNoOfLosses(String noOfLosses) {
        this.noOfLosses = noOfLosses;
    }

    public String getNoOfDraws() {
        return noOfDraws;
    }

    public void setNoOfDraws(String noOfDraws) {
        this.noOfDraws = noOfDraws;
    }

   
    
    
    
}
