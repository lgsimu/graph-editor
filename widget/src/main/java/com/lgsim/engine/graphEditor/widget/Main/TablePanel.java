package com.lgsim.engine.graphEditor.widget.Main;

import com.lgsim.engine.graphEditor.widget.PoJo.Unit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.ItemEvent;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class TablePanel extends JFrame{

    JScrollPane scrollPane;
    JTable table;
    JComboBox comboBoxLen,comboBoxArea;
    static double number = 1;
    private List<TableCellEditor> editors = new ArrayList<>();


    public void calculate(Unit unit){


       int row = table.getSelectedRow();
       int column = table.getSelectedColumn();
       number = Double.parseDouble(table.getValueAt(row,column-1).toString());
       Double num =number/unit.getMultiplier();

        //num = Double.parseDouble(table.getValueAt(row,column-1).toString())*unit.getMultiplier();


       table.setValueAt(num.toString(),row,column-1);



    }

    public void setComboBoxCell(){

        comboBoxLen = new JComboBox(new Unit[]{
                new Unit("m",1.0),
                new Unit("mm",0.01)});
        setListener(comboBoxLen);
        DefaultCellEditor cellEditor = new DefaultCellEditor(comboBoxLen);
        editors.add(cellEditor);

        comboBoxArea = new JComboBox(new Unit[]{new Unit("m2",1.0),
                new Unit("mm2",0.001)});
        setListener(comboBoxArea);
        DefaultCellEditor cellEditor1 = new DefaultCellEditor(comboBoxArea);
        editors.add(cellEditor1);

    }

    public void setListener(JComboBox comboBox){
        int preIndex = comboBox.getSelectedIndex();
        comboBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                JComboBox<Unit> combo = (JComboBox<Unit>) e.getSource();
                Unit item = (Unit) combo.getSelectedItem();
                calculate(item);


            }

            });

    }

    public TablePanel(){

        setComboBoxCell();
        Object[][] data = {{"长度",1,comboBoxLen.getSelectedItem().toString()},
                {"面积",2,comboBoxArea.getSelectedItem().toString()}};
        String[] columns = {"属性","值","单位"};

        DefaultTableModel model = new DefaultTableModel(data,columns);

         table = new JTable(model) {
            public TableCellEditor getCellEditor(int row, int column) {
                int modelColumn = convertColumnIndexToModel(column);

                if (modelColumn == 2 && row < editors.size())
                    return editors.get(row);
                else
                    return super.getCellEditor(row, column);
            }
        };


        scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setSize(300,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args){
        new TablePanel();
    }
}
