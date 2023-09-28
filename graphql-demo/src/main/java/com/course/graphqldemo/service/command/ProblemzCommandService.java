package com.course.graphqldemo.service.command;

import com.course.graphqldemo.datasource.problemz.entity.Problemz;
import com.course.graphqldemo.datasource.problemz.repository.ProblemzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class ProblemzCommandService {

    private Sinks.Many<Problemz> problemzSink = Sinks.many().multicast().onBackpressureBuffer();

    @Autowired
    private ProblemzRepository repository;

    public Problemz createProblemz(Problemz p) {

        var created = repository.save(p);
        problemzSink.tryEmitNext(created);

        return created;
    }

    public Flux<Problemz> problemzFlux() {
        return problemzSink.asFlux();
    }
}
