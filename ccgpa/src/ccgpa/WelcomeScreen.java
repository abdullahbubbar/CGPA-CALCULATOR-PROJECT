package ccgpa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class WelcomeScreen extends JFrame {
    private JButton nextButton;

    public WelcomeScreen() {
        setTitle("Welcome to CGPA Calculator");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        // Use JLabel with ImageIcon for background
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\HP\\Desktop\\cgpa calculator\\CGPA-Calculator.png"); // Replace with your image file
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());

        // Create next button
        nextButton = new JButton("CALCULATE YOUR CGPA");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 24)); // Adjust the font size
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCGPACalculator();
            }
        });

        // Add welcome text as a heading
        JLabel welcomeLabel = new JLabel("Welcome to CGPA Calculator");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 34));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add components to the backgroundLabel
        backgroundLabel.add(welcomeLabel, BorderLayout.NORTH);

        // Create a panel for the "Next" button and position it at the center
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the button
        buttonPanel.setOpaque(false); // Make the panel transparent
        buttonPanel.add(nextButton);
        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);

        // Set layout, add backgroundLabel to the content pane
        setLayout(new BorderLayout());
        add(backgroundLabel);

        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void openCGPACalculator() {
        CGPACalculatorGUI1 calculatorGUI = new CGPACalculatorGUI1();
        calculatorGUI.setVisible(true);
        dispose(); // Close the welcome screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeScreen().setVisible(true);
            }
        });
    }
}
