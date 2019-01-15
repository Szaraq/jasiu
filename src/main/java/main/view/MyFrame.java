package main.view;

import main.model.Arriver;
import main.model.ArriverExecutor;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.util.Map;

public class MyFrame extends JFrame {
    private JFrame f;

    public MyFrame() {
        JSpinner busHourMin = buildTime(23);
        JSpinner busMinutesMin = buildTime(59);
        JSpinner busHourMax = buildTime(23);
        JSpinner busMinutesMax = buildTime(59);
        JSpinner jasiuHourMin = buildTime(23);
        JSpinner jasiuMinutesMin = buildTime(59);
        JSpinner jasiuHourMax = buildTime(23);
        JSpinner jasiuMinutesMax = buildTime(59);

        buildLabel("Min").setBounds(210, 10, 30, 30);
        buildLabel("Max").setBounds(370, 10, 30, 30);

        buildLabel("Godzina przyjazdu autobusu: ").setBounds(20, 50, 230, 30);
        busHourMin.setBounds(200,50,50,30);
        busMinutesMin.setBounds(250,50,50,30);
        busHourMax.setBounds(360, 50, 50, 30);
        busMinutesMax.setBounds(410, 50, 50, 30);

        buildLabel("Godzina przyjazdu Jasia: ").setBounds(20, 80, 230, 30);
        jasiuHourMin.setBounds(200,80,50,30);
        jasiuMinutesMin.setBounds(250,80,50,30);
        jasiuHourMax.setBounds(360, 80, 50, 30);
        jasiuMinutesMax.setBounds(410, 80, 50, 30);

        buildLabel("Liczba próbek: ").setBounds(20, 150, 230, 30);
        JFormattedTextField numberOfSamples = buildNumberOfSamples();
        numberOfSamples.setBounds(200, 150, 50, 30);
        add(numberOfSamples);

        JButton b = new JButton("Simulate");
        b.setBounds(20,200,100, 40);
        b.addActionListener(e -> {
            Arriver bus = new Arriver(
                    LocalTime.of((int) busHourMin.getValue(), (int) busMinutesMin.getValue()),
                    LocalTime.of((int) busHourMax.getValue(), (int) busMinutesMax.getValue())
            );
            Arriver jasiu = new Arriver(
                    LocalTime.of((int) jasiuHourMin.getValue(), (int) jasiuMinutesMin.getValue()),
                    LocalTime.of((int) jasiuHourMax.getValue(), (int) jasiuMinutesMax.getValue())
            );
            int numberOfSamplesValue = (int) numberOfSamples.getValue();
            Map<Boolean, Long> result = ArriverExecutor.arriveTimes(bus, jasiu, numberOfSamplesValue);
            String message = String.format("Na %s prób, Jasiu spóźnił się %s razy, co daje %s procent", numberOfSamplesValue, result.get(false), ((double) result.get(false)) / ((double) numberOfSamplesValue) * 100);
            JOptionPane.showMessageDialog(null, message);
        });

        add(b);
        setSize(500,500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JSpinner buildTime(int maxValue) {
        SpinnerModel value =
                new SpinnerNumberModel(0,
                        0,
                        maxValue,
                        1);
        JSpinner spinner = new JSpinner(value);
        add(spinner);
        return spinner;
    }

    private JLabel buildLabel(String text) {
        JLabel label = new JLabel(text);
        add(label);
        return label;
    }

    private JFormattedTextField buildNumberOfSamples() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        return new JFormattedTextField(formatter);
    }
}
