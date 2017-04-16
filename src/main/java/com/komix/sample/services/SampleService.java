package com.komix.sample.services;

import com.komix.sample.services.common.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SampleService extends AbstractService {

    @PostConstruct
    public void init() {
        log.info("init");
    }

}
