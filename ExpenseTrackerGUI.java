import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Expense {
    String title;
    double amount;

    Expense(String title, double amount) {
        this.title = title;
        this.amount = amount;
    }
}

public class ExpenseTrackerGUI extends JFrame implements ActionListener {

    JTextField titleField, amountField;
    JTextArea displayArea;
    JButton addBtn, viewBtn, totalBtn, clearBtn;

    ArrayList<Expense> expenses = new ArrayList<>();

    ExpenseTrackerGUI() {

        setTitle("Expense Tracker");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLayout(null);

        getContentPane().setBackground(new Color(230, 240, 255));

        
        JLabel heading = new JLabel(" Expense Tracker");// create heading text
        heading.setBounds(400, 10, 400, 40);
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setHorizontalAlignment(JLabel.CENTER);
        add(heading);

        
        JLabel titleLabel = new JLabel("Title:");//label title input
        titleLabel.setBounds(50, 80, 100, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel);

        JLabel amountLabel = new JLabel("Amount:");// label amount input
        amountLabel.setBounds(50, 130, 100, 30);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(amountLabel);

        // input for title
        titleField = new JTextField();
        titleField.setBounds(150, 80, 250, 30);
        titleField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(titleField);

        amountField = new JTextField();
        amountField.setBounds(150, 130, 250, 30);
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(amountField);

        
        addBtn = new JButton("Add");
        addBtn.setBounds(50, 200, 120, 40);
        add(addBtn);

        viewBtn = new JButton("View");
        viewBtn.setBounds(180, 200, 120, 40);
        add(viewBtn);

        totalBtn = new JButton("Total");
        totalBtn.setBounds(310, 200, 120, 40);
        add(totalBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(440, 200, 120, 40);
        add(clearBtn);

       
        addBtn.setBackground(new Color(33, 150, 243)); 
        addBtn.setForeground(Color.WHITE);

        viewBtn.setBackground(new Color(255, 182, 193)); 
        viewBtn.setForeground(Color.BLACK);

        totalBtn.setBackground(new Color(76, 175, 80)); 
        totalBtn.setForeground(Color.WHITE);

        clearBtn.setBackground(Color.DARK_GRAY);
        clearBtn.setForeground(Color.WHITE);

        
        addBtn.setFocusPainted(false);
        viewBtn.setFocusPainted(false);
        totalBtn.setFocusPainted(false);
        clearBtn.setFocusPainted(false);

        //display area
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 280, 600, 300);
        add(scrollPane);

        addBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        totalBtn.addActionListener(this);

        clearBtn.addActionListener(e -> displayArea.setText(""));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == addBtn) {

                String title = titleField.getText();
                double amount = Double.parseDouble(amountField.getText());

                expenses.add(new Expense(title, amount));

                JOptionPane.showMessageDialog(this, "Expense Added Successfully!");

                titleField.setText("");
                amountField.setText("");
            }

            else if (e.getSource() == viewBtn) {
                displayArea.setText("");

                for (Expense ex : expenses) {
                    displayArea.append("• " + ex.title + "  →  ₹" + ex.amount + "\n");
                }
            }

            else if (e.getSource() == totalBtn) {
                double total = 0;

                for (Expense ex : expenses) {
                    total += ex.amount;
                }

                displayArea.setText("Total Expense: ₹" + total);
            }

        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❗ Enter valid data!");
        }
    }

    public static void main(String[] args) {
        new ExpenseTrackerGUI();
    }
}