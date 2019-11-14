package com.github.pagehelper.mapper;

import com.github.pagehelper.model.RsInventory;
import com.github.pagehelper.model.RsInventoryCondition;
import com.github.pagehelper.model.RsInventoryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvjun
 */
public interface RsInventoryMapper {
    /**
     * 按条件统计
     *
     * @param example
     * @return
     */
    long countByExample(RsInventoryCondition example);

    /**
     * 按条件删除
     *
     * @param example
     * @return
     */
    int deleteByExample(RsInventoryCondition example);

    /**
     * 按主键删除
     *
     * @param inventoryId
     * @return
     */
    int deleteByPrimaryKey(Integer inventoryId);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(RsInventory record);

    /**
     * 部分插入
     *
     * @param record
     * @return
     */
    int insertSelective(RsInventory record);

    /**
     * 按条件查询
     *
     * @param example
     * @return
     */
    List<RsInventory> selectByExampleWithBLOBs(RsInventoryCondition example);

    /**
     * 按条件查询
     *
     * @param example
     * @return
     */
    List<RsInventory> selectByExample(RsInventoryCondition example);

    /**
     * 按主键查询
     *
     * @param inventoryId
     * @return
     */
    RsInventory selectByPrimaryKey(Integer inventoryId);

    /**
     * 按条件部分更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") RsInventory record, @Param("example") RsInventoryCondition example);

    /**
     * 按条件更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleWithBLOBs(@Param("record") RsInventory record, @Param("example") RsInventoryCondition example);

    /**
     * 按条件更新
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") RsInventory record, @Param("example") RsInventoryCondition example);

    /**
     * 按主键部分更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RsInventory record);

    /**
     * 按主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(RsInventory record);

    /**
     * 按主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(RsInventory record);


    /**
     * @param rsInventoryQuery
     * @return
     */
    List<RsInventory> queryInventory(RsInventoryQuery rsInventoryQuery);

    int batchInsert(@Param("list") List<RsInventory> rsInventories);


}
