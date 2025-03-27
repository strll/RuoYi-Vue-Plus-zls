package org.dromara.system.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 硬件对象 sensor
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sensor")
public class Sensor extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 传感器状态
     */
    private String state;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 位置
     */
    private String place;

    /**
     * 备注
     */
    private String remarks;
    @TableField(value = "tenant_id")
    private String  tenantId;

    private String name;
}
