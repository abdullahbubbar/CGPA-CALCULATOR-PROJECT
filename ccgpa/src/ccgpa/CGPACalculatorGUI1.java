package ccgpa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

// Define an immutable class for a Course
class Course {
    private final String name;
    private final int credits;
    private final double grade;

    public Course(String name, int credits, double grade) {
        this.name = name;
        this.credits = credits;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public double getGrade() {
        return grade;
    }
}

// Define an abstract data type for CGPA calculation
class CGPACalculator {
    private final Course[] courses;

    public CGPACalculator(Course[] courses) {
        this.courses = courses;
    }

    public double calculateCGPA() {
        double totalGradePoints = 0;
        int totalGrade = 0;

        for (Course course : courses) {
            totalGradePoints += course.getGrade() * course.getCredits();
            totalGrade += course.getCredits();
        }

        if (totalGrade == 0) {
            return 0; // Avoid division by zero
        }

        return totalGradePoints / totalGrade;
    }
}

// GUI class for the CGPA calculator
public class CGPACalculatorGUI1 extends JFrame {
    private JTextField courseNameField, creditsField, gradeField;
    private JTextArea coursesTextArea;
    private JButton addCourseButton, calculateCGPAButton;

    private Course[] coursesArray = new Course[0];

    public CGPACalculatorGUI1() {
        setTitle("CGPA Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add background image
        ImageIcon backgroundImage = new ImageIcon("C:/Users/HP/Desktop/cgpa calculator/Calculator.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        setContentPane(backgroundLabel);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        // Add heading
        JLabel headingLabel = new JLabel("CGPA CALCULATOR");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headingLabel.setForeground(Color.BLACK); // Set text color to white
        headingLabel.setHorizontalAlignment(JLabel.CENTER); // Center the heading

        // Create heading panel
        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        headingPanel.add(headingLabel);
        mainPanel.add(headingPanel, BorderLayout.NORTH);

        initComponents(mainPanel);

        add(mainPanel);

        setLayout(new FlowLayout());
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents(JPanel mainPanel) {
        courseNameField = new JTextField(15);
        creditsField = new JTextField(5);
        gradeField = new JTextField(5);

        addCourseButton = new JButton("Add Course");
        calculateCGPAButton = new JButton("Calculate CGPA");

        coursesTextArea = new JTextArea(10, 30);
        coursesTextArea.setEditable(false);

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        calculateCGPAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateCGPA();
            }
        });

        // Create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Course Name:"));
        inputPanel.add(courseNameField);
        inputPanel.add(new JLabel("Credits:"));
        inputPanel.add(creditsField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(addCourseButton);
        inputPanel.add(calculateCGPAButton);

        // Create result panel
        JPanel resultPanel = new JPanel();
        resultPanel.setOpaque(false);
        resultPanel.add(new JScrollPane(coursesTextArea));

        // Create center panel to hold input and result panels
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(resultPanel, BorderLayout.SOUTH);

        // Center input panel and result panel within main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void addCourse() {
        try {
            String name = courseNameField.getText();
            int credits = Integer.parseInt(creditsField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            Course newCourse = new Course(name, credits, grade);

            Course[] newCoursesArray = new Course[coursesArray.length + 1];
            System.arraycopy(coursesArray, 0, newCoursesArray, 0, coursesArray.length);
            newCoursesArray[coursesArray.length] = newCourse;

            coursesArray = newCoursesArray;

            updateCoursesTextArea();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numeric values.");
        }
    }

    private void calculateCGPA() {
        CGPACalculator calculator = new CGPACalculator(coursesArray);
        double cgpa = calculator.calculateCGPA();
        JOptionPane.showMessageDialog(this, "CGPA: " + cgpa);
    }

    private void updateCoursesTextArea() {
        coursesTextArea.setText("");
        for (Course course : coursesArray) {
            coursesTextArea.append(course.getName() + " - Credits: " + course.getCredits() + ", Grade: " + course.getGrade() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CGPACalculatorGUI1().setVisible(true);
            }
        });
    }
}
