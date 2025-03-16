import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalUI {
    JFrame frame;
    JTextField txt;
    JPanel panel;
    JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bMul, bMinus, bPlus, bDiv, bClear, bEqual;
    CalHandler handler;

    static Font font = new Font("Inter", Font.PLAIN, 20);

    CalUI() {
        init();
        handler = new CalHandler();

        addActionListeners();
    }

    private void style(JButton btn, Boolean num) {
        btn.setBackground(num ? new Color(0xf7f9fc) : new Color(0xE0F2F1));
        btn.setFont(font);
        btn.setForeground(Color.black);
        btn.setBorder(num ? BorderFactory.createMatteBorder(2, 1, 0, 1, new Color(0xE0F2F1))
                : BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btn.setBorderPainted(true);
    }

    private void addActionListeners() {
        b0.addActionListener(handler);
        b1.addActionListener(handler);
        b2.addActionListener(handler);
        b3.addActionListener(handler);
        b4.addActionListener(handler);
        b5.addActionListener(handler);
        b6.addActionListener(handler);
        b7.addActionListener(handler);
        b8.addActionListener(handler);
        b9.addActionListener(handler);
        bMul.addActionListener(handler);
        bDiv.addActionListener(handler);
        bMinus.addActionListener(handler);
        bPlus.addActionListener(handler);
        bClear.addActionListener(handler);
        bEqual.addActionListener(handler);
    }

    private void init() {
        frame = new JFrame("Calculator");
        Container c = frame.getContentPane();
        c.setBackground(new Color(0xeff4f9));
        c.setLayout(new BorderLayout());

        txt = new JTextField(10);
        txt.setEnabled(false);
        txt.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        txt.setPreferredSize(new Dimension(350, 90));
        txt.setBackground(new Color(0xeef4f9));
        txt.setFont(new Font("Inter", Font.PLAIN, 30));
        txt.setDisabledTextColor(Color.black);
        txt.setHorizontalAlignment(JTextField.RIGHT);

        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        bMul = new JButton("×");
        bDiv = new JButton("/");
        bMinus = new JButton("-");
        bPlus = new JButton("+");
        bClear = new JButton("C");
        bEqual = new JButton("=");

        panel = new JPanel(new GridLayout(4, 4));
        panel.setBackground(new Color(0xeff4f9));

        style(b0, false);
        style(b1, true);
        style(b2, true);
        style(b3, true);
        style(b4, true);
        style(b5, true);
        style(b6, true);
        style(b7, true);
        style(b8, true);
        style(b9, true);
        style(bMul, false);
        style(bMinus, false);
        style(bPlus, false);
        style(bEqual, false);
        style(bClear, false);
        style(bDiv, false);

        bEqual.setBackground(new Color(0x00897B));
        bEqual.setForeground(Color.WHITE);

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(bClear);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(bMul);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);
        panel.add(bDiv);
        panel.add(b0);
        panel.add(bPlus);
        panel.add(bMinus);
        panel.add(bEqual);

        c.add(txt, BorderLayout.NORTH);
        c.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private class CalHandler implements ActionListener {
        String operand1, operator, operand2;

        CalHandler() {
            reset();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(bClear)) {
                reset();
            } else if (e.getSource().equals(b0)) {
                handleNumber("0");
            } else if (e.getSource().equals(b1)) {
                handleNumber("1");
            } else if (e.getSource().equals(b2)) {
                handleNumber("2");
            } else if (e.getSource().equals(b3)) {
                handleNumber("3");
            } else if (e.getSource().equals(b4)) {
                handleNumber("4");
            } else if (e.getSource().equals(b5)) {
                handleNumber("5");
            } else if (e.getSource().equals(b6)) {
                handleNumber("6");
            } else if (e.getSource().equals(b7)) {
                handleNumber("7");
            } else if (e.getSource().equals(b8)) {
                handleNumber("8");
            } else if (e.getSource().equals(b9)) {
                handleNumber("9");
            } else if (e.getSource().equals(bEqual)) {
                calculate();
            } else if (e.getSource().equals(bPlus)) {
                handleOperator("+");
            } else if (e.getSource().equals(bMinus)) {
                handleOperator("-");
            } else if (e.getSource().equals(bMul)) {
                handleOperator("*");
            } else if (e.getSource().equals(bDiv)) {
                handleOperator("/");
            }
        }

        private void handleNumber(String num) {
            if (!operator.equals("")) {
                operand2 = operand2 + num;
            } else {
                operand1 = operand1 + num;
            }
            updateDisplay();
        }

        private void handleOperator(String op) {
            if (!operator.equals("") && !operand2.equals("")) {
                calculate();
                operator = op;
                updateDisplay();
            } else if (!operand1.equals("")) {
                operator = op;
                updateDisplay();
            }
        }

        private void calculate() {
            if (!operator.equals("") && !operand2.equals("")) {
                double result;
                double num1 = Double.parseDouble(operand1);
                double num2 = Double.parseDouble(operand2);

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            txt.setText("Error: Division by zero");
                            reset();
                            return;
                        }
                        result = num1 / num2;
                        break;
                    default:
                        return;
                }

                txt.setText(operand1 + " " + operator + " " + operand2 + " = " + String.format("%.2f", result));

                operand1 = String.valueOf(result);
                operator = operand2 = "";
            }
        }

        private void updateDisplay() {
            txt.setText(operand1 + " " + operator + " " + operand2);
        }

        private void reset() {
            operand1 = operator = operand2 = "";
            txt.setText("");
        }
    }
}