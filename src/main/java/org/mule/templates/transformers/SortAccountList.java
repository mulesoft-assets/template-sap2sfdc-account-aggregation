/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.transformers;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

import com.google.common.collect.Lists;

/**
 * This transformer will take to list as input and create a third one that will be the merge of the previous two. The identity of an element of the list is
 * defined by its name.
 * 
 * @author cesar.garcia
 */
public class SortAccountList extends AbstractMessageTransformer {

	public static Comparator<Map<String, String>> recordComparator = new Comparator<Map<String, String>>() {

		public int compare(Map<String, String> account1, Map<String, String> account2) {

			String key1 = buildKey(account1);
			String key2 = buildKey(account2);

			return key1.compareTo(key2);
		}

		private String buildKey(Map<String, String> account) {
			StringBuilder key = new StringBuilder();

			if (StringUtils.isNotBlank(account.get("IDInA")) && StringUtils.isNotBlank(account.get("IDInB"))) {
				key.append("~~");
				key.append(account.get("IDInA"));
				key.append(account.get("IDInB"));
				key.append(account.get("Name"));
			}

			if (StringUtils.isNotBlank(account.get("IDInA")) && StringUtils.isBlank(account.get("IDInB"))) {
				key.append(account.get("IDInA"));
				key.append("~");
				key.append(account.get("Name"));
			}

			if (StringUtils.isBlank(account.get("IDInA")) && StringUtils.isNotBlank(account.get("IDInB"))) {
				key.append("~");
				key.append(account.get("IDInB"));
				key.append(account.get("Name"));
			}

			return key.toString();
		}

	};

	@SuppressWarnings("unchecked")
	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException {

		List<Map<String, String>> sortedAccountsList = Lists.newArrayList((Iterator<Map<String, String>>) message.getPayload());

		Collections.sort(sortedAccountsList, recordComparator);

		return sortedAccountsList;

	}

}
