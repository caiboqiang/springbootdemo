<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
 <html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>单文件上传</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<%--<script>

    
    var socket;
    if(typeof(WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    }else{
        console.log("您的浏览器支持WebSocket");
        //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
        //等同于socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");
        /*socket = new WebSocket("${basePath}websocket/${cid}".replace("http","ws"));*/
        socket = new WebSocket("ws://localhost:8080/websocket/20");
        //打开事件
        socket.onopen = function() {
            console.log(" 已打开");Socket
            //socket.send("这是来自客户端的消息" + location.href + new Date());
        };
        //获得消息事件
        socket.onmessage = function(msg) {
            console.log(msg.data);
            //发现消息进入    开始处理前端触发逻辑
        };
        //关闭事件
        socket.onclose = function() {
            console.log("Socket已关闭");
        };
        //发生了错误事件
        socket.onerror = function() {
            alert("Socket发生了错误");
            //此时可以尝试刷新页面
        }
        //离开页面时，关闭socket
        //jquery1.8中已经被废弃，3.0中已经移除
        // $(window).unload(function(){
        //     socket.close();
        //});
    }
    window.onload=myfun;
</script>--%>

<script type="text/javascript">
    var heartflag = false;
    var webSocket = null;
    var tryTime = 0;
    /*$(function () {

//        initSocket();
        window.onbeforeunload = function () {

        };
    });*/
    window.onload=initSocket;
    /**
     * 初始化websocket，建立连接
     */
    function initSocket() {
      /*  var serveraddress = $("#serveraddress").val();
        var userId = $("#userId").val();*/

        if (!window.WebSocket) {
            $("#connectStatu").append(getNowFormatDate()+"  您的浏览器不支持ws<br/>");
            return false;
        }
        //webSocket = new WebSocket(serveraddress+"/"+userId);
        webSocket = new WebSocket("ws://localhost:8081/webSocket/555");

        // 收到服务端消息
        webSocket.onmessage = function (msg) {
            console.log(msg+"=========="+msg.data);
            console.log(getNowFormatDate());
            $("#receivedMessage").append(getNowFormatDate()+"  收到消息 : "+msg.data+"<br/>");
        };

        // 异常
        webSocket.onerror = function (event) {
            heartflag = false;
            $("#connectStatu").append(getNowFormatDate()+"  异常<br/>");
        };

        // 建立连接
        webSocket.onopen = function (event) {
            heartflag = true;
            //连接成功发送消息
            heart();
            $("#connectStatu").append(getNowFormatDate()+"  建立连接成功<br/>");
            tryTime = 0;
        };

        // 断线重连
        webSocket.onclose = function () {
            heartflag = false;
            // 重试10次，每次之间间隔10秒
            if (tryTime < 10) {
                setTimeout(function () {
                    webSocket = null;
                    tryTime++;
                    initSocket();
                    $("#connectStatu").append( getNowFormatDate()+"  第"+tryTime+"次重连<br/>");
                }, 3*1000);
            } else {
                alert("重连失败.");
            }
        };

    }

    function send(){
        var message = $("#message").val();
        var img = $("#img").val();

        webSocket.send(message);
        webSocket.send(img);
    }

    function clearConnectStatu(){
        $("#connectStatu").empty();
    }

    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    }
    //链接成功发送消息到服务端
    function heart() {
        if (heartflag){
            webSocket.send("666666");
            $("#heartdiv").append(getNowFormatDate()+"  心跳 <br/>");
        }
        setTimeout("heart()", 10*60*1000);

    }
</script>

<body>
================${h}

server地址 :  <input id ="serveraddress" type="text" /><br/>
您的用户id :  <input id ="userId" type="text" /><br/>
<button onclick="initSocket()">连接</button><br/>

=====================================================<br/>
消息 :  <input id ="message" type="text" /><br/>
图片 :  <input id="img" type="file"><br>
<button onclick="send()">发送</button><br/>
=====================================================<br/>
连接状态 : <button onclick="clearConnectStatu()">清空</button><br/>
<div id="connectStatu"></div><br/>

=====================================================<br/>
收到消息 :<br/>
<div id="receivedMessage"></div><br/>
=====================================================<br/>
心跳 :<br/>
<div id="heartdiv"></div><br/>



</body>
</html>

