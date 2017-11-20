package wiki.scene.shop.event;

import wiki.scene.shop.entity.CurrentCycleInfo;

/**
 * Case By:获取当前期数
 * package:wiki.scene.shop.event
 * Author：scene on 2017/6/29 16:37
 */

public class GetCurrentCycleSuccessEvent {
    public CurrentCycleInfo cycleInfo;

    public GetCurrentCycleSuccessEvent(CurrentCycleInfo cycleInfo) {
        this.cycleInfo = cycleInfo;
    }
}
