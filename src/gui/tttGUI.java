/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import network.Network;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import data.DbConnector;
import data.Player;
import data.playerDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import static java.lang.Math.abs;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import play.Controller;

/**
 *
 * @author Chamin
 */
public class tttGUI extends javax.swing.JFrame {

    DbConnector dbConnector;
    playerDAO playerDao;
    Network lan;

    Controller controller = null;

    int list1[] = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // list that keep values of board
    int list1ID = 0;
    int list2[] = new int[9]; // list that keep click sequence
    int list2ID = 0;

    int gameMode;//1= single   2= multi offline   3= multi online
    int difficulty;  // 1-hard  // 2- medium   // 3 -easy

    Player player1;
    String p1name;
    Player player2;
    String p2name;

    boolean clickState[] = {false, false, false, false, false, false, false, false, false, false};

    //Random ran = new Random();
    int currentMark = 0;     //= ran.nextInt(2);

    int p1Mark = currentMark;
    int p2Mark = abs(currentMark - 1);

    int winnerIs;  // 0- pc  //1-plyer1  //2-player 2  //-1 draw
    String winnerStateTxt = "";
    String currentPlayer = p1name;
    //Dictionary<String, int> dic =new Dictionary<String, int>(){};

    int[] returnlist = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    String otherPC;

