package lk.ilabs.user.service;

import lk.ilabs.user.dto.UserDTO;

public interface UserService {
    public void saveUser(UserDTO user);

    UserDTO findUser(String username);
}
