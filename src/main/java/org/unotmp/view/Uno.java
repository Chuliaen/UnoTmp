package org.unotmp.view;

import org.unotmp.card.*;
import org.unotmp.game.GameDirection;
import org.unotmp.game.GameHandler;
import org.unotmp.player.Player;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Uno extends JFrame implements ActionListener {

    //Logic
    private ClientLogic clientLogic;
    private ActionListener playCardListener;
    private ActionListener drawCardListener;
    private ActionListener unoButtonListener;

    // Creating
    private JPanel mainPane;
    private JButton startButton;
    private JLabel playerNames;
    private JTextField playerName2;
    private JTextField playerName4;
    private JTextField playerName1;
    private JTextField playerName3;

    private JPanel playerPanel;
    private GridBagConstraints c;

    JRadioButton[] wildColors;

    private String activePlayerUsername;

    public Uno(ClientLogic clientLogic) {
        this.clientLogic = clientLogic;
        this.playCardListener = new PlayCardListener();
        this.drawCardListener = new DrawCardListener();
        this.unoButtonListener = new UnoButtonListener();

        setTitle("Uno");
        setSize(1280, 800);
        setLocationRelativeTo(null);

        setContentPane(mainPane);

        startButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.startButton) {
            mainPane.setVisible(false);
            generateBoard(clientLogic);
        }
    }

    private void generateBoard(ClientLogic clientLogic) {
        String[] playerList = new String[]{playerName1.getText(), playerName2.getText(), playerName3.getText(), playerName4.getText()};

        if (playerList[0] == null || playerList[0].equals("")) {
            mainPane.setVisible(true);
            return;
        }
        int enough = 0;
        for (int i = 1; i < playerList.length; i++) {
            if (playerList[i] != null && !playerList[i].equals("")) {
                enough++;
            }
        }
        if (enough < 1) {
            mainPane.setVisible(true);
            return;
        }

        clientLogic.createGame(playerList[0]);
        int gameId = clientLogic.getGameId();

        for (int i = 1; i < playerList.length; i++) {
            if (playerList[i] != null && !playerList[i].equals("")) {
                clientLogic.joinGame(gameId, playerList[i]);
            }
        }
        clientLogic.startGame();
    }

    public void showBoard(Player[] players, int activePlayerID, GameDirection gameDirection, Card topCard) {
        playerPanel = new JPanel();
        playerPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 0.10;
        c.weighty = 0.0;
        c.insets = new Insets(40, 0, 0, 40);

        activePlayerUsername = players[activePlayerID].getName();

        JLabel[] lNames = new JLabel[players.length];
        ArrayList<JButton>[] arrayList = new ArrayList[players.length];
        int counter = 1;

        //one more row for draw and active card
        for (Player player : players) {
            JLabel playerLabel = generatePlayerLabel(player.getName(), player.getCards().size());
            if (player.getName().equals(activePlayerUsername)) {
                playerLabel.setForeground(Color.GREEN);
            }

            List<Card> playerCards = player.getCards();

            setLabelFormat(playerLabel, 150, 100);
            addToPlayerPanel(playerLabel, counter, 0, 1, 1);
            lNames[counter - 1] = playerLabel;
            arrayList[counter - 1] = new ArrayList<JButton>();

            for (int i = 0; i < playerCards.size(); i++) {
                JButton b = new JButton();
                b.setOpaque(true);
                b.setBorderPainted(false);
                if (!player.getName().equals(activePlayerUsername)) {
                    b.setEnabled(false);
                    b.setText("UNO");
                    b.setBackground(Color.BLACK);
                } else {
                    b.setText(playerCards.get(i).toString().split(",[ ]*")[0]);
                    setButtonColor(playerCards.get(i).getCardColor(), b);
                }
                setButtonFormat(b);

                addToPlayerPanel(b, counter, i + 1, 1, 1);
                b.addActionListener(playCardListener);

                arrayList[counter - 1].add(b);
            }
            counter++;
        }

        JLabel informationLabel = new JLabel("Informations");
        setLabelFormat(informationLabel, 150, 100);
        addToPlayerPanel(informationLabel, counter, 0, 1, 1);

        JButton drawButton = new JButton();
        drawButton.setOpaque(true);
        drawButton.setBorderPainted(false);
        drawButton.setText("DRAW");
        drawButton.setBackground(Color.WHITE);
        setButtonFormat(drawButton);
        addToPlayerPanel(drawButton, counter, 1, 1, 1);
        drawButton.addActionListener(drawCardListener);

        JButton topCardButton = new JButton();
        //todo: set enabled false, but white textcolor
        topCardButton.setOpaque(true);
        topCardButton.setBorderPainted(false);
        topCardButton.setText(topCard.toString().split(",[ ]*")[0]);
        setButtonColor(topCard.getCardColor(), topCardButton);
        setButtonFormat(topCardButton);
        addToPlayerPanel(topCardButton, counter, 2, 1, 1);

        JButton unoButton = new JButton();
        unoButton.setOpaque(true);
        unoButton.setBorderPainted(false);
        unoButton.setText("Say Uno");
        unoButton.setBackground(new Color(200, 85, 80));
        setButtonFormat(unoButton);
        addToPlayerPanel(unoButton, counter, 3, 1, 1);
        unoButton.addActionListener(unoButtonListener);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel yourWildCard = new JLabel("Your wild card color:");
        radioPanel.add(yourWildCard);
        wildColors = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();
        String[] wildLabels = {"Red", "Blue", "Yellow", "Green"};
        for (int i = 0; i < 4; i++) {
            wildColors[i] = new JRadioButton(wildLabels[i]);
            wildColors[i].setActionCommand(wildLabels[i].substring(0, 1));
            buttonGroup.add(wildColors[i]);
            radioPanel.add(wildColors[i]);
        }
        addToPlayerPanel(radioPanel, counter, 4, 4, 1);

        setContentPane(playerPanel);
    }

    public void setButtonFormat(JButton b) {
        b.setMargin(new Insets(1, 1, 1, 1));
        b.setMinimumSize(new Dimension(80, 100));
        b.setMaximumSize(new Dimension(80, 100));
        b.setPreferredSize(new Dimension(80, 100));
        b.setFont(new Font("Tahoma", Font.BOLD, 10));
    }

    public void setButtonColor(CardColor c, JButton b) {
        if (c == CardColor.RED) {
            b.setBackground(new Color(242, 95, 92));
        } else if (c == CardColor.BLUE) {
            b.setBackground(new Color(36, 123, 160));
        } else if (c == CardColor.YELLOW) {
            b.setBackground(new Color(255, 224, 102));
        } else if (c == CardColor.GREEN) {
            b.setBackground(new Color(136, 212, 152));
        } else if (c == null) {
            b.setBackground(Color.LIGHT_GRAY);
        }
    }

    public JLabel generatePlayerLabel(String playerName, int cardCount) {
        return new JLabel(String.format("%s (%d cards)", playerName, cardCount));
    }

    public void setLabelFormat(JLabel l, int width, int height) {
        l.setMinimumSize(new Dimension(width, height));
        l.setMaximumSize(new Dimension(width, height));
        l.setPreferredSize(new Dimension(width, height));
    }

    public void addToPlayerPanel(Component comp, int row, int column, int width, int height) {
        // set gridx and gridy
        c.gridx = column;
        c.gridy = row;

        // set gridwidth and gridheight
        c.gridwidth = width;
        c.gridheight = height;

        // add component
        playerPanel.add(comp, c);
    }


    /**
     * Classes for ActionListener
     */
    private class UnoButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientLogic.sayUno(activePlayerUsername);
        }
    }

    private class DrawCardListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            playerPanel.setVisible(false);
            clientLogic.drawCard(activePlayerUsername);
        }
    }

    private class PlayCardListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            JButton button = (JButton) ae.getSource();
            Card card = createCard(button);
            playerPanel.setVisible(false);
            try {
                clientLogic.playCard(card, activePlayerUsername);
            } catch (IllegalStateException e) {
                String winner = e.getMessage();
                JOptionPane.showMessageDialog(playerPanel, winner, "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }

        public Card createCard(JButton button) {
            Color color = button.getBackground();
            String type = button.getText();
            CardColor cardColor = null;
            Card card;

            if (color.equals(new Color(242, 95, 92))) {
                //Rot
                cardColor = CardColor.RED;
            } else if (color.equals(new Color(36, 123, 160))) {
                //Blau
                cardColor = CardColor.BLUE;
            } else if (color.equals(new Color(255, 224, 102))) {
                //Gelb
                cardColor = CardColor.YELLOW;
            } else if (color.equals(new Color(136, 212, 152))) {
                //Grün
                cardColor = CardColor.GREEN;
            } else if (color.equals(Color.LIGHT_GRAY)) {
                //Wild
                for (JRadioButton radioButton : wildColors) {
                    if (radioButton.isSelected()) {
                        String s = radioButton.getText();
                        switch (s) {
                            case "Red" -> cardColor = CardColor.RED;
                            case "Blue" -> cardColor = CardColor.BLUE;
                            case "Yellow" -> cardColor = CardColor.YELLOW;
                            case "Green" -> cardColor = CardColor.GREEN;
                        }
                    }
                }
            }

            switch (type) {
                case "0" -> {
                    card = new CardNumber(0, cardColor);
                }
                case "1" -> {
                    card = new CardNumber(1, cardColor);
                }
                case "2" -> {
                    card = new CardNumber(2, cardColor);
                }
                case "3" -> {
                    card = new CardNumber(3, cardColor);
                }
                case "4" -> {
                    card = new CardNumber(4, cardColor);
                }
                case "5" -> {
                    card = new CardNumber(5, cardColor);
                }
                case "6" -> {
                    card = new CardNumber(6, cardColor);
                }
                case "7" -> {
                    card = new CardNumber(7, cardColor);
                }
                case "8" -> {
                    card = new CardNumber(8, cardColor);
                }
                case "9" -> {
                    card = new CardNumber(9, cardColor);
                }
                case "SKIP" -> {
                    card = new CardAction(CardType.SKIP, cardColor);
                }
                case "REVERSE" -> {
                    card = new CardAction(CardType.REVERSE, cardColor);
                }
                case "DRAW_TWO" -> {
                    card = new CardAction(CardType.DRAW_TWO, cardColor);
                }
                case "WILD" -> {
                    card = new CardWild(CardType.WILD, cardColor);
                }
                case "WILD_DRAW_FOUR" -> {
                    card = new CardWild(CardType.WILD_DRAW_FOUR, cardColor);
                }
                default -> {
                    card = null;
                }
            }
            return card;
        }
    }

    public static void main(String[] args) {
        GameHandler gameHandler = new GameHandler();
        ClientLogic clientLogic = new ClientLogic(gameHandler);
        gameHandler.setClientLogic(clientLogic);

        Uno uno = new Uno(clientLogic);
        clientLogic.setUno(uno);

    }
}
