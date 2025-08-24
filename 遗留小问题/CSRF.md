---
up:
  - "[[SpringSecurity 課程描述]]"
---
CSRF 是指跨站请求伪造（Cross-site request forgery），是 web 常见的攻击之一。

​https://blog.csdn.net/freeking101/article/details/86537087

​SpringSecurity 去防止 CSRF 攻击的方式就是通过 csrf_token。后端会生成一个 csrf_token，前端发起请求的时候需要携带这个 csrf_token，后端会有过滤器进行校验，如果没有携带或者是伪造的就不允许访问。

​我们可以发现 CSRF 攻击依靠的是 cookie 中所携带的认证信息。但是在前后端分离的项目中我们的认证信息其实是 token，而 token 并不是存储中 cookie 中，并且需要前端代码去把 token 设置到请求头中才可以，所以 CSRF 攻击也就不用担心了。
