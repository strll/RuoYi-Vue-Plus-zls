package org.dromara.system.domain.bo;

import org.dromara.system.domain.WaterMonitoringData;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 监测数据业务对象 water_monitoring_data
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WaterMonitoringData.class, reverseConvertGenerate = false)
public class WaterMonitoringDataBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 传感器编号
     */
    private String sensorId;

    /**
     * 水流量（L/min）
     */
    private Double flowRate;

    /**
     * 水压（MPa）
     */
    private Double pressure;

    /**
     * 水质指数（0-100）
     */
    private Double qualityIndex;

    /**
     * 记录时间
     */
    private Date timestamp;

    private String  tenantId;
}
