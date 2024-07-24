package com.danmou.course.resources.util;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class URL {
  public static String decodeParam(String text) {
    try {
      return java.net.URLDecoder.decode(text, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }

  public static Instant endOfDay(String textDate, Instant defaultDate) {
    try {
      LocalDate date = LocalDate.parse(textDate);
      LocalDateTime endOfDay = date.atTime(23, 59, 59, 999999999);
      ZonedDateTime zonedEndOfDay = endOfDay.atZone(ZoneId.systemDefault());
      Instant endOfDayInstant = zonedEndOfDay.toInstant();

      return endOfDayInstant;
    } catch (Exception e) {
      return defaultDate;
    }
  }

  public static Instant startOfDay(String textDate, Instant defaultDate) {
    try {
      LocalDate date = LocalDate.parse(textDate);
      LocalDateTime endOfDay = date.atTime(0, 0, 0, 0);
      ZonedDateTime zonedEndOfDay = endOfDay.atZone(ZoneId.systemDefault());
      Instant endOfDayInstant = zonedEndOfDay.toInstant();

      return endOfDayInstant;
    } catch (Exception e) {
      return defaultDate;
    }
  }
}
