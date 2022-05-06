package com.xdoj.demo.util;

import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    private static final String salt = "1a2b3c4d";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToDBPass(String inputPass){
        String str = salt + inputPass + "";
        return md5(str);
    }

}
