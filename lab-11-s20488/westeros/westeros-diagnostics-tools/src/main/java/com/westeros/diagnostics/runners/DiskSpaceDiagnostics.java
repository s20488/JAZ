package com.westeros.diagnostics.runners;

import com.westeros.diagnostics.service.contract.Diagnostics;

import java.io.File;

public class DiskSpaceDiagnostics implements IDiagnose {

    @Override
    public String getName() {
        return "DiskSpaceDiagnostics";
    }

    @Override
    public String getDescription() {
        return "Checks if there is at least 5% free space on the hard drive.";
    }

    @Override
    public Diagnostics run() {
        long totalSpace = new File("/").getTotalSpace();
        long freeSpace = new File("/").getFreeSpace();
        double freeSpacePercentage = (double) freeSpace / totalSpace * 100;
        boolean isSuccess = freeSpacePercentage >= 5;
        String name = getName();
        String errorMessage = isSuccess ? null : "Less than 5% free space on the hard drive.";
        String description = getDescription();
        return new Diagnostics(isSuccess, name, errorMessage, description);
    }
}
