package tp.checkers.client.gui;

import tp.checkers.client.GameService;
import tp.checkers.client.GameServicePlayed;
import tp.checkers.client.gui.button.ButtonCommit;
import tp.checkers.client.gui.button.ButtonReset;

import javax.swing.*;
import java.awt.*;

/**
 * Class of the client's window.
 */
public class WindowPlayed extends Window {
    /**
     * Reference to client's game service.
     */
    private GameService gameService;

    /**
     * Label showing the player's color.
     */
    private JLabel labelColor;

    /**
     * Label showing if it's the player's turn.
     */
    private JLabel labelTurn;

    /**
     * Default constructor.
     */
    public WindowPlayed() {
        initUI();
    }

    /**
     * Method responsible for receiving the data from client connector,
     * creating a panel and passing the data to game service.
     *
     * @param gameService reference to client's game service
     * @param color       color of the player
     * @param arraySide   length of one side of Fields array
     */
    @Override
    public void initBoard(GameService gameService, Color color, int arraySide) {
        System.out.println("Initialisation of the board and panel");

        this.gameService = gameService;
        this.color = color;

        initLabels();

        this.panel = new Panel(gameService, windowSide, arraySide, color);
        panel.add(labelColor);
        panel.add(labelTurn);
        this.add(panel);

        initButtons();

        this.setVisible(true);

        gameService.startGame(panel);
    }

    /**
     * Method responsible for initialisation of the Commit and Reset buttons.
     */
    @Override
    protected void initButtons() {
        ButtonCommit buttonCommit = new ButtonCommit((GameServicePlayed) gameService);
        panel.add(buttonCommit);

        ButtonReset buttonReset = new ButtonReset((GameServicePlayed) gameService);
        panel.add(buttonReset);
    }

    /**
     * Method responsible for initialisation of the Color and Turn labels.
     */
    @Override
    protected void initLabels() {
        labelColor = new JLabel("This is your color.");
        labelColor.setBounds(70, 20, 300, 40);
        labelColor.setFont(new Font(labelColor.getName(), Font.BOLD, 22));
        labelColor.setForeground(color.darker());

        labelTurn = new JLabel("Wait for your turn.");
        labelTurn.setBounds(70, 70, 300, 40);
        labelTurn.setFont(new Font(labelColor.getName(), Font.BOLD, 22));
    }

    /**
     * Setter for Turn label's text.
     *
     * @param text text to be set
     */
    public void setLabelTurnText(String text) {
        labelTurn.setText(text);
    }
}
