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
package leap.core;

import leap.core.junit.AppTestBase;
import leap.core.security.token.jwt.RsaSigner;
import leap.core.security.token.jwt.RsaVerifier;
import leap.lang.New;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

public class AppConfigTest extends AppTestBase {

	@Test
	public void testDefaultAppConfig(){
		AppConfig config = AppContext.config();
		assertNotNull(config);
		assertNotNull(config.getProfile());
		assertEquals(AppConfig.PROFILE_DEVELOPMENT, config.getProfile());
		assertTrue(config.isDebug());
		assertNotNull(config.getDefaultCharset());
		assertNotNull(config.getDefaultLocale());
		assertTrue(config.isReloadEnabled());

		assertTrue(config.getResources().size() > 5);
	}

    @Test
    public void testGetPrivateKey() {
		PrivateKey privateKey = config.ensureGetPrivateKey();
		PublicKey  publicKey  = config.ensureGetPublicKey();
        assertNotNull(privateKey);
		assertNotNull(publicKey);
		RsaSigner signer = new RsaSigner((RSAPrivateKey)privateKey);
		RsaVerifier verifier = new RsaVerifier((RSAPublicKey)publicKey);
		Map<String, Object> claim = New.hashMap("name", "admin", "password", "11111");
		String str = signer.sign(claim);
		Map<String, Object> pased = verifier.verify(str);
		assertEquals(claim.get("name"),pased.get("name"));
		assertEquals(claim.get("password"),pased.get("password"));
    }

}