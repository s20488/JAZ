package com.westeros.diagnostics;

import com.westeros.diagnostics.runners.IDiagnose;
import com.westeros.diagnostics.service.contract.Diagnostics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class DatabaseConnectivityDiagnostics implements IDiagnose {
    @Override
    public String getName() {
        return "DatabaseConnectivityDiagnostics";
    }

    @Override
    public String getDescription() {
        return "This class is responsible for testing the connectivity with the database.";
    }

    @Override
    public Diagnostics run() {
        Diagnostics diagnostics = new Diagnostics();
        diagnostics.setName(getName());

        try {
            String url = "jdbc:hsqldb:hsql://localhost/workdb";
            String username = "sa";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.close();
            diagnostics.setSuccess(true);
        } catch (Exception e) {
            diagnostics.setSuccess(false);
            diagnostics.setErrorMessage(e.getMessage());
        }

        return diagnostics;
    }
}
