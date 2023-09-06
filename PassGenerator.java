import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;
import javax.swing.*;
import javax.swing.border.Border;

public class PasswordGenerator extends JFrame {
    private JTextField lengthField;
    private JTextArea passwordArea;
    private JButton generateButton;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:,.<>?";

    public PasswordGenerator() {
        setTitle("Saba's Password Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10));

        mainPanel.add(new JLabel("Pass length that you seek?"));
        lengthField = new JTextField();
        mainPanel.add(lengthField);
        lengthField.setBackground(new Color(135, 206, 235));

        generateButton = new JButton("Click Me! To Generate");
        generateButton.setBackground(new Color(255, 105, 180));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
        mainPanel.add(generateButton);

        mainPanel.add(new JLabel("Generated Password:"));
        passwordArea = new JTextArea(5, 20);
        passwordArea.setEditable(false);
        passwordArea.setBackground(new Color(255, 223, 230));
        passwordArea.setFont(new Font("Arial", Font.BOLD, 14));
        passwordArea.setLineWrap(true);
        passwordArea.setWrapStyleWord(true);
        Border border = BorderFactory.createLineBorder(new Color(255, 105, 180), 2);
        Border roundedBorder = BorderFactory.createCompoundBorder(
            border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
        passwordArea.setBorder(roundedBorder);
        mainPanel.add(new JScrollPane(passwordArea));

        add(mainPanel);
    }

    public void generatePassword() {
        try {
            int passwordLength = Integer.parseInt(lengthField.getText());
            String generatedPassword = generateRandomPassword(passwordLength);
            passwordArea.setText(generatedPassword);
     
        } catch (NumberFormatException ex) {
            passwordArea.setText("Invalid input. Please enter a valid number.");
        }
    }

    public String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    public static void main(String[] args) { 
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordGenerator().setVisible(true);
            }
        });
    }
}
