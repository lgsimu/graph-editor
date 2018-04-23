package com.lgsim.engine.graphEditor.graph.editor;

import com.lgsim.engine.graphEditor.api.data.IVertex;
import com.lgsim.engine.graphEditor.api.data.IVertexArgument;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.api.data.IVertexStencil;
import com.lgsim.engine.graphEditor.api.data.impl.VertexImpl;
import com.lgsim.engine.graphEditor.util.CollectionUtil;
import com.lgsim.engine.graphEditor.util.StringUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
class Builder {
  @Contract(pure = true)
  static @NotNull IVertex createVertex(@NotNull IVertexStencil stencil, @Nullable Counter vertexCounter, boolean cavity)
  {
    String ID;
    if (vertexCounter == null) {
      ID = StringUtil.emptyString();
    } else {
      ID = vertexCounter.incInt() + "";
    }
    String typeID = stencil.getID();
    List<IVertexArgument> arguments = CollectionUtil.cloneList(stencil.getArguments());
    List<IVertexOutput> outputs = CollectionUtil.cloneList(stencil.getOutputs());
    List<IVertex> inputPorts = CollectionUtil.emptyList();
    List<IVertex> outputPorts = CollectionUtil.emptyList();
    return new VertexImpl(ID, typeID, arguments, outputs, inputPorts, outputPorts, cavity, ID);
  }
}
