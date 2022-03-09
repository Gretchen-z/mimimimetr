package ru.gretchen.mimimimetr.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gretchen.mimimimetr.model.entity.CatEntity;
import ru.gretchen.mimimimetr.service.CatService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/photos")
@Tag(name = "Photo", description = "Photo management")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Photo not found")
public class PhotoController {
    private final CatService catService;

    @GetMapping
    public void getPhoto(HttpServletResponse response, @RequestParam(name = "catId") UUID id) throws IOException {
        CatEntity cat = catService.get(id);

        byte[] photo = cat.getPhoto();
        if (photo != null) {
            ServletOutputStream os = response.getOutputStream();
            response.setContentType("image/png");
            os.write(photo);
            os.close();
        }
    }
}
