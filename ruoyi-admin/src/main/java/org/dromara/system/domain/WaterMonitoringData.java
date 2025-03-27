package org.dromara.system.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 监测数据对象 water_monitoring_data
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("water_monitoring_data")
public class WaterMonitoringData extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 传感器编号
     */
    @TableField(value = "sensor_id")
    private String sensorId;

    /**
     * 水流量（L/min）
     */
    @TableField(value = "flow_rate")
    private Double flowRate;

    /**
     * 水压（MPa）
     */
    private Double pressure;

    /**
     * 水质指数（0-100）
     */
    @TableField(value = "quality_index")
    private Double qualityIndex;

    /**
     * 记录时间
     */
    private Date timestamp;

    @TableField(value = "tenant_id")
    private String  tenantId;
}
