package org.dromara.system.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.SensorBo;
import org.dromara.system.domain.vo.SensorVo;
import org.dromara.system.domain.Sensor;
import org.dromara.system.mapper.SensorMapper;
import org.dromara.system.service.ISensorService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 硬件Service业务层处理
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@RequiredArgsConstructor
@Service
public class SensorServiceImpl implements ISensorService {

    private final SensorMapper baseMapper;

    /**
     * 查询硬件
     *
     * @param id 主键
     * @return 硬件
     */
    @Override
    public SensorVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询硬件列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 硬件分页列表
     */
    @Override
    public TableDataInfo<SensorVo> queryPageList(SensorBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Sensor> lqw = buildQueryWrapper(bo);
        Page<SensorVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的硬件列表
     *
     * @param bo 查询条件
     * @return 硬件列表
     */
    @Override
    public List<SensorVo> queryList(SensorBo bo) {
        LambdaQueryWrapper<Sensor> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Sensor> buildQueryWrapper(SensorBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Sensor> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getState()), Sensor::getState, bo.getState());
        lqw.orderByAsc(Sensor::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getPlace()), Sensor::getPlace, bo.getPlace());
        lqw.eq(StringUtils.isNotBlank(bo.getRemarks()), Sensor::getRemarks, bo.getRemarks());
        lqw.like(StringUtils.isNotBlank(bo.getName()), Sensor::getName, bo.getName() );
        return lqw;
    }

    /**
     * 新增硬件
     *
     * @param bo 硬件
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(SensorBo bo) {
        Sensor add = MapstructUtils.convert(bo, Sensor.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改硬件
     *
     * @param bo 硬件
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(SensorBo bo) {
        Sensor update = MapstructUtils.convert(bo, Sensor.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Sensor entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除硬件信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
