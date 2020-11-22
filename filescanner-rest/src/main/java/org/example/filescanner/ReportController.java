package org.example.filescanner;

import lombok.RequiredArgsConstructor;

import org.example.filescanner.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    /**
     * Delivers a collection of folders and their subfolders sorted by name.
     *
     * @return List of folders
     */
    @GetMapping("/folders")
    public ResponseEntity<List<String>> getFolders() {
        return new ResponseEntity<>(reportService.getFolders(), HttpStatus.OK);
    }

    /**
     * Delivers folders and sub-folders with aggregated filesize sorted by size.
     *
     * @param extension optionally filtered by file extension.
     * @return List of folders sorted by their aggregated filesize.
     */
    @GetMapping("/filesizes")
    public ResponseEntity<List<String>> getFoldersWithSizes(@RequestParam(required = false) String extension) {
        return new ResponseEntity<>(reportService.getFoldersWithSizes(extension), HttpStatus.OK);
    }
}
