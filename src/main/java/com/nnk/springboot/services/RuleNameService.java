package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

public interface RuleNameService {

    void saveRuleName(RuleName ruleName);
    void updateRuleName(RuleName updatedRuleName, int ruleNameToUpdateId);
    List<RuleName> getAllRuleNames();
    RuleName getRuleNameById(int id);
    void deleteRuleNameById(int id);

}
