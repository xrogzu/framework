/*
 * Copyright 2014 the original author or authors.
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
package leap.orm.annotation.domain;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import leap.core.metamodel.ReservedMetaFieldName;
import leap.orm.annotation.Domain;
import leap.orm.annotation.meta.MetaName;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RUNTIME)
@Domain("updatedAt")
@MetaName(reserved=ReservedMetaFieldName.TITLE)
public @interface UpdatedAt {

}