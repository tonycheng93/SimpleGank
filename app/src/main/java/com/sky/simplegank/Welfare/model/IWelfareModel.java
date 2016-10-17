package com.sky.simplegank.Welfare.model;

import com.sky.simplegank.Welfare.model.impl.WelfareModelImpl;

/**
 * Created by tonycheng on 2016/10/17.
 */

public interface IWelfareModel {

    void loadWelfareList(String category, int count, int page,
                         WelfareModelImpl.OnLoadWelfareListListener listener);

}
