package com.sky.simplegank.IOS.model;

import com.sky.simplegank.IOS.model.impl.IOSModelImpl;

/**
 * 项目名称：SimpleGank
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2016/10/30 14:34
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface IIOSModel {
    void onLoadIOSList(int count, int page,
                       IOSModelImpl.OnLoadIOSListListener listener);
}
