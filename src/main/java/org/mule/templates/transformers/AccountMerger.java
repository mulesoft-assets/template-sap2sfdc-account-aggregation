/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.transformers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This transformer will take to list as input and create a third one that will be the merge of the previous two. The identity of an element of the list is
 * defined by its name.
 * 
 * @author cesar.garcia
 */
public class AccountMerger {	
	
	private static final Logger LOGGER = LogManager.getLogger(AccountMerger.class);

	/**
	 * The method will merge the accounts from the two lists creating a new one.
	 * 
	 * @param accountsFromOrgA
	 *            accounts from organization A
	 * @param accountsFromOrgB
	 *            accounts from organization B
	 * @return a list with the merged content of the to input lists
	 */
	List<Map<String, String>> mergeList(List<Map<String, String>> accountsFromOrgA, List<Map<String, String>> accountsFromOrgB) {
		List<Map<String, String>> mergedAccountsList = new ArrayList<Map<String, String>>();

		// Put all accounts from A in the merged mergedAccountsList
		for (Map<String, String> accountFromA : accountsFromOrgA) {
			Map<String, String> mergedAccount = createMergedAccount(accountFromA);
			mergedAccount.put("IDInA", accountFromA.get("Id"));
			mergedAccount.put("StreetInA", accountFromA.get("BillingStreet"));
			mergedAccountsList.add(mergedAccount);
		}

		// Add the new accounts from B and update the exiting ones
		for (Map<String, String> accountFromB : accountsFromOrgB) {
			Map<String, String> accountFromA = findAccountInList(accountFromB.get("Name"), mergedAccountsList);
			if (accountFromA != null) {
				accountFromA.put("IDInB", accountFromB.get("Id"));
				accountFromA.put("StreetInB", accountFromB.get("BillingStreet"));
			} else {
				Map<String, String> mergedAccount = createMergedAccount(accountFromB);
				mergedAccount.put("IDInB", accountFromB.get("Id"));
				mergedAccount.put("StreetInB", accountFromB.get("BillingStreet"));
				mergedAccountsList.add(mergedAccount);
			}
		}
		return mergedAccountsList;
	}

	/**
	 * Creates new merged account, which has to be filled. 
	 * @param the account, which has to be merged
	 * @return new merged account
	 */
	private Map<String, String> createMergedAccount(Map<String, String> account) {
		Map<String, String> mergedAccount = new HashMap<String, String>();
		mergedAccount.put("Name", account.get("Name"));
		mergedAccount.put("IDInA", "");
		mergedAccount.put("StreetInA", "");
		mergedAccount.put("IDInB", "");
		mergedAccount.put("StreetInB", "");
		return mergedAccount;
	}

	/**
	 * Finds a concrete account by its name.
	 * @param accountName the account name
	 * @param orgList the list to be searched 
	 * @return found account or null
	 */
	private Map<String, String> findAccountInList(String accountName, List<Map<String, String>> orgList) {
		LOGGER.info("Looking for: "+ accountName);
		for (Map<String, String> account : orgList) {
			if (account.get("Name").equals(accountName)) {
				return account;
			}
		}
		return null;
	}
}
