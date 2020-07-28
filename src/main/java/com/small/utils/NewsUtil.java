package com.small.utils;

import lombok.Data;
import org.springframework.ui.Model;

/**
 * @author xueshiqi
 * @since 2020/5/6
 */
@Data
public class NewsUtil {
    /**
     * 警告消息
     */
    private String warn;

    public static void getModel(Model model,NewsUtil newsUtil){
        model.addAttribute("warn" , newsUtil.getWarn());
    }

}
