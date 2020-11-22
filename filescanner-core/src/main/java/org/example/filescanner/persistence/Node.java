package org.example.filescanner.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "node")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Node parent;
    private String path;
    private String name;
    private Long size;

    @UpdateTimestamp
    private LocalDateTime modDate;

    @CreationTimestamp
    private LocalDateTime scanDate;

    @Enumerated(EnumType.STRING)
    private NodeTypeEnum type;
}
