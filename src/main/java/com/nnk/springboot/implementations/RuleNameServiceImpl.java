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

/**
 * Provides an implementation for the {@link RuleNameService}.
 * Manages CRUD operations related to the {@link RuleName} entity.
 *
 */
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RuleNameServiceImpl implements RuleNameService {

    /** The repository responsible for managing RuleName data. */
    private final RuleNameRepository ruleNameRepository;

    /**
     * Saves the provided {@link RuleName} entity to the database.
     *
     * @param ruleName the ruleName entity to save.
     */
    public void saveRuleName(RuleName ruleName) {
        log.info("saveRuleName method called with : {}", ruleName);
        ruleNameRepository.save(ruleName);
    }

    /**
     * Updates an existing {@link RuleName} based on the provided updatedRuleName and its ID.
     *
     * @param updatedRuleName the updated ruleName information.
     * @param ruleNameToUpdateId the ID of the ruleName to be updated.
     */
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

    /**
     * Retrieves all the {@link RuleName} entities from the database.
     *
     * @return a list of all ruleNames.
     */
    public List<RuleName> getAllRuleNames() {
        log.info("getAllRuleNames method called");
        return ruleNameRepository.findAll();
    }

    /**
     * Retrieves a {@link RuleName} by its ID.
     *
     * @param id the ID of the ruleName to retrieve.
     * @return the corresponding ruleName.
     * @throws NoSuchElementException if the ruleName with the provided ID is not found.
     */
    public RuleName getRuleNameById(int id) {
        log.info("getRuleNameById method called with : {}", id);
        Optional<RuleName> ruleNameOptional = ruleNameRepository.findById(id);
        return ruleNameOptional.orElseThrow(NoSuchElementException::new);
    }

    /**
     * Deletes a {@link RuleName} from the database based on its ID.
     *
     * @param id the ID of the ruleName to delete.
     */
    public void deleteRuleNameById(int id) {
        log.info("deleteRuleNameById method called with : {}", id);
        ruleNameRepository.deleteById(id);
    }
}
