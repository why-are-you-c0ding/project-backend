package wayc.backend.common.image;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/images")
@RestController
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResponseDto> uploadImage(@RequestParam MultipartFile images) throws IOException {
        String imageUrl = imageService.upload(images.getInputStream(), images.getOriginalFilename(), images.getSize());
        return ResponseEntity.ok(new ImageResponseDto(imageUrl));
    }
}
