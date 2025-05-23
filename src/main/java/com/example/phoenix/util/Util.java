package com.example.phoenix.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
  public static boolean validateUzPhoneNumber(String phoneNumber) {
    final String UZ_PHONE_NUMBER_PATTERN = "^\\+998\\d{9}$";
    final Pattern pattern = Pattern.compile(UZ_PHONE_NUMBER_PATTERN, Pattern.CASE_INSENSITIVE);
    final Matcher matcher = pattern.matcher(phoneNumber);

    return matcher.matches();
  }
}
