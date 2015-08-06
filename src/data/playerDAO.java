/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chamin
 */
public class playerDAO {
    
    private final Connection myConn;
    

    public playerDAO(Connection myConn) {
        this.myConn = myConn;
    }
    
    public Connection getMyConn(){
        return myConn;   
    }
    
    
    // add a new player
    public void addPlayer(Player player) throws SQLException{
        PreparedStatement myStmnt =null;
        
        try{
        String query = "INSERT INTO player (name,noOfWins,noOfLosses,noOfDraws) values (?,?,?,?)";
        myStmnt = myConn.prepareStatement(query);
            //System.out.println("create my stmnt");
        //set parameters
        
        myStmnt.setString(1, player.getName());
        myStmnt.setString(2, player.getNoOfWins());
        myStmnt.setString(3, player.getNoOfLosses());
        myStmnt.setString(4, player.getNoOfDraws());
        
        myStmnt.executeUpdate();
        //System.out.println("Successfully added");
        
        }
        
        finally{
        close(myStmnt);
        
        } 
    }
    /**
     * update player
     *
     * @throws java.sql.SQLException */
    public void updatePlayer (Player player, String selectedPlayer) throws SQLException{
        //System.out.println(selectedPlayer+ "   saving " + player.getName());
        PreparedStatement myStmnt = null; 
        try{
            //prepare the statement
            String query = "UPDATE player set name=?,noOfWins=?,noOfLosses=?,noOfDraws=? WHERE name=?"; 
            myStmnt = myConn.prepareStatement(query);
            // set params
            myStmnt.setString(1,player.getName() );
            myStmnt.setString(2, player.getNoOfWins() );
            myStmnt.setString(3, player.getNoOfLosses() );
            myStmnt.setString(4, player.getNoOfDraws());
            
            myStmnt.setString(5, selectedPlayer);
            
            //execute statement
            myStmnt.executeUpdate();
        }
        finally {
            close(myStmnt);
        }
    }
    
    public List<Player> getAllPlayers() throws SQLException {
        
        List<Player> list = new ArrayList<>();
        Statement myStmnt = null;
        ResultSet myRslt = null; 

        try {
                myStmnt = myConn.createStatement();
                myRslt = myStmnt.executeQuery("select * from player");
                
                // load each player object to the player List
                while (myRslt.next()) {
                        Player tempPlayer = convertRawToPlayer(myRslt);
                        list.add(tempPlayer);
                }
                return list;		
        }
        catch (SQLException ex) {        
            Logger.getLogger(playerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            close(myStmnt, myRslt);
        }
        return null;
    }
     // search player by keyword
    public List<Player> searchPlayer(String keyWord) throws SQLException{
        
        List<Player> list = new ArrayList<>();
        
        keyWord = "%"+keyWord+"%";
        PreparedStatement myStmnt = null;
        ResultSet myRslt = null;
        
        try{
            
            String  query = "SELECT * from player where name like ? ";
            myStmnt = myConn.prepareStatement(query);
            myStmnt.setString(1, keyWord);

            myRslt = myStmnt.executeQuery();  
            Player player = null;
            while (myRslt.next()) {
                
               Player tempPlayer= convertRawToPlayer(myRslt);
               list.add(tempPlayer);
            }
            return list;
        }
        finally {
                close(myStmnt, myRslt);
        }
        
        
    }
    
      public void deletePlayer (String name) throws SQLException{
        PreparedStatement myStmt = null;
        try{
            // prepare statement
            myStmt = myConn.prepareStatement("delete from player where name=?");
            
            //set param
            myStmt.setString(1, name);
            
            //execute statement
            myStmt.executeUpdate();
        }
        finally{
            close(myStmt);
        }
    }  
        
                
        
        
        
    private Player convertRawToPlayer(ResultSet myRslt) throws SQLException {
       
        
        
        
        String name = myRslt.getString(1);
        //System.out.println(name+"    name");
        
        String noOfWins = myRslt.getString(2);
        String noOfLosses = myRslt.getString(3);
        String noOfDraws = myRslt.getString(4);
        /*
        String tempMarksList = myRslt.getString(5);
        int[] marksList=null;
        for(int i=0;i<9;i++){      
            marksList[i]=Character.getNumericValue(tempMarksList.charAt(i));
        }
                
        String tempChoiceList = myRslt.getString(6);
        int[] choiceList=null;
        for(int i=0;i<9;i++){      
            choiceList[i]=Character.getNumericValue(tempChoiceList.charAt(i));
        }*/
    
        Player tempPlayer =new Player( name,  noOfWins,  noOfLosses,  noOfDraws);

        return tempPlayer;
        
    }
    
    
    
    
    
    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

        if (myRs != null) {
                myRs.close();
        }
        if (myStmt != null) {
                myStmt.close();
        }
        if (myConn != null) {
                myConn.close();
        }
    }
    
    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);	
    }
    private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);	
    }
}
