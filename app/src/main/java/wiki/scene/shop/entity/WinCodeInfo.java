package wiki.scene.shop.entity;

import java.io.Serializable;

/**
 * 彩票开奖结果列表
 * Created by scene on 2017/11/14.
 */

public class WinCodeInfo implements Serializable {

    /**
     * id : 71
     * cycle_code : 20171108085
     * result : 66868
     * open_time : 1510143000
     * actual_open_time : 1510143061
     * status : 3
     * create_time : 1510142804
     * result_text : 大|双|大双
     */

    private int id;
    private String cycle_code;
    private String result;
    private int open_time;
    private int actual_open_time;
    private int status;
    private int create_time;
    private String result_text;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getOpen_time() {
        return open_time;
    }

    public void setOpen_time(int open_time) {
        this.open_time = open_time;
    }

    public int getActual_open_time() {
        return actual_open_time;
    }

    public void setActual_open_time(int actual_open_time) {
        this.actual_open_time = actual_open_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getResult_text() {
        return result_text;
    }

    public void setResult_text(String result_text) {
        this.result_text = result_text;
    }
}
