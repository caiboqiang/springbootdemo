<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
 <html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>单文件上传</title>
</head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>


<script type="text/javascript">
    var heartflag = false;
    var webSocket = null;
    var tryTime = 0;
    var Base64 = null;
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
        var img_upload = document.getElementById("img_upload");
        var base64_code = document.getElementById("base64_code");
        var img_area = document.getElementById("img_area");
        img_upload.addEventListener('change', readFile, false);
        webSocket.send(message);
        alert(Base64)
        webSocket.send(Base64);
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



 /*   window.onload = function() {
       var img_upload = document.getElementById("img_upload");
        var base64_code = document.getElementById("base64_code");
        var img_area = document.getElementById("img_area");
        img_upload.addEventListener('change', readFile, false);
    }*/

    function readFile() {
        var file = this.files[0];
        if (!/image\/\w+/.test(file.type)) {
            alert("请确保文件为图像类型");
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e) {
            var data = e.target.result;
            var image = new Image();
            image.onload = function() {
                var width = image.width;
                var height = image.height;
                var info = `width=${width},height=${height},size=${file.size}`;

                document.getElementById("info").innerHTML = info;
            }
            image.src = data;
            Base64 = this.result
                base64_code.innerHTML = this.result;
            img_area.innerHTML = '<img src="' + this.result + '" alt=""/>';
        }
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

======================图片===================================
<div >
    <span>图片属性：</span>
    <span id="info"></span>
</div>
<div class="a c">
    <p>base64编码：</p>
    <textarea id="base64_code" rows="20" cols="60" class="a b"></textarea>
</div>
<div class="a c" style="width:445px;height:365px;">
    <p>图片展示：</p>
    <div id="img_area"></div>
</div>

<div class="d">
    <input type="file" id="img_upload" />
</div>
</body>

</body>
</html>

