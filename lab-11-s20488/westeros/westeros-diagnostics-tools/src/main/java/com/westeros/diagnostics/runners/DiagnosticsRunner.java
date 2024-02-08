package com.westeros.diagnostics.runners;

import com.westeros.diagnostics.service.contract.Diagnostics;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticsRunner implements IRunDiagnoses {

    @Override
    public List<Diagnostics> runAll() {
        List<Diagnostics> diagnosticsList = new ArrayList<>();
        diagnosticsList.add(new DiskSpaceDiagnostics().run());
        return diagnosticsList;
    }
}
