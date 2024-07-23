package com.danmou.course.resources.util;

import java.io.UnsupportedEncodingException;

public class URL {
  public static String decodeParam(String text) {
    try {
      return java.net.URLDecoder.decode(text, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }
}
