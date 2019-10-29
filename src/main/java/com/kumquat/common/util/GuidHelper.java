package com.kumquat.common.util;

import java.util.UUID;

/**
 * Created by 13260 on 2019/5/11.
 */
public class GuidHelper {

    /**
     * 获取guid
     * @return
     */
    public static String getGuid(){
        UUID uuid=UUID.randomUUID();
        String guid=uuid.toString();
        return	 guid.replaceAll("-", "");
    }
}
