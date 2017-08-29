/*
 * Copyright 2017 the original author or authors.
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

package org.springframework.cloud.stream.app.task.launcher.kubernetes.sink;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.deployer.spi.kubernetes.KubernetesDeployerProperties;
import org.springframework.cloud.deployer.spi.task.TaskLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test Properties for the Task Launcher Kubernetes Sink.
 *
 * @author Thomas Risberg
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public abstract class TaskLauncherKubernetesSinkTests {

	@Autowired
	protected KubernetesDeployerProperties properties;

	@TestPropertySource(properties = { "spring.cloud.deployer.kubernetes.namespace = test"})
	public static class PropertiesPopulatedTests extends TaskLauncherKubernetesSinkTests {

		@Test
		public void test() throws Exception {
			assertEquals("test", properties.getNamespace());
		}
	}

	@SpringBootApplication
	public static class TaskLauncherSinkApplication {

		@Bean
		public TaskLauncher taskLauncher() {
			return mock(TaskLauncher.class);
		}
	}
}
