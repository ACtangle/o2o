$(function () {

    var url = '/o2o/frontend/listmainpageinfo';

    $.getJSON(url, function (data) {
        if (data.success) {

            //头条
            var headLineList = data.headLineList;
            var swiperHtml = '';
            headLineList.map(function (item, index) {
                swiperHtml += '' + '<div class="swiper-slide img-wrap">'
                    + '<a href="' + item.lineLink
                    + '" external><img class="banner-img" src="'
                    + item.lineImg
                    + '" alt="'
                    + item.lineName
                    + '"> </a>'
                    + '</div>';
            });
            //将轮播图组赋值给前端html控件
            $('.swiper-wrapper').html(swiperHtml);
            //设定轮播图轮换时间为3秒
            $('.swiper-container').swiper({
                autoplay: 3000,
                //用户多轮播图进行操作时，是否自动停止autoplay
                autoplayDisableOnInteraction: false
            });

            //一级商铺分类
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = '';
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                    + '<div class="col-50 shop-classify" data-category='
                    + item.shopCategoryId + '>' + '<div class="word">'
                    + '<p class="shop-title">'
                    + item.shopCategoryName
                    + '</p>' + '<p class="shop-desc">'
                    + item.shopCategoryDesc
                    + '</p>' + '</div>'
                    + '<div class="shop-classify-img-warp">'
                    + '<img class="shop-img" src="'
                    + item.shopCategoryImg
                    + '">'
                    + '</div>'
                    + '</div>';
            });
            $('.row').html(categoryHtml);

        }
    });

    //若点击底部按钮，即"我的"，则显示侧栏
    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });

    $('.row').on('click', '.shop-classify', function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/o2o/frontend/shoplist?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    });

});