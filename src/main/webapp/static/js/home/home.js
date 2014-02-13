;
$(function () {
    var contextPath = $('#contextPath').data('contextpath');
    var data = [];
    $.plot('#chart-holder', [data]);
    var i = 1;

    function retrievData(){
        var deviceId = $('input[name=deviceId]').val();
        $.ajax({
            url: contextPath + '/data/get/' + deviceId + '/1',
            dataType: "json",
            success: function(response){
                if (data.length > 10) {
                    data.shift();
                }
                data.push([i++, response]);
                repaintChart();
            }
        });
        setTimeout(retrievData, 1000);
    }
    setTimeout(retrievData, 1000);

    function repaintChart() {
        $.plot('#chart-holder', [data], {
            xaxis: {
                tickFormatter: function () {
                    return "";
                }
            },
            yaxis: {
                min: 4000,
                max: 8000 }
        });
    }

});