package org.example.filescanner.service;

import org.example.filescanner.model.NodeDto;
import org.example.filescanner.persistence.Node;
import org.example.filescanner.persistence.NodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScannerServiceTest {

    @InjectMocks
    private ScannerService scannerService;

    @Mock
    private NodeRepository nodeRepository;

    @Test
    void scanFolder() {
        when(nodeRepository.save(any(Node.class))).then(AdditionalAnswers.returnsFirstArg());

        scannerService.setPath(new File("src/test/resources/data").getAbsolutePath());
        List<NodeDto> result = scannerService.scanFolder();



        assertEquals(4, result.size());
        /* TODO Active test
        assertEquals("DIR", result.get(0).getType());
        assertEquals("data", result.get(0).getName());
        assertEquals("foto", result.get(1).getName());
        assertEquals("test2.txt", result.get(2).getName());
        assertEquals("test1.txt", result.get(3).getName());

         */
    }
}
