package com.lgsim.engine.graphEditor.graph.component;

import com.lgsim.engine.graphEditor.graph.util.MessageBundleUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class StencilComponentXmlFileParser
{
  private final OriginType originType;
  private final String path;
  private final SAXReader reader;


  StencilComponentXmlFileParser(@NotNull OriginType originType, @NotNull String path)
  {
    this.originType = originType;
    this.path = path;
    this.reader = new SAXReader();
  }


  @NotNull
  public List<StencilComponent> parse() throws ResourceFileMissingException, FileNotFoundException, DocumentException
  {
    final boolean predefined = originType == OriginType.PREDEFINED;
    final Document document;
    if (predefined)
    {
      URL url = Thread.currentThread().getContextClassLoader().getResource(path);
      if (url == null)
      {
        throw new ResourceFileMissingException();
      }
      else
      {
        document = reader.read(url);
      }
    }
    else
    {
      File file = new File(path);
      if (file.exists())
      {
        FileReader fileReader = new FileReader(file);
        document = reader.read(fileReader);
      }
      else
      {
        throw new FileNotFoundException();
      }
    }
    return parse0(document.getRootElement(), predefined);
  }


  private List<StencilComponent> parse0(final Element element, final boolean predefined)
  {
    final List<Element> models = element.elements("model");
    final Vector<StencilComponent> nodes = new Vector<>();
    for (Element model : models)
    {
      StencilComponent node = parseNode(model, predefined);
      nodes.add(node);
    }
    return nodes;
  }


  @NotNull
  private StencilComponent parseNode(Element element, boolean predefined)
  {
    String id = element.attributeValue("id");
    String name = parseName(element, predefined);
    Element iconElement = element.element("icon");
    String modelIcon, canvasIcon;
    if (iconElement == null)
    {
      modelIcon = null;
      canvasIcon = null;
    }
    else
    {
      modelIcon = iconElement.attributeValue("model");
      canvasIcon = iconElement.attributeValue("canvas");
    }
    List<InstanceComponentArgument> arguments = parseArguments(element, predefined);
    List<InstanceComponentOutput> outputs = parseOutputs(element, predefined);
    InstanceComponentConnectRestriction restriction = parseRestriction(element);
    return new StencilComponent(originType, id, name, modelIcon, canvasIcon, arguments, outputs, restriction);
  }


  @NotNull
  private List<InstanceComponentArgument> parseArguments(Element element, boolean predefined)
  {
    List<InstanceComponentArgument> arguments = new ArrayList<>();
    Element argumentsElem = element.element("arguments");
    if (argumentsElem == null)
    {
      return arguments;
    }
    else
    {
      for (Element elem : argumentsElem.elements())
      {
        String id = elem.attributeValue("id");
        String type = elem.attributeValue("type");
        String name = elem.attributeValue("name");
        String description = parseDescription(elem, predefined);
        BigDecimal value = parseNumerical(elem.attributeValue("value"));
        arguments.add(new InstanceComponentArgument(id, type, name, description, value));
      }
      return arguments;
    }
  }


  @NotNull
  private List<InstanceComponentOutput> parseOutputs(Element element, boolean predefined)
  {
    List<InstanceComponentOutput> outputs = new ArrayList<>();
    Element argumentsElem = element.element("outputs");
    if (argumentsElem == null)
    {
      return outputs;
    }
    else
    {
      for (Element elem : argumentsElem.elements())
      {
        String id = elem.attributeValue("id");
        String type = elem.attributeValue("type");
        String name = elem.attributeValue("name");
        String description = parseDescription(elem, predefined);
        BigDecimal value = parseNumerical(elem.attributeValue("value"));
        outputs.add(new InstanceComponentOutput(id, type, name, description, value));
      }
      return outputs;
    }
  }


  @Nullable
  private InstanceComponentConnectRestriction parseRestriction(Element element)
  {
    Element resElem = element.element("restriction");
    if (resElem == null)
    {
      return null;
    }
    else
    {
      int minInputPortCount = Integer.valueOf(resElem.attributeValue("minInputPortCount"));
      int maxInputPortCount = Integer.valueOf(resElem.attributeValue("maxInputPortCount"));
      int minOutputPortCount = Integer.valueOf(resElem.attributeValue("minOutputPortCount"));
      int maxOutputPortCount = Integer.valueOf(resElem.attributeValue("maxOutputPortCount"));
      List<String> inputTypeIds = new ArrayList<>();
      resElem.element("inputTypeIds").elementIterator().forEachRemaining(it -> {
        String itId = it.getTextTrim();
        inputTypeIds.add(itId);
      });
      List<String> outputTypeIds = new ArrayList<>();
      resElem.element("outputTypeIds").elementIterator().forEachRemaining(it -> {
        String itId = it.getTextTrim();
        outputTypeIds.add(itId);
      });
      return new InstanceComponentConnectRestriction(minInputPortCount, maxInputPortCount,
                                                     minOutputPortCount, maxOutputPortCount,
                                                     inputTypeIds, outputTypeIds);
    }
  }


  private String parseName(Element element, boolean predefined)
  {
    String name = element.attributeValue("name");
    return parsePossibleResourceString(predefined, name);
  }


  private String parseDescription(Element element, boolean predefined)
  {
    String desc = element.attributeValue("description");
    return parsePossibleResourceString(predefined, desc);
  }


  private String parsePossibleResourceString(boolean predefined, String key)
  {
    if (predefined)
    {
      return MessageBundleUtil.get(key);
    }
    else
    {
      return key;
    }
  }


  @NotNull
  private BigDecimal parseNumerical(String s)
  {
    return new BigDecimal(s);
  }
}
