package com.lgsim.engine.graphEditor.ui.bean;

import java.io.Serializable;

public class SolverEnvBean implements Serializable {

  private String caseName;
  private String executable;
  private String arguments;


  public SolverEnvBean() {
  }


  public String getCaseName() {
    return caseName;
  }


  public void setCaseName(String caseName) {
    this.caseName = caseName;
  }


  public String getExecutable() {
    return executable;
  }


  public void setExecutable(String executable) {
    this.executable = executable;
  }


  public String getArguments() {
    return arguments;
  }


  public void setArguments(String arguments) {
    this.arguments = arguments;
  }
}
