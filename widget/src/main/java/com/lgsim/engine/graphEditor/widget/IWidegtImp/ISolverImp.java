package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.google.common.io.Files;
import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.calc.InvokeCalcExecutableResult;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphCalcCodec;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
import com.lgsim.engine.graphEditor.util.exception.InvokeExecutableException;
import com.lgsim.engine.graphEditor.widget.PoJo.Solver;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class ISolverImp implements ISolver {

  private Solver solver = new Solver();
  private static final int STATUS_CODE_OK = 0;


  @Override
  public @NotNull IGraph calc(@NotNull ISolverEnvironment environment) throws CalcException
  {
    try {
      InvokeCalcExecutableResult result = invokeCalcExecutable(environment);
      if (result.getStatusCode() == STATUS_CODE_OK) {
        IGraphCalcCodec codec = ImplementationUtil.getInstanceOf(IGraphCalcCodec.class);
        byte[] bytes = Files.toByteArray(result.getOutputFile());
        return codec.decode(bytes);
      }
      else {
        throw new CalcException();
      }
    }
    catch (Exception e) {
      throw new CalcException();
    }
  }


  @Override
  public @NotNull InvokeCalcExecutableResult invokeCalcExecutable(@NotNull ISolverEnvironment environment) throws InvokeExecutableException
  {
    Object[] exeResult = solver.executeCmd(environment);
    int status = Integer.parseInt(exeResult[0].toString());
    File file = new File(exeResult[1].toString() + ".inp");
    InvokeCalcExecutableResult result = new InvokeCalcExecutableResult(status, file);
    return result;
  }
}