    public tttGUI(int gameMode, Player player1, Player player2, int difficulty) {//multi player mode
        initComponents();
        controller = new Controller();

        try {
            dbConnector = new DbConnector();
            playerDao = new playerDAO(dbConnector.getMyConn());

        } catch (IOException | SQLException ex) {
            Logger.getLogger(PlayerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.player1 = player1;
        this.p1name = player1.getName();
        this.player2 = player2;
        this.p2name = player2.getName();

        title.setText(player1.getName() + "  VS  " + player2.getName());

        difficultyLbl.setText("");

        stateUpdate();

        // set a method to the default exit button window lisntner
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    saveGame();// call exit method
                } catch (SQLException ex) {
                    Logger.getLogger(tttGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    tttGUI(int gameMode, Player player1, int difficulty, String otherPC) {// this is for single player mode and online mode
        initComponents();
        controller = new Controller();
        lan = new Network();

        try {
            dbConnector = new DbConnector();
            playerDao = new playerDAO(dbConnector.getMyConn());

        } catch (IOException | SQLException ex) {
            Logger.getLogger(PlayerMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.player1 = player1;
        this.p1name = player1.getName();
        this.p2name = "PC";
        this.otherPC = otherPC;

        if (gameMode == 1) {
            if (difficulty == 1) {
                difficultyLbl.setText("Difficulty level : Hard");
            } else if (difficulty == 2) {
                difficultyLbl.setText("Difficulty level : Medium");
            } else {
                difficultyLbl.setText("Difficulty level : Easy");
            }
        } else {// multi player online mode
            this.p2name = "Lan Player";
            try {

                currentMark = lan.createFile(otherPC);
                if (currentMark == 0) {
                    currentPlayer=p1name;
                } else {
                    currentPlayer="Lan Player";
                }
                System.out.println(currentMark);
            } catch (IOException ex) {
                Logger.getLogger(tttGUI.class.getName()).log(Level.SEVERE, null, ex);

            }

            readThread(otherPC);

        }
        title.setText(player1.getName() + "  VS  " + p2name);

        stateUpdate();

        // set a method to the default exit button window lisntner
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    saveGame();// call exit method
                    btnMenu.doClick();
                } catch (SQLException ex) {
                    Logger.getLogger(tttGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrp1 = new javax.swing.ButtonGroup();
        btn2 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        winnerState = new javax.swing.JLabel();
        btnReplay = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        nxtMovelbl = new javax.swing.JLabel();
        difficultyLbl = new javax.swing.JLabel();
        title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");

        btn2.setContentAreaFilled(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn1.setContentAreaFilled(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn3.setContentAreaFilled(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn6.setContentAreaFilled(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn5.setContentAreaFilled(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn4.setContentAreaFilled(false);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn9.setContentAreaFilled(false);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });

        btn8.setContentAreaFilled(false);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        btn7.setContentAreaFilled(false);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        winnerState.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        winnerState.setText("Winner is :");

        btnReplay.setText("Retry");
        btnReplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReplayActionPerformed(evt);
            }
        });

        btnMenu.setText("Player Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        nxtMovelbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nxtMovelbl.setText("Next move to :");

        difficultyLbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        difficultyLbl.setText("Difficulty level :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReplay, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(winnerState, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nxtMovelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(difficultyLbl))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(difficultyLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(winnerState, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nxtMovelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(btnReplay, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        title.setFont(new java.awt.Font("GungsuhChe", 0, 18)); // NOI18N
        title.setText("Player 1 Vs Player 2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        if (!clickState[4]) {
            click(4, currentMark);
            clickState[4] = true;
        }
        //btn4.setEnabled(false);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        if (!clickState[7]) {
            click(7, currentMark);
            clickState[7] = true;
        }
        //btn7.setEnabled(false);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        if (!clickState[3]) {
            click(3, currentMark);
            clickState[3] = true;
        }
        //btn3.setEnabled(false);

    }//GEN-LAST:event_btn3ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        if (!clickState[2]) {
            click(2, currentMark);
            clickState[2] = true;
        }
        //btn2.setEnabled(false);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        if (!clickState[1]) {
            click(1, currentMark);
            clickState[1] = true;
        }
        //btn1.setEnabled(false);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        if (!clickState[6]) {
            click(6, currentMark);
            clickState[6] = true;
        }
        //btn6.setEnabled(false);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        if (!clickState[5]) {
            click(5, currentMark);
            clickState[5] = true;
        }
        //btn5.setEnabled(false);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        if (!clickState[9]) {
            click(9, currentMark);
            clickState[9] = true;
        }
        //btn9.setEnabled(false);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        if (!clickState[8]) {
            click(8, currentMark);
            clickState[8] = true;
        }
        //btn8.setEnabled(false);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btnReplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplayActionPerformed
        try {
            replay();
        } catch (SQLException ex) {
            Logger.getLogger(tttGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReplayActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed

        try {
            saveGame();

        } catch (SQLException ex) {
            Logger.getLogger(tttGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
        new PlayerMenu().setVisible(true);
    }//GEN-LAST:event_btnMenuActionPerformed

    public static void main(String args[]) {

        try {
            /* Create and display the form */
            javax.swing.UIManager.setLookAndFeel(new NoireLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(tttGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new tttGUI().setVisible(true);
            }
        });
    }

    void readThread(String otherPC) {

        Thread threadSB = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {

                        //System.out.println("in read tread");
                        returnlist = lan.read(otherPC);
                        if (returnlist == null) {
                            continue;
                        }
                        //System.out.println("after read tread");

                        boolean equal = true;
                        for (int i = 0; i < returnlist.length; i++) {
                            if (returnlist[i] != list1[i]) {
                                equal = false;

                            }

                        }
                        if (equal == false) {
                            list1 = returnlist;
                            System.out.println(Arrays.toString(returnlist));
                            currentPlayer = p1name;
                            btnUpdate(returnlist);
                        }

                        //let thread sleep
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        System.out.println("Error in state update thread");
                    }
                }
            }
        };

        threadSB.start();

    }

    void fileWrite() {

        try {
            //System.out.println(Arrays.toString(list1));
            //int[] tempLiest1=list1.clone();

            lan.write(list1);
            System.out.println("Writing");
        } catch (IOException ex) {
            Logger.getLogger(tttGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void stateUpdate() {

        Thread threadSB = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        winnerState.setText(winnerStateTxt);
                        nxtMovelbl.setText("Next move to :  " + currentPlayer);
                        //let thread sleep
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        System.out.println("Error in state update thread");
                    }
                }
            }
        };
        threadSB.start();
    }

