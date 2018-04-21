package com.lgsim.engine.graphEditor.widget.IWidegtImp;

import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.api.calc.InvokeCalcExecutableResult;
import com.lgsim.engine.graphEditor.api.data.IVertexOutput;
import com.lgsim.engine.graphEditor.util.exception.CalcException;
import com.lgsim.engine.graphEditor.util.exception.InvokeExecutableException;
import com.lgsim.engine.graphEditor.widget.PoJo.Solver;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ISolverImp implements ISolver {

    private Solver solver = new Solver();
    @Override
    public List<IVertexOutput> calc(@NotNull ISolverEnvironment environment) throws CalcException {
        return null;
    }

    @Override
    public InvokeCalcExecutableResult invokeCalcExecutable(@NotNull ISolverEnvironment environment) throws InvokeExecutableException {
        File file = new File(environment.getCaseName()+".out");
        InvokeCalcExecutableResult result = new InvokeCalcExecutableResult(0,file);
        solver.executeCmd(environment);
        return result;
    }
}
