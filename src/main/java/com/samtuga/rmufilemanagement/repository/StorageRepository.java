package com.samtuga.rmufilemanagement.repository;

import com.samtuga.rmufilemanagement.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByName(String fileName);
}
