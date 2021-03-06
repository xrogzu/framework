/*
 * Copyright 2013 the original author or authors.
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
package leap.core.junit;

import leap.core.AppConfig;
import leap.core.AppContext;
import leap.core.BeanFactory;
import leap.junit.concurrent.ConcurrentRule;

import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AppTestRunner.class)
public abstract class AppTestBase extends leap.junit.TestBase {
	
	protected final static AppContext  context;
	protected final static AppConfig   config;
	protected final static BeanFactory factory;
	
	static {
		if(AppContext.tryGetCurrent() == null){
			AppContext.initStandalone();
		}
		
		context = AppContext.current();
		config  = context.getConfig();
		factory = context.getBeanFactory();
	}
	
	@Rule
	public ConcurrentRule concurrentRule = new ConcurrentRule(true);

}