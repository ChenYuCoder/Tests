package com.vichen.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vichen
 */
@RestController @RequestMapping(value = "/transaction") public class TransactionController {

  @Autowired TransactionService transactionService;

  @GetMapping(value = "/add")
  public Integer add(@RequestParam(name = "u") String unique, @RequestParam(name = "i") Integer i) {
    return transactionService.add(unique, i);
  }

}
