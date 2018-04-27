package com.lgsim.engine.graphEditor.graph.document;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.graph.IDocument;
import com.lgsim.engine.graphEditor.util.Configuration;
import com.lgsim.engine.graphEditor.util.JarUtil;
import com.lgsim.engine.graphEditor.util.StringUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.function.BiPredicate;
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
    File entry = new File(document.getEntryFilePath());
    if (entry.exists()) {
      updateDocumentJarFile(document);
    }
    else {
      createDocumentJarFile(document, entry);
    }
  }


  @Contract(pure = true)
  private void createDocumentJarFile(@NotNull Document document, @NotNull File entry) throws IOException
  {
    BiPredicate<File, File> isParent = (dir, file) -> false;
    log.debug("create document jar file {}", StringUtil.getName(entry));
    File tempDir = Files.createTempDir();
    File doc = new File(tempDir, "document.xml");
    byte[] data = DocumentCodec.encode(document);
    Files.write(data, doc);
    Manifest manifestFile = createManifest();
    if (!isParent.test(tempDir, entry)) {
      JarUtil.pack(tempDir, entry, manifestFile);
    }
    else {
      log.error("jar file is under it's archive directory, hence it will not be created");
    }
  }


  private @NotNull Manifest createManifest()
  {
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_TITLE, application.getImplementationTitle());
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VERSION, application.getImplementationVersion());
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VENDOR, application.getImplementationVendor());
    return manifest;
  }


  private void updateDocumentJarFile(@NotNull IDocument document)
  {
    log.debug("update document jar file, {}", document);
  }
}
