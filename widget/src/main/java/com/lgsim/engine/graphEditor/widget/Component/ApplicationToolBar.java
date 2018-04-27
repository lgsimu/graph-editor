package com.lgsim.engine.graphEditor.widget.Component;


import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.util.ResourceUtil;
import com.lgsim.engine.graphEditor.widget.ActionBundle;

import javax.swing.*;
import java.util.*;

public class ApplicationToolBar extends JToolBar {

    private InitToolbarAction action = new InitToolbarAction();

    public ApplicationToolBar() {

        init();

    }

    public void init(){

        JButton news = setTools("", action, ActionBundle.get("file.new"));
        JButton open = setTools("", action, ActionBundle.get("file.open"));
        JButton save = setTools("", action, ActionBundle.get("file.save"));
        JButton cut = setTools("", action, ActionBundle.get("editor.cut"));
        JButton copy = setTools("", action, ActionBundle.get("editor.copy"));
        JButton paste = setTools("", action, ActionBundle.get("editor.paste"));
        JButton delete = setTools("", action, ActionBundle.get("editor.delete"));
        JButton undo = setTools("", action, ActionBundle.get("editor.undo"));
        JButton redo = setTools("", action, ActionBundle.get("editor.redo"));
        JButton search = setTools("", action, ActionBundle.get("layout.search.branch"));//查找分支
        JButton last = setTools("", action, ActionBundle.get("layout.last"));
        JButton next = setTools("", action, ActionBundle.get("layout.next"));
        JButton start = setTools("", action, ActionBundle.get("calc.start"));
        JButton argument = setTools("", action, ActionBundle.get("calc.argument"));//参数分析
        JButton trend = setTools("", action, ActionBundle.get("calc.show.trend"));//显示曲线
        JButton print = setTools("", action, ActionBundle.get("file.print"));
        JButton preview = setTools("", action, ActionBundle.get("file.preview"));
        JButton pictureOut = setTools("", action, ActionBundle.get("file.pictureOut"));//图片导出
        JButton auto = setTools("", action, ActionBundle.get("file.auto"));
        JButton svg = setTools("", action, ActionBundle.get("file.svg"));
        JButton flash = setTools("", action, ActionBundle.get("file.flash"));
        JButton sliver = setTools("", action, ActionBundle.get("file.sliver"));
        JButton pdf = setTools("", action, ActionBundle.get("file.pdf"));

        JButton[] buttons = {news, open, save, cut, copy, paste, delete, undo, redo, search, last, next
                , start, argument, trend, print, preview, pictureOut, auto, svg, flash, sliver, pdf};
        List<JButton> standardList = Arrays.asList(buttons);
        addToolBar(standardList);


        JButton grid = setTools("", action, ActionBundle.get("align.grid"));
        JButton same = setTools("", action, ActionBundle.get("size.same.grid"));
        JButton left = setTools("", action, ActionBundle.get("align.left"));
        JButton center = setTools("", action, ActionBundle.get("align.center"));
        JButton right = setTools("", action, ActionBundle.get("align.right"));
        JButton top = setTools("", action, ActionBundle.get("align.top"));
        JButton middle = setTools("", action, ActionBundle.get("align.middle"));
        JButton bottom = setTools("", action, ActionBundle.get("align.bottom"));//对齐底部

        JButton[] button2 = {grid, same, left, center, right, top, middle, bottom};
        List<JButton> layoutList = Arrays.asList(button2);
        addToolBar(layoutList);


        JButton hFlip = setTools("", action, ActionBundle.get("turn.H.flip"));
        JButton vFlip = setTools("", action, ActionBundle.get("turn.V.flip"));
        JButton tLeft = setTools("", action, ActionBundle.get("turn.left"));
        JButton tRight = setTools("", action, ActionBundle.get("turn.right"));//右旋

        JButton[] button3 = {hFlip, vFlip, tLeft, tRight};
        List<JButton> moveList = Arrays.asList(button3);
        addToolBar(moveList);


        JButton b = setTools("", action, ActionBundle.get("style.B"));//加粗
        JButton i = setTools("", action, ActionBundle.get("style.I"));
        JButton u = setTools("", action, ActionBundle.get("style.U"));//下划线

        JButton[] button4 = {b, i, u};
        List<JButton> styleList = Arrays.asList(button4);
        addToolBar(styleList);


        JButton rectangular = setTools("", action, ActionBundle.get("tool.rectangular"));
        JButton ellipse = setTools("", action, ActionBundle.get("tool.ellipse"));
        JButton line = setTools("", action, ActionBundle.get("tool.line"));
        JButton graph = setTools("", action, ActionBundle.get("tool.ellipse.graph"));

        JButton[] button5 = {rectangular, ellipse, line, graph};
        List<JButton> toolList = Arrays.asList(button5);
        addToolBar(toolList);


        JButton vGrid = setTools("", action, ActionBundle.get("view.grid"));
        JButton vScale = setTools("", action, ActionBundle.get("view.scale"));
        JButton reference = setTools("", action, ActionBundle.get("view.reference"));
        JButton port = setTools("", action, ActionBundle.get("view.port"));

        JButton[] button6 = {vGrid, vScale, reference, port};
        List<JButton> viewList = Arrays.asList(button6);
        addToolBar(viewList);


        JButton pointer = setTools("", action, ActionBundle.get("tool.pointer"));
        JButton control = setTools("", action, ActionBundle.get("toolBar.control.pel"));
        JButton magnify = setTools("", action, ActionBundle.get("view.magnify"));
        JButton narrow = setTools("", action, ActionBundle.get("view.narrow"));

        JButton[] button7 = {pointer, control, magnify, narrow};
        List<JButton> pelList = Arrays.asList(button7);
        addToolBar(pelList);

        JButton pNews = setTools("", action, ActionBundle.get("pel.new"));
        JButton pOpen = setTools("", action, ActionBundle.get("pel.open"));
        JButton pSave = setTools("", action, ActionBundle.get("pel.save"));
        JButton pClose = setTools("", action, ActionBundle.get("pel.close"));

        JButton[] button8 = {pNews, pOpen, pSave, pClose};
        List<JButton> pBpelList = Arrays.asList(button8);
        addToolBar(pBpelList);

        JButton custom = setTools("", action, ActionBundle.get("toolBar.custom"));
        List<JButton> customList = Arrays.asList(custom);
        addToolBar(customList);

    }

