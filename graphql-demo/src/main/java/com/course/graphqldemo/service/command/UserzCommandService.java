package com.course.graphqldemo.service.command;

import com.course.graphqldemo.datasource.problemz.entity.Userz;
import com.course.graphqldemo.datasource.problemz.entity.UserzToken;
import com.course.graphqldemo.datasource.problemz.repository.UserzRepository;
import com.course.graphqldemo.datasource.problemz.repository.UserzTokenRepository;
import com.course.graphqldemo.util.HashUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserzCommandService {

    @Autowired
    private UserzRepository userzRepository;

    @Autowired
    private UserzTokenRepository userzTokenRepository;

    public UserzToken login(String username, String password) {
        var userzQueryResult = userzRepository.findByUsernameIgnoreCase(username);//.orElseThrow(() -> new IllegalArgumentException("Invalid credential"));

        if (userzQueryResult.isEmpty() || !HashUtil.isBcryptMatch(password, userzQueryResult.get().getHashedPassword())) {
            throw new IllegalArgumentException("Invalid credential");
        }

        var randomAuthToken = RandomStringUtils.randomAlphanumeric(40);

        return refreshToken(userzQueryResult.get().getId(), randomAuthToken);
    }

    private UserzToken refreshToken(UUID userId, String authToken) {
        var userzToken = new UserzToken();
        userzToken.setUserId(userId);
        userzToken.setAuthToken(authToken);

        var now = LocalDateTime.now();
        userzToken.setCreationTimestamp(now);
        userzToken.setExpiryTimestamp(now.plusHours(2));

        return userzTokenRepository.save(userzToken);
    }

    public Userz createUserz(Userz userz) {
        return userzRepository.save(userz);
    }

    /*public Optional<Userz> activateUser(String username, boolean isActive) {
        userzRepository.activateUser(username, isActive);

        return userzRepository.findByUsernameIgnoreCase(username);
    }*/
}
