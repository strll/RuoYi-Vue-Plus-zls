package org.dromara.system.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.system.domain.vo.WaterMonitoringDataVo;
import org.dromara.system.domain.bo.WaterMonitoringDataBo;
import org.dromara.system.service.IWaterMonitoringDataService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 监测数据
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/monitoringData")
public class WaterMonitoringDataController extends BaseController {

    private final IWaterMonitoringDataService waterMonitoringDataService;

    /**
     * 查询监测数据列表
     */
    @SaCheckPermission("system:monitoringData:list")
    @GetMapping("/list")
    public TableDataInfo<WaterMonitoringDataVo> list(WaterMonitoringDataBo bo, PageQuery pageQuery) {
        return waterMonitoringDataService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出监测数据列表
     */
    @SaCheckPermission("system:monitoringData:export")
    @Log(title = "监测数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WaterMonitoringDataBo bo, HttpServletResponse response) {
        List<WaterMonitoringDataVo> list = waterMonitoringDataService.queryList(bo);
        ExcelUtil.exportExcel(list, "监测数据", WaterMonitoringDataVo.class, response);
    }

    /**
     * 获取监测数据详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:monitoringData:query")
    @GetMapping("/{id}")
    public R<WaterMonitoringDataVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(waterMonitoringDataService.queryById(id));
    }

    /**
     * 新增监测数据
     */
    @SaCheckPermission("system:monitoringData:add")
    @Log(title = "监测数据", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WaterMonitoringDataBo bo) {
        return toAjax(waterMonitoringDataService.insertByBo(bo));
    }

    /**
     * 修改监测数据
     */
    @SaCheckPermission("system:monitoringData:edit")
    @Log(title = "监测数据", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WaterMonitoringDataBo bo) {
        return toAjax(waterMonitoringDataService.updateByBo(bo));
    }

    /**
     * 删除监测数据
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:monitoringData:remove")
    @Log(title = "监测数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(waterMonitoringDataService.deleteWithValidByIds(List.of(ids), true));
    }
}
