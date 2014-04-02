;
$(function () {
    var contextPath = $('#contextPath').data('contextpath');
    var totalPoints = 1300;
    var data = [];
    var sensorData = 100;
    var realTimeTimeout;
    var plot;
    var updateCount = 0;

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
        var deviceId = $('input[name=deviceId]').val();
        var sensorNumber = $('input[name=sensorNumber]').val();
        $.ajax({
            url: contextPath + '/rs/data/get/aggregated/' + deviceId + '/hour?sensorNumber=' + sensorNumber,
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
            var deviceId = $('input[name=deviceId]').val();
            var sensorNumber = $('input[name=sensorNumber]').val();
            if (updateCount == 9) {
                $.ajax({
                    url: contextPath + '/rs/data/get/' + deviceId + '?sensorNumber=' + sensorNumber,
                    dataType: "json",
                    success: function (response) {
                        document.getElementById('currentPowerHolder').innerHTML = response.data;
                        sensorData = response.data / 100;
                    }
                });
                updateCount = 0;
            }
            updateCount++;
            plot.setData([
                {color: 'green', data: getData()}
            ]);
            plot.draw();
            realTimeTimeout = setTimeout(update, 300);
        }
        update();
    }
});