package com.smoko.application;

import com.smoko.manager.GameBoardManager;

import javax.swing.JFrame;
import java.awt.Color;

public class SmokoApp {
   
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.add(new GameBoardManager());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.WHITE);
        window.setBounds(500, 30, GameBoardManager.getBoardWidth(), GameBoardManager.getBoardHeight());
        window.setVisible(true);
    }
}


