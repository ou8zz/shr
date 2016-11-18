#spring boot framework

##遇到的问题
* mybatis中使用like在mysql中表达式可以用like "%"#{name}"%"，但是h2数据库中不能支持，根据我测试来看like concat(concat('%',#{name}),'%')可以支持h2,mysql,oracle
* h2数据库中datetime类型或者timestamp类型中默认是23位长度2016-08-13 20:43:44.402 而mysql中是19位2016-08-13 20:43:44，然而在使用between时h2数据库因为后面的3位会匹配不到那条数据，因为传参时默认变成了2016-08-13 20:43:44.000
* 今天发现form表单自动提交情况说明：  <br/>
	1.默认情况下，单个输入框，无论按钮的type="submit"还是type="button"类型，回车即提交。   <br/>
	2.当type="submit"时，无论有几个type="text"输入框，回车均表示提交。（<button>按钮默认的type为submit）   <br/>
    3.当type="button"时，且存在多个输入框，回车不提交。（button） <br/>
    解决方案： <br/>
	1.解决单个输入框的回车即提交问题，可以增加一个隐藏的input="text" display='none'; 然后type类型为button。 <br/>
 	2.在form表单或input中加入：onkeydown="if(event.keyCode==13){return false;}" <br/>
* tomcat在启动时出现如下异常问题： <br/>
        严重: IOException while loading persisted sessions: java.io.EOFException <br/>
        严重: Exception loading sessions from persistent storage <br/>
    解决方案：tomcat中找到SESSIONS.ser删除即可 <br/>
* SpringBoot从eclipse切换IDEA环境中运行启动：
```
java.lang.IllegalStateException: Could not evaluate condition on org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration#propertySourcesPlaceholderConfigurer due to internal class not found. This can happen if you are @ComponentScanning a springframework package (e.g. if you put a @ComponentScan in the default package by mistake)
    at org.springframework.boot.autoconfigure.condition.SpringBootCondition.matches(SpringBootCondition.java:51)
```
解决方法tomcat or jetty 配置去掉scope
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
    <scope>provided</scope>
</dependency>
```
* 还有在IDEA环境中maven编译mybatis的xml加载不到，设置maven编译时将xml文件放进去
```
<resources>
	<resource>
		<directory>src/main/java</directory>
		<includes>
			<include>**/*.xml</include>
		</includes>
		<filtering>true</filtering>
	</resource>
</resources>
```
##心得
* 关于学习h2数据库的一些心得
* h2是java开发的数据库，上手相对来说比较简单直接在java环境下>> -cp E:\repository\com\h2database\h2\1.3.172\h2-1.3.172.jar org.h2.tools.Server -web可以直接启用。
* 配置url=jdbc:h2:file:~/.h2/bkm;MODE=MySQL;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1;MVCC=true文件方式后如果修改脚本需要删除对应.db文件后执行新脚本
* 使用h2数据库时，mybatis中foreach使用union all进行select连接时里面的变量表达式只能写${id},如果写成#{id}会报类型错。但是如果用in(foreach)却没有这样的问题。可能是H2的bug.
* 在spring中init脚本中不能完全把mysql的脚本丢过来用，对于部分编码格式和语法h2不支持，详细可以看schema.sql文件语法。
* 在spring中使用h2数据库控制台，需要把完整的JDBC URL路径配置到连接控制台中才能使用该库jdbc:h2:file:~/.h2/bkm;MODE=MySQL;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1;MVCC=true
* Oracle在insert的语境中默认字符串长度为4000，所以在insert中clob字段大于4000会insert错误ora-01704. 而通过测试MySql没有这样限制，可以正常insert.
* mybatis中如果使用selectOne调用Insert的SQL语句理论上是可以执行的，经过测试普通的insert语句没有问，但是当使用selectKey来绑定主键ID的时候会导致selectKey不会执行的情况，在执行SQL时还是注意按照mybatis的规范来写避免这样的问题。
* mybatis 配置文件 mappers package目录配置只能配置class扫描目录，不能配置xml文件，所以xml只能mapper一个个配置。mybatis-config.xml不支持mapper配置*.xml写法。
好在可以在application.properties中配置mybatis.mapper-locations=classpath:/com/spring/model/xml/*.xml
* 关于重写hashCode和equals方法需要注意记住的事情:
尽量保证使用对象的同一个属性来生成hashCode()和equals()两个方法。在我们的案例中,我们使用员工id。
eqauls方法必须保证一致（如果对象没有被修改，equals应该返回相同的值）
任何时候只要a.equals(b),那么a.hashCode()必须和b.hashCode()相等。
两者必须同时重写。
* 在SpringMVC中Controller中如果使用respose.getWriter就不能使用@ResponseBody注解，否则报错java.lang.IllegalStateException: WRITER
* SrpingBoot约定大于配置的方式：springAOP容器处于在SpringBootApplication文件所在的包下。而error自定义配置文件在templates/error文件目录下，自定义error页面需要在配置中关闭原始错误提示页面server.error.whitelabel.enabled=false。
* 使用Thymeleaf 和jsp遇到一个include的问题，jsp中可以将页面元素拆开来进行导入，比如jsp我可以把一个html中从任意一段拆开做成通用的common.jsp，然后所有页面对这个jsp进行行include, 而thymeleaf因为是基于xml所以不能将不规则的标签统一设定成一个common.html，需要保证标签的完整性（如完整的head，完整的div，script需要用div包起来），再进行include。
* 使用Thymeleaf3.0版本配置:
```
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!-- set thymeleaf version -->
    <thymeleaf.version>3.0.0.RELEASE</thymeleaf.version>
    <thymeleaf-layout-dialect.version>2.0.0</thymeleaf-layout-dialect.version>
  </properties>
```
* Thymeleaf3.0变化
（1）模板变化；
（2）配置变化
（3）完整HTML5标志支持：Thymeleaf 3.0 不再是基于XML结构的。由于引入新的解析引擎，模板的内容格式不再需要严格遵守XML规范。即不在要求标签闭合，属性加引号等等。当然，出于易读性考虑，还是推荐你按找XML的标准去编写模板。2.0版本的hr标签是会报错的，3.0之后就可以正常访问了。（博主这个特性特意测试了下，通过）
（4）模板类型：Thymeleaf 3 移除了之前版本的模板类型，新的模板类型为：HTML、XML、TEXT、JAVASCRIPT、CSS、RAW。
（5）片段（Fragment）表达式；
（6）无操作标记；
（7）模板逻辑解耦：Thymeleaf 3.0 允许 HTML和XML模式下的模板内容和控制逻辑完全解耦。
（8）性能提示：
（9）不依赖于Servlet API；
（10）新的方言系统；
（11）重构了核心API;
* 先就这么多。后续再补充
