package com.example.prog4.service;

import com.example.prog4.model.exception.ForbiddenException;
import com.example.prog4.repository.SessionRepository;
import com.example.prog4.repository.UserRepository;
import com.example.prog4.repository.entity.Session;
import com.example.prog4.repository.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private static final Integer AUTHENTICATION_DURATION = 3_600;

    public Session authenticateUser(User user, String sessionId) {
        Optional<User> currentUser = userRepository.findOneByUsername(user.getUsername());

        if (currentUser.isEmpty()) {
            throw new ForbiddenException("Please verify your credentials.");
        }
        if (!currentUser.get().getPassword().equals(user.getPassword())) {
            throw new ForbiddenException("Please verify your credentials.");
        }
        return sessionRepository.save(Session.builder().sessionId(sessionId).user(currentUser.get()).timeout(LocalDateTime.now().plusSeconds(AUTHENTICATION_DURATION)).build());
    }

    public void verifySession(String sessionId){
        Optional<Session> session = sessionRepository.findOneBySessionId(sessionId);
        if(session.isPresent() && session.get().getTimeout().isAfter(LocalDateTime.now())){
            throw new ForbiddenException("Session expired.");
        }
    }

}
