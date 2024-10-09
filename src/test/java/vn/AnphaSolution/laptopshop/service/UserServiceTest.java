package vn.AnphaSolution.laptopshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import lombok.extern.slf4j.Slf4j;
import vn.AnphaSolution.laptopshop.dto.RequestDto.CreateUserDto;
import vn.AnphaSolution.laptopshop.dto.ResponseDto.UserResponseDto;
import vn.AnphaSolution.laptopshop.repository.UserRepository;
import vn.AnphaSolution.laptopshop.service.UserService;

@Slf4j
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private CreateUserDto createUserDto;
    private UserResponseDto userResponseDto;

    @BeforeEach
    void InitDate() {
        createUserDto = CreateUserDto.builder()
                .email("test@email.com")
                .password("test123")
                .fullName("Test")
                .address("Thu duc")
                .phone("123456")
                .build();

        userResponseDto = UserResponseDto.builder()
                .email("test@email.com")
                // .password("test123")
                .fullName("Test")
                .address("Thu duc")
                .phone("123456")
                .build();
    }
}
