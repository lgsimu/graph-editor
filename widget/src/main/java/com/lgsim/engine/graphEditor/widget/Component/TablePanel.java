package com.lgsim.engine.graphEditor.widget.Component;

import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.widget.PoJo.RowContent;
import com.lgsim.engine.graphEditor.widget.PoJo.Unit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TablePanel extends JPanel {
    JScrollPane scrollPane;
    static JTable table;
    JComboBox comboBoxLen, comboBoxArea;
    List<Unit> units, units2;
    static int rowNum = 0;
    private static List<TableCellEditor> editors = new ArrayList<>();

    public void calculate(Unit unit, IVertexArgument rowContent) {
        Double number = rowContent.getValue() / unit.getUnitRate();
        table.setValueAt(number.toString(), table.getSelectedRow(), table.getSelectedColumn() - 1);
    }

    /**
     * 根据单位设置下拉框
     */
    public Object[][] setComboBoxCell(Object[][] data, IVertexArgument rowContent) {

        String[] lenUnit = {"m", "dm", "cm", "mm"};
        List lenList = Arrays.asList(lenUnit);
        if (lenList.contains(rowContent.getUnit())) {
            rowNum++;
            units = new ArrayList<>();
            units.add(new Unit("m", 1.0));
            units.add(new Unit("mm", 0.001));
            comboBoxLen = new JComboBox(setComboBoxArray(units));
            setListener(comboBoxLen, rowContent);
            DefaultCellEditor cellEditor = new DefaultCellEditor(comboBoxLen);
            editors.add(cellEditor);
            data[rowNum - 1][0] = rowContent.getID();
            data[rowNum - 1][1] = rowContent.getValue();
            data[rowNum - 1][2] = rowContent.getUnit();
            data[rowNum - 1][3] = rowContent.getDescription();

        }

        String[] areaUnit = {"m2", "dm2", "cm2", "mm2"};
        List areaList = Arrays.asList(areaUnit);
        if (areaList.contains(rowContent.getUnit())) {
            rowNum++;
            units2 = new ArrayList<>();
            units2.add(new Unit("m2", 1.0));
            units2.add(new Unit("mm2", 0.001 * 0.001));
            comboBoxArea = new JComboBox(setComboBoxArray(units2));
            setListener(comboBoxArea, rowContent);
            DefaultCellEditor cellEditor1 = new DefaultCellEditor(comboBoxArea);
            editors.add(cellEditor1);
            data[rowNum - 1][0] = rowContent.getID();
            data[rowNum - 1][1] = rowContent.getValue();
            data[rowNum - 1][2] = rowContent.getUnit();
            data[rowNum - 1][3] = rowContent.getDescription();
        }

        return data;
    }

    public Unit[] setComboBoxArray(List<Unit> list) {
        Object[] arrayObject = list.toArray();
        Unit[] data = Arrays.copyOf(arrayObject, arrayObject.length, Unit[].class);
        return data;
    }

    /**
     * 添加事件
     *
     * @param comboBox
     */
    public void setListener(JComboBox comboBox, IVertexArgument rowContent) {
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox<Unit> combo = (JComboBox<Unit>) e.getSource();
                Unit item = (Unit) combo.getSelectedItem();
                calculate(item, rowContent);
            }

        });
    }


    public void end(DefaultTableModel model) {
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
    }

    public TablePanel(List<IVertexArgument> argumentList) {
        String[] columns = {"属性", "值", "单位", "描述"};
        Object[][] data = new Object[10][4];
        for (IVertexArgument rowContent1 : argumentList) {
            setComboBoxCell(data, rowContent1);
        }
        DefaultTableModel model = new DefaultTableModel(data, columns);
        end(model);

    }

}
