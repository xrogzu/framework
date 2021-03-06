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
package app.controllers;

import leap.lang.http.HTTP;
import leap.web.WebTestCase;
import leap.web.cors.CorsHandler;

import org.junit.Test;

public class CorsTestControllerTest extends WebTestCase {

    @Test
    public void testOptions() {
        request(HTTP.Method.OPTIONS, "/cors_test/enabled")
                .addHeader(CorsHandler.REQUEST_HEADER_ORIGIN, "example.com")
                .send()
                .assertOk()
                .assertHeaderEquals(CorsHandler.RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, "example.com");
    }

	@Test
	public void testOrigin() {
		forGet("/cors_test/normal")
			.addHeader(CorsHandler.REQUEST_HEADER_ORIGIN, "")
			.send()
			.assertContentEquals("normal");

		forGet("/cors_test/enabled")
			.addHeader(CorsHandler.REQUEST_HEADER_ORIGIN, "")
			.send()
			.assertStatusEquals(HTTP.SC_FORBIDDEN)
		   	.assertContentEmpty();
		
		forGet("/cors_test/enabled")
			.addHeader(CorsHandler.REQUEST_HEADER_ORIGIN, "http://example.com")
			.send()
			.assertHeaderEquals(CorsHandler.RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, "http://example.com")
		   	.assertContentEquals("enabled");		
	}
	
}