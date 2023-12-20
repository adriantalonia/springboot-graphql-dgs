package com.graph.product.repository;

import com.graph.product.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModelRepository extends JpaRepository<Model, UUID> {
}
