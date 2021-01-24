package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;

import static ru.itis.javalab.dto.UserDto.from;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        return  from(usersRepository.findAll(page, size));
    }

    @Override
    public void addUser(UserDto userDto) {
        usersRepository.save(User.builder()
        .age(null)
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
                .build());
    }

    @Override
    public void deleteUserById(Long id) {
        usersRepository.findOneById(id).ifPresent(
                user -> {
                    user.setIsDeleted(true);
                    usersRepository.update(user);
                }
        );
    }
}
