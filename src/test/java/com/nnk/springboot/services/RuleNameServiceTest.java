package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.implementations.RuleNameServiceImpl;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RuleNameServiceTest {

    @InjectMocks
    public RuleNameServiceImpl ruleNameService;

    @Mock
    RuleNameRepository ruleNameRepository;

    RuleName mockRuleName;

    @BeforeEach
    void setUp() {
        mockRuleName = new RuleName();
        mockRuleName.setName("testName");
        mockRuleName.setDescription("testDescription");
        mockRuleName.setJson("testjson");
        mockRuleName.setTemplate("testTemplate");
        mockRuleName.setSqlStr("testSqlStr");
        mockRuleName.setSqlPart("testSqlPart");
    }

    @Test
    void testSaveRuleName() {
        //Arrange

        //Act
        ruleNameService.saveRuleName(mockRuleName);

        //Assert
        verify(ruleNameRepository, times(1)).save(mockRuleName);
    }

    @Test
    void testUpdateRuleName() {
        //Arrange
        RuleName updatedRuleName = new RuleName();
        updatedRuleName.setName("updatedName");
        updatedRuleName.setDescription("updatedDescription");
        updatedRuleName.setJson("updatedJson");
        updatedRuleName.setTemplate("updatedTemplate");
        updatedRuleName.setSqlStr("updatedSqlStr");
        updatedRuleName.setSqlPart("updatedSqlPart");

        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(mockRuleName));

        //Act
        ruleNameService.updateRuleName(updatedRuleName, 1);

        //Assert
        verify(ruleNameRepository, times(1)).findById(anyInt());
        verify(ruleNameRepository, times(1)).save(mockRuleName);

        assertThat(mockRuleName.getName()).isEqualTo(updatedRuleName.getName());
        assertThat(mockRuleName.getDescription()).isEqualTo(updatedRuleName.getDescription());
        assertThat(mockRuleName.getJson()).isEqualTo(updatedRuleName.getJson());
        assertThat(mockRuleName.getTemplate()).isEqualTo(updatedRuleName.getTemplate());
        assertThat(mockRuleName.getSqlStr()).isEqualTo(updatedRuleName.getSqlStr());
        assertThat(mockRuleName.getSqlPart()).isEqualTo(updatedRuleName.getSqlPart());
    }

    @Test
    void testGetAllRuleNames() {
        //Arrange
        when(ruleNameRepository.findAll()).thenReturn(Collections.singletonList(mockRuleName));

        //Act
        List<RuleName> result = ruleNameService.getAllRuleNames();

        //Assert
        verify(ruleNameRepository, times(1)).findAll();
        assertThat(result).contains(mockRuleName);
    }

    @Test
    void testGetRuleNameById() {
        //Arrange
        when(ruleNameRepository.findById(anyInt())).thenReturn(Optional.of(mockRuleName));

        //Act
        RuleName result = ruleNameService.getRuleNameById(1);

        //Assert
        verify(ruleNameRepository, times(1)).findById(anyInt());
        assertThat(result).isEqualTo(mockRuleName);
    }

    @Test
    void testDeleteRuleNameById() {
        //Arrange

        //Act
        ruleNameService.deleteRuleNameById(1);

        //Assert
        verify(ruleNameRepository, times(1)).deleteById(anyInt());
    }
}


