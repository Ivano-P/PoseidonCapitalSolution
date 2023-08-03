package com.nnk.springboot.implementations;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RuleNameServiceImpl implements RuleNameService {

    private final RuleNameRepository ruleNameRepository;

    public void saveRuleName(RuleName ruleName) {
        log.info("saveRuleName method called with : {}", ruleName);
        ruleNameRepository.save(ruleName);
    }

    public void updateRuleName(RuleName updatedRuleName, int ruleNameToUpdateId) {
        log.info("updateRuleName method called with : {}, {}", updatedRuleName, ruleNameToUpdateId);
        RuleName ruleNameToUpdate = getRuleNameById(ruleNameToUpdateId);
        ruleNameToUpdate.setName(updatedRuleName.getName());
        ruleNameToUpdate.setDescription(updatedRuleName.getDescription());
        ruleNameToUpdate.setJson(updatedRuleName.getJson());
        ruleNameToUpdate.setTemplate(updatedRuleName.getTemplate());
        ruleNameToUpdate.setSqlStr(updatedRuleName.getSqlStr());
        ruleNameToUpdate.setSqlPart(updatedRuleName.getSqlPart());
        saveRuleName(ruleNameToUpdate);
    }

    public List<RuleName> getAllRuleNames() {
        log.info("getAllRuleNames method called");
        return ruleNameRepository.findAll();
    }

    public RuleName getRuleNameById(int id) {
        log.info("getRuleNameById method called with : {}", id);
        Optional<RuleName> ruleNameOptional = ruleNameRepository.findById(id);
        return ruleNameOptional.orElseThrow(NoSuchElementException::new);
    }

    public void deleteRuleNameById(int id) {
        log.info("deleteRuleNameById method called with : {}", id);
        ruleNameRepository.deleteById(id);
    }
}
