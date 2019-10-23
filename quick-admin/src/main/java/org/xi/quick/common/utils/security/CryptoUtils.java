package org.xi.quick.common.utils.security;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class CryptoUtils {

    public static String getSHA256(String s, String salt) {
        return new Sha256Hash(s, salt).toHex();
    }
}
