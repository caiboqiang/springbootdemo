<%--
  Created by IntelliJ IDEA.
  User: caiboqiang
  Date: 2018/12/24
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://vjs.zencdn.net/5.20.1/video-js.css" rel="stylesheet">
    <script src="http://vjs.zencdn.net/5.20.1/videojs-ie8.min.js"></script>
</head>
<body>

<video id="example_video_1" class="video-js vjs-default-skin" controls preload="auto" width="1280" height="720" poster="http://vjs.zencdn.net/v/oceans.png" data-setup="{}">
    <!-- <source src="1.mp4" type="video/mp4"> -->
    <source src="rtmp://192.168.100.180:1935/live/4321" type="rtmp/flv">

    <p class="vjs-no-js">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a></p>
</video>

<script src="http://vjs.zencdn.net/5.20.1/video.js"></script>
</html>
