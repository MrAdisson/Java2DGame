package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        //SET UP ECRAN + paramètres ECRAN
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setTitle("Mon Jeu Java 2D");

        //AJOUT DU PANEL DE JEU A L'ECRAN
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //GAME PANEL SETUP FUNCTIONS AND THREAD START (LOOP)
        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}