package org.dromara.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.system.domain.WaterMonitoringData;
import org.dromara.system.domain.bo.SensorBo;
import org.dromara.system.domain.vo.SensorVo;
import org.dromara.system.mapper.SensorMapper;
import org.dromara.system.mapper.WaterMonitoringDataMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/zxt")
//折线图的接口
public class zxtController {

    private final WaterMonitoringDataMapper baseMapper;
    private final SensorMapper sensorMapper;

    @Operation(summary = "平均水压的接口")
    @GetMapping("/pjsy")
    @SaIgnore
    //平均水压的接口
    public Object list() {

        QueryWrapper<WaterMonitoringData> wrapper = new QueryWrapper<>();

        wrapper.select("DATE(timestamp) as date", "AVG(pressure) as avg_pressure")
            .ge("timestamp", LocalDateTime.now().minusDays(7))
            .groupBy("DATE(timestamp)")
            .orderByAsc("date");

        List<Map<String, Object>> maps = baseMapper.selectMaps(wrapper);

        ArrayList<String> timename = new ArrayList<>();

        ArrayList<Double> integers = new ArrayList<>();

        maps.forEach(item->{
            Object getdate = item.get("date");
            Object avg_pressure = item.get("avg_pressure");

            // 定义格式化模板
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 执行格式化
            String formattedDate = sdf.format(getdate);
            timename.add(formattedDate);
            Double aDouble = Convert.toDouble(avg_pressure);
            BigDecimal bd = new BigDecimal(Double.toString(aDouble));
            // 截断模式（RoundingMode.DOWN）直接舍弃第三位及之后的小数[2,5](@ref)
            bd = bd.setScale(2, RoundingMode.DOWN);
            integers.add(bd.doubleValue());
        });
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("date",timename);
        objectObjectHashMap.put("num",integers);
        return objectObjectHashMap;
    }


    @Operation(summary = "平均水质的接口")
    @GetMapping("/pjsz")
    @SaIgnore
    //平均水压的接口
    public Object pjsz() {

        QueryWrapper<WaterMonitoringData> wrapper = new QueryWrapper<>();

        wrapper.select("DATE(timestamp) as date", "AVG(pressure) as quality_index")
            .ge("timestamp", LocalDateTime.now().minusDays(7))
            .groupBy("DATE(timestamp)")
            .orderByAsc("date");

        List<Map<String, Object>> maps = baseMapper.selectMaps(wrapper);

        ArrayList<String> timename = new ArrayList<>();

        ArrayList<Double> integers = new ArrayList<>();

        maps.forEach(item->{
            Object getdate = item.get("date");
            Object avg_pressure = item.get("quality_index");

            // 定义格式化模板
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 执行格式化
            String formattedDate = sdf.format(getdate);
            timename.add(formattedDate);
            Double aDouble = Convert.toDouble(avg_pressure);
            BigDecimal bd = new BigDecimal(Double.toString(aDouble));
            // 截断模式（RoundingMode.DOWN）直接舍弃第三位及之后的小数[2,5](@ref)
            bd = bd.setScale(2, RoundingMode.DOWN);
            integers.add(bd.doubleValue());
        });
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("date",timename);
        objectObjectHashMap.put("num",integers);
        return objectObjectHashMap;
    }




    @Operation(summary = "当前水流的接口")
    @GetMapping("/dqsl")
    @SaIgnore

    public Object dqsl() {

        QueryWrapper<WaterMonitoringData> wrapper = new QueryWrapper<>();
        wrapper.apply(
                "(w.sensor_id, w.timestamp) IN (" +
                    "   SELECT sensor_id, MAX(timestamp) " +
                    "   FROM water_monitoring_data " +
                    "   GROUP BY sensor_id" +
                    ")"
            )
            .orderByDesc("w.timestamp");
        List<Map<String, Object>> result = baseMapper.selectLatestFlowRates(wrapper);
        return result;
    }



    @Operation(summary = "水质评分")
    @GetMapping("/szpf")
    @SaIgnore

    public Object szpf() {

        QueryWrapper<WaterMonitoringData> wrapper = new QueryWrapper<>();
        wrapper.apply(
                "(w.sensor_id, w.timestamp) IN (" +
                    "   SELECT sensor_id, MAX(timestamp) " +
                    "   FROM water_monitoring_data " +
                    "   GROUP BY sensor_id" +
                    ")"
            )
            .orderByDesc("w.timestamp");
        List<Map<String, Object>> result = baseMapper.selectpf(wrapper);

        ArrayList<Object> name = new ArrayList<>();
        ArrayList<Object> vaue = new ArrayList<>();
        result.forEach(item->{
            name.add(item.get("name"));
            vaue.add(item.get("value"));
        });
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name",name);
        objectObjectHashMap.put("value",vaue);
        return objectObjectHashMap;
    }

}
