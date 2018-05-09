package com.myweb.service.impl;

import com.myweb.dao.jpa.hibernate.*;
import com.myweb.service.OneService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("OneService")
@SuppressWarnings("All")
@Transactional(readOnly = true)
@PropertySource({"classpath:application.properties"})
public class OneServiceImpl implements OneService {

    private static final Logger logger = LogManager.getLogger(OneServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookstoreRepository bookstoreRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private RecordRepository recordRepository;
}
