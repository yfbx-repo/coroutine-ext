# Coroutine Extention
[![](https://img.shields.io/badge/release-1.0.0-blue.svg)](https://github.com/yfbx-repo/coroutine-ext/releases)     

协程扩展，主要用于retrofit网络请求


#### 添加依赖
```
repositories {
	maven { url 'https://jitpack.io' }
}
```

```
dependencies {
	implementation 'com.github.yfbx:coroutine-ext:1.0.0'
}
```

#### 使用方法

 1. **定义retrofit API接口, 使用关键字`suspend` 返回结果直接就是你需要的Bean类型**
```
interface UserApi {

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(@Field("account") mobile: String, @Field("password") password: String): User

}
```

 2. **需要在`LifecycleOwner` 或者 `CoroutineScope` 的子类中使用以下扩展方法**

```
   /**
    * network
    */
   private fun login() = network {
        val user = Net.create<UserApi>().login("18888888888", "123456")
        //UI
        infoTxt.append("Response：${user}")
    }

   /**
    * network with loading
    */
   private fun login() {
        loading {
            val user = Net.create<UserApi>().login("18888888888", "123456")
            //UI
            infoTxt.append("Response：${user}")
        }.onError { code, msg ->
            //error
            infoTxt.append("code：$code , message:$msg")
        }
    }
```
