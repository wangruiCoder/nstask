package cn.newstrength.core.service;

import org.springframework.stereotype.Component;

@Component
public interface BaseDeleteOneService {
    int deleteOneById(int deleteId);
}
