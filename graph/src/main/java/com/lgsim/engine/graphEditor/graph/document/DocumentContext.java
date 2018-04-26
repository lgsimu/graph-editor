package com.lgsim.engine.graphEditor.graph.document;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocument;
import com.lgsim.engine.graphEditor.util.Configuration;
import com.lgsim.engine.graphEditor.util.JarUtil;
import com.lgsim.engine.graphEditor.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@SuppressWarnings("WeakerAccess")
public class DocumentContext {

  private static final Logger log = LoggerFactory.getLogger(DocumentContext.class);
  private IApplication application;
  private Configuration configuration;
  private File temp;


  public DocumentContext(@NotNull IApplication application) {
    this.application = application;
    this.configuration = application.getConfiguration();
    this.temp = configuration.getTempDirectory();
  }


  public void put(@NotNull Document document) throws IOException {
    byte[] data = DocumentCodec.encode(document);
    File entryFile = new File(document.getEntryFilePath());
    if (entryFile.exists()) {
      updateDocumentJarFile(document);
    }
    else {
      createDocumentJarFile(document, temp);
      Files.write(data, entryFile);
    }
  }


  private void createDocumentJarFile(@NotNull IGraphDocument document, @NotNull File entry) throws IOException
  {
    log.debug("create document jar file {}", StringUtil.getName(entry));
    Manifest manifestFile = createManifest();
    JarUtil.pack(temp, entry, manifestFile);
  }


  private @NotNull Manifest createManifest()
  {
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_TITLE, application.getImplementationTitle());
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VERSION, application.getImplementationVersion());
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VENDOR, application.getImplementationVendor());
    return manifest;
  }


  private void updateDocumentJarFile(@NotNull IGraphDocument document)
  {
    log.debug("update document jar file, {}", document);
  }
}
