package team.kas.anime.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * MD5 密码加密
 */
public class Md5Util {

    public static String encryption(String username, String password) {
        Md5Hash md5Hash = new Md5Hash(password,username,1000); //hashlterations:加密次数
        String s = md5Hash.toString();
        return s;
    }

//    public static void main(String[] args) {
//        String username = "111";
//        String password = "111";
//        Md5Hash md5Hash = new Md5Hash(password,username,1000); //hashlterations:加密次数
//        String s = md5Hash.toString();
//        System.out.println(s);
//    }
}
