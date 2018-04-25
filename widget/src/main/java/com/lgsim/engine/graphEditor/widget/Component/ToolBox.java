package com.lgsim.engine.graphEditor.widget.Component;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.util.ResourceUtil;
import com.lgsim.engine.graphEditor.widget.ActionBundle;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("WeakerAccess")
public class ToolBox extends JToolBar {
  private JPopupMenu menu;
  private List<JCheckBoxMenuItem> checkBoxMenuItemList = new ArrayList<>();
  private Map<JCheckBoxMenuItem, JToolBar> map = new HashMap<>();
  private JPanel panel = new JPanel();
  private Supplier<IApplicationAction> actionSupplier;

  public ToolBox() {
    createMenuItem();
    this.setLayout(new GridLayout(0, 2, 0, 0));
    this.setVisible(true);
  }

  public void createMenuItem() {

    JCheckBoxMenuItem standard = new JCheckBoxMenuItem(ActionBundle.get("toolBar.standard"), false);
    JCheckBoxMenuItem layout = new JCheckBoxMenuItem(ActionBundle.get("toolBar.layout"), false);
    JCheckBoxMenuItem move = new JCheckBoxMenuItem(ActionBundle.get("toolBar.move"), false);
    JCheckBoxMenuItem format = new JCheckBoxMenuItem(ActionBundle.get("toolBar.format"), false);
    JCheckBoxMenuItem tool = new JCheckBoxMenuItem(ActionBundle.get("toolBar.tool"), false);
    JCheckBoxMenuItem view = new JCheckBoxMenuItem(ActionBundle.get("toolBar.view"), false);
    JCheckBoxMenuItem control = new JCheckBoxMenuItem(ActionBundle.get("toolBar.control.pel"), false);
    JCheckBoxMenuItem bank = new JCheckBoxMenuItem(ActionBundle.get("toolBar.bank.pel"), false);
    JCheckBoxMenuItem custom = new JCheckBoxMenuItem(ActionBundle.get("toolBar.custom"), false);

    JCheckBoxMenuItem[] menuItems = {standard, layout, move, format, tool, view, control, bank, custom};
    checkBoxMenuItemList = Arrays.asList(menuItems);
    addMenuItem(checkBoxMenuItemList);
  }

  /**
   * 添加菜单项
   *
   * @param menuItemList
   */
  public void addMenuItem(List<JCheckBoxMenuItem> menuItemList) {
    menu = new JPopupMenu();
    for (JCheckBoxMenuItem menuItem : menuItemList) {
      setMenuItemListener(menuItem);
      menu.add(menuItem);
    }

    setMenuLister(menu);
  }

