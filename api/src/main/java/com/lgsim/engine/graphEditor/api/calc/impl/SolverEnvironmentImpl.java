package com.lgsim.engine.graphEditor.api.calc.impl;

import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class SolverEnvironmentImpl implements ISolverEnvironment
{
  private File executableFile;
  private File solverInputFile;
  private String caseName;
  private String solverCommandArguments;


  @Override
  public @NotNull File getExecutableFile()
  {
    return executableFile;
  }


  @Override
  public @NotNull File getSolverInputFile()
  {
    return solverInputFile;
  }


  @Override
  public @NotNull String getCaseName()
  {
    return caseName;
  }


  @Override
  public @NotNull String getSolverCommandlineArguments()
  {
    return solverCommandArguments;
  }


  public void setExecutableFile(File executableFile)
  {
    this.executableFile = executableFile;
  }


  public void setSolverInputFile(File solverInputFile)
  {
    this.solverInputFile = solverInputFile;
  }


  public void setCaseName(String caseName)
  {
    this.caseName = caseName;
  }


  public String getSolverCommandArguments()
  {
    return solverCommandArguments;
  }


  public void setSolverCommandArguments(String solverCommandArguments)
  {
    this.solverCommandArguments = solverCommandArguments;
  }
}
