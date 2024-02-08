package com.westeros.diagnostics.service.contract;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DiagnosticsResultsDto {

    private String serviceName;
    private List<Diagnostics> diagnostics = new ArrayList<>();
}
