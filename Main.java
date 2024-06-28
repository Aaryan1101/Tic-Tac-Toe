import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static boolean gameOver = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 3));

        JButton[][] buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(100, 100));

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(row, col, buttons);
                    }
                });
                buttons[i][j] = button;
                panel.add(button);
            }
        }

        JPanel emptyPanel = new JPanel();
        panel.add(emptyPanel);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame(buttons);
            }
        });
        panel.add(resetButton);

        JPanel emptyPane2 = new JPanel();
        panel.add(emptyPane2);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void buttonClicked(int row, int col, JButton[][] buttons) {
        if (!gameOver && board[row][col] == 0) {
            board[row][col] = currentPlayer;
            buttons[row][col].setText(String.valueOf(currentPlayer));

            if (checkForWinner(currentPlayer)) {
                JOptionPane.showMessageDialog(null, currentPlayer + " wins!");
                gameOver = true;
            } else if (checkForDraw()) {
                JOptionPane.showMessageDialog(null, "It's a draw!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private static boolean checkForWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    private static boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void resetGame(JButton[][] buttons) {
        gameOver = false;
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }
}