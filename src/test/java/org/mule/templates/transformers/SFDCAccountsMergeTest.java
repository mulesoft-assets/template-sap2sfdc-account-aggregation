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

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.templates.transformers.SFDCAccountMerge;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class SFDCAccountsMergeTest {
	private static final String QUERY_COMPANY_A = "accountsFromOrgA";
	private static final String QUERY_COMPANY_B = "accountsFromOrgB";

	@Mock
	private MuleContext muleContext;

	@Test
	public void testMerge() throws TransformerException {
		List<Map<String, String>> accountsA = createaccountLists("A", 0, 1);
		List<Map<String, String>> accountsB = createaccountLists("B", 1, 2);

		MuleMessage message = new DefaultMuleMessage(null, muleContext);
		message.setInvocationProperty(QUERY_COMPANY_A, accountsA.iterator());
		message.setInvocationProperty(QUERY_COMPANY_B, accountsB.iterator());

		SFDCAccountMerge transformer = new SFDCAccountMerge();
		List<Map<String, String>> mergedList = (List<Map<String, String>>) transformer.transform(message, "UTF-8");

		System.out.println(mergedList);
		Assert.assertEquals("The merged list obtained is not as expected", createExpectedList(), mergedList);

	}
	

	private List<Map<String, String>> createExpectedList() {
		Map<String, String> account0 = new HashMap<String, String>();
		account0.put("IDInA", "0");
		account0.put("IDInB", "");
		account0.put("Name", "SomeName_0");
		account0.put("StreetInA", "street_0_A");
		account0.put("StreetInB", "");

		Map<String, String> account1 = new HashMap<String, String>();
		account1.put("IDInA", "1");
		account1.put("IDInB", "1");
		account1.put("Name", "SomeName_1");
		account1.put("StreetInA", "street_1_A");
		account1.put("StreetInB", "street_1_B");

		Map<String, String> account2 = new HashMap<String, String>();
		account2.put("IDInA", "");
		account2.put("IDInB", "2");
		account2.put("Name", "SomeName_2");
		account2.put("StreetInA", "");
		account2.put("StreetInB", "street_2_B");

		List<Map<String, String>> accountList = new ArrayList<Map<String, String>>();
		accountList.add(account0);
		accountList.add(account1);
		accountList.add(account2);

		return accountList;

	}

	private List<Map<String, String>> createaccountLists(String orgId, int start, int end) {
		List<Map<String, String>> accountList = new ArrayList<Map<String, String>>();
		for (int i = start; i <= end; i++) {
			accountList.add(createaccount(orgId, i));
		}
		return accountList;
	}

	private Map<String, String> createaccount(String orgId, int sequence) {
		Map<String, String> account = new HashMap<String, String>();

		account.put("Id", new Integer(sequence).toString());
		account.put("BillingStreet", "street_" + sequence + "_" + orgId);
		account.put("Name", "SomeName_" + sequence);

		return account;
	}
}
