package com.course.graphqldemo.datasource.problemz.repository;

import com.course.graphqldemo.datasource.problemz.entity.UserzToken;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserzTokenRepository extends CrudRepository<UserzToken, UUID> {
}
