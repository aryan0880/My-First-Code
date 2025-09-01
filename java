import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RockPaperScissorsGUI extends JFrame implements ActionListener {

    private JButton rockButton, paperButton, scissorsButton;
    private JLabel resultLabel, scoreLabel, computerChoiceLabel;
    private int userScore = 0, compScore = 0;
    private Random random = new Random();

    public RockPaperScissorsGUI() {
        setTitle("Rock Paper Scissors Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Title
        JLabel title = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Info Panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        resultLabel = new JLabel("Make your choice!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        computerChoiceLabel = new JLabel("Computer: ---", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: You 0 - 0 Computer", SwingConstants.CENTER);

        infoPanel.add(resultLabel);
        infoPanel.add(computerChoiceLabel);
        infoPanel.add(scoreLabel);

        add(infoPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userChoice = "";
        if (e.getSource() == rockButton) {
            userChoice = "rock";
        } else if (e.getSource() == paperButton) {
            userChoice = "paper";
        } else if (e.getSource() == scissorsButton) {
            userChoice = "scissors";
        }

        String[] choices = {"rock", "paper", "scissors"};
        String compChoice = choices[random.nextInt(3)];

        computerChoiceLabel.setText("Computer: " + compChoice);

        String result = decideWinner(userChoice, compChoice);
        resultLabel.setText(result);

        scoreLabel.setText("Score: You " + userScore + " - " + compScore + " Computer");
    }

    private String decideWinner(String user, String comp) {
        if (user.equals(comp)) {
            return "It's a draw!";
        } else if ((user.equals("rock") && comp.equals("scissors")) ||
                   (user.equals("paper") && comp.equals("rock")) ||
                   (user.equals("scissors") && comp.equals("paper"))) {
            userScore++;
            return "You win this round!";
        } else {
            compScore++;
            return "Computer wins this round!";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsGUI game = new RockPaperScissorsGUI();
            game.setVisible(true);
        });
    }
}
