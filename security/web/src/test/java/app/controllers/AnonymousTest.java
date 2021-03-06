/*
 * Copyright 2016 the original author or authors.
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
package app.controllers;

import leap.web.security.SecurityTestCase;
import org.junit.Test;

public class AnonymousTest extends SecurityTestCase {

    @Test
    public void testAllowAnonymous() {
        logout();
        get("/anonymous").assertContentEquals("Hello");
        get("/anonymous1").assertContentEquals("Hello1");
    }

    @Test
    public void testInheritedAnnotation() {
        logout();
        ajaxGet("/anonymous2/allow").assertOk();
        ajaxGet("/anonymous2/deny").assert401();

        ajaxGet("/anonymous3/allow").assertOk();
        ajaxGet("/anonymous3/deny").assert401();
    }

}
