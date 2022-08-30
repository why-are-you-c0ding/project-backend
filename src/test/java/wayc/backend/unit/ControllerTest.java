package wayc.backend.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import wayc.backend.factory.member.dto.CreateMemberResponseDtoFactory;
import wayc.backend.factory.member.dto.PostMemberRequestDtoFactory;
import wayc.backend.member.business.MemberService;
import wayc.backend.member.business.dto.request.CreateMemberRequestDto;
import wayc.backend.member.business.dto.response.CreateMemberResponseDto;
import wayc.backend.member.presentation.MemberController;
import wayc.backend.member.presentation.dto.request.PostMemberRequestDto;
import wayc.backend.unit.member.presentation.MemberControllerTest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureRestDocs
public class ControllerTest extends MemberControllerTest {

    @Test
    @DisplayName("멤버 생성 성공 테스트")
    void createMember() throws Exception {
        //given
        PostMemberRequestDto req = PostMemberRequestDtoFactory.createSuccessCaseDto();
        CreateMemberResponseDto res = CreateMemberResponseDtoFactory.createSuccessCaseDto();
        given(memberService.createMember(Mockito.any(CreateMemberRequestDto.class))).willReturn(res);

        String value = mapper.writeValueAsString(req);

        //when
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(value)
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.nickName").value("nickName"))
                .andDo(print());
    }

}
