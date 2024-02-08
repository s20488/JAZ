package com.westeros.diagnostics.controllers;

import com.westeros.diagnostics.runners.IRunDiagnoses;
import com.westeros.diagnostics.service.contract.Diagnostics;
import com.westeros.diagnostics.service.contract.DiagnosticsResultsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("diagnostics")
public class DiagnosticsController {
    IRunDiagnoses diagnosticRunner;

    @GetMapping
    public ResponseEntity<String> checkStatus(){
        return ResponseEntity.ok("ALIVE");
    }

    @GetMapping("/check")
    public ResponseEntity<DiagnosticsResultsDto> checkDiagnostics() {
        List<Diagnostics> diagnostics = diagnosticRunner.runAll();
        DiagnosticsResultsDto diagnosticsResultsDto = new DiagnosticsResultsDto();
        diagnosticsResultsDto.setServiceName("diagnostics check");
        diagnosticsResultsDto.setDiagnostics(diagnostics);
        return ResponseEntity.ok(diagnosticsResultsDto);
    }
}
