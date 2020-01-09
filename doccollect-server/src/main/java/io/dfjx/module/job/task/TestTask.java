/**
 * Copyright 2018 东方金信
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.dfjx.module.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 测试定时任务(演示Demo，可删除)
 *
 * testTask为spring bean的名称
 *
 * @author Mark mazong@gmail.com
 */
@Component("testTask")
public class TestTask implements ITask {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(String params){
		logger.debug("TestTask定时任务正在执行，参数为：{}", params);
	}
}
