package com.lgsim.engine.graphEditor.graph.document;

import com.lgsim.engine.graphEditor.graph.graph.Graph;
import com.lgsim.engine.graphEditor.graph.graph.GraphComponent;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Node;

import java.nio.charset.Charset;

@SuppressWarnings("WeakerAccess")
public class DocumentCodec {

  private static final Charset codecCharset = Charset.forName("UTF-8");


  @Contract(pure = true)
  public static @NotNull byte[] encode(@NotNull Document document) {
    mxCodec codec = new mxCodec();
    mxGraphComponent graphComponent = document.getSwingComponent();
    mxGraph graph = graphComponent.getGraph();
    mxIGraphModel model = graph.getModel();
    Node node = codec.encode(model);
    String xml = mxXmlUtils.getXml(node);
    return xml.getBytes(codecCharset);
  }


  @Contract(pure = true)
  public static @NotNull Document decode(@NotNull byte[] bytes) {
    String xml = new String(bytes, codecCharset);
    org.w3c.dom.Document doc = mxXmlUtils.parseXml(xml);
    mxCodec codec = new mxCodec(doc);
    Document document = new Document();
    Graph graph = new Graph();
    document.setSwingComponent(new GraphComponent(graph));
    codec.decode(doc.getDocumentElement(), graph.getModel());
    return document;
  }
}
