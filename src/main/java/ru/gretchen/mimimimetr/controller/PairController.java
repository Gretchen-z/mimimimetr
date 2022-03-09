package ru.gretchen.mimimimetr.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gretchen.mimimimetr.model.dto.CatDto;
import ru.gretchen.mimimimetr.model.entity.PairEntity;
import ru.gretchen.mimimimetr.service.CatService;
import ru.gretchen.mimimimetr.service.PairService;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/pairs")
@Tag(name = "Pair", description = "Pair management")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Pair not found")
public class PairController {
    private final PairService pairService;
    private final CatService catService;

    @GetMapping
    @Transactional
    public String showPair(Model model, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        if (Objects.isNull(userId)) {
            return "redirect:/users/login";
        }

        List<PairEntity> userPairs = (ArrayList<PairEntity>) session.getAttribute("pairs");
        PairEntity pair = null;
        if (Objects.isNull(userPairs)) {
            List<PairEntity> pairs = pairService.getAll();
            pair = pairs.get(0);
            pairs.remove(0);
            session.setAttribute("pairs", pairs);
        } else {
            if (userPairs.isEmpty()) {
                return "redirect:/cats";
            }
            pair = userPairs.get(0);
            userPairs.remove(0);
        }
        CatDto catOne = pairService.getCatOneFromPair(pair);
        CatDto catTwo = pairService.getCatTwoFromPair(pair);
        model.addAttribute("catOne", catOne);
        model.addAttribute("catTwo", catTwo);

        return "vote";
    }

    @GetMapping("/vote")
    public String vote(@RequestParam UUID catId, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        if (Objects.isNull(userId)) {
            return "redirect:/users/login";
        }

        catService.addVoice(catId);
        return "redirect:/pairs";
    }

}
