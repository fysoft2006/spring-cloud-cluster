/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.cloud.cluster.leader.event;

import org.springframework.cloud.cluster.leader.Context;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * Default implementation of {@link LeaderEventPublisher}.
 * 
 * @author Janne Valkealahti
 *
 */
public class DefaultLeaderEventPublisher implements LeaderEventPublisher, ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;

	/**
	 * Instantiates a new leader event publisher.
	 */
	public DefaultLeaderEventPublisher() {
	}

	/**
	 * Instantiates a new leader event publisher.
	 * 
	 * @param applicationEventPublisher the application event publisher
	 */
	public DefaultLeaderEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
	
	@Override
	public void publishOnGranted(Object source, Context context) {
		if (applicationEventPublisher != null) {
			applicationEventPublisher.publishEvent(new OnGrantedEvent(source, context));
		}
	}

	@Override
	public void publishOnRevoked(Object source, Context context) {
		if (applicationEventPublisher != null) {
			applicationEventPublisher.publishEvent(new OnRevokedEvent(source, context));
		}
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
	
}
