package com.lgsim.engine.graphEditor.graph.action;

import com.lgsim.engine.graphEditor.graph.document.Document;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public class DocumentSaveAction extends DocumentAction {

  private static final Logger log = LoggerFactory.getLogger(DocumentSaveAction.class);
  private JFileChooser saveLocationChooser;


  public DocumentSaveAction(@NotNull Document document) {
    super(document);
    this.saveLocationChooser = new JFileChooser();
  }


  @Override
  public void actionPerformed(ActionEvent event)
  {
    log.debug("save document {}", document);
    int code = saveLocationChooser.showOpenDialog(document.getSwingComponent());
    if (code == JFileChooser.APPROVE_OPTION) {
      File entry = saveLocationChooser.getSelectedFile();
      save(entry);
    }
  }


  private void save(@NotNull File entry) {
    try {
      document.setEntryFilePath(entry.getPath());
      document.output();
    }
    catch (IOException e) {
      log.debug("save document failed {}", e.getMessage());
    }
  }
}
