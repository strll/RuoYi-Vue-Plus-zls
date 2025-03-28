package org.dromara.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dromara.system.domain.WaterMonitoringData;
import org.dromara.system.domain.vo.WaterMonitoringDataVo;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

import java.util.List;
import java.util.Map;

/**
 * 监测数据Mapper接口
 *
 * @author Lion Li
 * @date 2025-03-27
 */
public interface WaterMonitoringDataMapper extends BaseMapperPlus<WaterMonitoringData, WaterMonitoringDataVo> {

    @Select(
        "SELECT w.flow_rate AS value, s.name AS name " +
            "FROM water_monitoring_data w " +
            "LEFT JOIN sensor s ON w.sensor_id = s.id " +
            "${ew.customSqlSegment}"
    )
    List<Map<String, Object>> selectLatestFlowRates(@Param(Constants.WRAPPER) QueryWrapper<WaterMonitoringData> wrapper);


    @Select(
        "SELECT w.quality_index AS value, s.name AS name " +
            "FROM water_monitoring_data w " +
            "LEFT JOIN sensor s ON w.sensor_id = s.id " +
            "${ew.customSqlSegment}"
    )
    List<Map<String, Object>> selectpf(@Param(Constants.WRAPPER) QueryWrapper<WaterMonitoringData> wrapper);
}
