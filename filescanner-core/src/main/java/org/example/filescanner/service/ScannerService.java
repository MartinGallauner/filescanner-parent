package org.example.filescanner.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.filescanner.model.NodeDto;
import org.example.filescanner.persistence.Node;
import org.example.filescanner.persistence.NodeRepository;
import org.example.filescanner.persistence.NodeTypeEnum;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class ScannerService {

    private final NodeRepository nodeRepository;

    @Setter
    @Value("${scanner.folder.path}")
    private String path;


    @Transactional
    public void purgeDatabase() {
        nodeRepository.deleteAll();
    }

    @PostConstruct
    @Transactional
    public List<NodeDto> scanFolder() {
        nodeRepository.deleteAll();

        List<Node> nodes = new ArrayList<>();

        try (Stream<Path> stream = Files.walk(Paths.get(path))) {
            nodes = stream.map(this::buildNode)
                    .map(nodeRepository::save)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodes.stream()
                .map(this::setChildNodes)
                .map(this::mapNode)
                .collect(Collectors.toList());
    }

    private Node setChildNodes(Node parent) {
        Set<Node> childNodes = nodeRepository.findAllByPathIs(parent.getPath());
        for (Node child : childNodes) {
            child.setParent(parent);
        }
        nodeRepository.saveAll(childNodes);
        return parent;
    }

    private NodeDto mapNode(Node node) {
        return NodeDto.builder()
                .name(node.getName())
                .path(node.getPath())
                .scanDate(node.getScanDate())
                .modDate(node.getModDate())
                .size(node.getSize())
                .type(node.getType().name())
                .build();
    }

    private Node buildNode(Path path) {
        return Node.builder()
                .name(path.getFileName().toString())
                .path(path.toString())
                .scanDate(LocalDateTime.now())
                .type(Files.isDirectory(path) ? NodeTypeEnum.DIR : NodeTypeEnum.FILE)
                .size(!Files.isDirectory(path) ? readFileSize(path) : null)
                .build();
    }

    private Long readFileSize(Path path) {
        long size = 0L;

        try (FileChannel fileChannel = FileChannel.open(path)) {
            size = fileChannel.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }
}
