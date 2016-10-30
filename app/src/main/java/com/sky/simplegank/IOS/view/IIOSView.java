package com.sky.simplegank.IOS.view;

import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * 项目名称：SimpleGank
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2016/10/30 14:45
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface IIOSView {
    void showLoading();
    void addIOSList(List<GankEntity> iOSList);
    void hideLoading();
    void showLoadFailMsg();
}
