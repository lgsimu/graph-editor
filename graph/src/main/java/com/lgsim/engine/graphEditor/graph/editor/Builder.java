package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.IconBundle;
import com.lgsim.engine.graphEditor.api.MessageBundle;
import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.util.CollectionUtil;
import com.lgsim.engine.graphEditor.util.StringUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

@SuppressWarnings("unused")
class Builder {
  @Contract(pure = true)
  static @NotNull IVertex createVertex(@NotNull IVertexStencil stencil, boolean cavity)
  {
    String ID = StringUtil.emptyString();
    String typeID = stencil.getID();
    List<IVertexArgument> arguments = CollectionUtil.cloneList(stencil.getArguments());
    List<IVertexOutput> outputs = CollectionUtil.cloneList(stencil.getOutputs());
    List<IVertex> inputPorts = CollectionUtil.emptyList();
    List<IVertex> outputPorts = CollectionUtil.emptyList();
    return new VertexImpl(ID, typeID, arguments, outputs, inputPorts, outputPorts, cavity, ID);
  }

  @Contract(pure = true)
  static @NotNull StencilPalette createStencilPalette() {
    return new StencilPalette();
  }

  @Contract(pure = true)
  static @NotNull Action createAction(@NotNull String bundle, @NotNull Action action, @NotNull Object comp) {
    String name = MessageBundle.get(bundle);
    Icon icon = IconBundle.get(bundle);
    AbstractAction newAction = new AbstractAction(name, icon) {
      public void actionPerformed(ActionEvent e)
      {
        action.actionPerformed(new ActionEvent(comp, e.getID(), e.getActionCommand()));
      }
    };
    newAction.putValue(Action.SHORT_DESCRIPTION, action.getValue(Action.SHORT_DESCRIPTION));
    return newAction;
  }
}
