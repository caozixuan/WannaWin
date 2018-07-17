package com.citiexchangeplatform.pointsleague;

import com.citiexchangeplatform.pointsleague.models.PointsExchangeModel;

public interface ItemClickListener {
    /**
     * 展开子Item
     * @param bean
     */
    void onExpandChildren(PointsExchangeModel bean);

    /**
     * 隐藏子Item
     * @param bean
     */
    void onHideChildren(PointsExchangeModel bean);
}