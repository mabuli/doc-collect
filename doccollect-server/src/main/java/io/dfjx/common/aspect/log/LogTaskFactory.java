package io.dfjx.common.aspect.log;

import java.util.TimerTask;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTaskFactory {

	private static Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);
	private static Gson gson = new Gson();

	public static TimerTask operatorLog(long userId, String className, String methodName, Object[] args, Object result, long time) {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					logger.info("Execution Log, userId: {}, Class：{}, Method：{}, Params：{}, Result: {}, Total Time：{}", userId, className, methodName, gson.toJson(args), gson.toJson(result), time);
				} catch (Exception e) {
					logger.error("保存操作日志异常：", e);
				}
			}
		};
	}
}
