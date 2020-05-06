package com.vichen.transaction;

//import com.sun.java.util.jar.pack.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vichen
 */
@Service public class TransactionService {

  @Autowired TransactionRepository transactionRepository;
  private static Map<String, Integer> me = new HashMap<>();


  @Transactional(rollbackFor = Exception.class)
  public synchronized Integer add(String unique, Integer i) {

    //    List<Integer> list=  new ArrayList<>();
    //    list.contains()
    //    RandomAccessFile randomAccessFile = new RandomAccessFile();
    //    randomAccessFile.close();
    //    randomAccessFile.seek();
    //      new File().s
    //
    //    Files.
    //
    //    Integer index = me.get(unique);
    //    if (index == null) {
    //      TransactionBean transactionBean =
    //        transactionRepository.getFirstByUniqueEqualsOrderByIndexDesc(unique);
    //      index = transactionBean == null ? 0 : transactionBean.getIndex();
    //    }
    //
    //    index += i;
    //    TransactionBean transactionBean = new TransactionBean();
    //    transactionBean.setIndex(index);
    //    transactionBean.setUnique(unique);
    //
    //    transactionRepository.save(transactionBean);
    //
    //    if (index % 3 == 0) {
    //      throw new RuntimeException();
    //    }
    //
    //    me.put(unique, index);



    return 1;

  }
}
