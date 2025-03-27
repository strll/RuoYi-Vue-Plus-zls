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
import org.dromara.system.domain.vo.SensorVo;
import org.dromara.system.domain.bo.SensorBo;
import org.dromara.system.service.ISensorService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 硬件
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/sensor")
public class SensorController extends BaseController {

    private final ISensorService sensorService;

    /**
     * 查询硬件列表
     */
    @SaCheckPermission("system:sensor:list")
    @GetMapping("/list")
    public TableDataInfo<SensorVo> list(SensorBo bo, PageQuery pageQuery) {
        return sensorService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出硬件列表
     */
    @SaCheckPermission("system:sensor:export")
    @Log(title = "硬件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SensorBo bo, HttpServletResponse response) {
        List<SensorVo> list = sensorService.queryList(bo);
        ExcelUtil.exportExcel(list, "硬件", SensorVo.class, response);
    }

    /**
     * 获取硬件详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:sensor:query")
    @GetMapping("/{id}")
    public R<SensorVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(sensorService.queryById(id));
    }

    /**
     * 新增硬件
     */
    @SaCheckPermission("system:sensor:add")
    @Log(title = "硬件", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SensorBo bo) {
        return toAjax(sensorService.insertByBo(bo));
    }

    /**
     * 修改硬件
     */
    @SaCheckPermission("system:sensor:edit")
    @Log(title = "硬件", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SensorBo bo) {
        return toAjax(sensorService.updateByBo(bo));
    }

    /**
     * 删除硬件
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:sensor:remove")
    @Log(title = "硬件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(sensorService.deleteWithValidByIds(List.of(ids), true));
    }
}
