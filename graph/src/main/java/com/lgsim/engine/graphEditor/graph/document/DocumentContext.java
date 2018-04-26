package com.lgsim.engine.graphEditor.graph.document;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.IApplication;
import com.lgsim.engine.graphEditor.api.graph.IGraphDocument;
import com.lgsim.engine.graphEditor.api.graph.impl.GraphStyleCodecImpl;
import com.lgsim.engine.graphEditor.graph.ImplementationContext;
import com.lgsim.engine.graphEditor.util.JarUtil;
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
  private final IApplication spec;

  public DocumentContext(@NotNull IApplication spec) {
    this.spec = spec;
  }

  public void put(@NotNull Document document) throws IOException {
//    final File docFile = document.getGraphDocumentFile().getEntryFile();
//    if (docFile.exists()) {
//      updateDocumentJarFile(document);
//    } else {
//      File workDir = Files.createTempDir();
//      File jarFile = createDocumentJarFile(document, workDir);
//      Files.copy(jarFile, docFile);
//      if (workDir.delete()) {
//        log.debug("delete temp work dir {}", workDir);
//      }
//    }
  }

  private @NotNull File createDocumentJarFile(@NotNull IGraphDocument document, @NotNull File workDir) throws IOException
  {
    log.debug("create document jar file");
    File temp = new File(workDir, "tmp");
    File jarFile = new File(workDir, document.getTitle() + ".jar");
    createDocumentModelFile(document, temp);
    createDocumentStyleFile(document, temp);
    Manifest manifestFile = createManifest();
    JarUtil.pack(temp, jarFile, manifestFile);
    return jarFile;
  }

  private void createDocumentModelFile(@NotNull IGraphDocument document, @NotNull File workDir) throws IOException
  {
    byte[] data = ImplementationContext.INSTANCE.getGraphCodec().encode(document.getGraph());
    File file = new File(workDir, "model");
    writeToFile(data, file);
  }

  private void createDocumentStyleFile(@NotNull IGraphDocument document, @NotNull File workDir) throws IOException
  {
    GraphStyleCodecImpl codec = new GraphStyleCodecImpl();
    byte[] data = codec.encode(document.getGraphStyle());
    File file = new File(workDir, "style");
    writeToFile(data, file);
  }

  private void writeToFile(byte[] data, @NotNull File file) throws IOException
  {
    Files.write(data, file);
  }

  private @NotNull Manifest createManifest()
  {
    Manifest manifest = new Manifest();
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_TITLE, spec.getImplementationTitle());
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VERSION, spec.getImplementationVersion());
    manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VENDOR, spec.getImplementationVendor());
    return manifest;
  }

  private void updateDocumentJarFile(@NotNull IGraphDocument document)
  {
    log.debug("update document jar file, {}", document);
  }
}
