package com.lgsim.engine.graphEditor.widget.Component;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.widget.ActionBundle;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationPopupMenu extends JPopupMenu {

    public List<JCheckBoxMenuItem> checkBoxMenuItemList = new ArrayList<>();

    private JPopupMenu menu;

    public ApplicationPopupMenu() {
    }

    public void bindAction(IApplicationAction action) {
        createMenuItem(action);
    }

    public void createMenuItem(IApplicationAction popupMenuAction) {

        JCheckBoxMenuItem standard = createCheckBoxMenuItem("toolBar.standard", popupMenuAction.getStandardAction(), false);
        JCheckBoxMenuItem layout = createCheckBoxMenuItem("toolBar.layout", popupMenuAction.getLayoutAction(), false);

        JCheckBoxMenuItem move = createCheckBoxMenuItem("toolBar.move", popupMenuAction.getMoveAction(), false);
        JCheckBoxMenuItem format = createCheckBoxMenuItem("toolBar.format", popupMenuAction.getFormatAction(), false);

        JCheckBoxMenuItem tool = createCheckBoxMenuItem("toolBar.tool", popupMenuAction.getToolAction(), false);
        JCheckBoxMenuItem view = createCheckBoxMenuItem("toolBar.view", popupMenuAction.getViewAction(), false);

        JCheckBoxMenuItem control = createCheckBoxMenuItem("toolBar.control.pel", popupMenuAction.getControlAction(), false);
        JCheckBoxMenuItem bank = createCheckBoxMenuItem("toolBar.bank.pel", popupMenuAction.getBankAction(), false);

        JCheckBoxMenuItem custom = createCheckBoxMenuItem("toolBar.custom", popupMenuAction.getCustomAction(), false);

        JCheckBoxMenuItem[] menuItems = {standard, layout, move, format, tool, view, control, bank, custom};
        checkBoxMenuItemList = Arrays.asList(menuItems);
        addMenuItem(checkBoxMenuItemList);

    }

    /**
     * 创建 JCheckBoxMenuItem
     *
     * @param key
     * @param action
     * @param select
     * @return
     */
    public JCheckBoxMenuItem createCheckBoxMenuItem(String key, Action action, boolean select) {

        JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(ActionBundle.get(key), select);
        checkBoxMenuItem.addActionListener(action);
        return checkBoxMenuItem;
    }


    public void setMenuLister(JPopupMenu menu) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    menu.show(ApplicationPopupMenu.this, e.getX(), e.getY());
                }
            }
        });
    }

    public void addMenuItem(List<JCheckBoxMenuItem> menuItemList) {

        menu = new JPopupMenu();
        for (JCheckBoxMenuItem menuItem : menuItemList) {
            menu.add(menuItem);
        }
        setMenuLister(menu);
        this.add(menu);
    }


}
