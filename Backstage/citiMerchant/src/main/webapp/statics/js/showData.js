$(function () {
    // 基于准备好的dom，初始化echarts实例
    var order = echarts.init(document.getElementById('order-table'));
    var point = echarts.init(document.getElementById('point-table'));
    var pie = echarts.init(document.getElementById('pie-table'));
// 指定图表的配置项和数据
    var orderOption = {
        tooltip: {},
        legend: {
            data:['消费情况']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '消费情况',
            type: 'line',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };

    var pointOption = {
        tooltip: {},
        legend: {
            data:['兑换情况']
        },
        xAxis: {
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
            name: '兑换情况',
            type: 'line',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };
    var pieOption = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['订单积分使用','会员卡积分兑换','优惠券积分使用']
        },
        series: [
            {
                name:'使用情况',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:335, name:'订单积分使用'},
                    {value:310, name:'会员卡积分兑换'},
                    {value:234, name:'优惠券积分使用'},
                ]
            }
        ]
    };

    pie.setOption(pieOption);
    point.setOption(pointOption)
    order.setOption(orderOption);

    $.get("tableData",function (data,status) {
        if (status=="success"){
            var tableData=$.parseJSON(data);
            var dataArray=new Array();
            for (var p in tableData.xAxis) {
                dataArray.push(timestampToTime( tableData.xAxis[p]));
            };
            order.setOption({
                xAxis: {
                    data: dataArray
                },
                series: [{
                    name: '消费情况',
                    data: tableData.points_exchange
                }]
            });

            point.setOption(
                {
                    xAxis: {
                        data: dataArray
                    },
                    series: [{
                        name: '兑换情况',
                        data: tableData.points
                    }]
                }
            );

            pie.setOption(
                {
                    series: [
                        {
                            data: [
                                {value: tableData.total_order_points, name: '订单积分使用'},
                                {value: tableData.total_points_exchange, name: '会员卡积分兑换'},
                                {value: tableData.total_merchant_coupon_record, name: '优惠券积分使用'},
                            ]
                        }]
                }
            )
        }
    })
});

function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate();
    return Y+M+D;
}
