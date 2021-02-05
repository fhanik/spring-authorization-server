/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.oauth2.server.authorization.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;

public final class DefaultOAuth2AuthorizationEventPublisher implements OAuth2AuthorizationEventPublisher {
	private final ApplicationEventPublisher publisher;

	public DefaultOAuth2AuthorizationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void tokenIssuedEvent(OAuth2AccessTokenAuthenticationToken token, AuthorizationGrantType grantType) {
		if (grantType == AuthorizationGrantType.CLIENT_CREDENTIALS) {
			publisher.publishEvent(new OAuth2ClientCredentialsTokenIssuedEvent(token));
		}
		else {
			throw new UnsupportedOperationException(grantType.getValue() + " is not a supported event");
		}
	}

	@Override
	public void authorizationEvent(OAuth2Authorization authorization) {
	}
}
