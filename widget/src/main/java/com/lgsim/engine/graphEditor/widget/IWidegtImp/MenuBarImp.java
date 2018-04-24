//package com.lgsim.engine.graphEditor.widget.IWidegtImp;
//
//import com.lgsim.engine.graphEditor.api.IconBundle;
//import com.lgsim.engine.graphEditor.api.widget.MessageBundleCN;
//import org.jetbrains.annotations.NotNull;
//
//import javax.swing.*;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class MenuBarImp extends JMenuBar{
//
//    private final GraphEditor editor;
//
//
//    public MenuBarImp(@NotNull GraphEditor editor) {
//        this.editor = editor;
//        addFileMenu();
//        addEditorMenu();
//        addViewMenu();
//        addFormatMenu();
//        addMoveMenu();
//        addLayoutMenu();
//        addToolMenu();
//        addUserDataManageMenu();
//        addAddOnsMenu();
//        addHelpMenu();
//    }
//
//
//    private void addFileMenu() {
//        JMenu doc = new JMenu(MessageBundleCN.get("file.name"));
//        DocumentSaveAction action = new DocumentSaveAction();//临时
//
//        Action save = setAction("file.save", action, "file.save");
//        Action news = setAction("file.new", action, "file.new");
//        Action open = setAction("file.open", action, "file.open");
//        Action saveAs = setAction("file.saveAs", action, "file.saveAs");
//        Action pictureOut = setAction("file.pictureOut", action, "file.pictureOut");
//        Action auto = setAction("file.auto", action, "file.auto");
//        Action svg = setAction("file.svg", action, "file.svg");
//        Action flash = setAction("file.flash", action, "file.flash");
//        Action sliver = setAction("file.sliver", action, "file.sliver");
//        Action pdf = setAction("file.pdf", action, "file.pdf");
//        Action pageSet = setAction("file.pageSet", action, "file.pageSet");
//        Action preview = setAction("file.preview", action, "file.preview");
//        Action print = setAction("file.print", action, "file.print");
//        Action set = setAction("file.set", action, "file.set");
//        Action exit = setAction("file.exit", action, "file.exit");
//
//        Action[] actions = {save, news, open, saveAs, pictureOut, auto, svg, flash,
//                sliver, pdf, pageSet, preview, print, set, exit};
//
//        List<Action> actionList = Arrays.asList(actions);
//        List<Integer> separatorList = Arrays.asList(4, 10, 14);
//
//        addMenuItem(actionList, doc, separatorList, null);
//        add(doc);
//    }
//
//    private void addEditorMenu() {
//        JMenu doc = new JMenu(MessageBundleCN.get("editor.name"));
//        DocumentSaveAction action = new DocumentSaveAction();//临时
//
//        Action undo = setAction("editor.undo", action, "editor.undo");
//        Action redo = setAction("editor.redo", action, "editor.redo");
//        Action cut = setAction("editor.cut", action, "editor.cut");
//        Action copy = setAction("editor.copy", action, "editor.copy");
//        Action paste = setAction("editor.paste", action, "editor.paste");
//        Action delete = setAction("editor.delete", action, "editor.delete");
//        Action selectAll = setAction("editor.selectAll", action, "editor.selectAll");
//        Action dimensional = setAction("editor.one.dimensional.shape", action, "editor.one.dimensional.shape");
//        Action dimensional2 = setAction("editor.two.dimensional.shape", action, "editor.two.dimensional.shape");
//
//        Action[] actions = {undo, redo, cut, copy, paste, delete, selectAll, dimensional, dimensional2};
//
//        List<Action> actionList = Arrays.asList(actions);
//        List<Integer> separatorList = Arrays.asList(2, 6);
//
//        addMenuItem(actionList, doc, separatorList, null);
//        add(doc);
//
//    }
//
//    private void addViewMenu() {
//        JMenu doc = new JMenu(MessageBundleCN.get("view.name"));
//
//        DocumentSaveAction action = new DocumentSaveAction();
//
//        Action scale = setAction("view.scale", action, "view.scale");
//        Action grid = setAction("view.grid", action, "view.grid");
//        Action reference = setAction("view.reference", action, "view.reference");
//        Action port = setAction("view.port", action, "view.port");
//        Action shadow = setAction("view.shadow", action, "view.shadow");
//        Action arrow = setAction("view.arrow", action, "view.arrow");
//        Action library = setAction("view.component,library.browser", action, "view.component,library.browser");
//        Action property = setAction("view.property.browser", action, "view.property.browser");
//
//        JMenu subGridStyle = new JMenu(MessageBundleCN.get("view.grid.style"));
//
//        Action mainLine = setAction("grid.style.main.line", action, "grid.style.main.line");
//        Action secondLine = setAction("grid.style.main.second.line", action, "grid.style.main.second.line");
//        Action point = setAction("grid.style.main.point", action, "grid.style.main.point");
//        Action scan = setAction("grid.style.interlaced.scan", action, "grid.style.interlaced.scan");
//        Action horizontal = setAction("grid.style.interlaced.horizontal", action, "grid.style.interlaced.horizontal");
//        Action vertical = setAction("grid.style.interlaced.vertical", action, "grid.style.interlaced.vertical");
//        Action[] gridSubActions = {mainLine, secondLine, point, scan, horizontal, vertical};
//
//        List<Action> gridSubActionList = Arrays.asList(gridSubActions);
//        addMenuItem(gridSubActionList, subGridStyle, null, null);
//
//        JMenu subLookLayout = new JMenu(MessageBundleCN.get("view.lookLayout"));
//        Action common = setAction("lookLayout.common", action, "lookLayout.common");
//        Action suit = setAction("lookLayout.suit", action, "lookLayout.suit");
//        Action stretch = setAction("lookLayout.stretch", action, "lookLayout.stretch");
//        Action Hstretch = setAction("lookLayout.H.stretch", action, "lookLayout.H.stretch");
//        Action Vstretch = setAction("lookLayout.V.stretch", action, "lookLayout.V.stretch");
//
//        Action[] lookLayoutSubActions = {common, suit, stretch, Hstretch, Vstretch};
//        List<Action> lookLayoutSubActionList = Arrays.asList(lookLayoutSubActions);
//        addMenuItem(lookLayoutSubActionList, subLookLayout, null, null);
//
//
//        Action magnify = setAction("view.magnify", action, "view.magnify");
//        Action narrow = setAction("view.narrow", action, "view.narrow");
//        Action ratio = setAction("view.magnify.ratio", action, "view.magnify.ratio");
//        Action alignGrid = setAction("view.align.grid", action, "view.align.grid");
//        Action alignScale = setAction("view.align.scale", action, "view.align.scale");
//        Action guidance = setAction("view.capture.guidance", action, "view.capture.guidance");
//        Action rotate = setAction("view.capture.rotate", action, "view.capture.rotate");
//
//        Action[] actions = {scale, grid, reference, port, shadow, arrow, library, property, null, null,
//                magnify, narrow, ratio, alignGrid, alignScale, guidance, rotate};
//
//        List<Action> actionList = Arrays.asList(actions);
//        List<Integer> separatorList = Arrays.asList(8, 10, 13);
//
//        //子菜单的集合
//        Map<Integer, JMenu> map = new HashMap();
//        map.put(8, subGridStyle);
//        map.put(9, subLookLayout);
//
//        addMenuItem(actionList, doc, separatorList, map);
//        add(doc);
//    }
//
//
//    private void addFormatMenu() {
//        JMenu doc = new JMenu(MessageBundleCN.get("format.name"));
//
//        DocumentSaveAction action = new DocumentSaveAction();
//
//        Action fill = setAction("format.fill.style", action, "format.fill.style");
//        Action brush = setAction("format.brush.style", action, "format.brush.style");
//        Action shadow = setAction("format.shadow.style", action, "format.shadow.style");
//        Action text = setAction("format.text.style", action, "format.text.style");
//        Action startArrrow = setAction("format.start.arrow.style", action, "format.start.arrow.style");
//        Action endArrow = setAction("format.end.arrow.style", action, "format.end.arrow.style");
//        Action connect = setAction("format.connect.style", action, "format.connect.style");
//        Action interaction = setAction("format.interaction.style", action, "format.interaction.style");
//        Action model = setAction("format.model", action, "format.model");
//
//        Action[] actions = {fill, brush, shadow, text, startArrrow, endArrow, connect, interaction, model};
//        List<Action> actionList = Arrays.asList(actions);
//        List<Integer> separatorList = Arrays.asList(4, 7);
//
//        addMenuItem(actionList, doc, separatorList, null);
//        add(doc);
//
//    }
//
//    private void addMoveMenu() {
//        JMenu doc = new JMenu(MessageBundleCN.get("move.name"));
//        DocumentSaveAction action = new DocumentSaveAction();
//
//        JMenu operate = new JMenu(MessageBundleCN.get("move.operate"));
//        Action relink = setAction("operate.relink", action, "operate.relink");
//        Action reverse = setAction("operate.reverse", action, "operate.reverse");
//        Action refect = setAction("operate.refect", action, "operate.refect");
//        Action border = setAction("operate.update.border", action, "operate.update.border");
//        Action[] operateArr = {relink, reverse, refect, border};
//        List<Action> operateList = Arrays.asList(operateArr);
//        addMenuItem(operateList, operate, null, null);
//
//        JMenu group = new JMenu(MessageBundleCN.get("move.group"));
//        Action group2 = setAction("group.group", action, "group.group");
//        Action cancel = setAction("group.cancel", action, "group.cancel");
//        List<Action> groupList = Arrays.asList(group2, cancel);
//        addMenuItem(groupList, group, null, null);
//
//        Action layout = setAction("move.layout", action, "move.layout");
//
//        JMenu order = new JMenu(MessageBundleCN.get("move.order"));
//        Action first = setAction("order.first", action, "order.first");
//        Action last = setAction("order.last", action, "order.last");
//        Action previous = setAction("order.previous", action, "order.previous");
//        Action next = setAction("order.next", action, "order.next");
//        List<Action> orderList = Arrays.asList(first, last, previous, next);
//        addMenuItem(orderList, order, null, null);
//
//        JMenu turn = new JMenu(MessageBundleCN.get("move.turn"));
//        Action left = setAction("turn.left", action, "turn.left");
//        Action right = setAction("turn.right", action, "turn.right");
//        Action hFlip = setAction("turn.H.flip", action, "turn.H.flip");
//        Action vflip = setAction("turn.V.flip", action, "turn.V.flip");
//        Action[] turnArr = {left, right, hFlip, vflip};
//        List<Action> turnList = Arrays.asList(turnArr);
//        addMenuItem(turnList, turn, null, null);
//
//        JMenu tiny = new JMenu(MessageBundleCN.get("move.tiny"));
//        Action tLeft = setAction("tiny.left", action, "tiny.left");
//        Action tRight = setAction("tiny.right", action, "tiny.right");
//        Action tTop = setAction("tiny.top", action, "tiny.top");
//        Action tBottom = setAction("tiny.bottom", action, "tiny.bottom");
//        Action[] tinyArr = {tLeft, tRight, tTop, tBottom};
//        List<Action> tinyList = Arrays.asList(tinyArr);
//        addMenuItem(tinyList, tiny, null, null);
//        Action[] actions = {null, null, layout, null, null, null};
//        List<Action> actionList = Arrays.asList(actions);
//
//        Map<Integer, JMenu> map = new HashMap<>();
//        map.put(1, operate);
//        map.put(2, group);
//        map.put(4, order);
//        map.put(5, turn);
//        map.put(6, tiny);
//
//        addMenuItem(actionList, doc, null, map);
//        add(doc);
//    }
//
//    private void addLayoutMenu() {
//        JMenu doc = new JMenu(MessageBundleCN.get("layout.name"));
//        DocumentSaveAction action = new DocumentSaveAction();
//
//        JMenu align = new JMenu(MessageBundleCN.get("layout.align"));
//        Action left = setAction("align.left", action, "align.left");
//        Action center = setAction("align.center", action, "align.center");
//        Action right = setAction("align.right", action, "align.right");
//        Action top = setAction("align.top", action, "align.top");
//        Action middle = setAction("align.middle", action, "align.middle");
//        Action bottom = setAction("align.bottom", action, "align.bottom");
//        Action grid = setAction("align.grid", action, "align.grid");
//        Action[] alignArr = {left, center, right, top, middle, bottom, grid};
//        List<Action> alignList = Arrays.asList(alignArr);
//        addMenuItem(alignList, align, null, null);
//
//        JMenu size = new JMenu(MessageBundleCN.get("layout.size"));
//        Action width = setAction("size.same.width", action, "size.same.width");
//        Action height = setAction("size.same.height", action, "size.same.height");
//        Action sSize = setAction("size.same.size", action, "size.same.size");
//        Action sGrid = setAction("size.same.grid", action, "size.same.grid");
//        List<Action> sizeList = Arrays.asList(width, height, sSize, sGrid);
//        addMenuItem(sizeList, size, null, null);
//
//        JMenu textCenter = new JMenu(MessageBundleCN.get("layout.center"));
//        Action hFile = setAction("center.file.H", action, "center.file.H");
//        Action vFile = setAction("center.file.V", action, "center.file.V");
//        List<Action> centerList = Arrays.asList(hFile, vFile);
//        addMenuItem(centerList, textCenter, null, null);
//
//        JMenu gap = new JMenu(MessageBundleCN.get("layout.gap"));
//        Action sameH = setAction("gap.H.same", action, "gap.H.same");
//        Action addH = setAction("gap.H.add", action, "gap.H.add");
//        Action reduceH = setAction("gap.H.reduce", action, "gap.H.reduce");
//        Action moveH = setAction("gap.H.move", action, "gap.H.move");
//        Action sameV = setAction("gap.V.same", action, "gap.V.same");
//        Action addV = setAction("gap.V.add", action, "gap.V.add");
//        Action reduceV = setAction("gap.V.reduce", action, "gap.V.reduce");
//        Action moveV = setAction("gap.V.move", action, "gap.V.move");
//        Action[] gapArr = {sameH, addH, reduceH, moveH, sameV, addV, reduceV, moveV};
//        List<Action> gapList = Arrays.asList(gapArr);
//        addMenuItem(gapList, gap, null, null);
//
//        Action shape = setAction("layout.shape", action, "layout.shape");
//
//        Action[] actions = {null, null, center, null, shape};
//        List<Action> actionList = Arrays.asList(actions);
//        List<Integer> separatorList = Arrays.asList(4);
//
//        Map<Integer, JMenu> map = new HashMap<>();
//        map.put(1, align);
//        map.put(2, size);
//        map.put(3, textCenter);
//        map.put(4, gap);
//
//        addMenuItem(actionList, doc, separatorList, map);
//        add(doc);
//    }
//
//    private void addToolMenu() {
//        JMenu doc = new JMenu(MessageBundleCN.get("tool.name"));
//        DocumentSaveAction action = new DocumentSaveAction();
//
//        Action pointer = setAction("tool.pointer", action, "tool.pointer");
//        Action rectangular = setAction("tool.rectangular", action, "tool.rectangular");
//        Action ellipse = setAction("tool.ellipse", action, "tool.ellipse");
//        Action line = setAction("tool.line", action, "tool.line");
//        Action graph = setAction("tool.ellipse.graph", action, "tool.ellipse.graph");
//        Action arc = setAction("tool.arc", action, "tool.arc");
//        Action fold = setAction("tool.fold.line", action, "tool.fold.line");
//        Action polygon = setAction("tool.polygon", action, "tool.polygon");
//        Action curve = setAction("tool.curve", action, "tool.curve");
//        Action close = setAction("tool.close.curve", action, "tool.close.curve");
//        Action bezier = setAction("tool.bezier.curve", action, "tool.bezier.curve");
//        Action text = setAction("tool.text", action, "tool.text");
//        Action drag = setAction("tool.drag", action, "tool.drag");
//
//        Action[] actions = {pointer, rectangular, ellipse, line, graph, arc, fold, polygon, curve, close, bezier, text, drag};
//        List<Action> actionList = Arrays.asList(actions);
//
//        addMenuItem(actionList, doc, null, null);
//        add(doc);
//
//    }
//
//    private void addUserDataManageMenu() {
//        JMenu doc = new JMenu(MessageBundleCN.get("userDataManage.name"));
//        DocumentSaveAction action = new DocumentSaveAction();
//
//        Action login = setAction("userDataManage.database.login", action, "userDataManage.database.login");
//        Action cancel = setAction("userDataManage.cancel", action, "userDataManage.cancel");
//        Action manage = setAction("userDataManage.data.manage", action, "userDataManage.data.manage");
//        Action backup = setAction("userDataManage.backup", action, "userDataManage.data.backup");
//        Action reset = setAction("userDataManage.reset", action, "userDataManage.reset");
//        Action user = setAction("userDataManage.user.mange", action, "userDataManage.user.mange");
//
//        Action[] actions = {login, cancel, manage, backup, reset, user};
//        List<Action> actionList = Arrays.asList(actions);
//
//        addMenuItem(actionList, doc, null, null);
//        add(doc);
//    }
//
//    private void addAddOnsMenu() {
//        JMenu doc = new JMenu(MessageBundleCN.get("add.ons.name"));
//        DocumentSaveAction action = new DocumentSaveAction();
//
//        Action pel = setAction("add.ons.custom.pel", action, "add.ons.custom.pel");
//        Action pen = setAction("add.ons.pen", action, "add.ons.pen");
//        Action toolkit = setAction("add.ons.toolkit", action, "add.ons.toolkit");
//
//        List<Action> actionList = Arrays.asList(pel, pen, toolkit);
//
//        addMenuItem(actionList, doc, null, null);
//        add(doc);
//
//    }
//
//    private void addHelpMenu() {
//        JMenu doc = new JMenu(MessageBundleCN.get("help.name"));
//        DocumentSaveAction action = new DocumentSaveAction();
//
//        Action instructions = setAction("help.instructions", action, "help.instructions");
//        Action about = setAction("help.about", action, "help.about");
//
//        List<Action> actionList = Arrays.asList(instructions, about);
//
//        addMenuItem(actionList, doc, null, null);
//        add(doc);
//    }
//
//    private Action setAction(String key, AbstractAction action, String icon) {
//        Action actionName = editor.bind(MessageBundleCN.get(key), action, IconBundle.get(icon));
//        return actionName;
//    }
//
//    /**
//     * @param addList       菜单各项
//     * @param doc           主菜单
//     * @param separatorList 分割线
//     * @param subMap        <子菜单,位置>
//     */
//    private void addMenuItem(List<Action> addList, JMenu doc, List<Integer> separatorList,
//                             Map<Integer, JMenu> subMap) {
//        int index = 0;
//        for (Action action : addList) {
//            index++;
//            if (action != null) {
//                doc.add(action);
//            }
//            if (separatorList != null && separatorList.contains(index)) {
//                doc.addSeparator();
//            }
//            if (subMap != null) {
//                if (subMap.containsKey(index)) {
//                    doc.add(subMap.get(index));
//                }
//            }
//        }
//    }
//}
