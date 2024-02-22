/*
 *
 *  * Copyright (c) 2024. Manuel Daniel Dahmen
 *  *
 *  *
 *  *    Copyright 2024 Manuel Daniel Dahmen
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 *
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package one.empty3.apps.opad;

import one.empty3.apps.opad.menu.LevelMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/*__
 *
 * Meta Description missing
 * @author Manuel Dahmen dathewolf@gmail.com
 */
public class PanelGraphics extends JDialog {
    private final PanelGraphics panelGraphics;
    LevelMenu levelMenu = new LevelMenu();
    private int renderer = 0;


    /*__
     * Creates new form PanelGraphics
     */
    public PanelGraphics(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initPlayersList();
        panelGraphics = this;
    }

    private void jRadioButtonECGraphActionPerformed(ActionEvent e) {
        this.renderer = 0;
    }

    private void jRadioButtonOGLGraphActionPerformed(ActionEvent e) {
        this.renderer = 1;
    }

    private void initPlayersList() {
        ArrayList<Player> localPlayers = new ArrayList();
        try {
            localPlayers = Game.getLocalPlayers();

            ArrayList<String> playersNames = new ArrayList<String>();
            for (Player localPlayer : localPlayers) {
                playersNames.add(localPlayer.getName());
            }
            String[] playersnamesArray = new String[playersNames.size() + 1];
            int i = 0;
            for (String s : playersNames) {
                playersnamesArray[i] = s;
                i++;
            }
            playersnamesArray[i] = "New player";

            jComboBoxNom = new JComboBox<>();
            jComboBoxNom.setModel(new DefaultComboBoxModel<>(
                    playersnamesArray
            ));
            jComboBoxNom.setAction(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (((String) jComboBoxNom.getSelectedItem()).equals("New player")) {
                        NewPlayerDialog newPlayerDialog = new NewPlayerDialog((Frame) getParent(), true);
                        newPlayerDialog.setVisible(true);
                        newPlayerDialog.dispose();

                        initPlayersList();
                    }
                }

                ;
            });
        } catch (Exception ex) {
        }

    }

    /*__
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    private void initComponents() {
        ResourceBundle bundle = ResourceBundle.getBundle("one.empty3.apps.opad.Bundle");
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jTextFieldNom = new JTextField();
        jLabel3 = new JLabel();
        jRadioButtonECGraph = new JRadioButton();
        jRadioButtonOGLGraph = new JRadioButton();
        jLabel4 = new JLabel();
        jComboBoxTerrain = new JComboBox<>();
        jButtonStart = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(bundle.getString("PanelGraphics.title"));
        setBackground(new Color(51, 0, 255));
        setName("Form");
        setResizable(false);
        setAutoRequestFocus(false);
        setForeground(Color.lightGray);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                formWindowClosed(e);
            }
        });
        Container contentPane = getContentPane();

        //---- jLabel1 ----
        jLabel1.setFont(new Font("Parchment", Font.PLAIN, 48));
        jLabel1.setForeground(new Color(51, 0, 255));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText(bundle.getString("PanelGraphics.jLabel1.text"));
        jLabel1.setName("jLabel1");
        jLabel1.setBackground(Color.white);

        //---- jLabel2 ----
        jLabel2.setForeground(new Color(51, 0, 255));
        jLabel2.setLabelFor(jTextFieldNom);
        jLabel2.setText(bundle.getString("PanelGraphics.jLabel2.text"));
        jLabel2.setName("jLabel2");

        //---- jTextFieldNom ----
        jTextFieldNom.setForeground(new Color(51, 0, 255));
        jTextFieldNom.setText(bundle.getString("PanelGraphics.jTextFieldNom.text"));
        jTextFieldNom.setName("jTextFieldNom");

        //---- jLabel3 ----
        jLabel3.setForeground(new Color(51, 0, 255));
        jLabel3.setLabelFor(jRadioButtonECGraph);
        jLabel3.setText(bundle.getString("PanelGraphics.jLabel3.text"));
        jLabel3.setName("jLabel3");

        //---- jRadioButtonECGraph ----
        jRadioButtonECGraph.setForeground(new Color(51, 0, 255));
        jRadioButtonECGraph.setText(bundle.getString("PanelGraphics.jRadioButtonECGraph.text"));
        jRadioButtonECGraph.setName("jRadioButtonECGraph");
        jRadioButtonECGraph.addActionListener(e -> jRadioButtonECGraphActionPerformed(e));

        //---- jRadioButtonOGLGraph ----
        jRadioButtonOGLGraph.setForeground(new Color(51, 0, 255));
        jRadioButtonOGLGraph.setSelected(true);
        jRadioButtonOGLGraph.setText(bundle.getString("PanelGraphics.jRadioButtonOGLGraph.text"));
        jRadioButtonOGLGraph.setName("jRadioButtonOGLGraph");
        jRadioButtonOGLGraph.addActionListener(e -> jRadioButtonOGLGraphActionPerformed(e));

        //---- jLabel4 ----
        jLabel4.setForeground(new Color(51, 0, 255));
        jLabel4.setLabelFor(jComboBoxTerrain);
        jLabel4.setText(bundle.getString("PanelGraphics.jLabel4.text"));
        jLabel4.setName("jLabel4");

        //---- jComboBoxTerrain ----
        jComboBoxTerrain.setForeground(new Color(51, 0, 255));
        jComboBoxTerrain.setModel(new DefaultComboBoxModel<>(new String[]{
                "Plan",
                "Sinusoide",
                "Sinusoide Mouvante",
                "Sphere",
                "Tube"
        }));
        jComboBoxTerrain.setToolTipText(bundle.getString("PanelGraphics.jComboBoxTerrain.toolTipText"));
        jComboBoxTerrain.setName("jComboBoxTerrain");

        //---- jButtonStart ----
        jButtonStart.setFont(new Font("Tahoma", Font.PLAIN, 48));
        jButtonStart.setForeground(new Color(51, 0, 255));
        jButtonStart.setText(bundle.getString("PanelGraphics.jButtonStart.text"));
        jButtonStart.setName("jButton1");
        jButtonStart.addActionListener(this::jButton1ActionPerformed);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                                .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(jTextFieldNom)
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(jRadioButtonECGraph, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jRadioButtonOGLGraph, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jComboBoxTerrain))
                                                .addContainerGap())))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jButtonStart, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 21, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                        .addComponent(jTextFieldNom))
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGroup(contentPaneLayout.createParallelGroup()
                                                        .addComponent(jRadioButtonECGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jRadioButtonOGLGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(4, 4, 4)))
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(jComboBoxTerrain, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                        .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonStart, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        setSize(670, 405);
        setLocationRelativeTo(null);

        //---- buttonGroupGraphics ----
        ButtonGroup buttonGroupGraphics = new ButtonGroup();
        buttonGroupGraphics.add(jRadioButtonECGraph);
        buttonGroupGraphics.add(jRadioButtonOGLGraph);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


        DarkFortressGUI df;
        if (jRadioButtonECGraph.isSelected()) {

            df = new DarkFortressGUI(EcDrawer.class);
        } else {
            df = new DarkFortressGUI(JoglDrawer.class);
        }

        int selectedIndex = jComboBoxTerrain.getSelectedIndex();

        if (selectedIndex < 0 || selectedIndex >= levelMenu.getLevel().length) {
            Logger.getAnonymousLogger().log(Level.INFO, "Error creating init Level");
        } else {
            levelMenu.setIndex(selectedIndex);
        }

        Class<Terrain> loadClass = levelMenu.loadClass();

        if (loadClass == null)
            throw new NullPointerException("classForSol == null");
        Player byName = null;
        try {
            byName = Player.getByName(jComboBoxNom.getSelectedItem().toString());
        } catch (Exception ex) {
        }


        Game game = new Game();

        assert byName != null;

        game.setCurrentPlayer(byName);

        df.setGame(game);


        df.setLevel(loadClass, game.getLocalPlayer());


    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    /*__
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbu
	at java.desktop/java.awt.Component.setVisible(Component.java:1667)s look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelGraphics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelGraphics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelGraphics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelGraphics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                PanelGraphics dialog = new PanelGraphics(new JFrame(), false);
             /*   dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });*/
                dialog.setVisible(true);
                Sounds.playMusic();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField jTextFieldNom;
    private JLabel jLabel3;
    private JRadioButton jRadioButtonECGraph;
    private JRadioButton jRadioButtonOGLGraph;
    private JLabel jLabel4;
    private JComboBox<String> jComboBoxTerrain;
    private JButton jButtonStart;
    // End of variables declaration//GEN-END:variables
    JComboBox<String> jComboBoxNom;
}
