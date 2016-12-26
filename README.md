# PP-SDK In Action

![yeepay icon](http://www.yeepay.com/images/logo.png)

> PP-SDK是基于[Payplus](http://payplus.yeepay.com)接口封装的开发工具包。她屏蔽了大部分细节、简化了接入流程、同时提供了一些便捷的方法和标准化的参数枚举值。帮助开发者在接入过程中避开一些常见的问题，让开发者快速接入[Payplus](http://payplus.yeepay.com)的服务。  

> *注: 该开发工具包仅支持Java语言，其他语言开发者可以参照Payplus的官方文档。*

## 一、快速接入

> *PayplusConnector 是一个核心类可以帮助我们接入Payplus核心系统。  
> 从此，你不再需要关注接口协议、加密方法、测试数据等等...*

#### 测试阶段，快速接入。

```java
//pp-sdk核心类
PayplusConnector payplusConnector =new PayplusConnector();

UserRegisterReq userRegisterReq =new UserRegisterReq();

userRegisterReq.setRequestNo(null);
userRegisterReq.setMerchantNo(null);
userRegisterReq.setMerchantUserId("Joey");

/**

当然，你也可以使用JDK自带的Map来封装参数，调用我们的服务

Map<String, String> params = new HashMap<String, String>();

params.put("requestNo", null);
params.put("merchantNo", null);
params.put("merchantUserId", "Joey");

**/

//所有的hessian服务地址都封装在PayplusURI里
Torphy trophy = payplusConnector.call(PayplusURI.USER_REGISTER, userRegisterReq);

//打印结果
trophy.print();

```

>*你可能注意到了，我们在实例化变量registerReq的时候，将requestNo和merchantNo赋值为null, 这是因为底层封装了默认的测试数据，这将有利于我们快速了解Payplus的核心业务，而不需要关注测试参数等细节。*

>*注1：当然你也可以完全不传这两个变量，这里赋值null是为了不给用户造成误导，因为这两个参数是确实存在的。*

>*注2：所有接口列表中入参小于等于五个的接口，都会提供严格依照文档参数顺序的构造函数，以便快速构建代码。*

#### 正式商用，你可以完善商编和密钥等信息，让这套支付系统完完全全的为你服务。

> 申请使用这套支付系统：*<ppsupport@yeepay.com>*

```java

/**
 * 商户的用户名密码：appKey & appSecret
 */
String appKey = "";
String appSecret = "";

PayplusConnector payplusConnector = new PayplusConnector(appKey, appSecret);

//实例化一个注册对象
UserRegisterReq userRegisterReq = new UserRegisterReq();
userRegisterReq.setMerchantUserId("Joey");

//调用
payplusConnector.call(PayplusURI.USER_REGISTER, userRegisterReq);

```

#### 一行代码接入[Payplus](http://payplus.yeepay.com)服务
> 这已经是一个极简的调用了，当然我们还能再做一些减法。

```java

new PayplusConnector().call(PayplusURI.USER_REGISTER, new UserRegisterReq(null, null, "Joey")).print();

```

## 二、接入指南

#### 请求参数

目前，我们提供了BO和Map两种方式来进行参数传递。

> *推荐使用 BO（Business Object）来定义参数，至少BO内部提供了一些枚举值可以让你的程序更健壮。*  
> *当然你也可以继承父类 BaseBO 去开发属于你自己的业务实体Bean。*

```java

UserRegisterReq userRegisterReq =new UserRegisterReq();

userRegisterReq.setRequestNo(null);
userRegisterReq.setMerchantNo(null);
userRegisterReq.setMerchantUserId("Joey");

/**

Map<String, String> params = new HashMap<String, String>();

params.put("requestNo", null);
params.put("merchantNo", null);
params.put("merchantUserId", "Joey");

**/

```

#### 响应参数

服务返回的对象*Trophy* 整合了一些工具方法和一些便捷的变量访问
> KeyInfo 这个变量可以让你更便捷地使用我们的服务。

```java
public class Trophy {
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
    private String response;

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

#### 一些细节

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

* 使用符合[Payplus](http://payplus.yeepay.com)要求的标准时间格式

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

>*PayplusURI* 定义了全部可调用的服务，详参[接口对照表](https://github.com/sharq34/pp-sdk/blob/master/%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3%E5%92%8CPayplusURI%E5%AF%B9%E7%85%A7%E8%A1%A8.xlsx)。

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

* 业务枚举值  

> BO（Business Object）如 *UserVerifyPWDReq* 定义了一些变量的枚举值。

```java
public class UserVerifyPWDReq extends BaseBO {

    /**
     * tokenBizType 枚举值
     */
     public static final String TOKENBIZTYPE_TRANSFER = "ORDER_TRANSFER";
     public static final String TOKENBIZTYPE_WITHDRAW = "WITHDRAW";
     public static final String TOKENBIZTYPE_UN_BIND_CARD = "UN_BIND_CARD";

    //其他变量及set&get方法
}
```

#### 参考[DEMO](https://github.com/sharq34/pp-sdk/tree/master/src/test/java)
