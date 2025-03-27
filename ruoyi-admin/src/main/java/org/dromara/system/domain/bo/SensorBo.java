package org.dromara.system.domain.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.system.domain.Sensor;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 硬件业务对象 sensor
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Sensor.class, reverseConvertGenerate = false)
public class SensorBo extends BaseEntity {

    /**
     * 传感器状态
     */
    private String state;

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 位置
     */
    private String place;

    /**
     * 备注
     */
    private String remarks;

    private String  tenantId;
    private String name;
}
