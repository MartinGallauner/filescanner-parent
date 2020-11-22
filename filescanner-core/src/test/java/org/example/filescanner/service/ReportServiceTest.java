package org.example.filescanner.service;

import org.example.filescanner.persistence.Node;
import org.example.filescanner.persistence.NodeRepository;
import org.example.filescanner.persistence.NodeTypeEnum;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @InjectMocks
    ReportService reportService;

    @Mock
    NodeRepository nodeRepository;

    void getFolders() {
        // arrange
        when(nodeRepository.findAllByTypeIs(any(NodeTypeEnum.class))).thenReturn(createTestNodes());


        // act
        List<String> result = reportService.getFolders();

        // assert
        assertEquals(4, result.size());
    }

    private Set<Node> createTestNodes() {
        Set<Node> mockNodes = new HashSet<>();
        mockNodes.add(Node.builder().id(55L).name("Documents").path("/home/user").build());
        mockNodes.add(Node.builder().id(42L).name("Office").path("/home/user/Documents").build());
        mockNodes.add(Node.builder().id(53L).name("Downloads").path("/home/user").build());
        mockNodes.add(Node.builder().id(12L).name("Pictures").path("/home/user/Pictures").build());
        mockNodes.add(Node.builder().id(13L).name("Vacation").path("/home/user/Pictures/Vacation").build());
        mockNodes.add(Node.builder().id(16L).name("Party").path("/home/user/Pictures/Party").build());
        mockNodes.add(Node.builder().id(187L).name("Videos").path("/home/user/Videos").build());
        mockNodes.add(Node.builder().id(1256L).name("random").path("/home/user/Videos").build());
        return mockNodes;
    }

}
