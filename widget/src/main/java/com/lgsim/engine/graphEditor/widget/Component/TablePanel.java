package com.lgsim.engine.graphEditor.widget.Component;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.unit.IUnit;
import com.lgsim.engine.graphEditor.api.unit.IUnitBundle;
import com.lgsim.engine.graphEditor.api.unit.IUnitsContext;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.widget.PoJo.RowContent;
import com.lgsim.engine.graphEditor.widget.PoJo.Unit;
import org.apache.commons.lang.StringUtils;
import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.util.*;
import java.util.List;

import static org.mariuszgromada.math.mxparser.Constant.SYNTAX_ERROR_OR_STATUS_UNKNOWN;


public class TablePanel extends JPanel {

    private JScrollPane scrollPane;

    private static JTable table;

    private JComboBox comboBox;

    private Vector<IUnit> unitLen;

    private static List<TableCellEditor> editors = new ArrayList<>();

    private static List<TableCellEditor> editor2 = new ArrayList<>();

    private static DefaultTableModel model = new DefaultTableModel();

    private List<Vector> vectorList = new Vector<>();

    private IUnitsContext units;

    private IUnit iunit;

    /**
     * 单位转换
     *
     * @param
     * @param str
     */
    public void calculate(IUnit unit,String str) {

        double num;

        try {
            num = Double.parseDouble(str);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "请检查输入的值!");
            return;
        }

        Double number = num * unit.getScale();
        table.setValueAt(number.toString(), table.getSelectedRow(), table.getSelectedColumn() - 1);
    }


    public IUnitsContext getIUnitBundleInstance() {

        try {
            units = ImplementationUtil.getInstanceOf(IUnitsContext.class);
        } catch (InstantiationException e) {
            ExceptionManager.INSTANCE.dealWith(e);
        }

        return units;
    }

    public IUnit getUnit() {

        try {
            iunit = ImplementationUtil.getInstanceOf(IUnit.class);
        } catch (InstantiationException e) {
            ExceptionManager.INSTANCE.dealWith(e);
        }

        return iunit;
    }


    /**
     * 将 collection  转换为 vector
     *
     * @param collection
     * @return
     */
    public Vector getVector(Collection<IUnit> collection) {

        Vector vector = new Vector();

        for (IUnit unit : collection) {

            vector.add(unit);
        }

        return vector;
    }


    /**
     * 根据单位设置下拉框
     */
    public void setComboBoxCell(IVertexArgument rowContent, JTextField textField) {

        Collection<IUnitBundle> unitBundles = units.getSupportUnitBundles();
        for (IUnitBundle bundle : unitBundles) {
            if (StringUtils.equals(rowContent.getUnit(), bundle.getID())) {
                unitLen = getVector(bundle.getUnitFamily());
                break;
            }
        }


        comboBox = new JComboBox(unitLen);

        createComboBox(comboBox);
        setListener(comboBox, textField);
    }

    /**
     * 设置表格显示的内容
     *
     * @param
     * @param rowContent
     * @param
     */
    public void setTableContent(IVertexArgument rowContent) {

        Vector vector = new Vector();

        vector.add(rowContent.getID());
        vector.add(rowContent.getValue());
        vector.add(getDefaultUnit(rowContent));
        vector.add(rowContent.getDescription());

        vectorList.add(vector);

        JTextField textField = setTextListener(rowContent);
        setComboBoxCell(rowContent, textField);

    }

    /**
     * 得到单位
     *
     * @param
     * @return
     */
    public String getDefaultUnit(IVertexArgument rowContent) {

        Collection<IUnitBundle> unitBundles = units.getSupportUnitBundles();
        for (IUnitBundle bundle : unitBundles) {
            if (StringUtils.equals(rowContent.getUnit(), bundle.getID())) {
                unitLen = getVector(bundle.getUnitFamily());
                break;
            }
        }
        return unitLen.get(0).getName();
    }


    public void createComboBox(JComboBox comboBox) {
        DefaultCellEditor cellEditor = new DefaultCellEditor(comboBox);
        editors.add(cellEditor);
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

    /**
     * 设置文本框事件
     *
     * @param rowContent
     * @return
     */
    public JTextField setTextListener(IVertexArgument rowContent) {

        JTextField textField = new JTextField();
        textField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (!isNumber(textField.getText())) {
                    JOptionPane.showMessageDialog(null, "请输入数字!");
                } else {
                    rowContent.setValue(Double.parseDouble(textField.getText()));
                }
            }
        });

        DefaultCellEditor cellEditor2 = new DefaultCellEditor(textField);
        editor2.add(cellEditor2);

        return textField;
    }


    /**
     * 添加事件
     *
     * @param comboBox
     */
    public void setListener(JComboBox comboBox, JTextField textField) {
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {

                IUnit unit = (IUnit) comboBox.getSelectedItem();
                calculate(unit,textField.getText());
            }
        });
    }

    /**
     * 展示
     *
     * @param
     */
    public void shows(DefaultTableModel mode) {
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

        for (IVertexArgument rowContent : vertex.getArguments()) {
            setTableContent(rowContent);
        }

        for (Vector vector1 : vectorList) {
            model.addRow(vector1);
        }

        vectorList.clear();

        shows(model);
    }

    public TablePanel() {

        getIUnitBundleInstance();
        getUnit();

        String[] columns = {"名称", "值", "单位", "说明"};
        model.setColumnIdentifiers(columns);
        shows(model);
        scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        this.setVisible(true);
    }
}
