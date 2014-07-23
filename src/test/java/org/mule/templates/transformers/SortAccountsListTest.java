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
import org.mule.templates.transformers.SortAccountList;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class SortAccountsListTest {
	@Mock
	private MuleContext muleContext;

	@Test
	public void testSort() throws TransformerException {

		MuleMessage message = new DefaultMuleMessage(createOriginalList(), muleContext);

		SortAccountList transformer = new SortAccountList();
		List<Map<String, String>> sortedList = (List<Map<String, String>>) transformer.transform(message, "UTF-8");

		System.out.println(sortedList);
		Assert.assertEquals("The merged list obtained is not as expected", createExpectedList(), sortedList);

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
		accountList.add(account2);
		accountList.add(account1);

		return accountList;

	}

	private List<Map<String, String>> createOriginalList() {
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

}
