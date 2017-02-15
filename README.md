# jkinfo-taotao


* 通用Mapper的使用: applicationContext-mybatis.xml 配置
```
    <!-- 配置sqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 配置数据源 -->
        <property name="dataSource" ref="dataSource"></property>
        <!-- 配置mybatis的全局配置文件 -->
        <property name="configLocation" value="classpath:mybatis/mybatis-config.xml"></property>
        <!-- 配置mapper.xml文件扫描路径 -->
        <property name="mapperLocations" value="classpath:mybatis/mappers/**/*.xml"/>
        <!-- 配置别名包 -->
        <property name="typeAliasesPackage" value="com.jkinfo.taotao.pojo"/>
    </bean>
    
    <!-- 配置mapper接口扫描包 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.jkinfo.taotao.mapper"></property>
    </bean>
```

* 通用Mapper的使用: mybatis-config.xml 配置
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <!-- 驼峰自动映射 -->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>

    <!-- 注意插件的顺序，优先执行分页助手 -->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageHelper">
            <property name="dialect" value="mysql" />
            <!-- 该参数默认为false -->
            <!-- 设置为true时，使用RowBounds分页会进行count查询 -->
            <property name="rowBoundsWithCount" value="true" />
        </plugin>

        <plugin interceptor="com.github.abel533.mapperhelper.MapperInterceptor">
            <!--主键自增回写方法,默认值MYSQL,详细说明请看文档 -->
            <property name="IDENTITY" value="MYSQL" />
            <!--通用Mapper接口，多个通用接口用逗号隔开 -->
            <property name="mappers" value="com.github.abel533.mapper.Mapper" />
        </plugin>
    </plugins>
</configuration>
```

* eclipse使用maven中tomcat插件启动项目乱码问题
    * eclipse 的编码格式为UTF-8, 数据库中设置的也为UTF-8,但是保存到数据中的中文却是乱码。
    * 解决办法: 在pom.xml 中修改配置
```java
<!-- 配置插件 -->
<plugins>
     <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <configuration>
            <port>8083</port>
            <path>/</path>
            <uriEncoding>UTF-8</uriEncoding>
        </configuration>
    </plugin>
</plugins>
```

* 实现mysql主从的读写功能: db.properites 配置
``` 
jdbc.master.driver=com.mysql.jdbc.Driver
jdbc.master.url=jdbc:mysql://127.0.0.1:3306/taotao?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true
jdbc.master.username=root
jdbc.master.password=root


jdbc.slave01.driver=com.mysql.jdbc.Driver
jdbc.slave01.url=jdbc:mysql://127.0.0.1:3306/taotao?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true
jdbc.slave01.username=root
jdbc.slave01.password=root

```
    
* 实现mysql主从的读写功能: applicationContext.xml 配置
```
<!-- 加载配置文件 -->
    <context:property-placeholder location="classpath:resource/*.properties"/>
    
    <!-- 定义数据源，使用自己实现的数据源 -->
    <bean id="dataSource" class="com.jkinfo.taotao.datasource.DynamicDataSource">
        <!-- 设置多个数据源 -->
        <property name="targetDataSources">
            <map key-type="java.lang.String">
                <!-- 这个key需要和程序中的key一致 -->
                <entry key="master" value-ref="masterDataSource"/>
                <entry key="slave" value-ref="slave01DataSource"/>
            </map>
        </property>
        <!-- 设置默认的数据源，这里默认走写库 -->
        <property name="defaultTargetDataSource" ref="masterDataSource"/>
    </bean>
    
    <!-- 配置连接池 -->
    <bean id="masterDataSource" class="com.jolbox.bonecp.BoneCPDataSource"
        destroy-method="close">
        <!-- 数据库驱动 -->
        <property name="driverClass" value="${jdbc.master.driver}" />
        <!-- 相应驱动的jdbcUrl -->
        <property name="jdbcUrl" value="${jdbc.master.url}" />
        <!-- 数据库的用户名 -->
        <property name="username" value="${jdbc.master.username}" />
        <!-- 数据库的密码 -->
        <property name="password" value="${jdbc.master.password}" />
        <!-- 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0 -->
        <property name="idleConnectionTestPeriod" value="60" />
        <!-- 连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0 -->
        <property name="idleMaxAge" value="30" />
        <!-- 每个分区最大的连接数 -->
        <property name="maxConnectionsPerPartition" value="150" />
        <!-- 每个分区最小的连接数 -->
        <property name="minConnectionsPerPartition" value="5" />
    </bean>
    
    <!-- 配置连接池 -->
    <bean id="slave01DataSource" class="com.jolbox.bonecp.BoneCPDataSource"
        destroy-method="close">
        <!-- 数据库驱动 -->
        <property name="driverClass" value="${jdbc.slave01.driver}" />
        <!-- 相应驱动的jdbcUrl -->
        <property name="jdbcUrl" value="${jdbc.slave01.url}" />
        <!-- 数据库的用户名 -->
        <property name="username" value="${jdbc.slave01.username}" />
        <!-- 数据库的密码 -->
        <property name="password" value="${jdbc.slave01.password}" />
        <!-- 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0 -->
        <property name="idleConnectionTestPeriod" value="60" />
        <!-- 连接池中未使用的链接最大存活时间，单位是分，默认值：60，如果要永远存活设置为0 -->
        <property name="idleMaxAge" value="30" />
        <!-- 每个分区最大的连接数 -->
        <property name="maxConnectionsPerPartition" value="150" />
        <!-- 每个分区最小的连接数 -->
        <property name="minConnectionsPerPartition" value="5" />
    </bean>
```
