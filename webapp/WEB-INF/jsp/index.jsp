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

<%--<%@include file="test.html"%>--%>

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
<%--iphone 6 的 REM 为 20px--%>
<!-- page集合的容器，里面放多个平行的.page，其他.page作为内联页面由路由控制展示 -->
<div class="page-group">
    <%--每个 .page 都是一个页面，加载后第一页面为 .page-current。每个页面有一个 ID，使用 `<a href='#{page-id}'>跳转</a>` 跳转--%>
    <div class="page page-current" id="index">
        <div class="content">
            <div class="row">
                <div class="col-20">
                    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                </div>
                <div class="col-60" style="text-align: center">
                    <%--元素四周均有留白--%>
                    <div class="content-padded">
                        <img src="/images/logo.png" style="opacity: 0.03" alt="蜘蛛众筹">
                    </div>
                </div>
                <div class="col-20">

                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-20">
                    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                </div>
                <div class="col-60">
                    <input type="text" placeholder="****" class="validCode" style="text-align: center;display: inline-block;height: 2.4rem;padding: 0 0.5rem;line-height: 2.4rem;color: #4cd964;-webkit-border-radius: 1.25rem;-moz-border-radius: 1.25rem;border-radius: 1.25rem;vertical-align: middle;background-color: #ffffff;border: 1px solid #cccccc;-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);-moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);-webkit-transition: border linear .2s, box-shadow linear .2s;-moz-transition: border linear .2s, box-shadow linear .2s;-o-transition: border linear .2s, box-shadow linear .2s;transition: border linear .2s, box-shadow linear .2s;padding-top: 2px;padding-bottom: 2px;">
                </div>
                <div class="col-20">

                </div>
            </div>
            <div class="row">
                <div class="col-20">
                    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                </div>
                <div class="col-60">
                    <%--通过 #{page-id} 跳转到指定 page--%>
                    <%--将页面缓存起来--%>
                    <p><a href="#mainCategory" class="createTable button button-fill button-success button-big button-round">开桌</a></p>
                </div>
                <div class="col-20">

                </div>
            </div>
        </div>
    </div>

    <div class="page">
        <header class="bar bar-nav">
            <h1 class='title'>侧栏</h1>
        </header>
        <div class="content">
            <div class="content-block">
                <p><a href="#" class="button button-fill open-panel" data-panel='#panel-left-demo'>打开左侧栏</a></p>
            </div>
        </div>
    </div>

    <div class="page" id="mainCategory">
        <header class="bar bar-nav">
            <div class="button pull-left">
                <span class="generatedValidCode open-panel"></span>
            </div>
            <button class="button pull-right">
                <span class="icon icon-cart"></span>
            </button>
        </header>
        <div class="content">
            <div class="content-padded" style="text-align: center">
                <h1 class="mainCategoryRName"></h1>
                <h2 class="mainCategoryTableCode"></h2>
            </div>
            <div class="row">
                <div class="col-50">
                    <div class="card demo-card-header-pic">
                        <div valign="bottom" class="card-header color-white no-border no-padding">
                            <img class='card-cover' src="//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-50">
                    <div class="card demo-card-header-pic">
                        <div valign="bottom" class="card-header color-white no-border no-padding">
                            <img class='card-cover' src="//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg" alt="">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-50">
                    <div class="card demo-card-header-pic">
                        <div valign="bottom" class="card-header color-white no-border no-padding">
                            <img class='card-cover' src="//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-50">
                    <div class="card demo-card-header-pic">
                        <div valign="bottom" class="card-header color-white no-border no-padding">
                            <img class='card-cover' src="//gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- popup, panel 等放在这里 -->
<div class="panel-overlay"></div>
<div class="panel panel-left panel-reveal theme-dark" id="slider">
    <div class="card">
        <div class="card-content">
            <div class="list-block media-list">
                <ul>
                    <li class="item-content">
                        <div class="item-media">
                            <img src="http://gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg" width="44">
                        </div>
                        <div class="item-inner">
                            <div class="item-title-row">
                                <div class="item-title">这里应该是用户名</div>
                            </div>
                            <div class="item-subtitle">这里是性别或其它</div>
                        </div>
                    </li>
                    <li class="item-content">
                        <div class="item-inner">
                            <h3 class="mainCategoryRName"><span>餐厅名：</span></h3>
                            <h4 class="mainCategoryTableCode"><span>桌号：</span></h4>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="sliderCategory list-block">
        <ul>

        </ul>
    </div>
    <div class="list-block">
        <ul>
            <li class="item-content">
                <div class="item-inner">
                    <div class="item-title">更多分类</div>
                    <div class="item-after">杜蕾斯极致超薄型</div>
                </div>
            </li>
            <li class="item-content">
                <div class="item-inner">
                    <div class="item-title">七夕干嘛</div>
                    <div class="item-after">美食记忆</div>
                </div>
            </li>
        </ul>
    </div>
    <div class="content-block">
        <p>用户设置</p>
        <p><a href="#" class="close-panel">关闭</a></p>
    </div>
