package com.swak.lib.common.test.ognl;

import com.swak.lib.common.test.entiy.UserTest;
import com.swak.lib.common.tools.CollectionTools;
import ognl.Ognl;
import ognl.OgnlContext;

/**
 * @author: ljq
 * @date: 2024/11/10
 */
public class OgnlTest {


    public static void main(String[] args) throws Exception {

        UserTest userTest = new UserTest();
        userTest.setName("ljqtest");
        userTest.setSub(CollectionTools.newArrayList(userTest));
        OgnlContext context = Ognl.createDefaultContext(userTest);
        context.put("user", userTest);
        Object expression = Ognl.parseExpression("#user.name");

        Object result = Ognl.getValue(expression, context, userTest);
        System.out.println(result);



    }
}