    public void click(int index, int mark) {

        setMark(index, mark);  //setting icon of btn intially
        if (gameMode == 3) {
            fileWrite();
            currentPlayer = "Lan Player";
        }
        /*
         list1[index]=mark;
         list2[list2ID]=index;
         list2ID++;
         */

        int winner = controller.checkForWin(list1, abs(mark - 1), mark);// list1 ,com marks,plyer1 marks

        if (winner == 0) {
            if (list2[8] != 0) {
                //System.out.println("Game is draw");
                JOptionPane.showMessageDialog(null, "Game is draw");
                lockBtns();
                winnerIs = -1;
            } else if (gameMode == 1) {
                singlePlayer(mark);  //single player mode

            } else if (gameMode == 2) {
                currentMark = abs(mark - 1);  // two palyer mode
                if (currentMark == p1Mark) {
                    currentPlayer = p1name;

                } else {
                    currentPlayer = p2name;
                }
            }
        } else if (winner == -1) {
            if (mark == p1Mark) {

                JOptionPane.showMessageDialog(null, p2name + " wins");
                winnerStateTxt = p2name + " wins";
                lockBtns();
                if (p2name.equals("PC")) {
                    winnerIs = 0;
                } else {
                    winnerIs = 2;
                }
            } else {
                JOptionPane.showMessageDialog(null, p1name + "wins");
                winnerStateTxt = p1name + " wins";
                lockBtns();
                winnerIs = 1;
            }

        } else if (winner == -2) {

            if (mark == p1Mark) {
                //System.out.println("**************  player 1 **************");
                JOptionPane.showMessageDialog(null, p1name + " wins");
                winnerStateTxt = p1name + " wins";
                lockBtns();
                winnerIs = 1;
            } else {
                JOptionPane.showMessageDialog(null, p2name + " wins");
                winnerStateTxt = p2name + " wins";
                lockBtns();
                if (p2name.equals("PC")) {
                    winnerIs = 0;
                } else {
                    winnerIs = 2;
                }
            }
        }

    }

    private void singlePlayer(int mark) {

        int tempList2[] = Arrays.copyOfRange(list2, 0, list2ID);
        int nextClick;

        if (difficulty == 1) {
            nextClick = controller.nextChoice(list1, tempList2, abs(mark - 1), mark);
        } else if (difficulty == 2) {
            nextClick = controller.mediumChoice(list1, abs(mark - 1), mark);
        } else {
            nextClick = controller.eazyChoise(list1, abs(mark - 1), mark);
        }

        //System.out.println("next click is");
        if (nextClick == 0) {
            JOptionPane.showMessageDialog(null, "Game is draw");
            winnerStateTxt = "Game is draw";
            lockBtns();
            winnerIs = -1;
        }

        if (mark == 0) {
            setMark(nextClick, 1);
        }
        if (mark == 1) {
            setMark(nextClick, 0);

        }

        int winner = controller.checkForWin(list1, abs(mark - 1), mark);// list1 ,com marks,plyer1 marks

        //System.out.println(winner + "********" + list2[8]);
        if (winner == 0 && (list2[8] != 0)) {
            JOptionPane.showMessageDialog(null, "Game is draw");
            winnerStateTxt = "Game is draw";
            lockBtns();
            winnerIs = -1;

        } else if (winner == -1) {
            //System.out.println("**************  Com win **************");
            JOptionPane.showMessageDialog(null, "PC wins");
            winnerStateTxt = "PC wins";
            lockBtns();
            winnerIs = 0;

        } else if (winner == -2) {

            //System.out.println("**************  user win **************");
            JOptionPane.showMessageDialog(null, p1name + " wins");
            winnerStateTxt = p1name + " wins";
            lockBtns();
            winnerIs = 1;
        } else {
            currentPlayer = p1name;
        }
    }

    private void lockBtns() {
        for (int i = 1; i < 10; i++) {
            clickState[i] = true;
        }
    }

