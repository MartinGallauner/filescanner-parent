package org.example.filescanner.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {

    Set<Node> findAllByTypeIs(NodeTypeEnum type);

    Set<Node> findAllByPathIs(String parentPath);
}
