package com.westeros.diagnostics.runners;

import com.westeros.diagnostics.service.contract.Diagnostics;

public interface IDiagnose {
    String getName();
    String getDescription();
    Diagnostics run();
}
