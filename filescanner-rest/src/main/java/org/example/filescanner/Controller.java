package org.example.filescanner;

import lombok.RequiredArgsConstructor;


import org.example.filescanner.model.NodeDto;
import org.example.filescanner.service.ScannerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class Controller {

    private final ScannerService scannerService;

    /**
     * Triggers file scan.
     */
    @PostMapping("/start")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<NodeDto> initScan() {
        return scannerService.scanFolder();
    }

    /**
     * Purges database from all files and folders.
     */
    @PostMapping("/purge")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void purgeDatabase() {
        scannerService.purgeDatabase();
    }
}
