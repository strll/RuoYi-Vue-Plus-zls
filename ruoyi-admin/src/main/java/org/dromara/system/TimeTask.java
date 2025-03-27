package org.dromara.system;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.anyline.annotation.Autowired;
import org.dromara.system.domain.Sensor;
import org.dromara.system.domain.WaterMonitoringData;
import org.dromara.system.domain.bo.WaterMonitoringDataBo;
import org.dromara.system.mapper.SensorMapper;
import org.dromara.system.mapper.WaterMonitoringDataMapper;
import org.dromara.system.service.IWaterMonitoringDataService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
@RequiredArgsConstructor
@Configuration
@EnableAsync
@Service
@Validated
public class TimeTask {


    // 定义权重数组：前60%索引对应70+区间，后40%对应1-69区间
    private static final int[] WEIGHTS = new int[100];

    static {
        Arrays.fill(WEIGHTS, 0, 60, 1);  // 70+权重占60%
        Arrays.fill(WEIGHTS, 60, 100, 0); // 1-69权重占40%
    }

    public static long generate() {
        int index = RandomUtil.randomInt(WEIGHTS.length);
        return (WEIGHTS[index] == 1) ?
            RandomUtil.randomLong(70, 101) :
            RandomUtil.randomLong(1, 70);
    }


    private final WaterMonitoringDataMapper baseMapper;

    private final SensorMapper sensorMapper;

    private final IWaterMonitoringDataService iWaterMonitoringDataService;


    private Double DoubleMethod(Double mydouble){
        BigDecimal bd = new BigDecimal(String.valueOf(mydouble)); // 避免精度丢失[4,8](@ref)
        bd = bd.setScale(2, RoundingMode.DOWN);              // 截断至后两位
        return bd.doubleValue();
    }

    @Async // 标注异步执行
    @Scheduled(cron = "0 0/5 * * * ?")
    public void asyncTask() {
        // 长时间任务逻辑
        List<Sensor> sensors = sensorMapper.selectList();
        sensors.forEach(item -> {
            Long id = item.getId();
            WaterMonitoringDataBo waterMonitoringData = new WaterMonitoringDataBo();

            waterMonitoringData.setTimestamp(new Date());
            waterMonitoringData.setSensorId(String.valueOf(id));
            //pressure 水压（MPa）
            SecureRandom secureRand = new SecureRandom();
            double randomValue = 0.07 + (0.2 - 0.07) * secureRand.nextDouble();
            waterMonitoringData.setPressure(DoubleMethod(randomValue) );

            //flow_rate 水流量（L/min）

            double velocity = ThreadLocalRandom.current().nextDouble(0.8, 2.0);
            waterMonitoringData.setFlowRate(DoubleMethod(velocity));
            //quality_index 水质指数（0-100）
            int count70Plus = 0;
            int total = 1_000_000;

            for (int i = 0; i < total; i++) {
                if (generate() >= 70) count70Plus++;
            }

            double v = count70Plus * 100.0 / total;

            waterMonitoringData.setQualityIndex(DoubleMethod(v));
            iWaterMonitoringDataService.insertByBo(waterMonitoringData);
        });


    }
}