</div>

<script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.js' charset='utf-8'></script>
<%--SUI 默认使用了路由功能--%>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.js' charset='utf-8'></script>
<script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.js' charset='utf-8'></script>
<script id="mainScript">
    // 页面初始化，需要在所有的 pageInit 事件绑定之后再调用 $.init() 方法
    $.init()
    $.config = {
        swipePanel: "left"
    }
    $('.validCode').width($('.createTable').width())
    $('.validCode').on('keyup', function () {
        if ($(this).val().length === 4) {
            // 加载内联页面
            $.router.load("#mainCategory")
        }
    })
    // 新页面的 DOM 插入当前页面之后，动画执行之前
    $(document).on("pageAnimationStart", "#mainCategory", function(e, pageId, $page) {
        $.showPreloader('左上角为验证码<br>点击验证码打开/关闭用户中心')
        var start = new Date().getTime()
        $.ajax({
            type: 'GET',
            url: '/01A1/date.do?userId=1&validCode=' + $('.validCode').val(),
            success: function (data) {
                $('.generatedValidCode').text(data.validCode)
                $('.mainCategoryRName').append(data.rName)
                $('.mainCategoryTableCode').append(data.tableCode)
                var lis = ''
                var pages = ''
                data.rCategory.forEach(function (item, index) {
                    lis += '<li class="item-content"><div class="item-inner"><div class="item-title"><a href="#' + item + '">' + item + '</a></div></div></li>'
                    pages += '<div class="page" id="' + item + '"><header class="bar bar-nav"><div class="button pull-left"><span class="generatedValidCode open-panel">' + data.validCode + '</span></div><button class="button pull-right"><span class="icon icon-cart"></span></button></header><div class="content"></div></div>'
                    $('body').append('<script>$(document).on("pageAnimationStart", "#' + item + '", function(e, pageId, $page) {getGoods("' + item + '");console.log("呵呵呵")})<\/script>')
                })
                $('.sliderCategory').find('ul').append(lis)
                $('.page-group').append(pages)
                var time = setInterval(function () {
                    var end = new Date().getTime()
                    console.log(end - start)
                    if (end - start > 1000) {
                        $.hidePreloader()
                        clearInterval(time)
                    }
                }, 100)
            },
            error: function (err) {
                alert('主页面：' + err)
            }
        })
    })
//    $(document).on("beforePageSwitch", "#' + item + '", function(e, pageId, $page) {$.closePanel("#slider")});
    var getGoods = function (category) {
        $('.page-group').css({opacity: 0})
        $.showPreloader('正在加载...')
        var time = setTimeout(function () {
            var bool = $.closePanel("#slider")
            console.log(bool)
            $.hidePreloader()
            clearInterval(time)
            $('.page-group').css({opacity: 1})
        }, 1000)
/*        console.log('关闭侧边栏')
        var bool = $.closePanel("#slider")
        console.log(bool)
        var open = $.openPanel("#slider");
        console.log(open)*/
/*        $(document).on("click", ".generatedValidCode", function() {
            console.log('打开侧边栏')
            $.openPanel("#slider");
        });*/
        $.ajax({
            type: 'GET',
            url: '/category.do?restaurantId=2&category=' + category,
            success: function (data) {
                console.log(data)
                var content = '<div class="list-block media-list"> <ul>'
                for (var key in data.dishes) {
                    console.log(key)
                    content += '<li><a href="#" class="item-link item-content"><div class="item-media"><img src="http://gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg" style="width: 4rem;"></div><div class="item-inner"><div class="item-title-row"><div class="item-title">标题</div><div class="item-after">$15</div></div><div class="item-subtitle">标题</div><div class="item-text">此处是文本内容...</div></div></a></li>'
                }
                content += '</ul></div>'
                console.log(content)
                $('#' + category).find('.content').append(content)
            },
            error: function (err) {
                alert('分类详情：' + err)
            }
        })
    }
</script>
</body>
</html>
