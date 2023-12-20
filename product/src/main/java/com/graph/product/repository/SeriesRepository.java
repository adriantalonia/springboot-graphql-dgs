package com.graph.product.repository;

import com.graph.product.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeriesRepository extends JpaRepository<Serie, UUID> {
}