    public void bindActions(IApplicationAction action) {
        createTools(action);
    }

    private void createTools(IApplicationAction action) {

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
        addToolBar(standardList);


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
        addToolBar(layoutList);


        JButton hFlip = setTools("", null, ActionBundle.get("turn.H.flip"));
        JButton vFlip = setTools("", null, ActionBundle.get("turn.V.flip"));
        JButton tLeft = setTools("", null, ActionBundle.get("turn.left"));
        JButton tRight = setTools("", null, ActionBundle.get("turn.right"));//右旋

        JButton[] button3 = {hFlip, vFlip, tLeft, tRight};
        List<JButton> moveList = Arrays.asList(button3);
         addToolBar(moveList);


        JButton b = setTools("", null, ActionBundle.get("style.B"));//加粗
        JButton i = setTools("", null, ActionBundle.get("style.I"));
        JButton u = setTools("", null, ActionBundle.get("style.U"));//下划线

        JButton[] button4 = {b, i, u};
        List<JButton> styleList = Arrays.asList(button4);
        addToolBar(styleList);


        JButton rectangular = setTools("", null, ActionBundle.get("tool.rectangular"));
        JButton ellipse = setTools("", null, ActionBundle.get("tool.ellipse"));
        JButton line = setTools("", null, ActionBundle.get("tool.line"));
        JButton graph = setTools("", null, ActionBundle.get("tool.ellipse.graph"));

        JButton[] button5 = {rectangular, ellipse, line, graph};
        List<JButton> toolList = Arrays.asList(button5);
        addToolBar(toolList);


        JButton vGrid = setTools("", null, ActionBundle.get("view.grid"));
        JButton vScale = setTools("", null, ActionBundle.get("view.scale"));
        JButton reference = setTools("", null, ActionBundle.get("view.reference"));
        JButton port = setTools("", null, ActionBundle.get("view.port"));

        JButton[] button6 = {vGrid, vScale, reference, port};
        List<JButton> viewList = Arrays.asList(button6);
        addToolBar(viewList);


        JButton pointer = setTools("", null, ActionBundle.get("tool.pointer"));
        JButton control = setTools("", null, ActionBundle.get("toolBar.control.pel"));
        JButton magnify = setTools("", null, ActionBundle.get("view.magnify"));
        JButton narrow = setTools("", null, ActionBundle.get("view.narrow"));

        JButton[] button7 = {pointer, control, magnify, narrow};
        List<JButton> pelList = Arrays.asList(button7);
        addToolBar(pelList);

        JButton pNews = setTools("", null, ActionBundle.get("pel.new"));
        JButton pOpen = setTools("", null, ActionBundle.get("pel.open"));
        JButton pSave = setTools("", null, ActionBundle.get("pel.save"));
        JButton pClose = setTools("", null, ActionBundle.get("pel.close"));

        JButton[] button8 = {pNews, pOpen, pSave, pClose};
        List<JButton> pBpelList = Arrays.asList(button8);
        addToolBar(pBpelList);

        JButton custom = setTools("", null, ActionBundle.get("toolBar.custom"));
        List<JButton> customList = Arrays.asList(custom);
        addToolBar(customList);


    }

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

    public void addToolBar(List<JButton> buttonList) {
        JToolBar toolBar = new JToolBar();
        for (JButton button : buttonList) {
            if (button != null) {
                toolBar.add(button);
               // toolBar.setVisible(true);
            }
            this.add(toolBar);
        }
    }
}
