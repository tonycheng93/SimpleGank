package com.sky.simplegank.Expand.model;

import com.sky.simplegank.Expand.model.impl.ExpandModelImpl;

/**
 * Created by tonycheng on 2016/10/31.
 */

public interface IExpandModel {
    void loadExpandList(int count, int page,
                        ExpandModelImpl.OnLoadExpandListListener listener);
}
