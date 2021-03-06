/*
 *
 *  * Copyright 2013 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package leap.web.api.meta.desc;

import leap.web.action.Action;
import leap.web.action.Argument;

/**
 * Created by kael on 2016/11/8.
 */
public interface OperationDescSet {
    /**
     * Returns an {@link OperationDesc} mapping this action or null
     */
    OperationDesc getOperationDesc(Action action);


    interface OperationDesc{
        /**
         * Returns summary of this operation
         */
        String getSummary();

        /**
         * Returns description of this operation
         */
        String getDescription();

        /**
         * Returns {@link Action} of this operation
         */
        Action getAction();

        /**
         * Retturns the {@link ParameterDesc} of this argument
         * @param argument
         * @return
         */
        ParameterDesc getParameter(Argument argument);
    }
    interface ParameterDesc {
        /**
         * Returns description of this parameter
         */
        String getDescription();

        /**
         * Returns the argument object of this description
         */
        Argument getArgument();

        /**
         * Returns the property with name of this argument
         */
        PropertyDesc getProperty(String name);

    }

    interface PropertyDesc {
        /**
         * Returns property name
         */
        String getName();

        /**
         * Returns property description
         */
        String getDesc();
    }
}
