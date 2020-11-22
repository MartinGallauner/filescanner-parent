package org.example.filescanner.service;

import org.example.filescanner.model.NodeDto;
import org.example.filescanner.persistence.NodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ScannerServiceTest {

    @InjectMocks
    ScannerService scannerService;

    @Mock
    NodeRepository nodeRepository;

    @Test
    void scanFolder() {
        //arrange
        scannerService.setPath(new File("src/test/resources/data").getAbsolutePath());

        //act
        List<NodeDto> result = scannerService.scanFolder();


        //assert
    }
}
