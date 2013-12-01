;
$(function () {
    var contextPath = $('#contextPath').data('contextpath');
    function postData() {
        var deviceId = $('input').val();
        $.ajax({url: contextPath + '/data/post?deviceId=' + deviceId + '&data=' + Math.floor(Math.random()*10)
        });
        setTimeout(postData, 1000);
    }
    setTimeout(postData, 1000);
});