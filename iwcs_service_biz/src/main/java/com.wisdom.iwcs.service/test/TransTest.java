package com.wisdom.iwcs.service.test;

import com.wisdom.iwcs.domain.codec.CodecCountry;
import com.wisdom.iwcs.mapper.codec.CodecCountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 测试事务
 */
@Service
public class TransTest {
    @Autowired
    private CodecCountryMapper codecCountryMapper;

    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    TransactionTemplate transactionTemplate;

    public void transTest() {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        try {

            // [1] 插入纪录
            CodecCountry codecCountry = new CodecCountry();
            codecCountryMapper.insert(codecCountry);

            // [2] 范例抛出异常
            Integer i = null;
            if (i.equals(0)) {

            }

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);

            throw e;
        }

    }


    public void trans2Test() {
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                CodecCountry codecCountry = new CodecCountry();
                codecCountryMapper.insert(codecCountry);
                transactionStatus.setRollbackOnly();

                Integer i = null;

                if (i.equals(0)) {

                }
                return null;
            }
        });

    }
}
