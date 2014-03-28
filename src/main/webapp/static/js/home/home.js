;
$(function () {
    var contextPath = $('#contextPath').data('contextpath');
    var deviceId = $('input[name=deviceId]').val();
    var data = [];
    var totalPoints = 1300;
    var sensorData = 100;
    var updateInterval = 300;
    var plot = $.plot($('#chart-placeholder'), [{color: 'green', data: getData()}], {
        series: {
            lines: {show: true, fill: 0.15, lineWidth: 4}
        }
    });

    function getData() {
        if (data.length > 0)
            data = data.slice(1);

        while (data.length < totalPoints) {
            data.push(sensorData);
        }
        var result = [];
        for (var i = 0; i < data.length; i++) {
            result.push([i, data[i]])
        }
        return result;
    }

    function update() {
        plot.setData([
            {color: 'green', data: getData()}
        ]);
        plot.draw();
        setTimeout(update, updateInterval);
    }

    update();

    //SSE
    var source = new EventSource(contextPath + '/data/get/SSE/' + deviceId);
    source.onmessage = function (event) {
        document.getElementById('currentPowerHolder').innerHTML = event.data;
        sensorData = event.data / 100;
    };
});