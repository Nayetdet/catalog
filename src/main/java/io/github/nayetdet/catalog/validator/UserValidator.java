package io.github.nayetdet.catalog.validator;

import io.github.nayetdet.catalog.exception.DuplicateEntryException;
import io.github.nayetdet.catalog.model.User;
import io.github.nayetdet.catalog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserValidator {

    private final UserRepository userRepository;

    public void validate(User user) {
        if (isUserDuplicated(user)) {
            throw new DuplicateEntryException("A user with the same name already exists");
        }
    }

    private boolean isUserDuplicated(User user) {
        return userRepository.existsByUsername(user.getUsername());
    }

}
