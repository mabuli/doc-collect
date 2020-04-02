package io.dfjx.event.listener;

import io.dfjx.common.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationPreparedListener implements ApplicationListener<ApplicationPreparedEvent> {

    private static Logger logger = LoggerFactory.getLogger(ApplicationPreparedListener.class);


    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        logger.info(" >>>>>> ApplicationPreparedEvent <<<<<<");

        SpringContextUtils.setApplicationContext(event.getApplicationContext());
    }
}

