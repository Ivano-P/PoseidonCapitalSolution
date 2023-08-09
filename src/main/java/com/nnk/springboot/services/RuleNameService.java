package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

/**
 * Service interface for operations related to RuleName.
 */
public interface RuleNameService {

    /**
     * Persists a given RuleName entity.
     *
     * @param ruleName the rule name to save
     */
    void saveRuleName(RuleName ruleName);

    /**
     * Updates an existing RuleName entity identified by its ID.
     *
     * @param updatedRuleName the updated rule name
     * @param ruleNameToUpdateId the ID of the rule name to update
     */
    void updateRuleName(RuleName updatedRuleName, int ruleNameToUpdateId);

    /**
     * Retrieves all RuleName entities.
     *
     * @return a list of all rule names
     */
    List<RuleName> getAllRuleNames();

    /**
     * Retrieves a RuleName entity by its ID.
     *
     * @param id the ID of the rule name to retrieve
     * @return the rule name corresponding to the given ID
     */
    RuleName getRuleNameById(int id);

    /**
     * Deletes a RuleName entity identified by its ID.
     *
     * @param id the ID of the rule name to delete
     */
    void deleteRuleNameById(int id);

}
