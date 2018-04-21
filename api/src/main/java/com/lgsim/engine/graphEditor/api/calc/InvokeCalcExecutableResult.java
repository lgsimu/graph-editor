package com.lgsim.engine.graphEditor.api.calc;

import java.io.File;

public class InvokeCalcExecutableResult
{
  private int statusCode;
  private File outputFile;


  public InvokeCalcExecutableResult(int statusCode, File outputFile)
  {
    this.statusCode = statusCode;
    this.outputFile = outputFile;
  }


  public int getStatusCode()
  {
    return statusCode;
  }


  public void setStatusCode(int statusCode)
  {
    this.statusCode = statusCode;
  }


  public File getOutputFile()
  {
    return outputFile;
  }


  public void setOutputFile(File outputFile)
  {
    this.outputFile = outputFile;
  }
}
