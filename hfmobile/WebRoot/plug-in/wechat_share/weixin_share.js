/**
 * 微信分享网页的缩略图、链接、标题和摘要
 */

var shareTitle = $("#_wx_share_title").val();
var descContent = $("#_wx_share_desc_content").val();
var imgUrl = $("#_wx_share_img_url").val();
var lineLink = $("#_wx_share_link").val();

function shareFriend() {
    WeixinJSBridge.invoke('sendAppMessage', {
        "img_url": imgUrl,
        "img_width": "640",
        "img_height": "640",
        "link": lineLink,
        "desc": descContent,
        "title": shareTitle
    }, function(res) {
        _report('send_msg', res.err_msg);
    });
}
function shareTimeline() {
    WeixinJSBridge.invoke('shareTimeline', {
        "img_url": imgUrl,
        "img_width": "640",
        "img_height": "640",
        "link": lineLink,
        "desc": descContent,
        "title": shareTitle
    }, function(res) {
        _report('timeline', res.err_msg);
    });
}
function shareWeibo() {
    WeixinJSBridge.invoke('shareWeibo', {
        "content": descContent,
        "url": lineLink,
    }, function(res) {
        _report('weibo', res.err_msg);
    });
}
//当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {

    // 发送给好友
    WeixinJSBridge.on('menu:share:appmessage', function(argv) {
        shareFriend();
    });

    // 分享到朋友圈
    WeixinJSBridge.on('menu:share:timeline', function(argv) {
        shareTimeline();
    });

    // 分享到微博
    WeixinJSBridge.on('menu:share:weibo', function(argv) {
        shareWeibo();
    });
}, false);

// 总是要显示右上角的分享
function onBridgeReady() {
    WeixinJSBridge.call('showOptionMenu');
}

if (typeof WeixinJSBridge == "undefined") {
    if (document.addEventListener) {
        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
    } else if (document.attachEvent) {
        document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
    }
} else {
    onBridgeReady();
}

