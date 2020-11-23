package org.example.filescanner.service;

import org.example.filescanner.persistence.Node;
import org.example.filescanner.persistence.NodeRepository;
import org.example.filescanner.persistence.NodeTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @InjectMocks
    ReportService reportService;

    @Mock
    NodeRepository nodeRepository;

    @Test
    void getFolders() {
        List<String> expected = Arrays.asList(
                "/home/user/Documents",
                "/home/user/Documents/Office",
                "/home/user/Downloads",
                "/home/user/Pictures",
                "/home/user/Pictures/Party",
                "/home/user/Pictures/Vacation",
                "/home/user/Videos",
                "/home/user/Videos/random"
        );

        when(nodeRepository.findAllByTypeIs(any(NodeTypeEnum.class))).thenReturn(createTestNodes());
        List<String> result = reportService.getFolders();
        assertEquals(8, result.size());
        assertEquals(expected, result);
    }

    private Set<Node> createTestNodes() {
        Set<Node> mockNodes = new HashSet<>();
        mockNodes.add(Node.builder().id(55L).name("Documents").path("/home/user/Documents").build());
        mockNodes.add(Node.builder().id(42L).name("Office").path("/home/user/Documents/Office").build());
        mockNodes.add(Node.builder().id(53L).name("Downloads").path("/home/user/Downloads").build());
        mockNodes.add(Node.builder().id(12L).name("Pictures").path("/home/user/Pictures").build());
        mockNodes.add(Node.builder().id(16L).name("Party").path("/home/user/Pictures/Party").build());
        mockNodes.add(Node.builder().id(13L).name("Vacation").path("/home/user/Pictures/Vacation").build());
        mockNodes.add(Node.builder().id(187L).name("Videos").path("/home/user/Videos").build());
        mockNodes.add(Node.builder().id(1256L).name("random").path("/home/user/Videos/random").build());
        return mockNodes;
    }
}
