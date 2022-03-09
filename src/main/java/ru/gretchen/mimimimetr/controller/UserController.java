package ru.gretchen.mimimimetr.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gretchen.mimimimetr.model.dto.UserCreateDto;
import ru.gretchen.mimimimetr.service.PairService;
import ru.gretchen.mimimimetr.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@Tag(name = "User", description = "User management")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "User not found")
public class UserController {
    private final UserService userService;
    private final PairService pairService;

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("user", new UserCreateDto());
        return "login";
    }

    @PostMapping("/login")
    public String submitLogin(@ModelAttribute("user") UserCreateDto createDto, HttpSession session) {
        UUID userId = userService.create(createDto).getId();
        session.setAttribute("userId", userId);
        return "redirect:/pairs";
    }
}
