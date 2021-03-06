/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package leap.web.api.meta.model;

import java.util.HashMap;
import java.util.Map;

import leap.lang.accessor.AttributeAccessor;
import leap.lang.meta.MNamedWithDescBuilder;

public abstract class MApiNamedWithDescBuilder<T extends MApiObject> extends MNamedWithDescBuilder<T> implements AttributeAccessor {

	protected Map<String, Object> attrs;
	
	public MApiNamedWithDescBuilder() {
        super();
    }

	public Map<String, Object> getAttributes() {
		if(null == attrs){
			attrs = new HashMap<String, Object>();
		}
		return attrs;
	}
	
	public void setAttributes(Map<String, Object> m) {
		if(m != null) {
			getAttributes().putAll(m);
		}
	}

	@Override
    public Object getAttribute(String name) {
        return getAttributes().get(name);
    }

	@Override
    public void setAttribute(String name, Object value) {
		getAttributes().put(name, value);
    }

	@Override
    public void removeAttribute(String name) {
		getAttributes().remove(name);
    }
	
}
