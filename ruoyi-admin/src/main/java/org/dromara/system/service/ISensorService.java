package org.dromara.system.service;

import org.dromara.system.domain.vo.SensorVo;
import org.dromara.system.domain.bo.SensorBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 硬件Service接口
 *
 * @author Lion Li
 * @date 2025-03-27
 */
public interface ISensorService {

    /**
     * 查询硬件
     *
     * @param id 主键
     * @return 硬件
     */
    SensorVo queryById(Long id);

    /**
     * 分页查询硬件列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 硬件分页列表
     */
    TableDataInfo<SensorVo> queryPageList(SensorBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的硬件列表
     *
     * @param bo 查询条件
     * @return 硬件列表
     */
    List<SensorVo> queryList(SensorBo bo);

    /**
     * 新增硬件
     *
     * @param bo 硬件
     * @return 是否新增成功
     */
    Boolean insertByBo(SensorBo bo);

    /**
     * 修改硬件
     *
     * @param bo 硬件
     * @return 是否修改成功
     */
    Boolean updateByBo(SensorBo bo);

    /**
     * 校验并批量删除硬件信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
