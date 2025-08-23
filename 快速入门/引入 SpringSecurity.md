---
up:
  - "[[SpringSecurity 課程描述]]"
---
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

引入依赖后我们在尝试去访问之前的接口就会自动跳转到一个 SpringSecurity 的默认登陆页面，默认用户名是 user，密码会输出在控制台。

​必须登陆之后才能对接口进行访问。