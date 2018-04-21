package com.lgsim.engine.graphEditor.widget.FunWay;

import com.lgsim.engine.graphEditor.api.calc.ISolver;
import com.lgsim.engine.graphEditor.api.calc.ISolverEnvironment;
import com.lgsim.engine.graphEditor.widget.PoJo.Solver;
import org.jetbrains.annotations.NotNull;

public class SolverImp implements ISolver {
    private Solver solver = new Solver();

    public SolverImp() {}

    @Override
    public int calc(@NotNull ISolverEnvironment environment) {
        solver.executeCmd(environment);
        return 0;
    }
}
