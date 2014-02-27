;
$(function () {
    var contextPath = $('#contextPath').data('contextpath');
    function postData() {
        var deviceId = $('input').val();
        $.ajax({url: contextPath + '/data/post/' + deviceId + '/' + '0F30'+(Math.floor(Math.random()*1000)+1000)+'1111222233334444555566667777'
        });
        setTimeout(postData, 1000);
    }
    setTimeout(postData, 1000);
});