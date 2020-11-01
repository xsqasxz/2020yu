package com.small.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xueshiqi
 * @since 2020/5/8
 */
@Component
@Data
public class ToConfigure {
    @Value("${authority_verification}")
    private String authorityVerification;
    @Value("${url.path}")
    private String urlPath;
    @Value("${nginx.path}")
    private String nginxPath;
}
