# 0. 开发环境

- IDE：IntelliJ IDEA 2017.1 x64

- jdk：1.8.0_91

- Spring Boot：2.1.1.RELEASE

  <br>

# 1. 引入依赖

~~~xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
~~~

<br>

# 2. 新建Swagger2配置类

~~~java
package cn.wbnull.springbootdemo.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class GlobalSwagger2 {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //生成api文档扫描路径
                .apis(RequestHandlerSelectors.basePackage("cn.wbnull.springbootdemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("SpringBootDemo Api")
                //作者
                .contact(new Contact("dukunbiao(null)", "https://github.com/dkbnull/SpringBootDemo", ""))
                //版本号
                .version("1.0")
                //描述
                .description("Base Java 1.8")
                .build();
    }
}
~~~

<br>

# 3. Restful接口增加注解

~~~java
package cn.wbnull.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("prototype")
@Api(tags = "登录接口")
public class LoginController extends BaseController {

    @PostMapping(value = "/login")
    @ApiOperation("登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sign", value = "签名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", required = true, dataType = "String"),
            @ApiImplicitParam(name = "data", value = "请求数据", required = true, dataType = "String")
    })
    public JSONObject login(
            @RequestParam(value = "sign") String sign,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "data") String data
    ) throws Exception {
        return baseService.login(data);
    }
}
~~~

<br>

# 4. 测试

启动程序

浏览器访问 <font color="#FF0000">**ip:port/context-path/swagger-ui.html**</font> 即可进入Api文档页面，博主这里地址为：http://localhost:8090/springbootdemo/swagger-ui.html

![1552207136039](07_Spring Boot整合Swagger2.assets\1552207136039.png)

展开某个接口后可以看到具体求参数与返回参数

![1552207885021](07_Spring Boot整合Swagger2.assets\1552207885021.png)

点击 **<font color="#FF0000">Try it out</font> ** 按钮可以进行测试，其中红框输入请求参数，输入后点击<font color="#FF0000">**Execute**</font>

![1552208014292](07_Spring Boot整合Swagger2.assets\1552208014292.png)

可以看到返回信息

![1552208332065](07_Spring Boot整合Swagger2.assets\1552208332065.png)

<br>

# 5. Swagger2常用注解

~~~
@Api()：注解类，描述Controller的作用
	tags = ”描述Controller的作用“
@ApiOperation()：注解方法，描述具体接口的作用
	value=“具体说明接口的作用”
    notes="接口方法备注“
@ApiImplicitParams()：注解接口方法，描述一组请求参数，可以包含多个@ApiImplicitParam()
@ApiImplicitParam()：注解接口方法，描述一个具体的请求参数
	name：请求参数名
	value：请求参数描述
	required：是否必传
	dataType：参数类型
	defaultValue：默认值
@ApiResponses()：注解接口方法，描述一组HTTP返回值，可以包含多个@ApiResponse()
@ApiResponse()：注解接口方法，描述一个HTTP响应信息
	code：HTTP返回值
    message：返回信息
    response：抛出异常的类
@ApiModel()：注解Model，描述响应数据Model类
@ApiModelProperty()：注解属性，描述响应Model类的属性

@ApiIgnore()：注解类，表示忽略这个Api
~~~

<br>

---

GitHub：[https://github.com/dkbnull/SpringBootDemo](https://github.com/dkbnull/SpringBootDemo)

CSDN：[https://blog.csdn.net/dkbnull/article/details/88380987](https://blog.csdn.net/dkbnull/article/details/88380987)

微信：[https://mp.weixin.qq.com/s/hAg-sBKurC5QtcA5JFzjTQ](https://mp.weixin.qq.com/s/hAg-sBKurC5QtcA5JFzjTQ)

微博：[https://weibo.com/ttarticle/p/show?id=2309404447241761849549](https://weibo.com/ttarticle/p/show?id=2309404447241761849549)

知乎：[https://zhuanlan.zhihu.com/p/95993014](https://zhuanlan.zhihu.com/p/95993014)