    void replay() throws SQLException {
        saveGame();
        this.dispose();
        if (gameMode == 1 || gameMode == 3) {
            new tttGUI(gameMode, player1, difficulty, otherPC).setVisible(true);
        } else if (gameMode == 2) {
            new tttGUI(gameMode, player1, player2, difficulty).setVisible(true);
        }
    }

    void saveGame() throws SQLException {
        if (gameMode == 3) {// delete the file in muti play online mode
           
            lan.deleteFile();

        }
        switch (winnerIs) {
            case -1:
                if (gameMode == 1 || gameMode == 3) {
                    int draws1 = Integer.parseInt(player1.getNoOfDraws()) + 1;
                    player1.setNoOfDraws(Integer.toString(draws1));

                } else if (gameMode == 2) {
                    int draws1 = Integer.parseInt(player1.getNoOfDraws()) + 1;
                    player1.setNoOfDraws(Integer.toString(draws1));

                    int draws2 = Integer.parseInt(player2.getNoOfDraws()) + 1;
                    player2.setNoOfDraws(Integer.toString(draws2));
                }
                break;

            case 0:
                int losses1 = Integer.parseInt(player1.getNoOfLosses()) + 1;
                player1.setNoOfLosses(Integer.toString(losses1));
                break;

            case 1:
                if (gameMode == 1 || gameMode == 3) {

                    int wins = Integer.parseInt(player1.getNoOfWins()) + 1;
                    player1.setNoOfWins(Integer.toString(wins));

                } else if (gameMode == 2) {
                    int wins = Integer.parseInt(player1.getNoOfWins()) + 1;
                    player1.setNoOfWins(Integer.toString(wins));

                    int losses = Integer.parseInt(player2.getNoOfLosses()) + 1;
                    player2.setNoOfLosses(Integer.toString(losses));
                }
                break;

            case 2:
                if (gameMode == 2) {
                    int wins = Integer.parseInt(player2.getNoOfWins()) + 1;
                    player2.setNoOfWins(Integer.toString(wins));

                    int losses = Integer.parseInt(player1.getNoOfLosses()) + 1;
                    player1.setNoOfLosses(Integer.toString(losses));
                }
                break;

        }

        if (gameMode == 1 || gameMode == 3) {
            playerDao.updatePlayer(player1, p1name);
        } else if (gameMode == 2) {
            playerDao.updatePlayer(player1, p1name);
            playerDao.updatePlayer(player2, p2name);

        }

    }

    private void setMark(int nextClick, int nextMark) {

        if (clickState[0] == false) {
            //SPbtn.setEnabled(false);
            //TPbtn.setEnabled(false);
            clickState[0] = true; //atleast one butto has clicked once
            //System.out.println("**********************************************************");
        }

        //System.out.println(nextClick + "***************************");
        list1[nextClick - 1] = nextMark;

        list2[list2ID] = nextClick;

        list2ID++;

        if (nextMark == 1) {
            //System.out.println("nextMark  1 in if  ******************");
            switch (nextClick) {
                case 1:
                    btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
                    clickState[1] = true;
                    break;
                case 2:
                    btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
                    clickState[2] = true;
                    break;
                case 3:
                    btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
                    clickState[3] = true;
                    break;
                case 4:
                    btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
                    clickState[4] = true;
                    break;
                case 5:
                    btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
                    clickState[5] = true;
                    break;
                case 6:
                    btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
                    clickState[6] = true;
                    break;
                case 7:
                    btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
                    clickState[7] = true;
                    break;
                case 8:
                    btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
                    clickState[8] = true;
                    break;
                case 9:
                    btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
                    clickState[9] = true;
                    break;
                default:
                    System.out.println("There is a error");
            }
        } else if (nextMark == 0) {
            //System.out.println("nextMark  0 in if  ******************");
            switch (nextClick) {
                case 1:
                    btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
                    clickState[1] = true;
                    break;
                case 2:
                    btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
                    clickState[2] = true;
                    break;
                case 3:
                    btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
                    clickState[3] = true;
                    break;
                case 4:
                    btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
                    clickState[4] = true;
                    break;
                case 5:
                    btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
                    clickState[5] = true;
                    break;
                case 6:
                    btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
                    clickState[6] = true;
                    break;
                case 7:
                    btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
                    clickState[7] = true;
                    break;
                case 8:
                    btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
                    clickState[8] = true;
                    break;
                case 9:
                    btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
                    clickState[9] = true;
                    break;
                default:
                    System.out.println("There is a error");

            }

        }

    }

