package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.calc.InvokeCalcExecutableResult;
import com.lgsim.engine.graphEditor.api.data.IGraph;
import com.lgsim.engine.graphEditor.api.data.IGraphCalcCodec;
import com.lgsim.engine.graphEditor.util.ImplementationUtil;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
import com.lgsim.engine.graphEditor.util.exception.InvokeExecutableException;
import com.lgsim.engine.graphEditor.widget.PoJo.Solver;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.charset.Charset;

public class ISolverImp implements ISolver {

  private Solver solver = new Solver();
  private static final int STATUS_CODE_OK = 0;


  @Override
  public @NotNull IGraph calc(@NotNull ISolverEnvironment environment) throws CalcException
  {
    try {
      InvokeCalcExecutableResult result = invokeCalcExecutable(environment);
      if (result.getStatusCode() == STATUS_CODE_OK) {
        final String encoding = "utf-8";
        IGraphCalcCodec codec = ImplementationUtil.getInstanceOf(IGraphCalcCodec.class);
        String s = FileUtils.readFileToString(result.getOutputFile(), encoding);
        return codec.decode(s.getBytes(Charset.forName(encoding)));
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
