package com.ian.ianproject.service;

import com.ian.ianproject.domain.UserDomain;
import com.ian.ianproject.dto.UserDto;
import com.ian.ianproject.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomain userDomain;

    @Autowired
    ModelMapper modelMapper;

    public UserDto getUserById(String userId) {
        Optional<User> result = userDomain.getUser(userId);
        if (result.isPresent()) {
            return modelMapper.map(result.get(), UserDto.class);
        } else {
            return null;
        }
    }

    public boolean insertUser(UserDto userDto) throws Exception {
        return userDomain.insertUser(userDto);
    }

    public boolean updateUser(String userId, UserDto user) {
        return userDomain.updateUser(userId, user);
    }

    public boolean deleteUser(String userId) {
        return userDomain.deleteUser(userId);
    }

    public boolean disable(String userId) {
        return userDomain.disable(userId);
    }

    public List<UserDto> getUsers() {
        List<User> users = userDomain.getUsers();
        return users.stream()
                .map(m -> modelMapper.map(m, UserDto.class))
                .collect(Collectors.toList());
    }
}
