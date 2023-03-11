package com.mong.mmbs.common.util;

import java.util.Random;

public class UserUtil {
  public static String getTemporaryPassword() {

    Random random = new Random();

    String characterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String password = "";

    while (password.length() < 10) {
      int characterIndex = random.nextInt(characterSet.length());
      password += characterSet.charAt(characterIndex);
    }

    return password;
}
}
