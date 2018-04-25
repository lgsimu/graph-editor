package com.lgsim.engine.graphEditor.widget.Component;

import com.lgsim.engine.graphEditor.api.action.IApplicationAction;
import com.lgsim.engine.graphEditor.util.ExceptionManager;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.util.ResourceUtil;
import com.lgsim.engine.graphEditor.widget.ActionBundle;
import org.apache.commons.lang.exception.ExceptionUtils;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuBar extends JMenuBar {

    private IApplicationAction iApplicationAction;

    public MenuBar() {

        getActionInstance();
        addFileMenu();
        addEditorMenu();
        addViewMenu();
        addFormatMenu();
        addMoveMenu();
        addLayoutMenu();
        addDrawMenu();
        addDefine();
        addCalc();
        addCoupCalcMenu();
        addToolMenu();
        addHelpMenu();
    }

    public IApplicationAction getActionInstance() {

        try {
            iApplicationAction = ImplementationUtil.getInstanceOf(IApplicationAction.class);
        } catch (InstantiationException e) {
            ExceptionManager.INSTANCE.dealWith(e);
        }

        return iApplicationAction;
    }

    private void addFileMenu() {
        JMenu doc = new JMenu(ActionBundle.get("file.name"));

        JMenuItem save = createMenuItem("file.save", iApplicationAction.getDocumentSaveAction(), "file.save");
        JMenuItem news = createMenuItem("file.new", iApplicationAction.getDocumentNewAction(), "file.new");
        JMenuItem open = createMenuItem("file.open", iApplicationAction.getDocumentOpenAction(), "file.open");
        JMenuItem saveAs = createMenuItem("file.saveAs", null, "file.saveAs");
        JMenuItem pictureOut = createMenuItem("file.pictureOut", null, "file.pictureOut");
        JMenuItem network = createMenuItem("file.switch.network", null, "file.switch.network");
        JMenuItem input = createMenuItem("file.import.data", null, "file.import.data");
        JMenuItem out = createMenuItem("file.out.data", null, "file.out.data");
        JMenuItem auto = createMenuItem("file.auto", null, "file.auto");
        JMenuItem svg = createMenuItem("file.svg", null, "file.svg");
        JMenuItem flash = createMenuItem("file.flash", null, "file.flash");
        JMenuItem sliver = createMenuItem("file.sliver", null, "file.sliver");
        JMenuItem pdf = createMenuItem("file.pdf", null, "file.pdf");
        JMenuItem pageSet = createMenuItem("file.pageSet", null, "file.pageSet");
        JMenuItem preview = createMenuItem("file.preview", null, "file.preview");
        JMenuItem print = createMenuItem("file.print", null, "file.print");
        JMenuItem set = createMenuItem("file.set", null, "file.set");
        JMenuItem close = createMenuItem("file.close", null, "file.close");
        JMenuItem exit = createMenuItem("file.exit", iApplicationAction.getApplicationExitAction(), "file.exit");

        JMenuItem[] actions = {news, open, save, saveAs, pictureOut, network, input, out, auto, svg, flash,
                sliver, pdf, pageSet, preview, print, set, close, exit};

        List<JMenuItem> actionList = Arrays.asList(actions);
        List<Integer> separatorList = Arrays.asList(4, 7, 13, 18);

        addMenuItem(actionList, doc, separatorList, null);
        add(doc);
    }

    private void addEditorMenu() {
        JMenu doc = new JMenu(ActionBundle.get("editor.name"));

        JMenuItem undo = createMenuItem("editor.undo", null, "editor.undo");
        JMenuItem redo = createMenuItem("editor.redo", null, "editor.redo");
        JMenuItem cut = createMenuItem("editor.cut", iApplicationAction.getVertexCellCutAction(), "editor.cut");
        JMenuItem copy = createMenuItem("editor.copy", iApplicationAction.getVertexCellCopyAction(), "editor.copy");
        JMenuItem paste = createMenuItem("editor.paste", iApplicationAction.getVertexCellPasteAction(), "editor.paste");
        JMenuItem delete = createMenuItem("editor.delete", iApplicationAction.getVertexCellDeleteAction(), "editor.delete");
        JMenuItem selectAll = createMenuItem("editor.selectAll", null, "editor.selectAll");
        JMenuItem dimensional = createMenuItem("editor.one.dimensional.shape", null, "editor.one.dimensional.shape");
        JMenuItem dimensional2 = createMenuItem("editor.two.dimensional.shape", null, "editor.two.dimensional.shape");

        JMenuItem[] actions = {undo, redo, cut, copy, paste, delete, selectAll, dimensional, dimensional2};

        List<JMenuItem> actionList = Arrays.asList(actions);
        List<Integer> separatorList = Arrays.asList(2, 6);

        addMenuItem(actionList, doc, separatorList, null);
        add(doc);

    }

    private void addViewMenu() {
        JMenu doc = new JMenu(ActionBundle.get("view.name"));


        JMenuItem scale = createMenuItem("view.scale", null, "view.scale");
        JMenuItem grid = createMenuItem("view.grid", null, "view.grid");
        JMenuItem reference = createMenuItem("view.reference", null, "view.reference");
        JMenuItem port = createMenuItem("view.port", null, "view.port");
        JMenuItem shadow = createMenuItem("view.shadow", null, "view.shadow");
        JMenuItem arrow = createMenuItem("view.arrow", null, "view.arrow");
        JMenuItem library = createMenuItem("view.component,library.browser", null, "view.component,library.browser");
        JMenuItem property = createMenuItem("view.property.browser", null, "view.property.browser");

        JMenu subGridStyle = new JMenu(ActionBundle.get("view.grid.style"));

        JMenuItem mainLine = createMenuItem("grid.style.main.line", null, "grid.style.main.line");
        JMenuItem secondLine = createMenuItem("grid.style.main.second.line", null, "grid.style.main.second.line");
        JMenuItem point = createMenuItem("grid.style.main.point", null, "grid.style.main.point");
        JMenuItem scan = createMenuItem("grid.style.interlaced.scan", null, "grid.style.interlaced.scan");
        JMenuItem horizontal = createMenuItem("grid.style.interlaced.horizontal", null, "grid.style.interlaced.horizontal");
        JMenuItem vertical = createMenuItem("grid.style.interlaced.vertical", null, "grid.style.interlaced.vertical");
        JMenuItem[] gridSubActions = {mainLine, secondLine, point, scan, horizontal, vertical};

        List<JMenuItem> gridSubActionList = Arrays.asList(gridSubActions);
        addMenuItem(gridSubActionList, subGridStyle, null, null);

        JMenu subLookLayout = new JMenu(ActionBundle.get("view.lookLayout"));
        JMenuItem common = createMenuItem("lookLayout.common", null, "lookLayout.common");
        JMenuItem suit = createMenuItem("lookLayout.suit", null, "lookLayout.suit");
        JMenuItem stretch = createMenuItem("lookLayout.stretch", null, "lookLayout.stretch");
        JMenuItem Hstretch = createMenuItem("lookLayout.H.stretch", null, "lookLayout.H.stretch");
        JMenuItem Vstretch = createMenuItem("lookLayout.V.stretch", null, "lookLayout.V.stretch");

        JMenuItem[] lookLayoutSubActions = {common, suit, stretch, Hstretch, Vstretch};
        List<JMenuItem> lookLayoutSubActionList = Arrays.asList(lookLayoutSubActions);
        addMenuItem(lookLayoutSubActionList, subLookLayout, null, null);


        JMenuItem magnify = createMenuItem("view.magnify", null, "view.magnify");
        JMenuItem narrow = createMenuItem("view.narrow", null, "view.narrow");
        JMenuItem ratio = createMenuItem("view.magnify.ratio", null, "view.magnify.ratio");
        JMenuItem alignGrid = createMenuItem("view.align.grid", null, "view.align.grid");
        JMenuItem alignScale = createMenuItem("view.align.scale", null, "view.align.scale");
        JMenuItem guidance = createMenuItem("view.capture.guidance", null, "view.capture.guidance");
        JMenuItem rotate = createMenuItem("view.capture.rotate", null, "view.capture.rotate");

        JMenuItem[] actions = {scale, grid, reference, port, shadow, arrow, library, property, null, null,
                magnify, narrow, ratio, alignGrid, alignScale, guidance, rotate};

        List<JMenuItem> actionList = Arrays.asList(actions);
        List<Integer> separatorList = Arrays.asList(8, 10, 13);

        //子菜单的集合
        Map<Integer, JMenu> map = new HashMap();
        map.put(8, subGridStyle);
        map.put(9, subLookLayout);

        addMenuItem(actionList, doc, separatorList, map);
        add(doc);
    }


    private void addFormatMenu() {
        JMenu doc = new JMenu(ActionBundle.get("format.name"));

        JMenuItem fill = createMenuItem("format.fill.style", null, "format.fill.style");
        JMenuItem brush = createMenuItem("format.brush.style", null, "format.brush.style");
        JMenuItem shadow = createMenuItem("format.shadow.style", null, "format.shadow.style");
        JMenuItem text = createMenuItem("format.text.style", null, "format.text.style");
        JMenuItem startArrrow = createMenuItem("format.start.arrow.style", null, "format.start.arrow.style");
        JMenuItem endArrow = createMenuItem("format.end.arrow.style", null, "format.end.arrow.style");
        JMenuItem connect = createMenuItem("format.connect.style", null, "format.connect.style");
        JMenuItem interaction = createMenuItem("format.interaction.style", null, "format.interaction.style");
        JMenuItem unit = createMenuItem("format.set.unit", null, "format.set.unit");
        JMenuItem save = createMenuItem("format.save", null, "format.save");
        JMenuItem interact = createMenuItem("format.interact", null, "format.interact");

        JMenuItem[] actions = {fill, brush, shadow, text, startArrrow, endArrow, connect, interaction, unit, save, interact};
        List<JMenuItem> actionList = Arrays.asList(actions);
        List<Integer> separatorList = Arrays.asList(4, 7, 9);

        addMenuItem(actionList, doc, separatorList, null);
        add(doc);

    }

    private void addMoveMenu() {
        JMenu doc = new JMenu(ActionBundle.get("move.name"));

        JMenu operate = new JMenu(ActionBundle.get("move.operate"));
        JMenuItem relink = createMenuItem("operate.relink", null, "operate.relink");
        JMenuItem reverse = createMenuItem("operate.reverse", null, "operate.reverse");
        JMenuItem refect = createMenuItem("operate.refect", null, "operate.refect");
        JMenuItem border = createMenuItem("operate.update.border", null, "operate.update.border");
        JMenuItem[] operateArr = {relink, reverse, refect, border};
        List<JMenuItem> operateList = Arrays.asList(operateArr);
        addMenuItem(operateList, operate, null, null);

        JMenu group = new JMenu(ActionBundle.get("move.group"));
        JMenuItem group2 = createMenuItem("group.group", null, "group.group");
        JMenuItem cancel = createMenuItem("group.cancel", null, "group.cancel");
        List<JMenuItem> groupList = Arrays.asList(group2, cancel);
        addMenuItem(groupList, group, null, null);

        JMenuItem layout = createMenuItem("move.layout", null, "move.layout");

        JMenu order = new JMenu(ActionBundle.get("move.order"));
        JMenuItem first = createMenuItem("order.first", null, "order.first");
        JMenuItem last = createMenuItem("order.last", null, "order.last");
        JMenuItem previous = createMenuItem("order.previous", null, "order.previous");
        JMenuItem next = createMenuItem("order.next", null, "order.next");
        List<JMenuItem> orderList = Arrays.asList(first, last, previous, next);
        addMenuItem(orderList, order, null, null);

        JMenu turn = new JMenu(ActionBundle.get("move.turn"));
        JMenuItem left = createMenuItem("turn.left", null, "turn.left");
        JMenuItem right = createMenuItem("turn.right", null, "turn.right");
        JMenuItem hFlip = createMenuItem("turn.H.flip", null, "turn.H.flip");
        JMenuItem vflip = createMenuItem("turn.V.flip", null, "turn.V.flip");
        JMenuItem[] turnArr = {left, right, hFlip, vflip};
        List<JMenuItem> turnList = Arrays.asList(turnArr);
        addMenuItem(turnList, turn, null, null);

        JMenu tiny = new JMenu(ActionBundle.get("move.tiny"));
        JMenuItem tLeft = createMenuItem("tiny.left", null, "tiny.left");
        JMenuItem tRight = createMenuItem("tiny.right", null, "tiny.right");
        JMenuItem tTop = createMenuItem("tiny.top", null, "tiny.top");
        JMenuItem tBottom = createMenuItem("tiny.bottom", null, "tiny.bottom");
        JMenuItem[] tinyArr = {tLeft, tRight, tTop, tBottom};
        List<JMenuItem> tinyList = Arrays.asList(tinyArr);
        addMenuItem(tinyList, tiny, null, null);
        JMenuItem[] actions = {null, null, layout, null, null, null};
        List<JMenuItem> actionList = Arrays.asList(actions);

        Map<Integer, JMenu> map = new HashMap<>();
        map.put(1, operate);
        map.put(2, group);
        map.put(4, order);
        map.put(5, turn);
        map.put(6, tiny);

        addMenuItem(actionList, doc, null, map);
        add(doc);
    }

    private void addLayoutMenu() {
        JMenu doc = new JMenu(ActionBundle.get("layout.name"));

        JMenuItem search = createMenuItem("layout.search.branch", null, "layout.search.branch");
        JMenuItem last = createMenuItem("layout.last", null, "layout.last");
        JMenuItem next = createMenuItem("layout.next", null, "layout.next");
        JMenuItem bgp = createMenuItem("layout.bgp", null, "layout.bgp");


        JMenu align = new JMenu(ActionBundle.get("layout.align"));
        JMenuItem left = createMenuItem("align.left", null, "align.left");
        JMenuItem center = createMenuItem("align.center", null, "align.center");
        JMenuItem right = createMenuItem("align.right", null, "align.right");
        JMenuItem top = createMenuItem("align.top", null, "align.top");
        JMenuItem middle = createMenuItem("align.middle", null, "align.middle");
        JMenuItem bottom = createMenuItem("align.bottom", null, "align.bottom");
        JMenuItem grid = createMenuItem("align.grid", null, "align.grid");
        JMenuItem[] alignArr = {left, center, right, top, middle, bottom, grid};
        List<JMenuItem> alignList = Arrays.asList(alignArr);
        addMenuItem(alignList, align, null, null);

        JMenu size = new JMenu(ActionBundle.get("layout.size"));
        JMenuItem width = createMenuItem("size.same.width", null, "size.same.width");
        JMenuItem height = createMenuItem("size.same.height", null, "size.same.height");
        JMenuItem sSize = createMenuItem("size.same.size", null, "size.same.size");
        JMenuItem sGrid = createMenuItem("size.same.grid", null, "size.same.grid");
        List<JMenuItem> sizeList = Arrays.asList(width, height, sSize, sGrid);
        addMenuItem(sizeList, size, null, null);

        JMenu textCenter = new JMenu(ActionBundle.get("layout.center"));
        JMenuItem hFile = createMenuItem("center.file.H", null, "center.file.H");
        JMenuItem vFile = createMenuItem("center.file.V", null, "center.file.V");
        List<JMenuItem> centerList = Arrays.asList(hFile, vFile);
        addMenuItem(centerList, textCenter, null, null);

        JMenu gap = new JMenu(ActionBundle.get("layout.gap"));
        JMenuItem sameH = createMenuItem("gap.H.same", null, "gap.H.same");
        JMenuItem addH = createMenuItem("gap.H.add", null, "gap.H.add");
        JMenuItem reduceH = createMenuItem("gap.H.reduce", null, "gap.H.reduce");
        JMenuItem moveH = createMenuItem("gap.H.move", null, "gap.H.move");
        JMenuItem sameV = createMenuItem("gap.V.same", null, "gap.V.same");
        JMenuItem addV = createMenuItem("gap.V.add", null, "gap.V.add");
        JMenuItem reduceV = createMenuItem("gap.V.reduce", null, "gap.V.reduce");
        JMenuItem moveV = createMenuItem("gap.V.move", null, "gap.V.move");
        JMenuItem[] gapArr = {sameH, addH, reduceH, moveH, sameV, addV, reduceV, moveV};
        List<JMenuItem> gapList = Arrays.asList(gapArr);
        addMenuItem(gapList, gap, null, null);

        JMenuItem shape = createMenuItem("layout.shape", null, "layout.shape");

        JMenuItem[] actions = {search, last, next, bgp, null, null, center, null, shape};
        List<JMenuItem> actionList = Arrays.asList(actions);
        List<Integer> separatorList = Arrays.asList(3, 8);

        Map<Integer, JMenu> map = new HashMap<>();
        map.put(1, align);
        map.put(2, size);
        map.put(3, textCenter);
        map.put(4, gap);

        addMenuItem(actionList, doc, separatorList, map);
        add(doc);
    }

    private void addDrawMenu() {
        JMenu doc = new JMenu(ActionBundle.get("tool.name"));

        JMenuItem pointer = createMenuItem("tool.pointer", null, "tool.pointer");
        JMenuItem rectangular = createMenuItem("tool.rectangular", null, "tool.rectangular");
        JMenuItem ellipse = createMenuItem("tool.ellipse", null, "tool.ellipse");
        JMenuItem line = createMenuItem("tool.line", null, "tool.line");
        JMenuItem graph = createMenuItem("tool.ellipse.graph", null, "tool.ellipse.graph");
        JMenuItem arc = createMenuItem("tool.arc", null, "tool.arc");
        JMenuItem fold = createMenuItem("tool.fold.line", null, "tool.fold.line");
        JMenuItem polygon = createMenuItem("tool.polygon", null, "tool.polygon");
        JMenuItem curve = createMenuItem("tool.curve", null, "tool.curve");
        JMenuItem close = createMenuItem("tool.close.curve", null, "tool.close.curve");
        JMenuItem bezier = createMenuItem("tool.bezier.curve", null, "tool.bezier.curve");
        JMenuItem text = createMenuItem("tool.text", null, "tool.text");
        JMenuItem drag = createMenuItem("tool.drag", null, "tool.drag");
        JMenuItem layer = createMenuItem("tool.layer.manage", null, "tool.layer.manage");
        JMenuItem custom = createMenuItem("tool.custom", null, "tool.custom");
        JMenuItem select = createMenuItem("tool.select", null, "tool.select");

        JMenuItem[] actions = {pointer, rectangular, ellipse, line, graph, arc, fold,
                polygon, curve, close, bezier, text, drag, layer, custom, select};
        List<JMenuItem> actionList = Arrays.asList(actions);

        addMenuItem(actionList, doc, null, null);
        add(doc);

    }

    private void addDefine() {
        JMenu doc = new JMenu(ActionBundle.get("define.name"));
        JMenuItem var = createMenuItem("define.var", null, "define.var");

        List<JMenuItem> actionList = Arrays.asList(var);

        addMenuItem(actionList, doc, null, null);
        add(doc);
    }

    private void addCalc() {
        JMenu doc = new JMenu(ActionBundle.get("calc.name"));

        JMenuItem start = createMenuItem("calc.start", null, "calc.start");
        JMenuItem argument = createMenuItem("calc.argument", null, "calc.argument");
        JMenuItem trend = createMenuItem("calc.show.trend", null, "calc.show.trend");

        List<JMenuItem> actionList = Arrays.asList(start, argument, trend);

        addMenuItem(actionList, doc, null, null);
        add(doc);

    }

    private void addToolMenu() {
        JMenu doc = new JMenu(ActionBundle.get("box.name"));
        JMenuItem note = createMenuItem("box.note", null, "box.note");
        JMenuItem calc = createMenuItem("box.calc", null, "box.calc");
        JMenuItem word = createMenuItem("box.word", null, "box.word");
        JMenuItem excel = createMenuItem("box.excel", null, "box.excel");
        JMenuItem[] arr = {note, calc, word, excel};

        List<JMenuItem> actionList = Arrays.asList(arr);

        addMenuItem(actionList, doc, null, null);
        add(doc);
    }


    private void addCoupCalcMenu() {
        JMenu doc = new JMenu(ActionBundle.get("coup.calc.name"));

        JMenuItem source = createMenuItem("coup.calc.source", null, "coup.calc.source");
        JMenuItem solve = createMenuItem("coup.calc.solve", null, "coup.calc.solve");

        List<JMenuItem> actionList = Arrays.asList(source, solve);

        addMenuItem(actionList, doc, null, null);
        add(doc);
    }

    private void addHelpMenu() {
        JMenu doc = new JMenu(ActionBundle.get("help.name"));
        JMenuItem instructions = createMenuItem("help.instructions", null, "help.instructions");
        JMenuItem about = createMenuItem("help.about", null, "help.about");

        List<JMenuItem> actionList = Arrays.asList(instructions, about);
        addMenuItem(actionList, doc, null, null);
        add(doc);
    }

    public JMenuItem createMenuItem(String key, Action action, String iconPath) {
        iconPath = "com/lgsim/engine/graphEditor/widget/png/monkey.png";
        Icon icon = ResourceUtil.lookupImageIcon(iconPath);
        JMenuItem menuItem = new JMenuItem(ActionBundle.get(key), icon);
        menuItem.addActionListener(action);
        return menuItem;
    }

    /**
     * @param addList       菜单各项
     * @param doc           主菜单
     * @param separatorList 分割线
     * @param subMap        <子菜单,位置>
     */
    private void addMenuItem(List<JMenuItem> addList, JMenu doc, List<Integer> separatorList,
                             Map<Integer, JMenu> subMap) {
        int index = 0;
        for (JMenuItem action : addList) {
            index++;
            if (action != null) {
                doc.add(action);
            }
            if (separatorList != null && separatorList.contains(index)) {
                doc.addSeparator();
            }
            if (subMap != null) {
                if (subMap.containsKey(index)) {
                    doc.add(subMap.get(index));
                }
            }
        }
    }
}
