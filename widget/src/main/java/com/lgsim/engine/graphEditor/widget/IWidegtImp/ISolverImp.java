package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.calc.InvokeCalcExecutableResult;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphCodec;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
import com.lgsim.engine.graphEditor.util.exception.InvokeExecutableException;
import com.lgsim.engine.graphEditor.widget.PoJo.Solver;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.charset.Charset;

public class ISolverImp implements ISolver
{
  private Solver solver = new Solver();
  private static final int STATUS_CODE_OK = 0;
  private static final String DEFAULT_ENCODING = "utf-8";


  @Override
  public @NotNull IGraph calc(@NotNull ISolverEnvironment environment) throws CalcException
  {
    try
    {
      InvokeCalcExecutableResult result = invokeCalcExecutable(environment);
      if (result.getStatusCode() == STATUS_CODE_OK)
      {
        IGraphCodec codec = ImplementationUtil.getInstanceOf(IGraphCodec.class);
        String s = FileUtils.readFileToString(result.getOutputFile(), DEFAULT_ENCODING);
        return codec.decode(s.getBytes(Charset.forName(DEFAULT_ENCODING)));
      }
      else
      {
        throw new CalcException();
      }
    }
    catch (Exception e)
    {
      throw new CalcException();
    }
  }


  @Override
  public @NotNull InvokeCalcExecutableResult invokeCalcExecutable(@NotNull ISolverEnvironment environment) throws InvokeExecutableException
  {
    File file = new File(environment.getCaseName() + ".out");
    InvokeCalcExecutableResult result = new InvokeCalcExecutableResult(0, file);
    solver.executeCmd(environment);
    return result;
  }
}
