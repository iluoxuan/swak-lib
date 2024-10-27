package com.swak.lib.common.web;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * 默认的controller 切点
 *
 * @author: ljq
 * @date: 2024/10/27
 */
@ControllerAdvice
public class DefaultAdviceControllerHandler extends AbstractExceptionHandler {


    @InitBinder
    public void initBinder(WebDataBinder binder) {

        // 绑定一个空字符串 转null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {

                setValue(DateUtil.parse(text));
            }
        });

    }

}
