package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 最新一期的数据
 * Created by scene on 2017/11/10.
 */

public class CurrentCycleInfo implements Serializable {

    /**
     * id : 73
     * cycle_code : 20171108090
     * result : 0
     * open_time : 1510146000
     * actual_open_time : 0
     * status : 1
     * create_time : 1510145450
     */

    private int id;
    private String cycle_code;
    private int result;
    private long open_time;
    private long actual_open_time;
    private int status;
    private long create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCycle_code() {
        return cycle_code;
    }

    public void setCycle_code(String cycle_code) {
        this.cycle_code = cycle_code;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public long getOpen_time() {
        return open_time;
    }

    public void setOpen_time(long open_time) {
        this.open_time = open_time;
    }

    public long getActual_open_time() {
        return actual_open_time;
    }

    public void setActual_open_time(long actual_open_time) {
        this.actual_open_time = actual_open_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }
}
