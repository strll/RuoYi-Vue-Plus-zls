package org.dromara.system.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import org.dromara.system.domain.Sensor;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 硬件视图对象 sensor
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Sensor.class)
public class SensorVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    /**
     * 传感器状态
     */
    @ExcelProperty(value = "传感器状态")
    private String state;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 位置
     */
    @ExcelProperty(value = "位置")
    private String place;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remarks;

    @ExcelProperty(value = "a")
    private String  tenantId;
}
