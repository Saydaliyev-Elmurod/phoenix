package com.example.phoenix.context.exception.handler;

import com.example.phoenix.context.exception.InvalidArgumentException;
import org.springframework.lang.Nullable;

public class Validator {
  public static void notNull(ErrorCode errorCode, @Nullable Object object, String message) {
    if (object == null) {
      throw new InvalidArgumentException(errorCode, message);
    }
  }

  public static void isTrue(
      final ErrorCode errorCode, final boolean expression, final String message) {
    if (!expression) {
      throw new InvalidArgumentException(errorCode, message);
    }
  }
}
