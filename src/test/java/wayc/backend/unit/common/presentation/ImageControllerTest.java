package wayc.backend.unit.common.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import wayc.backend.member.application.dto.request.CreateConsumerRequestDto;
import wayc.backend.unit.ControllerTest;
import wayc.backend.verification.presentation.dto.request.PostSendEmailRequestDto;
import wayc.backend.verification.presentation.dto.request.PostVerifyEmailRequestDto;

import java.io.InputStream;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

public class ImageControllerTest extends ControllerTest {

    @Test
    @DisplayName("이미지 업로드 성공 테스트")
    void upload_imagel() throws Exception {

        String url = "https://bucket.s3.ap-northeast-5.amazonaws.com/8d78cf624c99-%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%851%85%AE%206.23.05.png";

        //given
        MockMultipartFile images = new MockMultipartFile(
                "images", //requestParam
                "hello.txt", //original file name
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        given(imageService.upload(Mockito.any(InputStream.class), Mockito.any(String.class), Mockito.any(Long.class)))
                .willReturn(url);


        //when
        mockMvc.perform(RestDocumentationRequestBuilders.multipart("/images")
                        .file(images)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("upload_image",
                        getDocumentRequest(),
                        getDocumentResponse(),
                                responseFields(
                                        fieldWithPath("imageUrl").type(STRING).description("업로드한 이미지의 URL.")
                                )
                        ));
    }


    //https://www.baeldung.com/spring-multipart-post-request-test
}
