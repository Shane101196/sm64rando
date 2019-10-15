package rand;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class JavaWindow {
    JFrame frame;
    String getValue;
    Random rand = new Random();
    File fh; 

    public JavaWindow() {

        JButton buttonInput = new JButton("Insert Pre-defined Seed");
        JTextField preSeed = new JTextField(20);
        JTextField newSeed = new JTextField(20);
        JTextField filePath = new JTextField(20);
        JButton buttonFile = new JButton("Open ROM..");
        JButton buttonNew = new JButton("Create New Seed");
        JButton buttonFinal = new JButton("Randomize");
        JCheckBox redCoin = new JCheckBox("Red Coins Randomized");
        JCheckBox starSpawn = new JCheckBox("Star Spawns Randomized");
        JCheckBox openingCutscene = new JCheckBox("Disable Opening Cutscenes");
        JCheckBox marioSpawn = new JCheckBox("Mario Spawns Randomized");
        JCheckBox marioColor = new JCheckBox("Mario's Colors Randomized");
        JCheckBox enemyType = new JCheckBox("Enemy Types Randomized");
        JCheckBox starColor = new JCheckBox("Star Select Background Randomized");
        JCheckBox warps = new JCheckBox("Warps Randomized");
        buttonInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               getValue = preSeed.getText();
            }
        });
        
        buttonFile.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(frame);

            try {
             Scanner reader = new Scanner(fc.getSelectedFile());
             fh = fc.getSelectedFile();
             filePath.setText(fh.getPath());
            } catch (Exception z){}
            }
        });
        buttonNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newSeed.setText(String.format("%09d", rand.nextInt(1000000000)));
                getValue = newSeed.getText();
            }
        });
        buttonFinal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Seed.setSeeds(Long.parseLong(getValue));
                           try {
                 Sm64_Randomizer.randomizer(fh, redCoin.isSelected(), starSpawn.isSelected(), openingCutscene.isSelected(), marioColor.isSelected(), enemyType.isSelected(), starColor.isSelected(), marioSpawn.isSelected(), warps.isSelected());
                  } catch(IOException x) {
               }
            JOptionPane.showMessageDialog(frame.getComponent(0), "Successfully Randomized. Close this program and your file will be named: SM64 Random.ext.z64 Information is located at: SM64 Randomizer Info.txt");
            }
        });
        JPanel panel = new JPanel();
        panel.add(buttonFile);
        panel.add(filePath);
        filePath.setEditable(false);
        panel.add(buttonInput);
        panel.add(preSeed);
        preSeed.setText("Enter Existing Seed Here.");
        panel.add(buttonNew);
        panel.add(newSeed);
        newSeed.setEditable(false);
        panel.add(redCoin);
        panel.add(starSpawn);
        panel.add(openingCutscene);
        panel.add(marioSpawn);
        panel.add(marioColor);
        panel.add(enemyType);
        panel.add(starColor);
        panel.add(warps);
        panel.add(buttonFinal);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        redCoin.setSelected(false);
        starSpawn.setSelected(false);
        marioSpawn.setSelected(false);
        openingCutscene.setSelected(false);
        marioColor.setSelected(false);
        enemyType.setSelected(false);
        starColor.setSelected(false);
        warps.setSelected(false);
        frame.pack();
        frame.setLocation(200,200);
        frame.setSize(275, 700);
        frame.setVisible(true);
    }
public String getData (){
   return getValue;
}
    }