package com.course.graphqldemo.service.query;

import com.course.graphqldemo.datasource.problemz.entity.Userz;
import com.course.graphqldemo.datasource.problemz.repository.UserzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserzQueryService {

    @Autowired
    private UserzRepository userzRepository;

    public Optional<Userz> findUserzByAuthToken(String authToken) {
        return userzRepository.findUserByToken(authToken);
    }
}