  public void setMenuLister(JPopupMenu menu) {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getButton() == MouseEvent.BUTTON3) {
          menu.show(ToolBox.this, e.getX(), e.getY());
        }
      }
    });
  }

  /**
   * 弹出菜单设置监听
   *
   * @param menuItem
   */
  public void setMenuItemListener(JCheckBoxMenuItem menuItem) {
    menuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (menuItem.getState()) {
          map.get(menuItem).setVisible(true);
        } else {
          map.get(menuItem).setVisible(false);
        }
        menu.setVisible(true);
      }
    });
  }

  public JToolBar addToolBar(List<JButton> buttonList) {
    JToolBar toolBar = new JToolBar();
    for (JButton button : buttonList) {
      if (button != null) {
        toolBar.add(button);
        toolBar.setVisible(false);
      }

      panel.add(toolBar);
    }
    return toolBar;
  }

  /**
   * 设置工具
   */
  public void createTools() {
    if (actionSupplier != null) {
      IApplicationAction action = actionSupplier.get();
      JButton news = setTools("", action.getDocumentNewAction(), ActionBundle.get("file.new"));
      JButton open = setTools("", action.getDocumentOpenAction(), ActionBundle.get("file.open"));
      JButton save = setTools("", action.getDocumentSaveAction(), ActionBundle.get("file.save"));
      JButton cut = setTools("", action.getVertexCellCutAction(), ActionBundle.get("editor.cut"));
      JButton copy = setTools("", action.getVertexCellCopyAction(), ActionBundle.get("editor.copy"));
      JButton paste = setTools("", action.getVertexCellPasteAction(), ActionBundle.get("editor.paste"));
      JButton delete = setTools("", action.getVertexCellDeleteAction(), ActionBundle.get("editor.delete"));
      JButton undo = setTools("", null, ActionBundle.get("editor.undo"));
      JButton redo = setTools("", null, ActionBundle.get("editor.redo"));
      JButton search = setTools("", null, ActionBundle.get("layout.search.branch"));//查找分支
      JButton last = setTools("", null, ActionBundle.get("layout.last"));
      JButton next = setTools("", null, ActionBundle.get("layout.next"));
      JButton start = setTools("", null, ActionBundle.get("calc.start"));
      JButton argument = setTools("", null, ActionBundle.get("calc.argument"));//参数分析
      JButton trend = setTools("", null, ActionBundle.get("calc.show.trend"));//显示曲线
      JButton print = setTools("", null, ActionBundle.get("file.print"));
      JButton preview = setTools("", null, ActionBundle.get("file.preview"));
      JButton pictureOut = setTools("", null, ActionBundle.get("file.pictureOut"));//图片导出
      JButton auto = setTools("", null, ActionBundle.get("file.auto"));
      JButton svg = setTools("", null, ActionBundle.get("file.svg"));
      JButton flash = setTools("", null, ActionBundle.get("file.flash"));
      JButton sliver = setTools("", null, ActionBundle.get("file.sliver"));
      JButton pdf = setTools("", null, ActionBundle.get("file.pdf"));

      JButton[] buttons = {news, open, save, cut, copy, paste, delete, undo, redo, search, last, next
          , start, argument, trend, print, preview, pictureOut, auto, svg, flash, sliver, pdf};
      List<JButton> standardList = Arrays.asList(buttons);
      JToolBar standardToolBar = addToolBar(standardList);
      map.put(checkBoxMenuItemList.get(0), standardToolBar);

      JButton grid = setTools("", null, ActionBundle.get("align.grid"));
      JButton same = setTools("", null, ActionBundle.get("size.same.grid"));
      JButton left = setTools("", null, ActionBundle.get("align.left"));
      JButton center = setTools("", null, ActionBundle.get("align.center"));
      JButton right = setTools("", null, ActionBundle.get("align.right"));
      JButton top = setTools("", null, ActionBundle.get("align.top"));
      JButton middle = setTools("", null, ActionBundle.get("align.middle"));
      JButton bottom = setTools("", null, ActionBundle.get("align.bottom"));//对齐底部

      JButton[] button2 = {grid, same, left, center, right, top, middle, bottom};
      List<JButton> layoutList = Arrays.asList(button2);
      JToolBar layoutToolBar = addToolBar(layoutList);
      map.put(checkBoxMenuItemList.get(1), layoutToolBar);

      JButton hFlip = setTools("", null, ActionBundle.get("turn.H.flip"));
      JButton vFlip = setTools("", null, ActionBundle.get("turn.V.flip"));
      JButton tLeft = setTools("", null, ActionBundle.get("turn.left"));
      JButton tRight = setTools("", null, ActionBundle.get("turn.right"));//右旋

      JButton[] button3 = {hFlip, vFlip, tLeft, tRight};
      List<JButton> moveList = Arrays.asList(button3);
      JToolBar moveToolBar = addToolBar(moveList);
      map.put(checkBoxMenuItemList.get(2), moveToolBar);

      JButton b = setTools("", null, ActionBundle.get("style.B"));//加粗
      JButton i = setTools("", null, ActionBundle.get("style.I"));
      JButton u = setTools("", null, ActionBundle.get("style.U"));//下划线

      JButton[] button4 = {b, i, u};
      List<JButton> styleList = Arrays.asList(button4);
      JToolBar styleToolBar = addToolBar(styleList);
      map.put(checkBoxMenuItemList.get(3), styleToolBar);

      JButton rectangular = setTools("", null, ActionBundle.get("tool.rectangular"));
      JButton ellipse = setTools("", null, ActionBundle.get("tool.ellipse"));
      JButton line = setTools("", null, ActionBundle.get("tool.line"));
      JButton graph = setTools("", null, ActionBundle.get("tool.ellipse.graph"));

      JButton[] button5 = {rectangular, ellipse, line, graph};
      List<JButton> toolList = Arrays.asList(button5);
      JToolBar toolToolBar = addToolBar(toolList);
      map.put(checkBoxMenuItemList.get(4), toolToolBar);

      JButton vGrid = setTools("", null, ActionBundle.get("view.grid"));
      JButton vScale = setTools("", null, ActionBundle.get("view.scale"));
      JButton reference = setTools("", null, ActionBundle.get("view.reference"));
      JButton port = setTools("", null, ActionBundle.get("view.port"));

      JButton[] button6 = {vGrid, vScale, reference, port};
      List<JButton> viewList = Arrays.asList(button6);
      JToolBar viewToolBar = addToolBar(viewList);
      map.put(checkBoxMenuItemList.get(5), viewToolBar);

      JButton pointer = setTools("", null, ActionBundle.get("tool.pointer"));
      JButton control = setTools("", null, ActionBundle.get("toolBar.control.pel"));
      JButton magnify = setTools("", null, ActionBundle.get("view.magnify"));
      JButton narrow = setTools("", null, ActionBundle.get("view.narrow"));

      JButton[] button7 = {pointer, control, magnify, narrow};
      List<JButton> pelList = Arrays.asList(button7);
      JToolBar pelToolBar = addToolBar(pelList);
      map.put(checkBoxMenuItemList.get(6), pelToolBar);

      JButton pNews = setTools("", null, ActionBundle.get("pel.new"));
      JButton pOpen = setTools("", null, ActionBundle.get("pel.open"));
      JButton pSave = setTools("", null, ActionBundle.get("pel.save"));
      JButton pClose = setTools("", null, ActionBundle.get("pel.close"));

      JButton[] button8 = {pNews, pOpen, pSave, pClose};
      List<JButton> pBpelList = Arrays.asList(button8);
      JToolBar pBToolBar = addToolBar(pBpelList);
      map.put(checkBoxMenuItemList.get(7), pBToolBar);

      JButton custom = setTools("", null, ActionBundle.get("toolBar.custom"));
      List<JButton> customList = Arrays.asList(custom);
      JToolBar cToolBar = addToolBar(customList);
      map.put(checkBoxMenuItemList.get(8), cToolBar);
    }
  }

  /**
   * 设置工具
   */
  public JButton setTools(String image, Action action, String tip) {

    JButton button = new JButton(getImageIcon(image));
    button.setToolTipText(tip);
    button.addActionListener(action);
    return button;
  }

  public Icon getImageIcon(String iconPath) {
    iconPath = "com/lgsim/engine/graphEditor/widget/png/monkey.png";
    Icon icon = ResourceUtil.lookupImageIcon(iconPath);
    return icon;
  }

  public void setActionSupplier(@NotNull Supplier<IApplicationAction> supplier) {
    this.actionSupplier = supplier;
  }
}
