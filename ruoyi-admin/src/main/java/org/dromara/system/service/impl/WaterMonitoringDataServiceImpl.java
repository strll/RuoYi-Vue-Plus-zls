package org.dromara.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.system.domain.Sensor;
import org.dromara.system.mapper.SensorMapper;
import org.springframework.stereotype.Service;
import org.dromara.system.domain.bo.WaterMonitoringDataBo;
import org.dromara.system.domain.vo.WaterMonitoringDataVo;
import org.dromara.system.domain.WaterMonitoringData;
import org.dromara.system.mapper.WaterMonitoringDataMapper;
import org.dromara.system.service.IWaterMonitoringDataService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 监测数据Service业务层处理
 *
 * @author Lion Li
 * @date 2025-03-27
 */
@RequiredArgsConstructor
@Service
public class WaterMonitoringDataServiceImpl implements IWaterMonitoringDataService {

    private final WaterMonitoringDataMapper baseMapper;
    private final SensorMapper sensorMapper;
    /**
     * 查询监测数据
     *
     * @param id 主键
     * @return 监测数据
     */
    @Override
    public WaterMonitoringDataVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询监测数据列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 监测数据分页列表
     */
    @Override
    public TableDataInfo<WaterMonitoringDataVo> queryPageList(WaterMonitoringDataBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WaterMonitoringData> lqw = buildQueryWrapper(bo);
        Page<WaterMonitoringDataVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);

        List<WaterMonitoringDataVo> records = result.getRecords();
        for (int i = 0; i < records.size(); i++) {
            WaterMonitoringDataVo waterMonitoringDataVo = records.get(i);

            String sensorId = waterMonitoringDataVo.getSensorId();
            QueryWrapper<Sensor> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",sensorId);
            Sensor sensor = sensorMapper.selectOne(queryWrapper);

            waterMonitoringDataVo.setSensorName(sensor.getName());
        }

        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的监测数据列表
     *
     * @param bo 查询条件
     * @return 监测数据列表
     */
    @Override
    public List<WaterMonitoringDataVo> queryList(WaterMonitoringDataBo bo) {
        LambdaQueryWrapper<WaterMonitoringData> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WaterMonitoringData> buildQueryWrapper(WaterMonitoringDataBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WaterMonitoringData> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(WaterMonitoringData::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getSensorId()), WaterMonitoringData::getSensorId, bo.getSensorId());
        lqw.eq(bo.getFlowRate() != null, WaterMonitoringData::getFlowRate, bo.getFlowRate());
        lqw.eq(bo.getPressure() != null, WaterMonitoringData::getPressure, bo.getPressure());
        lqw.eq(bo.getQualityIndex() != null, WaterMonitoringData::getQualityIndex, bo.getQualityIndex());
        lqw.eq(bo.getTimestamp() != null, WaterMonitoringData::getTimestamp, bo.getTimestamp());
        return lqw;
    }

    /**
     * 新增监测数据
     *
     * @param bo 监测数据
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WaterMonitoringDataBo bo) {
        WaterMonitoringData add = MapstructUtils.convert(bo, WaterMonitoringData.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改监测数据
     *
     * @param bo 监测数据
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WaterMonitoringDataBo bo) {
        WaterMonitoringData update = MapstructUtils.convert(bo, WaterMonitoringData.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WaterMonitoringData entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除监测数据信息
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
