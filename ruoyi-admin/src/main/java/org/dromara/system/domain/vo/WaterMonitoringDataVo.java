package org.dromara.system.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.system.domain.WaterMonitoringData;
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
 * 监测数据视图对象 water_monitoring_data
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WaterMonitoringData.class)
public class WaterMonitoringDataVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "传感器名称")
    private String sensorName;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 传感器编号
     */
    @ExcelProperty(value = "传感器编号")
    private String sensorId;

    /**
     * 水流量（L/min）
     */
    @ExcelProperty(value = "水流量", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "L=/min")
    private Long flowRate;

    /**
     * 水压（MPa）
     */
    @ExcelProperty(value = "水压", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "M=Pa")
    private Long pressure;

    /**
     * 水质指数（0-100）
     */
    @ExcelProperty(value = "水质指数", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=-100")
    private Long qualityIndex;

    /**
     * 记录时间
     */
    @ExcelProperty(value = "记录时间")
    private Date timestamp;
    @ExcelProperty(value = "a")
    private String  tenantId;
}
