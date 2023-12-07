package wayc.backend.acceptance.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentRequest;
import static wayc.backend.docs.SpringRestDocsUtils.getDocumentResponse;

@AutoConfigureRestDocs(uriScheme = "https", uriHost = "waycabvav.shop", uriPort = 443)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AcceptanceLogOutTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void success_logout_test() throws Exception {
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/logout")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andDo(document("logout",
                        getDocumentRequest(),
                        getDocumentResponse()
                ));
    }
}
