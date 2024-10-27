## dao层规范

* sql语句写到mapper接口中，使用default实现
* 连表使用xml ，复杂的sql使用xml
* 封装分页查询和转换
* 封装基础表的相关字段 create_time, update_time
* 固定生成代码和xml格式，统一
* 不生成service controller代码

## 涉及和数据库相关的插件写这里

* 脱敏
* 加解密
* 动态表等