package com.graph.product.repository;

import com.graph.product.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, UUID> {
}
