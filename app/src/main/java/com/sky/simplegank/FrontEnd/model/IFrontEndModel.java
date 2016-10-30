package com.sky.simplegank.FrontEnd.model;

import com.sky.simplegank.FrontEnd.model.impl.FrontEndModelImpl;

/**
 * 项目名称：SimpleGank
 * 类描述：
 * 创建人：tonycheng
 * 创建时间：2016/10/30 21:34
 * 邮箱：tonycheng93@outlook.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public interface IFrontEndModel {
    void loadFrontEndList(int count, int page,
                          FrontEndModelImpl.OnLoadFrontEndListListener listener);
}
