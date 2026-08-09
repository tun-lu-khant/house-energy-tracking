package com.tunlukhant.service;

import com.tunlukhant.dto.UserDto;
import com.tunlukhant.entity.User;
import com.tunlukhant.payload.UserRequest;
import com.tunlukhant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto createUser(UserRequest req) {
        final User createdUser = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .surname(req.getSurname())
                .address(req.getAddress())
                .alerting(req.isAlerting())
                .energyAlertingThreshold(req.getEnergyAlertingThreshold())
                .build();
        final User savedUser = userRepository.save(createdUser);
        return toDto(savedUser);
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id).map(this::toDto)
                .orElse(null);
    }

    public void updateUser(Long id, UserRequest req) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found with id: " + id)
        );
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setSurname(req.getSurname());
        user.setAddress(req.getAddress());
        user.setAlerting(req.isAlerting());
        user.setEnergyAlertingThreshold(req.getEnergyAlertingThreshold());
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found with id: " + id)
        );
        userRepository.delete(user);
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .surname(user.getSurname())
                .address(user.getAddress())
                .alerting(user.isAlerting())
                .energyAlertingThreshold(user.getEnergyAlertingThreshold())
                .build();
    }
}
