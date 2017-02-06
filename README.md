# PP-SDK In Action

> PP-SDK是基于[Payplus](http://payplus.yeepay.com)接口封装的开发工具包。她屏蔽了大部分细节、简化了接入流程、同时提供了一些便捷的方法和标准化的参数枚举值。帮助开发者在接入过程中避开一些常见的问题，让开发者快速接入[Payplus](http://payplus.yeepay.com)的服务。

> *注: 该开发工具包仅支持Java语言，其他语言开发者可以参照Payplus的官方文档。*

## 一、快速接入

> *PayplusConnector 是一个核心类可以帮助我们接入Payplus核心系统。  
> 从此，你不再需要关注接口协议、加密方法、测试数据等等...*

### 准备工作

1. 在pom.xml里添加依赖，并将所有的依赖包添加到开发的环境变量中。

   ```xml
   <dependency>
       <groupId>com.yeepay</groupId>
       <artifactId>pp-sdk</artifactId>
       <version>1.0</version>
   </dependency>
   ```

2. 创建payplus.properties文件，内容如下：

> 测试阶段：MODEL可置为"TEST"，除非你想使用正式商编和密钥进行测试。  
> 发布生产：请务必填写APP_KEY和APP_SECRET，并且将MODEL置为"DEVELOPMENT"

```properties
# Both are Required, if the value of MODEL does not equal "TEST"
APP_KEY=
APP_SECRET=

# Please set this element to "TEST", if you do not have a specified pair of APP_KEY and APP_SECRET.
# Otherwise, you merely want to run your application under TEST environment.
MODEL=TEST
```

### DEMO

下面我们使用Java作为开发语言，对接[Payplus](http://payplus.yeepay.com)的用户注册接口。

```java
//pp-sdk核心类
PayplusConnector payplusConnector =new PayplusConnector();

Map<String, String> params = new HashMap<String, String>();

//使用系统工具生成具有唯一性的requstNo
params.put("requestNo", PayplusUtil.genRequestNo());
params.put("merchantUserId", "Joey");

//call方法包含2个参数：常量URI和入参
PayplusResp payplusResp = payplusConnector.call(PayplusURI.USER_REGISTER, param);

//打印结果
payplusResp.print();
```

### 发布生产

> 申请使用这套支付系统：*<ppsupport@yeepay.com>*

需要修改payplus.properties, 填写APP_KEY和APP_SECRET, 并将MODEL置为DEVELOPMENT.

```properties
# Both are Required, if the value of MODEL does not equal "TEST"
APP_KEY=
APP_SECRET=

# Please set this element to "TEST", if you do not have a specified pair of APP_KEY and APP_SECRET.
# Otherwise, you merely want to run your application under TEST environment.
MODEL=DEVELOPMENT
```

## 二、接入指南

### 请求参数

> 目前，我们采用Map方式来进行参数传递。

```java

Map<String, String> params = new HashMap<String, String>();

params.put("requestNo", PayplusUtil.genRequestNo());
params.put("merchantNo", "BM12345678901221");
params.put("merchantUserId", "Joey");

```

### 响应参数

> 服务返回的对象*PayplusResp* 整合了一些工具方法和一些便捷的变量访问

```java
public class PayplusResp {
    /**
     * 1.在某些接口中, 可能是H5页面链接
     * 2.另外一些接口, 二维码字节码
     */
    private String keyInfo;

    /**
     * 0-失败, 1-成功
     */
    private String state;

    /**
     * 响应实体, JSON串
     */
    private String respInfo;

    //for 3.2.1 sendRedPacket
    private String activeNo;

    //生成二维码图片
    public void genQRCodeImage(String path){
      //some operations
    }

    // 打印格式化的返回结果
    public void print(){
      // output formatted result.
    }
}
```

### 一些细节

> *PayplusUtil* 提供了若干个工具方法，提供给懒惰的人，对垒代码有莫名热忱的攻城狮请绕行...  

* 自动生成requestNo

```java
/**
 * 基于JDK自带的UUID生成。
 * 当然，这个requestNo会作为返回参数封装在Trophy对象里。
 * 可以通过调用 trophy.getRequestNo() 获取。
 */
String requestNo = PayplusUtil.genRequestNo();
```

* 使用符合[Payplus](http://payplus.yeepay.com)标准的时间格式

```java
String formattedDateString = PayplusUtil.getFormatDateString(new Date());
```

* 生成二维码图片

```java
//@params 出参对象、生成二维码地址
PayplusUtil.genQRCodeImage(trophy, path);
```

* 生成Base64文件字节码流

```java
//@params 被解析的文件地址
String Base64String = PayplusUtil.getBase64Stream(path);
```

* 为空判断

```java
Boolean flag = PayplusUtil.isNull(obj);
```

* 常量URI  

>*PayplusURI* 定义了全部可调用的服务。

```java
public class PayplusURI {

    // 2.1.2 实名
    public static final String USER_AUTH = "/rest/v1.0/payplus/user/auth";

    // 4.1.1 消费
    public static final String ORDER_CONSUME = "/rest/v1.0/payplus/order/consume";

    // 3.2.1 发送红包
    public static final String MARKETING_REDPACKET_SEND = "/rest/v1.0/payplus/merchant/sendRedPacket";

}

```
