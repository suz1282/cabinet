package com.suzhou.cabinet.app;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 *
 * 自动生成映射工具类
 *
 * @author hubin
 *
 */
public class AutoGeneratorHelper {

	/**
	 * <p>
	 * 测试 run 执行
	 * </p>
	 * <p>
	 * 更多使用查看 http://mp.baomidou.com
	 * </p>
	 */
    public static void main(String[] args) throws InterruptedException {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("d:/AutoGenerator/");
        gc.setFileOverride(true);//是否覆盖原文件
        gc.setActiveRecord(true);//开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("suz");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
         gc.setMapperName("%sMapper");
         gc.setXmlName("%sMapper");
         gc.setServiceName("%sService");
         gc.setServiceImplName("%sServiceImap");
         gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        /*dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });*/
        dsc.setDriverName("com.mysql.jdbc.Driver");
//        dsc.setUrl("jdbc:mysql://122.112.227.174:3306/waterdb_ii?useSSL=false&allowMultiQueries=true");
//        dsc.setUsername("root");
//        dsc.setPassword("dqbp@JLpoa$95");
        dsc.setUrl("jdbc:mysql://localhost:3306/cabinet?useSSL=false&allowMultiQueries=true&serverTimezone=UTC");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
//        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[] { "announcement","box","cabinet","order","region","user"});// 需要生成的表
        //strategy.setExclude(new String[]{"test"}); // 排除生成的表
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wpg");
        pc.setModuleName("web");
        mpg.setPackageInfo(pc);
        // 执行生成
        mpg.execute();
    }

}