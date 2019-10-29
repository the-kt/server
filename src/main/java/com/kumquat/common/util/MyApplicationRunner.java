package com.kumquat.common.util;

import com.kumquat.common.config.KumquatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by 13260 on 2019/10/22.
 */

@Component
public class MyApplicationRunner  implements ApplicationRunner {

    @Autowired
    KumquatProperties kumquatProperties;

    @Override
    public void run(ApplicationArguments var1) throws Exception{

//        office2PDF.startCommand(rainbowProperties.getOpenoffice());
    }
}
