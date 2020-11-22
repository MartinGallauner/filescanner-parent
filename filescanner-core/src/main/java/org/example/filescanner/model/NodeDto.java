package org.example.filescanner.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NodeDto {

    private String name;
    private String path;
    private Long size;
    private LocalDateTime modDate;
    private LocalDateTime scanDate;
    private String type;
}
