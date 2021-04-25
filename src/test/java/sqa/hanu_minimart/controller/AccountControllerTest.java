package sqa.hanu_minimart.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sqa.hanu_minimart.repository.UserRepository;
import sqa.hanu_minimart.service.AccountService;

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
public class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testDeleteAccount() throws Exception {
        doNothing().when(this.accountService).deleteUser((Long) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/account/delete");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllAccount() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/account/getAll");
        MockHttpServletRequestBuilder paramResult = getResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("size", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("sort",
                String.valueOf(new String[]{"foo", "foo", "foo"}));
        MockMvcBuilders.standaloneSetup(this.accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

