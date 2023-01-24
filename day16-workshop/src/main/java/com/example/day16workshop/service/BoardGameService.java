package com.example.day16workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.day16workshop.model.Mastermind;


@Service
public class BoardGameService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public int saveGame(final Mastermind mds) {
        redisTemplate.opsForValue().set(mds.getId(), mds);
        Mastermind result = (Mastermind) redisTemplate.opsForValue().get(mds.getId());
        System.out.println("  RESULT >>> " + result);
        if (result != null)
            return 1;
        return 0;
    }

    public Mastermind findById(final String msid) {
        return (Mastermind) redisTemplate.opsForValue().get(msid);
    }

    public int update(final Mastermind mds) {
        System.out.println("upsert ? " + mds.isUpSert());
        if (mds.isUpSert()) {
            System.out.println("upsert is true");
            redisTemplate.opsForValue().set(mds.getId(), mds);
        } else {
            System.out.println("upsert is false");
            redisTemplate.opsForValue().setIfAbsent(mds.getId(), mds);
        }
        Mastermind result = (Mastermind) redisTemplate.opsForValue()
                .get(mds.getId());

        if (null != result)
            return 1;
        return 0;
    }

}