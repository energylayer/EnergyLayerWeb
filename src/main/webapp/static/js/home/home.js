;
$(function () {
    var contextPath = $('#contextPath').data('contextpath');
    var deviceId = $('input[name=deviceId]').val();
    var totalPoints = 1300;
    var data = [];
    var sensorData = 100;
    var realTimeTimeout;
    var plot;
    var eventSource;

    (function init(){
        initUI();
        bingEvents();
    })();
    function initUI(){
        initChart();
    }
    function bingEvents(){
        $('#realTime').on('click', function(){
            var $this = $(this);
            if ($this.hasClass('active')){
                return;
            }
            $this.addClass('active');
            $('#hourly').removeClass('active');
            initUI();
        });
        $('#hourly').on('click', function(){
            var $this = $(this);
            if ($this.hasClass('active')){
                return;
            }
            $this.addClass('active');
            $('#realTime').removeClass('active');
            drawHourly();
        });
    }
    function drawHourly(){
        clearTimeout(realTimeTimeout);
        eventSource.close();
        $.ajax({
            url: contextPath + '/rs/data/get/aggregated/' + deviceId + '/hour',
            dataType: "json",
            success: function(response){
                plot = $.plot($('#chart-placeholder'), [{color: 'green', data: mapData(response.data)}], {
                    series: {
                        lines: {show: true, fill: 0.15, lineWidth: 4}
                    }
                });
            }
        });
    }
    function mapData(data){
        var result = [];
        for (var i = data.length; i > 0; i--) {
            result.push([i, (data[i]/300)])
        }
        return result;
    }
    function initChart(){
        plot = $.plot($('#chart-placeholder'), [{color: 'green', data: getData()}], {
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
            realTimeTimeout = setTimeout(update, 300);
        }
        update();

        eventSource = new EventSource('/rs/data/get/' + deviceId);
        eventSource.onmessage = function (event) {
            document.getElementById('currentPowerHolder').innerHTML = event.data;
            sensorData = event.data / 100;
        };
    }
});