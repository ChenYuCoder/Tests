package com.vichen.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vichen
 */
@Repository public interface TransactionRepository extends JpaRepository<TransactionBean, Integer> {

  TransactionBean getFirstByUniqueEqualsOrderByIndexDesc(String unique);
}
