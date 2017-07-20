package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Case By:夺宝首页
 * package:wiki.scene.shop.entity
 * Author：scene on 2017/7/12 13:47
 */

public class IndianaIndexInfo implements Serializable {
    List<SliderInfo> slider;
    List<WinningNoticeInfo> winning_notice;
    List<NewWaitInfo> new_waiting;
    InProgressInfo in_progress;

    public List<SliderInfo> getSlider() {
        return slider;
    }

    public void setSlider(List<SliderInfo> slider) {
        this.slider = slider;
    }

    public List<WinningNoticeInfo> getWinning_notice() {
        return winning_notice;
    }

    public void setWinning_notice(List<WinningNoticeInfo> winning_notice) {
        this.winning_notice = winning_notice;
    }

    public List<NewWaitInfo> getNew_waiting() {
        return new_waiting;
    }

    public void setNew_waiting(List<NewWaitInfo> new_waiting) {
        this.new_waiting = new_waiting;
    }

    public InProgressInfo getIn_progress() {
        return in_progress;
    }

    public void setIn_progress(InProgressInfo in_progress) {
        this.in_progress = in_progress;
    }
}
