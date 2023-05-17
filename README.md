# **api-encrypt-spring-boot** 

 [![](https://img.shields.io/badge/Maker-dp-blue)]() <img src="https://img.shields.io/badge/JDK-8+-green.svg" /> <img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg?label=license" /> <img src="https://img.shields.io/badge/文档-简体中文-blue.svg" alt="简体中文文档" /> 

### 一、介绍

API接口加密，可通过注解的方式对请求实体与响应实体进行AES/RSA加解密。

##### Gitee: https://gitee.com/lanters/api-encrypt-spring-boot 

##### Github: https://github.com/lanters10/OSSRH-91667

### 二、注解说明

|   应用  | 功能   | 描述                        |
|-----|------|---------------------------|
| @EnableApiEncrypt | 启用API接口请求参数加解密中间件 | 添加在启动类上 |
| @OpenEncrypt | 开启方法加密 | 添加在方法上，开启方法加密的注解 |
| @OpenDecrypt | 开启方法解密 | 添加在方法上，开启方法解密的注解 |
| @EncryptField | 需要加密的属性，如果不加这个，有加密注解，也不会进行加密 | 添加在实体类的属性上，encryptMode表示该属性值加密的模式，分为AES与RSA |
| @DecryptField | 需要解密的属性，如果不加这个，有解密注解，也不会进行加解密 | 添加在实体类的属性上，decryptMode表示该属性值解密的模式，分为AES与RSA性 |

### 三、使用说明

- #### 配置application.yml

  ```yaml
  api:
    encrypt:
      rsa:
        publicKey: # RSA公钥
        privateKey: # RSA私钥
      aes:
        key: #AES密钥
  ```

- #### 启动类Application中添加@EnableApiEncrypt注解

```java
@SpringBootApplication
@EnableApiEncrypt
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
```

- #### 方法上添加@OpenEncrypt注解，开启方法加密

```java
    @OpenEncrypt
	@GetMapping("/testEncrypt")
    public TestVo test(TestDto testDto) {
        TestVo testVo = new TestVo();
        BeanUtil.copyProperties(testDto, testVo);
        return testVo;
    }
```

- #### 方法上添加@OpenDecrypt注解，开启方法解密

```java
    @OpenDecrypt
	@GetMapping("/testDecrypt")
    public TestVo test(TestDto testDto) {
        TestVo testVo = new TestVo();
        BeanUtil.copyProperties(testDto, testVo);
        return testVo;
    }
```

- #### 属性上添加@DecryptField注解，表示对接该字段值进行解密，decryptMode表示该属性值解密模式，默认为AES方式

```java
@Data
public class TestDto {

    private static final long serialVersionUID = -2107983763366842754L;
    
    /**
     * 身份证
     */
    @DecryptField(decryptMode = DecryptModeEnum.AES)
    private String idcard;

    /**
     * 姓名
     */
    private String name;
}
```

- #### 属性上添加@EncryptField注解，表示对接该字段值进行加密，encryptMode表示该属性值加密模式，默认为AES方式

```java
@Data
public class TestVo {

    private static final long serialVersionUID = -5292102998487647142L;
    
    /**
     * 身份证
     */
    @EncryptField(encryptMode = EncryptModeEnum.AES)
    private String idcard;

    /**
     * 姓名
     */
    private String name;
}
```

### 四、示例

- #### 请求解密：前端提交API请求前对机密数据进行加密，后端服务通过中间件自动进行解密，如下图演示。

![解密](https://gitee.com/lanters/api-encrypt-spring-boot/raw/master/%E8%A7%A3%E5%AF%86.png)

- #### 响应加密：后端服务在响应接口之前会对机密数据进行加密，前端服务通过密钥进行解密，如下图所示。

![加密](https://gitee.com/lanters/api-encrypt-spring-boot/raw/master/%E5%8A%A0%E5%AF%86.png)

- #### 实际效果：前端请求加密传输，后端服务响应加密传输。

![示例](https://gitee.com/lanters/api-encrypt-spring-boot/raw/master/%E4%BA%8B%E4%BE%8B.png)