package tetris;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


public class Leaderboard extends JFrame implements Components, Globals, ActionListener {

    private JButton mainMenuButton;
    private JTable scoringTable;
    private JScrollPane scrollPane;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames = {"Player", "Score"};
    private Object[][] data = {};
    private String fileLeaderBoard = "highscore";
    private TableRowSorter<TableModel> sorter;

    public Leaderboard() {
        super(LEADERBOARD_TEXT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GAME_SIZE_X,GAME_SIZE_Y);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        mainMenuButton = new JButton(MENU_TITLE);
        mainMenuButton.setSize(BUTTON_SIZE);
        mainMenuButton.setFont(BUTTON_FONT);
        this.add(mainMenuButton);
        mainMenuButton.setFocusable(false);
        mainMenuButton.addActionListener(this);

        defaultTableModel = new DefaultTableModel(data,columnNames);
        scoringTable = new JTable();
        scoringTable.setModel(defaultTableModel);
        scoringTable.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(scoringTable);
        scrollPane.setBounds(0,50, GAME_SIZE_X, GAME_SIZE_Y);
        this.add(scrollPane);

        initTableData();
        initTableSorter();
    }

    public void initTableData() {
        Vector columnIdentifier = new Vector();
        columnIdentifier.add("Player");
        columnIdentifier.add("Score");

        try {
            FileInputStream fileInputStream = new FileInputStream(fileLeaderBoard);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            defaultTableModel.setDataVector((Vector<Vector>) objectInputStream.readObject(), columnIdentifier);

            fileInputStream.close();
            objectInputStream.close();
        } catch (Exception e){}
    }

    public void initTableSorter(){
        sorter = new TableRowSorter<>(defaultTableModel);
        scoringTable.setRowSorter(sorter);

        ArrayList<RowSorter.SortKey> keys = new ArrayList<>();
        keys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));

        sorter.setSortKeys(keys);
    }

    public void saveLeaderboard(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileLeaderBoard);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(defaultTableModel.getDataVector());

            fileOutputStream.close();
            objectOutputStream.close();
        } catch (Exception e){}
    }

    public void addPlayer(String playerName, int score){
        defaultTableModel.addRow(new Object[]{playerName, score});
        sorter.sort();
        saveLeaderboard();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mainMenuButton){
            this.setVisible(false);
            Tetris.backToMenu();
        }
    }
}