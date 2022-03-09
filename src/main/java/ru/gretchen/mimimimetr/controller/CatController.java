package ru.gretchen.mimimimetr.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gretchen.mimimimetr.model.dto.CatDto;
import ru.gretchen.mimimimetr.service.CatService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/cats")
@Tag(name = "Cat", description = "Cat management")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Cat not found")
public class CatController {
    private final CatService catService;

    @GetMapping
    public String getCatsTop(Model model) {
        List<CatDto> catsDto = catService.getTopCats();
        model.addAttribute("cats", catsDto);
        return "top";
    }
}
