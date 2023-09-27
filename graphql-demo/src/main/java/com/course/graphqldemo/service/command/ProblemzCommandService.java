package com.course.graphqldemo.service.command;

import com.course.graphqldemo.datasource.problemz.entity.Problemz;
import com.course.graphqldemo.datasource.problemz.repository.ProblemzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProblemzCommandService {

    @Autowired
    private ProblemzRepository repository;

    public Problemz createProblemz(Problemz p) {
        return repository.save(p);
    }
}
