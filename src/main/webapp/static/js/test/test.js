;
$(function () {
    var contextPath = $('#contextPath').data('contextpath');
    function postData() {
        var deviceId = $('input[name=deviceId]').val();
        $.ajax({url: contextPath + '/data/post/' + deviceId + '/' + '0F30'+(Math.floor(Math.random()*1000)+1000)+'1111222233334444555566667777'
        });
        if ($('input[name=doGet]').is(':checked')) {
            $.ajax({url: contextPath + '/data/get/' + deviceId + '/1'});
        }
        var delay = $('input[name=delay]').val();
        setTimeout(postData, delay);
    }
    setTimeout(postData, 1000);
});