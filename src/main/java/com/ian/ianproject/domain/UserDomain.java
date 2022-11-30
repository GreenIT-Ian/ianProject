package com.ian.ianproject.domain;

import com.ian.ianproject.dto.UserDto;
import com.ian.ianproject.entity.User;
import com.ian.ianproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDomain {

    private final UserRepository userRepository;

    @Transactional
    public boolean insertUser(UserDto userDto) throws Exception {
        boolean result = true;
        User user = User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .cellPhone(userDto.getCellPhone())
                .statusCode("N")
                .isActivated(true)
                .isDeleted(false)
                .password(userDto.getPassword())
                .build();
        try {
            userRepository.save(user);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public Optional<User> getUser(String userId) {
        return userRepository.findById(userId);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean updateUser(String userId, UserDto user) {
        Optional<User> result = this.getUser(userId);
        if (result.isPresent()) {
            User entity = result.get();
            entity.updateCellPhone(user.getCellPhone());
            userRepository.flush();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUser(String userId) {
        Optional<User> result = this.getUser(userId);
        if (result.isPresent()) {
            User entity = result.get();
            userRepository.delete(entity);
            return true;
        } else {
            return false;
        }
    }

    public boolean disable(String userId) {
        Optional<User> result = this.getUser(userId);
        if (result.isPresent()) {
            User entity = result.get();
            entity.deactivate();
            entity.delete();
            userRepository.flush();
            return true;
        } else {
            return false;
        }
    }
}
