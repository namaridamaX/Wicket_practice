package com.example.wsbp.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SampleService implements ISampleService {

    @Override
    public String makeCurrentHMS(){
        var now = LocalDateTime.now();
        var str = now.getHour()
                +":"+now.getMinute()
                +":"+now.getSecond();
        return str;
    }
}
