package wiki.scene.shop.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 晒单列表
 * Created by scene on 17-8-9.
 */

public class ShareListResultInfo implements Serializable {
    private List<ShareListInfo> data;
    private ResultPageInfo info;

    public List<ShareListInfo> getData() {
        return data;
    }

    public void setData(List<ShareListInfo> data) {
        this.data = data;
    }

    public ResultPageInfo getInfo() {
        return info;
    }

    public void setInfo(ResultPageInfo info) {
        this.info = info;
    }

    public class ShareListInfo implements Serializable {

        /**
         * id : 1
         * user_id : 2
         * cycle_id : 2
         * like : 0
         * ip : 14.111.63.168
         * area : 重庆
         * content : 反反复复发
         * create_time : 1502776849
         * title : iphone6s 64g
         * cycle_code : 2665689
         * thumb : /img/iphone.jpg
         * status : 2
         * images : ["/order.show/c6c4740c61afa71ca07b8782b69dfe05.jpeg","/order.show/b0afa8ca59ac93b832524e11bf3d488f.jpeg","/order.show/030fe147215371e05f506b01cbc8e3d1.jpeg","/order.show/749730e98f2f03bf156207ef4e1414d3.jpeg","/order.show/eb735b355d4164f5856ededc977cb3de.jpeg"]
         */

        private String id;
        private String user_id;
        private String cycle_id;
        private int like;
        private int like_number;
        private String ip;
        private String area;
        private String content;
        private long create_time;
        private String title;
        private String cycle_code;
        private String thumb;
        private int status;
        private List<String> images;
        private String nickname;
        private String mobile;
        private int level;
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

        public String getCycle_id() {
            return cycle_id;
        }

        public void setCycle_id(String cycle_id) {
            this.cycle_id = cycle_id;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getLike_number() {
            return like_number;
        }

        public void setLike_number(int like_number) {
            this.like_number = like_number;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCycle_code() {
            return cycle_code;
        }

        public void setCycle_code(String cycle_code) {
            this.cycle_code = cycle_code;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
