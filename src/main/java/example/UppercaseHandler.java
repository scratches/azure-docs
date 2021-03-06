/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.CosmosDBOutput;
import com.microsoft.azure.functions.annotation.EventHubTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

public class UppercaseHandler extends AzureSpringBootRequestHandler<Foo, Bar> {
	@FunctionName("uppercase")
	public void update( //
			@EventHubTrigger(name = "data", eventHubName = "events", //
					connection = "TRANSACTIONS_EVENT_HUB_CONNECTION_STRING") //
			Foo data, //
			@CosmosDBOutput(name = "document", databaseName = "inventory", collectionName = "messages", //
					connectionStringSetting = "PRODUCT_ITEMS_DOCUMENTDB_CONNECTION_STRING", createIfNotExists = true) //
			OutputBinding<Bar> document, //
			final ExecutionContext context) {
		handleOutput(data, document, context);
	}
}