package com.sky.simplegank.FrontEnd.view;

import com.sky.simplegank.entity.GankEntity;

import java.util.List;

/**
 * 项目名称：SimpleGank
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2016/10/30 21:51
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface IFrontEndView {
    void showLoading();
    void addFrontEnd(List<GankEntity> frontEndList);
    void hideLoading();
    void showLoadFailMsg();
}
