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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RuleNameControllerTest {

    @InjectMocks
    public RuleNameController ruleNameController;

    @Mock
    RuleNameService ruleNameService;

    @Mock
    UserService userService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;
    @Mock
    Principal principal;

    RuleName mockRuleName;

    @BeforeEach
    void setUp(){
        mockRuleName = new RuleName();
        mockRuleName.setName("testName");
        mockRuleName.setDescription("testDescription");
        mockRuleName.setJson("testjson");
        mockRuleName.setTemplate("testTemplate");
        mockRuleName.setSqlStr("testSqlStr");
        mockRuleName.setSqlPart("testSqlPart");
    }

    @Test
    void testHome() {
        //Arrange
        when(ruleNameService.getAllRuleNames()).thenReturn(Collections.singletonList(mockRuleName));
        when(principal.getName()).thenReturn("username");
        when(userService.getUserByUsername(anyString())).thenReturn(new User());

        //Act
        String result = ruleNameController.home(model, principal);

        //Assert
        verify(model, times(1)).addAttribute("ruleNames", Collections.singletonList(mockRuleName));
        verify(model, times(1)).addAttribute(eq("currentUser"), any());
        assertThat(result).isEqualTo("ruleName/list");
    }

    @Test
    void testAddRuleForm() {
        //Arrange

        //Act
        String result = ruleNameController.addRuleForm(mockRuleName, model);

        //Assert
        verify(model, times(1)).addAttribute("ruleName", new RuleName());
        assertThat(result).isEqualTo("ruleName/add");
    }

    @Test
    void testValidate() {
        //Arrange
        when(bindingResult.hasErrors()).thenReturn(false);

        //Act
        String result = ruleNameController.validate(mockRuleName, bindingResult);

        //Assert
        verify(ruleNameService, times(1)).saveRuleName(mockRuleName);
        assertThat(result).isEqualTo("redirect:/ruleName/list");
    }

    @Test
    void testShowUpdateForm() {
        //Arrange
        when(ruleNameService.getRuleNameById(anyInt())).thenReturn(mockRuleName);

        //Act
        String result = ruleNameController.showUpdateForm(1, model);

        //Assert
        verify(model, times(1)).addAttribute("ruleName", mockRuleName);
        assertThat(result).isEqualTo("ruleName/update");
    }

    @Test
    void testUpdateRuleName() {
        //Arrange
        when(bindingResult.hasErrors()).thenReturn(false);

        //Act
        String result = ruleNameController.updateRuleName(1, mockRuleName, bindingResult, model);

        //Assert
        verify(ruleNameService, times(1)).updateRuleName(mockRuleName, 1);
        assertThat(result).isEqualTo("redirect:/ruleName/list");
    }

    @Test
    void testDeleteRuleName() {
        //Arrange

        //Act
        String result = ruleNameController.deleteRuleName(1);

        //Assert
        verify(ruleNameService, times(1)).deleteRuleNameById(1);
        assertThat(result).isEqualTo("redirect:/ruleName/list");
    }

}
