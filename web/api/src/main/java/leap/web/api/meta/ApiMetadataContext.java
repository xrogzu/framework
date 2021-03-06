/*
 * Copyright 2015 the original author or authors.
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
package leap.web.api.meta;

import leap.core.meta.MTypeContainer;
import leap.web.api.config.ApiConfig;
import leap.web.api.meta.desc.ApiDescContainer;

/**
 * The context used by {@link ApiMetadataProcessor}.
 */
public interface ApiMetadataContext {

	/**
	 * Returns the configuration of api.
     */
	ApiConfig getConfig();

	/**
	 * Returns the {@link MTypeContainer}.
     */
	MTypeContainer getMTypeContainer();

	/**
	 * Returns the {@link ApiDescContainer}
	 * @return
	 */
	ApiDescContainer getDescContainer();
}
