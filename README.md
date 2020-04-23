# androidOpusEncode
android音频opus编码插件，引用该插件：  
1、在project的build.gradle中添加   
<pre name="code" class="java">
allprojects {
    repositories {
        ......
        maven { url 'https://jitpack.io' }
    }
}
</pre>
2、在module的build.gradle中添加  
<pre name="code" class="java">
implementation 'com.github.xiyy:androidOpusEncode:tag'
</pre>
3、拷贝so到module的libs目录中  
