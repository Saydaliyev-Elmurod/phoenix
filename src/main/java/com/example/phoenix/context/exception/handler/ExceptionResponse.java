package com.example.phoenix.context.exception.handler;

import com.example.phoenix.model.TextModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExceptionResponse(
    String status, String path, String message, String timestamp, TextModel description)  {

  public ExceptionResponse(
      final Exception exception,
      final String path,
      final HttpStatus status,
      final TextModel description) {
    this(
        String.format("%d %s", status.value(), status.getReasonPhrase()),
        path,
        exception.getMessage(),
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
            .withZone(ZoneId.of("UTC+5"))
            .format(Instant.now()),
        description);
  }
}
