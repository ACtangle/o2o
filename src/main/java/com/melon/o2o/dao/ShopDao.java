package com.melon.o2o.dao;

import com.melon.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ShopDao
 * @Description
 * @Author melon
 * @Date 2019-08-17 17:19
 * @Version
 */

public interface ShopDao {


    /**
     * 分页查询，可输入的条件，店铺名(模糊),店铺状态，店铺类别，区域Id，owner
     * @param shopCondition 查询条件
     * @param rowIndex  第几行开始取数据
     * @param pageSize  返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    /**
     * 返回queryShopList的总数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    /**
     * 根据id查询店铺
     *
     * @param shopId
     * @return
     */
    Shop queryByShopId(long shopId);

    /**
     * 新增店铺
     *
     * @param shop
     * @return int
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     *
     * @param shop
     * @return int
     */
    int updateShop(Shop shop);


}
