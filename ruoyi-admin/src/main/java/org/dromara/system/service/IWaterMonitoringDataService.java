package org.dromara.system.service;

import org.dromara.system.domain.vo.WaterMonitoringDataVo;
import org.dromara.system.domain.bo.WaterMonitoringDataBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 监测数据Service接口
 *
 * @author Lion Li
 * @date 2025-03-27
 */
public interface IWaterMonitoringDataService {

    /**
     * 查询监测数据
     *
     * @param id 主键
     * @return 监测数据
     */
    WaterMonitoringDataVo queryById(Long id);

    /**
     * 分页查询监测数据列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 监测数据分页列表
     */
    TableDataInfo<WaterMonitoringDataVo> queryPageList(WaterMonitoringDataBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的监测数据列表
     *
     * @param bo 查询条件
     * @return 监测数据列表
     */
    List<WaterMonitoringDataVo> queryList(WaterMonitoringDataBo bo);

    /**
     * 新增监测数据
     *
     * @param bo 监测数据
     * @return 是否新增成功
     */
    Boolean insertByBo(WaterMonitoringDataBo bo);

    /**
     * 修改监测数据
     *
     * @param bo 监测数据
     * @return 是否修改成功
     */
    Boolean updateByBo(WaterMonitoringDataBo bo);

    /**
     * 校验并批量删除监测数据信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
