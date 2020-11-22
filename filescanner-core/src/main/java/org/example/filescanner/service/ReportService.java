package org.example.filescanner.service;

import lombok.RequiredArgsConstructor;


import org.example.filescanner.persistence.Node;
import org.example.filescanner.persistence.NodeRepository;
import org.example.filescanner.persistence.NodeTypeEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Provides functionality to generate formatted reports.
 */
@RequiredArgsConstructor
@Component
public class ReportService {

    private final NodeRepository nodeRepository;

    public List<String> getFolders() {
        Set<Node> allFolders = nodeRepository.findAllByTypeIs(NodeTypeEnum.DIRECTORY);
        return allFolders.stream()
                .map(Node::getPath)
                .collect(Collectors.toSet())
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * @param extension Optionally filter for specified extension
     * @return returns folders and subfolders with aggregated filesize sorted by size.
     */
    public List<String> getFoldersWithSizes(String extension) {
        // TODO implement
        return new ArrayList<>();
    }
}