    void btnUpdate(int[] lanList) {
        if (lanList[0] == 1) {
            btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
            clickState[1] = true;
        }
        if (lanList[0] == 0) {
            btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
            clickState[1] = true;
        }
        if (lanList[1] == 1) {
            btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
            clickState[2] = true;
        }
        if (lanList[1] == 0) {
            btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
            clickState[2] = true;
        }
        if (lanList[2] == 1) {
            btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
            clickState[3] = true;
        }
        if (lanList[2] == 0) {
            btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
            clickState[3] = true;
        }
        if (lanList[3] == 1) {
            btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
            clickState[4] = true;
        }
        if (lanList[3] == 0) {
            btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
            clickState[4] = true;
        }
        if (lanList[4] == 1) {
            btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
            clickState[5] = true;
        }
        if (lanList[4] == 0) {
            btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
            clickState[5] = true;
        }
        if (lanList[5] == 1) {
            btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
            clickState[6] = true;
        }
        if (lanList[5] == 0) {
            btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
            clickState[6] = true;
        }
        if (lanList[6] == 1) {
            btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
            clickState[7] = true;
        }
        if (lanList[6] == 0) {
            btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
            clickState[7] = true;
        }
        if (lanList[7] == 1) {
            btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
            clickState[8] = true;
        }
        if (lanList[7] == 0) {
            btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
            clickState[8] = true;
        }
        if (lanList[8] == 1) {
            btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
            clickState[9] = true;
        }
        if (lanList[8] == 0) {
            btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
            clickState[9] = true;
        }

        /*
         int j = 1;
         for (int i : lanList) {
         if (i == 1) {

         //System.out.println("nextMark  1 in if  ******************");
         switch (j) {
         case 1:
         btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
         clickState[1] = true;
         break;
         case 2:
         btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
         clickState[2] = true;
         break;
         case 3:
         btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
         clickState[3] = true;
         break;
         case 4:
         btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
         clickState[4] = true;
         break;
         case 5:
         btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
         clickState[5] = true;
         break;
         case 6:
         btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
         clickState[6] = true;
         break;
         case 7:
         btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
         clickState[7] = true;
         break;
         case 8:
         btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
         clickState[8] = true;
         break;
         case 9:
         btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/cross.png")));
         clickState[9] = true;
         break;
         default:
         System.out.println("There is a error");
         }
         j++;
         } else if (i == 0) {
         //System.out.println("nextMark  0 in if  ******************");
         switch (j) {
         case 1:
         btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
         clickState[1] = true;
         break;
         case 2:
         btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
         clickState[2] = true;
         break;
         case 3:
         btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
         clickState[3] = true;
         break;
         case 4:
         btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
         clickState[4] = true;
         break;
         case 5:
         btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
         clickState[5] = true;
         break;
         case 6:
         btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
         clickState[6] = true;
         break;
         case 7:
         btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
         clickState[7] = true;
         break;
         case 8:
         btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
         clickState[8] = true;
         break;
         case 9:
         btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/png/nought.png")));
         clickState[9] = true;
         break;
         default:
         System.out.println("There is a error");

         }
         j++;

         }

         }*/
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.ButtonGroup btnGrp1;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnReplay;
    private javax.swing.JLabel difficultyLbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nxtMovelbl;
    private javax.swing.JLabel title;
    private javax.swing.JLabel winnerState;
    // End of variables declaration//GEN-END:variables

}
