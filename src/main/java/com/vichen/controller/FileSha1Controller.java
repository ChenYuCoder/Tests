package com.vichen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 手动返回404
 */
@RequestMapping(value = "/file") @Controller public class FileSha1Controller {

  Logger logger = LoggerFactory.getLogger(FileSha1Controller.class);

  /**
   * 获取文件sha1值
   *
   * @param filePath 文件相对路径
   * @return
   */

  @RequestMapping(value = "/getSha1", method = RequestMethod.GET) @ResponseBody
  public String getSha1(@RequestParam("filePath") String filePath) {

    throw new ResourceNotFoundException("", new FileSystemResource(filePath));
  }

  @ExceptionHandler({ResourceNotFoundException.class}) @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public void handleNullPointerException(Exception e) {
    logger.error(e.getMessage());
  }

}
