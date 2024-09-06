package boards.tourney;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Deterministic Pseudo-random List Scrambler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);

        contentPanel.setBorder(padding);

        frame.setContentPane(contentPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 15;

        JLabel inputList = new JLabel("Ordered List input: ");
        contentPanel.add(inputList, c);

        JTextArea userInputList = new JTextArea(5,5);
        c.gridx = 0;
        c.gridy = 1;
        contentPanel.add(userInputList, c);

        JLabel seedLabel = new JLabel("Random Order Seed: ");
        c.gridx = 0;
        c.gridy = 2;
        contentPanel.add(seedLabel, c);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        JTextField seed = new JFormattedTextField(formatter);
        c.gridx = 0;
        c.gridy = 3;
        contentPanel.add(seed, c);

        JButton btn = new JButton("Scramble");
        c.gridx = 0;
        c.gridy = 6;
        contentPanel.add(btn, c);

        JLabel outputL = new JLabel("Output: ");
        c.gridx = 0;
        c.gridy = 7;
        contentPanel.add(outputL, c);

        JTextArea output = new JTextArea(5,5);
        c.gridx = 0;
        c.gridy = 8;
        output.setEditable(false);
        contentPanel.add(output, c);

        btn.addActionListener(e -> {
            List<String> input = new ArrayList<>(userInputList.getText()
                    .lines().toList());
            Collections.shuffle(input, new Random(Integer.parseInt(seed.getText())));
            String output1 = StringUtils.join(input, "\n");
            output.setText(output1);
        });

        //Display the window.


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Runnable setMinium = () -> frame.setMinimumSize(contentPanel.getPreferredSize());
        SwingUtilities.invokeLater(setMinium);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(Main::createAndShowGUI);
    }
}