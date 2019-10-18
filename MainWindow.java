
// package com.mycompany.myproject1;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;

public class MainWindow extends javax.swing.JFrame {

    private JPanel resultsPanel = new JPanel();

    private DatabaseConnection databaseConnection;

    public MainWindow() {
        databaseConnection = new DatabaseConnection();
        // initComponents();
        setupFrame();
    }

    private void setupFrame() {
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JLabel queryPanel = new JLabel();
        queryPanel.setLayout(new GridBagLayout());
        queryPanel.setBorder(BorderFactory.createTitledBorder("Movie Search"));
        queryPanel.setVisible(true);

        GridBagConstraints j = new GridBagConstraints();

        BufferedImage myPicture;
        try {
            j.fill = GridBagConstraints.CENTER;
            j.weightx = 1;
            j.weighty = 1;
            j.gridx = 0;
            j.gridy = 0;
            myPicture = ImageIO.read(new File("Logo.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            add(picLabel);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        JPanel question1Panel = new JPanel();
        question1Panel.setLayout(new GridBagLayout());
        GridBagConstraints p = new GridBagConstraints();
        question1Panel.setVisible(true);

        JLabel startActor = new JLabel("Start Actor");
        p.fill = GridBagConstraints.CENTER;
        p.weightx = 1;
        p.weighty = 1;
        p.gridx = 0;
        p.gridy = 0;
        question1Panel.add(startActor, p);

        JTextField startActorEntry = new JTextField(1);
        p.fill = GridBagConstraints.HORIZONTAL;
        p.weightx = 5;
        p.weighty = 1;
        p.gridx = 1;
        p.gridy = 0;
        question1Panel.add(startActorEntry, p);

        JLabel endActor = new JLabel("End Actor");
        p.fill = GridBagConstraints.CENTER;
        p.weightx = 1;
        p.weighty = 1;
        p.gridx = 2;
        p.gridy = 0;
        question1Panel.add(endActor, p);

        JTextField endActorEntry = new JTextField(1);
        p.fill = GridBagConstraints.HORIZONTAL;
        p.weightx = 5;
        p.weighty = 1;
        p.gridx = 3;
        p.gridy = 0;
        question1Panel.add(endActorEntry, p);

        JLabel excludeActor = new JLabel("Exclude Actor");
        p.fill = GridBagConstraints.CENTER;
        p.weightx = 1;
        p.weighty = 1;
        p.gridx = 4;
        p.gridy = 0;
        question1Panel.add(excludeActor, p);

        JTextField excludeActorEntry = new JTextField(1);
        p.fill = GridBagConstraints.HORIZONTAL;
        p.weightx = 5;
        p.weighty = 1;
        p.gridx = 5;
        p.gridy = 0;
        question1Panel.add(excludeActorEntry, p);

        JButton question1Search = new JButton("Go");
        p.fill = GridBagConstraints.EAST;
        p.weightx = 1;
        p.weighty = 1;
        p.gridx = 6;
        p.gridy = 0;
        question1Panel.add(question1Search, p);
        question1Search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Buttion pressed");
                connectedActors(startActorEntry.getText(), endActorEntry.getText(), excludeActorEntry.getText());

            }
        });

        JPanel question2Panel = new JPanel();
        question2Panel.setLayout(new GridBagLayout());
        GridBagConstraints p1 = new GridBagConstraints();

        JLabel startYearLabel = new JLabel("Start Year");
        p1.fill = GridBagConstraints.CENTER;
        p1.weightx = 1;
        p1.weighty = 1;
        p1.gridx = 0;
        p1.gridy = 0;
        question2Panel.add(startYearLabel, p1);

        JTextField startYearTextField = new JTextField(1);
        p1.fill = GridBagConstraints.HORIZONTAL;
        p1.weightx = 5;
        p1.weighty = 1;
        p1.gridx = 1;
        p1.gridy = 0;
        question2Panel.add(startYearTextField, p1);

        JLabel endYearLabel = new JLabel("End Year");
        p1.fill = GridBagConstraints.CENTER;
        p1.weightx = 1;
        p1.weighty = 1;
        p1.gridx = 2;
        p1.gridy = 0;
        question2Panel.add(endYearLabel, p1);

        JTextField endYearTextField = new JTextField(1);
        p1.fill = GridBagConstraints.HORIZONTAL;
        p1.weightx = 5;
        p1.weighty = 1;
        p1.gridx = 3;
        p1.gridy = 0;
        question2Panel.add(endYearTextField, p1);

        JLabel excludeLabel = new JLabel("Exclude Actor");
        p1.fill = GridBagConstraints.CENTER;
        p1.weightx = 1;
        p1.weighty = 1;
        p1.gridx = 4;
        p1.gridy = 0;
        question2Panel.add(excludeLabel, p1);

        JTextField excludeLabelEntry = new JTextField(1);
        p1.fill = GridBagConstraints.HORIZONTAL;
        p1.weightx = 5;
        p1.weighty = 1;
        p1.gridx = 5;
        p1.gridy = 0;
        question2Panel.add(excludeLabelEntry, p1);

        JButton question2Search = new JButton("Go");
        p1.fill = GridBagConstraints.EAST;
        p1.weightx = 1;
        p1.weighty = 1;
        p1.gridx = 6;
        p1.gridy = 0;
        question2Panel.add(question2Search, p1);
        question2Search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                shortestList(startYearTextField.getText(), endYearTextField.getText(), excludeLabelEntry.getText());
            }
        });

        JPanel question4Panel = new JPanel();
        question4Panel.setLayout(new GridBagLayout());
        GridBagConstraints p3 = new GridBagConstraints();

        JLabel movieLabel = new JLabel("Movie");
        p3.fill = GridBagConstraints.CENTER;
        p3.weightx = 1;
        p3.weighty = 1;
        p3.gridx = 0;
        p3.gridy = 0;
        question4Panel.add(movieLabel, p3);

        JTextField movieTextField = new JTextField(1);
        p3.fill = GridBagConstraints.HORIZONTAL;
        p3.weightx = 5;
        p3.weighty = 1;
        p3.gridx = 1;
        p3.gridy = 0;
        question4Panel.add(movieTextField, p3);

        JLabel yearLabel = new JLabel("Year");
        p3.fill = GridBagConstraints.CENTER;
        p3.weightx = 1;
        p3.weighty = 1;
        p3.gridx = 2;
        p3.gridy = 0;
        question4Panel.add(yearLabel, p3);

        JTextField yearTextField = new JTextField(1);
        p3.fill = GridBagConstraints.HORIZONTAL;
        p3.weightx = 5;
        p3.weighty = 1;
        p3.gridx = 3;
        p3.gridy = 0;
        question4Panel.add(yearTextField, p3);

        JLabel genreLabel = new JLabel("Genre");
        p3.fill = GridBagConstraints.CENTER;
        p3.weightx = 1;
        p3.weighty = 1;
        p3.gridx = 5;
        p3.gridy = 0;
        question4Panel.add(genreLabel, p3);

        JTextField genreTextField = new JTextField(1);
        p3.fill = GridBagConstraints.HORIZONTAL;
        p3.weightx = 5;
        p3.weighty = 1;
        p3.gridx = 6;
        p3.gridy = 0;
        question4Panel.add(genreTextField, p3);

        JButton question4Search = new JButton("Go");
        p3.fill = GridBagConstraints.EAST;
        p3.weightx = 1;
        p3.weighty = 1;
        p3.gridx = 7;
        p3.gridy = 0;
        question4Panel.add(question4Search, p3);
        question4Search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                movieSuggestion(movieTextField.getText(), genreTextField.getText(), yearTextField.getText());

            }
        });

        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new GridBagLayout());
        radioButtonPanel.setBorder(BorderFactory.createTitledBorder("Language Selection"));

        GridBagConstraints r = new GridBagConstraints();
        r.weightx = 1;
        r.weighty = 1;
        r.fill = GridBagConstraints.BOTH;

        JRadioButton enRadio = new JRadioButton("English");
        r.gridx = 0;
        r.gridy = 0;
        radioButtonPanel.add(enRadio, r);

        JRadioButton itRadio = new JRadioButton("Italian");
        r.gridx = 1;
        r.gridy = 0;
        radioButtonPanel.add(itRadio, r);

        JRadioButton frRadio = new JRadioButton("French");
        r.gridx = 2;
        r.gridy = 0;
        radioButtonPanel.add(frRadio, r);

        JRadioButton chRadio = new JRadioButton("Chinesse");
        r.gridx = 0;
        r.gridy = 1;
        radioButtonPanel.add(chRadio, r);

        JRadioButton grRadio = new JRadioButton("German");
        r.gridx = 1;
        r.gridy = 1;
        radioButtonPanel.add(grRadio, r);

        JRadioButton oRadio = new JRadioButton("Other");
        r.gridx = 2;
        r.gridy = 1;
        radioButtonPanel.add(oRadio, r);

        j.fill = GridBagConstraints.BOTH;
        j.weightx = 1;
        j.weighty = 1;
        j.gridx = 0;
        j.gridy = 0;
        queryPanel.add(question1Panel, j);
        j.gridy = 1;
        queryPanel.add(question2Panel, j);

        j.gridy = 3;
        queryPanel.add(question4Panel, j);
        j.gridy = 4;
        queryPanel.add(radioButtonPanel, j);

        j.gridy = 1;
        add(queryPanel, j);

        j.gridy = 1;
        add(resultsPanel, j);

    }

    private void connectedActors(String startActor, String endActor, String excludeActor) {
        if (startActor.equals("") || endActor.equals("")) {
            JOptionPane.showMessageDialog(this, "Start Actor or End Actor can not be empty.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(startActor + endActor + excludeActor);
        // ConnectedActors temp = new ConnectedActors(startActor, endActor,
        // excludeActor);
    }

    private void shortestList(String startYear, String endYear, String excludeActor) {
        if (startYear.equals("") || endYear.equals("")) {
            JOptionPane.showMessageDialog(this, "Start Year or End Year cannot be empty.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(startYear + endYear + excludeActor);
        java.util.List<Director> directors = databaseConnection.getLeastMovies(Integer.parseInt(startYear),
                Integer.parseInt(endYear), excludeActor);

        System.out.println(directors);
    }

    private void movieSuggestion(String movie, String genre, String year) {
        if (movie.equals("") || genre.equals("") || year.equals("")) {
            JOptionPane.showMessageDialog(this, "Movie or Genre or Year cannot be empty.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(movie + genre + year);

    }
}
