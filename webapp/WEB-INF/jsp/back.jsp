<%--
  Created by IntelliJ IDEA.
  User: temp
  Date: 2016/7/15
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>LOGGED IN</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="shortcut icon" href="/favicon.ico">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.css">
</head>
<body>

<div class="page-group">
    <%--每个 .page 都是一个页面，加载后第一页面为 .page-current。每个页面有一个 ID，使用 `<a href='#{page-id}'>跳转</a>` 跳转--%>
    <div class="page page-current" id="mountain">
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left" href="/demos/card" data-transition='slide-out'>
                <span class="icon icon-left"></span>
                返回
            </a>
            <h1 class="title">我的生活</h1>
        </header>
        <nav class="bar bar-tab">
            <a class="tab-item active" href="#">
                <span class="icon icon-home"></span>
                <span class="tab-label">首页</span>
            </a>
            <a class="tab-item" href="#">
                <span class="icon icon-settings"></span>
                <span class="tab-label">设置</span>
            </a>
        </nav>
        <div class="content">
            <div class="page-index">
                <div class="card">
                    <div style="background-image:url(//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250.3.0q60.jpg)"
                         valign="bottom" class="card-header color-white no-border">旅途的山
                    </div>
                    <div class="card-content">
                        <div class="card-content-inner">
                            <p class="color-gray">发表于 2015/01/15</p>
                            <p>此处是内容...</p>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="#" class="link">赞</a>
                        <a href="#" class="link">更多</a>
                    </div>
                </div>
                <div class="card">
                    <div class="content-padded">
                        <c:choose>
                            <c:when test="${empty loggedInUser.openId}">
                                <a href="login">Login</a>
                            </c:when>
                            <c:otherwise>
                                <p>Hello, user ${loggedInUser.openId} !<a href="logout">Logout</a></p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="card">
                    <div class="content-padded">
                        <a href="#tool" data-no-cache="true">跳转到下一页</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="page" id="tool">
        <!-- 标题栏 -->
        <header class="bar bar-nav">
            <%--在任意元素上添加 back 类，自动调用 history.back--%>
            <%--或者通过 $.router.back() 调用--%>
            <a class="button button-link button-nav pull-left back" href="#">
                <span class="icon icon-left"></span>
                返回
            </a>
            <h1 class='title'>路由</h1>
        </header>
        <!-- 工具栏 -->
        <nav class="bar bar-tab">
            <a class="tab-item external active" href="#">
                <span class="icon icon-home"></span>
                <span class="tab-label">首页</span>
            </a>
            <a class="tab-item external" href="#">
                <span class="icon icon-star"></span>
                <span class="tab-label">收藏</span>
            </a>
            <a class="tab-item external" href="#mountain">
                <span class="icon icon-settings"></span>
                <span class="tab-label">设置</span>
            </a>
        </nav>
        <!-- 这里是页面内容区 -->
        <div class="content">
            <div class="content-block">
                <ul>
                    <li><a href="/docs-demos/router2">ajax加载新页面</a></li>
                    <%--$.router.load('/detail', true) 表示不缓存该页面--%>
                    <li><a href="/detail" data-no-cache="true">不使用缓存，否则默认缓存该页面</a></li>
                    <%--内联页面，用于返回很别扭啊，还是用来跳转页面吧--%>
                    <li><a href="#textStyle" data-no-cache="true">跳转到文本样式页</a></li>
                </ul>
            </div>
            <div class="content-block">
                这是内联编写的页面，点击左上角的 <a href="#" class='back'>返回</a> 按钮返回上一页。
            </div>
        </div>
    </div>
    <div class="page" id="textStyle">
        <!-- 标题栏 -->
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left back" href="#">
                <span class="icon icon-left"></span>
                返回
            </a>
            <a class="icon icon-refresh pull-right"></a>
            <button class="button button-link button-nav pull-right">
                下一步
                <span class="icon icon-right"></span>
            </button>
            <h1 class="title">标题</h1>
        </header>
        <!-- 工具栏 -->
        <nav class="bar bar-tab">
            <a class="tab-item external active" href="#">
                <span class="icon icon-home"></span>
                <span class="tab-label">首页</span>
            </a>
            <a class="tab-item external" href="#">
                <span class="icon icon-star"></span>
                <span class="tab-label">文本</span>
                <span class="badge">2</span>
            </a>
            <a class="tab-item external" href="#mountain">
                <span class="icon icon-settings"></span>
                <span class="tab-label">设置</span>
            </a>
        </nav>
        <!-- 这里是页面内容区 -->
        <div class="content">
            <div class="content-padded">
                <h1>标题一</h1>
                <h6>标题六</h6>
                <p>这是一个段落这是一个段落这是一个段落这是一个段落这是一个段落这是一个段落这是一个段落这是一个段落这是一个段落这是一个段落这是一个段落这是一个段落这是一个段落</p>
            </div>
            <div class="content-block">
                <p>这是内联编写的页面，点击左上角的 <a href="#" class='back'>返回</a> 按钮返回上一页。</p>
                <p>content-padded 和 content-block 的内边距不一样</p>
            </div>
        </div>
    </div>
</div>

<!-- popup, panel 等放在这里 -->
<div class="panel-overlay">
    <!-- 如果有 popup -->
    <div class="popup popup-about">
        <p>这是 popup</p>
    </div>
</div>
<!-- Left Panel with Reveal effect -->
<div class="panel panel-left panel-reveal">
    <div class="content-block">
        <p>这是一个侧栏</p>
        <p></p>
        <!-- Click on link with "close-panel" class will close panel -->
        <p><a href="#" class="close-panel">关闭</a></p>
    </div>
</div>

<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.js' charset='utf-8'></script>
<script>
    $.init()
    // alert(JSON.stringify($.device))
</script>
</body>
</html>
