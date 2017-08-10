package com.fz.zhihunews.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feizhuo on 2017/4/27.
 */

public class Latest implements Serializable{

    /**
     * date : 20170427
     * stories : [{"title":"什么样的结构布局，才会让衣柜方便实用？","ga_prefix":"042714","images":["https://pic2.zhimg.com/v2-b4678bddf393402112c70a90c334aa55.jpg"],"multipic":true,"type":0,"id":9385277},{"images":["https://pic4.zhimg.com/v2-d7a46ca34d448a58ea9132a052b45ba7.jpg"],"type":0,"id":9385210,"ga_prefix":"042713","title":"清酒的「辛口」和「甘口 」在说什么？"},{"images":["https://pic1.zhimg.com/v2-f126186d1e8181dba870e51980fbdc84.jpg"],"type":0,"id":9385467,"ga_prefix":"042712","title":"为了金发女孩，我毫不犹豫地购买了豪华会员"},{"images":["https://pic2.zhimg.com/v2-03bd15512b437356979df796d84e6c9d.jpg"],"type":0,"id":9384889,"ga_prefix":"042712","title":"大误 · 答应我，给孩子取个好名字好吗？"},{"images":["https://pic1.zhimg.com/v2-aaa3db3fb537fe126f25825ad96a8edc.jpg"],"type":0,"id":9361836,"ga_prefix":"042711","title":"「车联网」都要听腻了，司机们连导航都还是用着手机"},{"images":["https://pic4.zhimg.com/v2-ccdabe83b52f3fb7bcd1b696422cf16f.jpg"],"type":0,"id":9382861,"ga_prefix":"042709","title":"以前，上流社会可都是不系腰带的"},{"images":["https://pic3.zhimg.com/v2-6b465a4894ae318f40db310962d5f792.jpg"],"type":0,"id":9384926,"ga_prefix":"042708","title":"想要变长腿是没戏了，提高跑步速度还有这个办法"},{"images":["https://pic3.zhimg.com/v2-7aa49a9a74258affe6a25a760b3ad286.jpg"],"type":0,"id":9383943,"ga_prefix":"042707","title":"房屋中介服务糟糕的问题能解决吗？"},{"images":["https://pic4.zhimg.com/v2-72d05b3e8a1a25288b71a7653cdbf08f.jpg"],"type":0,"id":9384470,"ga_prefix":"042707","title":"京东物流独立了，但「5 年千亿营收」，嗯，有点难"},{"images":["https://pic3.zhimg.com/v2-6dfcf91c58984ea19e141f49585cd33a.jpg"],"type":0,"id":9383925,"ga_prefix":"042707","title":"腾讯游戏做得好，一半是自己努力，一半要感谢同行衬托"},{"images":["https://pic3.zhimg.com/v2-5c8d54dfe2d6320450d63082404632d2.jpg"],"type":0,"id":9382920,"ga_prefix":"042706","title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic3.zhimg.com/v2-ab95664e64ae89d0bfbf52e1d3326f76.jpg"],"type":0,"id":9383848,"ga_prefix":"042706","title":"这里是广告 · 从电影的世界里看 AI"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-f40f5b3d5ce449b5d5c822626895115e.jpg","type":0,"id":9384470,"ga_prefix":"042707","title":"京东物流独立了，但「5 年千亿营收」，嗯，有点难"},{"image":"https://pic2.zhimg.com/v2-ca0a6e84f83139eac5020f9afacfc0c9.jpg","type":0,"id":9383925,"ga_prefix":"042707","title":"腾讯游戏做得好，一半是自己努力，一半要感谢同行衬托"},{"image":"https://pic3.zhimg.com/v2-1b2b686e12c2c49cb2852a4dcf48c58a.jpg","type":0,"id":9383963,"ga_prefix":"042615","title":"满 100 岁的建筑大师贝聿铭，年轻时也有不少「黑历史」"},{"image":"https://pic4.zhimg.com/v2-e8c24af7828f087593d428fa1fc1fe2b.jpg","type":0,"id":9382803,"ga_prefix":"042607","title":"知道哪些法律小常识，可以在职场中保护自己？"},{"image":"https://pic3.zhimg.com/v2-430ce94c5a87e98a7ed7a2d20f0ca85e.jpg","type":0,"id":9383848,"ga_prefix":"042706","title":"这里是广告 · 从电影的世界里看 AI"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean implements Serializable{
        /**
         * title : 什么样的结构布局，才会让衣柜方便实用？
         * ga_prefix : 042714
         * images : ["https://pic2.zhimg.com/v2-b4678bddf393402112c70a90c334aa55.jpg"]
         * multipic : true
         * type : 0
         * id : 9385277
         */

        private String title;
        private String ga_prefix;
        private boolean multipic;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean implements Serializable{
        /**
         * image : https://pic3.zhimg.com/v2-f40f5b3d5ce449b5d5c822626895115e.jpg
         * type : 0
         * id : 9384470
         * ga_prefix : 042707
         * title : 京东物流独立了，但「5 年千亿营收」，嗯，有点难
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
