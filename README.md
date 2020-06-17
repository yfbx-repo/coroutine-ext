# Coroutine Extention
[![](https://img.shields.io/badge/release-1.0.0-blue.svg)](https://github.com/yfbx-repo/coroutine-ext/releases)

### Coroutine Extention For Retrofit2
branch master is based on:
```
 org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.6
```
branch lifecycle is based on:
```
 androidx.lifecycle:lifecycle-runtime-ktx:2.3.0-alpha03
```


#### Add the dependency
```
repositories {
	maven { url 'https://jitpack.io' }
}
...
dependencies {
	implementation 'com.github.yfbx:coroutine-ext:1.0.1-lifecycle'
}
```

#### How to use

 1. **Define api with keyword `suspend` and the return type is just what you want**
```
interface UserApi {

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(@Field("account") mobile: String, @Field("password") password: String): User

}
```

2. **Use extention in subtype of `LifecycleOwner`**

```

   private fun login() = network {
        val user = Net.create<UserApi>().login("18888888888", "123456")
        infoTxt.append("Response：${user}")
    }

   private fun login() {
        loading {
            val user = Net.create<UserApi>().login("18888888888", "123456")
            infoTxt.append("Response：${user}")
        }.onError { code, msg ->
            infoTxt.append("code：$code , message:$msg")
        }
    }
```
