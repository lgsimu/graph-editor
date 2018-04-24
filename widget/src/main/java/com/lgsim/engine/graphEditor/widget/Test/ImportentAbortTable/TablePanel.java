package com.lgsim.engine.graphEditor.widget.Test.ImportentAbortTable;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.widget.PoJo.RowContent;
import com.lgsim.engine.graphEditor.widget.PoJo.Unit;
import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static org.mariuszgromada.math.mxparser.Constant.SYNTAX_ERROR_OR_STATUS_UNKNOWN;


public class TablePanel extends JPanel {
    JScrollPane scrollPane;
    static JTable table;
    JComboBox comboBoxLen, comboBoxArea, comboBoxPa, comboBoxTem, comboBoxSwirl;
    List<Unit> unitLen, unitArea, unitPa, unitTem, unitSwirl;
    static int rowNum = 0;
    private static List<TableCellEditor> editors = new ArrayList<>();
    private static List<TableCellEditor> editor2 = new ArrayList<>();
    JTextField textField = new JTextField();
    static DefaultTableModel model = new DefaultTableModel();

    /**
     * 单位转换
     *
     * @param unit
     * @param str
     */
    public void calculate(Unit unit, String str) {
        double num = 0;
        try {
            num = Double.parseDouble(str);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "请检查输入的值!");
            return;
        }
        Double number = num / unit.getUnitRate();
        table.setValueAt(number.toString(), table.getSelectedRow(), table.getSelectedColumn() - 1);
    }

    /**
     * 根据单位设置下拉框
     */
    public Vector setComboBoxCell(Vector vector, IVertexArgument rowContent) {


        String[] lenUnit = {"m", "dm", "cm", "mm"};
        List lenList = Arrays.asList(lenUnit);
        if (rowContent != null && lenList.contains(rowContent.getUnit())) {
            unitLen = new ArrayList<>();
            unitLen.add(new Unit("m", 1.0));
            unitLen.add(new Unit("mm", 0.001));
            comboBoxLen = new JComboBox(setComboBoxArray(unitLen));
            setListener(comboBoxLen);
            setTextListener();
            DefaultCellEditor cellEditor1 = new DefaultCellEditor(comboBoxLen);
            editors.add(cellEditor1);
            vector.add(rowContent.getID());
            vector.add(rowContent.getValue());
            vector.add(rowContent.getUnit());
            vector.add(rowContent.getDescription());
        }

        String[] areaUnit = {"m2", "dm2", "cm2", "mm2"};
        List areaList = Arrays.asList(areaUnit);
        if (rowContent != null && areaList.contains(rowContent.getUnit())) {
            unitArea = new ArrayList<>();
            unitArea.add(new Unit("m2", 1.0));
            unitArea.add(new Unit("mm2", 0.001 * 0.001));
            comboBoxArea = new JComboBox(setComboBoxArray(unitArea));
            setListener(comboBoxArea);
            setTextListener();
            DefaultCellEditor cellEditor = new DefaultCellEditor(comboBoxArea);
            editors.add(cellEditor);
            vector.add(rowContent.getID());
            vector.add(rowContent.getValue());
            vector.add(rowContent.getUnit());
            vector.add(rowContent.getDescription());
        }


        String[] paUnit = {"Pa", "Bar"};
        List paList = Arrays.asList(paUnit);
        if (rowContent != null && paList.contains(rowContent.getUnit())) {
            unitPa = new ArrayList<>();
            unitPa.add(new Unit("Pa", 1.0));
            unitPa.add(new Unit("Bar", 100 * 100));
            comboBoxPa = new JComboBox(setComboBoxArray(unitPa));
            setListener(comboBoxPa);
            setTextListener();
            DefaultCellEditor cellEditor = new DefaultCellEditor(comboBoxPa);
            editors.add(cellEditor);
            vector.add(rowContent.getID());
            vector.add(rowContent.getValue());
            vector.add(rowContent.getUnit());
            vector.add(rowContent.getDescription());
        }

        String[] temUnit = {"K", "℃"};
        List temList = Arrays.asList(temUnit);
        if (rowContent != null && temList.contains(rowContent.getUnit())) {
            unitTem = new ArrayList<>();
            unitTem.add(new Unit("℃", rowContent.getValue() * 28.315));
            unitTem.add(new Unit("K", 1));
            comboBoxTem = new JComboBox(setComboBoxArray(unitTem));
            setListener(comboBoxTem);
            setTextListener();
            DefaultCellEditor cellEditor = new DefaultCellEditor(comboBoxTem);
            editors.add(cellEditor);
            vector.add(rowContent.getID());
            vector.add(rowContent.getValue());
            vector.add(rowContent.getUnit());
            vector.add(rowContent.getDescription());
        }

        String[] swirlUnit = {"m2/s"};
        List swirlList = Arrays.asList(swirlUnit);
        if (rowContent != null && swirlList.contains(rowContent.getUnit())) {
            unitSwirl = new ArrayList<>();
            unitSwirl.add(new Unit("m2/s", 1.0));
            comboBoxSwirl = new JComboBox(setComboBoxArray(unitSwirl));
            setListener(comboBoxSwirl);
            setTextListener();
            DefaultCellEditor cellEditor = new DefaultCellEditor(comboBoxSwirl);
            editors.add(cellEditor);
            vector.add(rowContent.getID());
            vector.add(rowContent.getValue());
            vector.add(rowContent.getUnit());
            vector.add(rowContent.getDescription());
        }
        return vector;
    }

    /**
     * 验证数字是否合法
     *
     * @param str
     * @return
     */
    public boolean isNumber(String str) {
        try {
            Expression e = new Expression(str);
            double count = e.calculate();
            e.getSyntaxStatus();
            if (e.getSyntaxStatus() == SYNTAX_ERROR_OR_STATUS_UNKNOWN || Double.isInfinite(count) || Double.isNaN(count)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setTextListener() {
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!isNumber(textField.getText())) {
                    JOptionPane.showMessageDialog(null, "请输入数字!");
                }
            }
        });
        DefaultCellEditor cellEditor2 = new DefaultCellEditor(textField);
        editor2.add(cellEditor2);
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
    public void setListener(JComboBox comboBox) {
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox<Unit> combo = (JComboBox<Unit>) e.getSource();
                Unit item = (Unit) combo.getSelectedItem();
                calculate(item, textField.getText());
            }
        });
    }

    /**
     * 展示
     *
     * @param
     */
    public void shows(DefaultTableModel model) {
        table = new JTable(model) {
            public TableCellEditor getCellEditor(int row, int column) {
                int modelColumn = convertColumnIndexToModel(column);
                if (modelColumn == 2 && row < editors.size())
                    return editors.get(row);
                if (modelColumn == 1 && row < editor2.size()) {
                    return editor2.get(row);
                } else
                    return super.getCellEditor(row, column);
            }
        };

    }

    public void showTable(IVertex vertex) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        Vector vector = new Vector();
        for (IVertexArgument rowContent : vertex.getArguments()) {
            model.addRow(setComboBoxCell(vector, rowContent));
            shows(model);
        }
    }


    public TablePanel() {
        String[] columns = {"属性", "值", "单位", "描述"};
        model.setColumnIdentifiers(columns);
        shows(model);
        scrollPane = new JScrollPane(table);
        this.setSize(300, 300);
        this.add(scrollPane);

    }

}
