package com.course.graphqldemo.service.command;

import com.course.graphqldemo.datasource.problemz.entity.Solutionz;
import com.course.graphqldemo.datasource.problemz.repository.SolutionzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SolutionzCommandService {

    @Autowired
    private SolutionzRepository repository;

    public Solutionz createSolutionz(Solutionz s) {
        return  repository.save(s);
    }

    public Optional<Solutionz> voteBad(UUID solutionId) {
        repository.addVoteBadCount(solutionId);
        var updated = repository.findById(solutionId);

        /*if (updated.isPresent()) {
            solutionzSink.tryEmitNext(updated.get());
        }*/

        return updated;
    }

    public Optional<Solutionz> voteGood(UUID solutionId) {
        repository.addVoteGoodCount(solutionId);
        var updated = repository.findById(solutionId);

        /*if (updated.isPresent()) {
            solutionzSink.tryEmitNext(updated.get());
        }*/

        return updated;
    }
}
