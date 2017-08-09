package wiki.scene.shop.entity;

import java.util.List;

/**
 * 产品详情
 * Created by scene on 17-7-26.
 */

public class GoodsDetailInfo {

    private GoodsDetailInfoData data;
    private List<LogInfo> log;
    private List<DanmuInfo> danmu;
    private List<BuyersInfo> buyers;
    private boolean collection;

    public GoodsDetailInfoData getData() {
        return data;
    }

    public void setData(GoodsDetailInfoData data) {
        this.data = data;
    }

    public List<LogInfo> getLog() {
        return log;
    }

    public void setLog(List<LogInfo> log) {
        this.log = log;
    }

    public List<DanmuInfo> getDanmu() {
        return danmu;
    }

    public void setDanmu(List<DanmuInfo> danmu) {
        this.danmu = danmu;
    }

    public List<BuyersInfo> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<BuyersInfo> buyers) {
        this.buyers = buyers;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public class GoodsDetailInfoData {
        private String id;
        private String title;
        private String second_title;
        private String cycle_code;
        private int need_source;
        private int current_source;
        private String product_id;
        private int status;
        private String lucky_user_id;
        private String lucky_code;
        private List<String> images;
        private int my_source;
        private List<String> my_codes;
        private List<ListGoodsInfo> hot;
        private WinnerInfo winner;
        private long open_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSecond_title() {
            return second_title;
        }

        public void setSecond_title(String second_title) {
            this.second_title = second_title;
        }

        public String getCycle_code() {
            return cycle_code;
        }

        public void setCycle_code(String cycle_code) {
            this.cycle_code = cycle_code;
        }

        public int getNeed_source() {
            return need_source;
        }

        public void setNeed_source(int need_source) {
            this.need_source = need_source;
        }

        public int getCurrent_source() {
            return current_source;
        }

        public void setCurrent_source(int current_source) {
            this.current_source = current_source;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLucky_user_id() {
            return lucky_user_id;
        }

        public void setLucky_user_id(String lucky_user_id) {
            this.lucky_user_id = lucky_user_id;
        }

        public String getLucky_code() {
            return lucky_code;
        }

        public void setLucky_code(String lucky_code) {
            this.lucky_code = lucky_code;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public int getMy_source() {
            return my_source;
        }

        public void setMy_source(int my_source) {
            this.my_source = my_source;
        }

        public List<String> getMy_codes() {
            return my_codes;
        }

        public void setMy_codes(List<String> my_codes) {
            this.my_codes = my_codes;
        }

        public List<ListGoodsInfo> getHot() {
            return hot;
        }

        public void setHot(List<ListGoodsInfo> hot) {
            this.hot = hot;
        }

        public WinnerInfo getWinner() {
            return winner;
        }

        public void setWinner(WinnerInfo winner) {
            this.winner = winner;
        }

        public long getOpen_time() {
            return open_time;
        }

        public void setOpen_time(long open_time) {
            this.open_time = open_time;
        }
    }

    public class WinnerInfo {
        private List<String> lucky_user_codes;
        private String user_id;
        private String ip;
        private String area;
        private String nickname;
        private String avatar;

        public List<String> getLucky_user_codes() {
            return lucky_user_codes;
        }

        public void setLucky_user_codes(List<String> lucky_user_codes) {
            this.lucky_user_codes = lucky_user_codes;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public class LogInfo {
        private String id;
        private String user_id;
        private int number;
        private long create_time;
        private String order_id;
        private String ip;
        private String area;
        private String nickname;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public class DanmuInfo {
        private String id;
        private String user_id;
        private int number;
        private long create_time;
        private String order_id;
        private String ip;
        private String area;
        private String nickname;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public class BuyersInfo {
        int number;
        String type;
        String avatar;
        String nickname;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
