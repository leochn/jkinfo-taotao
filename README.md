# jkinfo-taotao
* 通用Mapper的使用

* eclipse使用maven中tomcat插件启动项目乱码问题
    * eclipse 的编码格式为UTF-8, 数据库中设置的也为UTF-8,但是保存到数据中的中文却是乱码。
    * 解决办法: 在uriEncoding 节点配置 UTF-8
    * 
                <!-- 插件配置 -->
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
