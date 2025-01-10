package com.metacoding.restserver.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metacoding.restserver._core.util.JwtUtil;
import com.metacoding.restserver.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtUtil jwtUtil;

    private String jwt;
    private ObjectMapper om = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        System.out.println("mma : setUp");
        User user = User.builder()
                .id(1)
                .username("ssar")
                .build();
        jwt = jwtUtil.create(user);
    }

    @Test
    public void findById_test(){
        System.out.println("mma : findById");
    }

    @Test
    public void findAll_test() throws Exception {
        System.out.println("mma : findAll");
        // given


        // when
        ResultActions actions = mvc.perform(get("/api/post").header("Authorization", jwt));

        // eye
        String resBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("mma: " + resBody);


        // then
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.message").value("성공"));
        actions.andExpect(jsonPath("$.data[0].id").value(5));
        actions.andExpect(jsonPath("$.data[0].title").value("title 5"));
        actions.andExpect(jsonPath("$.data[0].content").value("content 5"));
        actions.andExpect(jsonPath("$.data[0].createdAt").isNotEmpty());
        actions.andExpect(jsonPath("$.data[0].userId").value(2));
        actions.andExpect(jsonPath("$.data[0].username").value("cos"));
    }
}