package com.swak.lib.dao.mybatis.plus.gen;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.BaseBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;

import java.util.Collections;

/**
 * @author: ljq
 * @date: 2024/9/21
 */
@Getter
@Slf4j
public abstract class MybatisPlusBaseGen {

    private GenInfo genInfo;

    protected final static String src = "/src/main/java";

    protected final static String sourcesMapper = "/src/main/resources/mapper";


    protected DataSourceConfig.Builder dataSourceConfig() {

        return new DataSourceConfig.Builder(genInfo.getJdbcUrl(), genInfo.getUserName(), genInfo.getPassword()).keyWordsHandler(new MySqlKeyWordsHandler())
                .typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    // 兼容旧版本转换成Integer
                    if (JdbcType.TINYINT == metaInfo.getJdbcType()) {
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                });
    }

    protected GlobalConfig.Builder globalConfig() {

        return new GlobalConfig.Builder().disableOpenDir().outputDir(getProjectPath() + src) // 设置输出目录
                .author(genInfo.getAuthor()) // 设置作者名
                .enableSwagger() // 开启 Swagger 模式
                .dateType(DateType.ONLY_DATE) // 设置时间类型策略
                .commentDate("yyyy-MM-dd").disableServiceInterface();// 设置注释日期格式


    }

    protected PackageConfig.Builder packageConfig() {

        return new PackageConfig.Builder().parent(genInfo.getPackageName()).entity("domain") // 设置 Entity 包名
                .mapper("mapper") // 设置 Mapper 包名
                .xml("mapper") // 设置 Mapper XML 包名
                .pathInfo(Collections.singletonMap(OutputFile.xml, getProjectPath() + sourcesMapper));
    }

    protected BaseBuilder strategyConfig() {

        return new StrategyConfig.Builder().addInclude(genInfo.getGenTableName())

                .entityBuilder()
                .disableSerialVersionUID()
                .enableLombok()
                .enableRemoveIsPrefix()
                .enableTableFieldAnnotation()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .addTableFills(new Column("create_time", FieldFill.INSERT))
                .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                .idType(IdType.AUTO)
                .formatFileName("%sDo")
                .enableFileOverride()

                .serviceBuilder()
                .disable()
                .controllerBuilder()
                .disable()

                .mapperBuilder()
                .superClass(BaseMapper.class)
                .enableMapperAnnotation()
                .enableBaseResultMap()
                .enableBaseColumnList()
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sMapper")
                .enableFileOverride();
    }


    private String getProjectPath() {
        String fileSeparator = System.getProperty("file.separator");
        return System.getProperty("user.dir") + fileSeparator + genInfo.getModelName();
    }


    protected void gen(GenInfo genInfo) {
        this.genInfo = genInfo;
        AutoGenerator generator = new AutoGenerator(dataSourceConfig().build());
        generator.global(globalConfig().build())
                .strategy(strategyConfig().build())
                .packageInfo(packageConfig().build())
                .execute();

    }
}
