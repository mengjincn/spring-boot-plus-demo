/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.test;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import io.geekidea.springbootplus.generator.config.SpringBootPlusMySqlQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spring-boot-plus代码生成器入口类
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class CodeGenerator {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DRIVER_URL = "jdbc:mysql://localhost:3306/spring_boot_plus?useUnicode=true&characterEncoding=UTF-8&useSSL=false";

    // 生成的类路径
    private static final String PROJECT_PACKAGE_PATH = "io/geekidea/springbootplus";

    // 项目主包路径
    private static final String PARENT_PACKAGE = "io.geekidea.springbootplus";
    private static final String COMMON_PARENT_PACKAGE = PARENT_PACKAGE + ".common";

    // 父类包路径
    private static final String SUPER_ENTITY = COMMON_PARENT_PACKAGE + ".entity.BaseEntity";
    private static final String SUPER_CONTROLLER = COMMON_PARENT_PACKAGE + ".controller.BaseController";
    private static final String SUPER_SERVICE = COMMON_PARENT_PACKAGE + ".service.BaseService";
    private static final String SUPER_SERVICE_IMPL = COMMON_PARENT_PACKAGE + ".service.impl.BaseServiceImpl";
    private static final String SUPER_QUERY_PARAM = COMMON_PARENT_PACKAGE + ".param.QueryParam";
    private static final String[] SUPER_ENTITY_COMMON_COLUMNS = new String[]{};

    // 公共类包路径
    private static final String COMMON_ID_PARAM = COMMON_PARENT_PACKAGE + ".param.IdParam";
    private static final String COMMON_API_RESULT = COMMON_PARENT_PACKAGE + ".api.ApiResult";
    private static final String COMMON_ORDER_ENUM = COMMON_PARENT_PACKAGE + ".enums.OrderEnum";
    private static final String COMMON_ORDER_QUERY_PARAM = COMMON_PARENT_PACKAGE + ".param.OrderQueryParam";
    private static final String COMMON_PAGING = COMMON_PARENT_PACKAGE + ".vo.Paging";

    // 包名称
    private static final String PACKAGE_CONTROLLER= "controller";

    // ############################ 配置部分 start ############################
    // 模块名称
    private static final String MODULE_NAME = "hello";
    // 作者
    private static final String AUTHOR = "geekidea";
    // 生成的表名称
    private static final String TABLE_NAME = "hello";
    // 主键数据库列名称
    private static final String PK_ID_COLUMN_NAME = "id";
    // 代码生成策略 true：All/false:SIMPLE
    private static final boolean GENERATOR_STRATEGY = true;
    // 分页列表查询是否排序 true：有排序参数/false：无
    private static final boolean PAGE_LIST_ORDER = false;
    // 是否生成validation校验，true：生成/false：不生成
    private static final boolean PARAM_VALIDATION = true;

    // 生成文件配置，是否生成entity/controller/service/serviceImpl/mapper/xml
    private static final boolean GENERATOR_ENTITY = true;
    private static final boolean GENERATOR_CONTROLLER = true;
    private static final boolean GENERATOR_SERVICE = true;
    private static final boolean GENERATOR_SERVICE_IMPL = true;
    private static final boolean GENERATOR_MAPPER = true;
    private static final boolean GENERATOR_MAPPER_XML = true;
    private static final boolean GENERATOR_QUERY_PARAM = true;
    private static final boolean GENERATOR_QUERY_VO = true;
    // ############################ 配置部分 end ############################


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);                  // 是否打开输出目录
        gc.setSwagger2(true);               // 启用swagger注解
        gc.setIdType(IdType.ID_WORKER);     // 主键类型:ID_WORKER
        gc.setServiceName("%sService");     // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setFileOverride(true);           // 是否覆盖已有文件
        gc.setDateType(DateType.ONLY_DATE); // 设置日期类型为Date

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(DRIVER_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        // 设置自定义查询
        dsc.setDbQuery(new SpringBootPlusMySqlQuery());

        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(MODULE_NAME);
        pc.setParent(PARENT_PACKAGE);
        pc.setController(PACKAGE_CONTROLLER);

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

                String camelTableName = underlineToCamel(TABLE_NAME);
                String pascalTableName = underlineToPascal(TABLE_NAME);

                Map<String, Object> map = new HashMap<>();
                map.put("customField", "Hello " + this.getConfig().getGlobalConfig().getAuthor());
                // 查询参数包路径
                String queryParamPackage = PARENT_PACKAGE + StringPool.DOT + pc.getModuleName() + ".param";
                map.put("queryParamPackage", queryParamPackage);
                // 查询参数类路径
                map.put("queryParamPath", queryParamPackage + StringPool.DOT + pascalTableName + "QueryParam");
                // 查询参数共公包路径
                map.put("queryParamCommonPath", SUPER_QUERY_PARAM);
                // 查询参数共公包路径
                map.put("idParamPath", COMMON_ID_PARAM);
                // 响应结果包路径
                String queryVoPackage = PARENT_PACKAGE + StringPool.DOT + pc.getModuleName() + ".vo";
                map.put("queryVoPackage", queryVoPackage);
                // 响应结果类路径
                map.put("queryVoPath", queryVoPackage + StringPool.DOT + pascalTableName + "QueryVo");
                // 实体对象名称
                map.put("entityObjectName", camelTableName);
                // service对象名称
                map.put("serviceObjectName", camelTableName + "Service");
                // mapper对象名称
                map.put("mapperObjectName", camelTableName + "Mapper");
                // 主键ID列名
                map.put("pkIdColumnName", PK_ID_COLUMN_NAME);
                // 主键ID驼峰名称
                map.put("pkIdCamelName", underlineToCamel(PK_ID_COLUMN_NAME));
                // 导入分页类
                map.put("paging", COMMON_PAGING);
                // 导入排序枚举
                map.put("orderEnum", COMMON_ORDER_ENUM);
                // ApiResult
                map.put("apiResult", COMMON_API_RESULT);
                // 分页列表查询是否排序
                map.put("pageListOrder", PAGE_LIST_ORDER);
                // 导入排序查询参数类
                map.put("orderQueryParamPath", COMMON_ORDER_QUERY_PARAM);
                // 代码生成策略
                map.put("generatorStrategy", GENERATOR_STRATEGY);
                // 代码Validation校验
                map.put("paramValidation", PARAM_VALIDATION);
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();

        // 生成mapper xml
        if (GENERATOR_MAPPER_XML) {
            focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
        }

        // 自定义queryParam模板
        if (GENERATOR_QUERY_PARAM) {
            focList.add(new FileOutConfig("/templates/queryParam.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath + "/src/main/java/" + PROJECT_PACKAGE_PATH + "/" + pc.getModuleName() + "/param/" + tableInfo.getEntityName() + "QueryParam" + StringPool.DOT_JAVA;
                }
            });
        }

        // 自定义queryVo模板
        if (GENERATOR_QUERY_VO) {
            focList.add(new FileOutConfig("/templates/queryVo.java.vm") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return projectPath + "/src/main/java/" + PROJECT_PACKAGE_PATH + "/" + pc.getModuleName() + "/vo/" + tableInfo.getEntityName() + "QueryVo" + StringPool.DOT_JAVA;
                }
            });
        }

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 模版生成配置，设置为空，表示不生成
        TemplateConfig templateConfig = new TemplateConfig();
        // xml使用自定义输出
        templateConfig.setXml(null);
        // 是否生成entity
        if (!GENERATOR_ENTITY) {
            templateConfig.setEntity(null);
        }
        // 是否生成controller
        if (!GENERATOR_CONTROLLER) {
            templateConfig.setController(null);
        }
        // 是否生成service
        if (!GENERATOR_SERVICE) {
            templateConfig.setService(null);
        }
        // 是否生成serviceImpl
        if (!GENERATOR_SERVICE_IMPL) {
            templateConfig.setServiceImpl(null);
        }
        // 是否生成mapper
        if (!GENERATOR_MAPPER) {
            templateConfig.setMapper(null);
        }
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(SUPER_ENTITY);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperControllerClass(SUPER_CONTROLLER);
        strategy.setSuperServiceClass(SUPER_SERVICE);
        strategy.setSuperServiceImplClass(SUPER_SERVICE_IMPL);
        strategy.setInclude(TABLE_NAME);
        strategy.setSuperEntityColumns(SUPER_ENTITY_COMMON_COLUMNS);
        strategy.setControllerMappingHyphenStyle(true);
        /**
         * 注意，根据实际情况，进行设置
         * 当表名称的前缀和模块名称一样时，会去掉表的前缀
         * 比如模块名称为user,表明为user_info,则生成的实体名称是Info.java,一定要注意
         */
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    public static String underlineToCamel(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.underlineToCamel(underline);
        }
        return null;
    }

    public static String underlineToPascal(String underline) {
        if (StringUtils.isNotBlank(underline)) {
            return NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(underline));
        }
        return null;
    }


}
