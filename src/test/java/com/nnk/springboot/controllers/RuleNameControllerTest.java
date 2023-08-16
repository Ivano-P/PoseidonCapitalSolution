package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RuleNameControllerTest {

    @InjectMocks
    public RuleNameController ruleNameController;

    @Mock
    RuleNameService ruleNameService;

    @Mock
    UserService userService;

    @Mock
    Model model;
    @Mock
    Principal principal;

    RuleName mockRuleName;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(ruleNameController).build();

        mockRuleName = new RuleName();
        mockRuleName.setName("testName");
        mockRuleName.setDescription("testDescription");
        mockRuleName.setJson("testjson");
        mockRuleName.setTemplate("testTemplate");
        mockRuleName.setSqlStr("testSqlStr");
        mockRuleName.setSqlPart("testSqlPart");
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testHome() throws Exception {
        //Arrange
        when(principal.getName()).thenReturn("mockUsername");
        when(userService.getUserByUsername(anyString())).thenReturn(new User());

        // Act  & Assert
        mockMvc.perform(get("/ruleName/list").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"));

        verify(userService, times(1)).getUserByUsername(anyString());
        verify(ruleNameService, times(1)).getAllRuleNames();
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testAddRuleForm() throws Exception {
        //Act
        String result = ruleNameController.addRuleForm(mockRuleName, model);

        // Assert & Act
        mockMvc.perform(get("/ruleName/add")).andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testValidate() throws Exception {
        // Assert & act
        mockMvc.perform(post("/ruleName/validate")
                        .flashAttr("ruleName", mockRuleName))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/ruleName/list"));
        verify(ruleNameService, times(1)).saveRuleName(mockRuleName);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testShowUpdateForm() throws Exception {
        //Arrange
        int id = 1;
        when(ruleNameService.getRuleNameById(anyInt())).thenReturn(mockRuleName);

        // Act & Assert
        mockMvc.perform(get("/ruleName/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));

        verify(ruleNameService, times(1)).getRuleNameById(id);
    }

    @Test
    @WithMockUser(username = "mockUsername", roles = "USER")
    void testUpdateRuleName() throws Exception {
        //Arrange
        int id = 1;

        // Act & Assert
        mockMvc.perform(post("/ruleName/update/" + id)
                        .flashAttr("ruleName", mockRuleName))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/ruleName/list"));
        verify(ruleNameService, times(1)).updateRuleName(mockRuleName, 1);
    }

    @Test
    void testDeleteRuleName() throws Exception {
        // Arrange
        int id = 1;

        //Act & Assert
        mockMvc.perform(get("/ruleName/delete/" + id))
                .andExpect(status().is3xxRedirection())  // Expecting a redirect
                .andExpect(redirectedUrl("/ruleName/list"));
        verify(ruleNameService, times(1)).deleteRuleNameById(id);
    }

}
