package com.lgsim.engine.graphEditor.api.calc;

import com.lgsim.engine.graphEditor.api.data.IGraph;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class SolverEnvironment implements ISolverEnvironment {

  private File executableFile;
  private IGraph graph;
  private String caseName;
  private String solverCommandlineArguments;


  public SolverEnvironment() {
  }


  @NotNull
  public File getExecutableFile() {
    return executableFile;
  }


  public void setExecutableFile(File executableFile) {
    this.executableFile = executableFile;
  }


  public IGraph getGraph() {
    return graph;
  }


  public void setGraph(IGraph graph) {
    this.graph = graph;
  }


  @NotNull
  public String getCaseName() {
    return caseName;
  }


  public void setCaseName(String caseName) {
    this.caseName = caseName;
  }


  @NotNull
  public String getSolverCommandlineArguments() {
    return solverCommandlineArguments;
  }


  public void setSolverCommandlineArguments(String solverCommandlineArguments) {
    this.solverCommandlineArguments = solverCommandlineArguments;
  }
}
