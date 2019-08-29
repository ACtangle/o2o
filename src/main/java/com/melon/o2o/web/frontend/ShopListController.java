package com.melon.o2o.web.frontend;

import com.melon.o2o.dto.ShopExecution;
import com.melon.o2o.entity.Area;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.entity.ShopCategory;
import com.melon.o2o.service.AreaService;
import com.melon.o2o.service.ShopCategoryService;
import com.melon.o2o.service.ShopService;
import com.melon.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShopListController
 * @Description
 * @Author melon
 * @Date 2019-08-29 20:49
 * @Version
 */

@Controller
@RequestMapping(value = "/frontend")
public class ShopListController {


    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private AreaService areaService;

    /**
     * 返回商铺列表页的shopcaegory列表（二级或者一级），以及区域信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/listshoppageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopPageInfo(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();
        long parentId = HttpServletRequestUtil.getLong(request, "parentId");

        //封装商铺分类列表
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        if (parentId != -1) {
            //如果parentId存在，则查出当前所选一级分类下的所有二级分类的列表
            try {
                ShopCategory shopCategoryCondition = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else {
            //若果不存在，则查出所有一级分类
            try {
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        }
        modelMap.put("shopCategoryList", shopCategoryList);

        //封装区域列表
        List<Area> areaList = new ArrayList<>();
        try {
            areaList = areaService.getAreaList();
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }


    @RequestMapping(value = "/listshops", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShops(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");

        if ((pageIndex > -1) && (pageSize > -1)) {
            //试着一级类别id
            long parentId = HttpServletRequestUtil.getLong(request, "parentId");
            //试着二级类别id
            long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
            //区域id
            int areaId = HttpServletRequestUtil.getInt(request, "areaId");
            //模糊查询名字
            String shopName = HttpServletRequestUtil.getString(request, "shopName");

            Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);
            ShopExecution shopExecution = shopService.getShopList(shopCondition, pageIndex, pageSize);
            modelMap.put("success", true);
            modelMap.put("shopList", shopExecution.getShopList());
            modelMap.put("count", shopExecution.getCount());
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex");
        }
        return modelMap;
    }

    //组合查询
    private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) {

        Shop shopCondition = new Shop();
        if (parentId != -1L) {
            //查询某个一级分类下的二级店铺列表
            ShopCategory childCategory = new ShopCategory();
            ShopCategory parentCategory = new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            childCategory.setParent(parentCategory);
            shopCondition.setShopCategory(childCategory);
        }
        if (shopCategoryId != -1L) {
            //查询某个二级ShopCategory下面的店铺列表
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if (areaId != -1) {
            //查询位于某个Id区域下的店铺列表
            Area area = new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }
        if (shopName != null) {
            shopCondition.setShopName(shopName);
        }
        shopCondition.setEnableStatus(1);
        return shopCondition;

    }

}
