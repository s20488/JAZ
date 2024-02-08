package com.westeros.diagnostics.service;


import com.westeros.diagnostics.runners.IRunDiagnoses;
import com.westeros.diagnostics.service.contract.DiagnosticsResultsDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DiagnosticsServiceClient implements IDiagnosticsServiceClient{
    RestTemplate restTemplate;
    IRunDiagnoses diagnosticRunner;

    @Override
    public void sendDiagnostics() {
        DiagnosticsResultsDto diagnosticsResults = new DiagnosticsResultsDto();
        diagnosticsResults.setServiceName("sendDiagnostics");
        diagnosticsResults.setDiagnostics(diagnosticRunner.runAll());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DiagnosticsResultsDto> entity = new HttpEntity<>(diagnosticsResults, headers);
        restTemplate.postForObject("http://localhost:8080/diagnostics", entity, String.class);
    }
}
