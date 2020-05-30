# Coroutine Extention

Coroutine Extention For Retrofit2

#### Add the dependency
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

#### How to use

 1. **Define api with aeyword `suspend` and the return type is just what you want**
```
interface UserApi {

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(@Field("account") mobile: String, @Field("password") password: String): User

}
```

 2. **Use extion in subtype of `LifecycleOwner` or `CoroutineScope`**

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
