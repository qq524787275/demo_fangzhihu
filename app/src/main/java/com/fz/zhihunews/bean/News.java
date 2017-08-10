package com.fz.zhihunews.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feizhuo on 2017/4/27.
 */

public class News implements Serializable{

    /**
     * body : <div class="main-wrap content-wrap">
     <div class="headline">

     <div class="img-place-holder"></div>



     <div class="headline-background">
     <a class="headline-background-link" href="http://cj.sina.com.cn/article/detail/6002223834/230340">
     <div class="heading">相关新闻</div>
     <div class="heading-content">京东物流独立</div>
     <i class="icon-arrow-right"></i>
     </a>
     </div>

     </div>

     <div class="content-inner">




     <div class="question">
     <h2 class="question-title">如何看待京东物流宣布独立？会带来哪些影响？</h2>

     <div class="answer">

     <div class="meta">
     <img class="avatar" src="http://pic4.zhimg.com/v2-1538c8a1556d2e8e2a97adb5cae97d83_is.jpg">
     <span class="author">条顿堡之枪，</span><span class="bio">喜欢历史，游戏，滑雪，及胡思乱想的TMT证券分析师</span>
     </div>

     <div class="content">
     <p><strong>我来谈三点：为什么京东开放物流，为什么京东物流要独立，以及未来京东物流会做到多大</strong></p>
     <p><strong>京东开放物流是必然的选择</strong></p>
     <p>综合性半自营性质的电商必须通过开放物流来降低履约成本，这点已经是大家的共识。提高物流使用率，放大规模效应，同时从第三方商家手里获取佣金之外的额外收入，这是开放物流的精髓。Amazon 通过 FBA（fulfillment by Amazon）服务，才彻彻底底解决了履约成本的问题。京东虽然目前在产品结构上做出了很多改进，但是低毛利（~3%）的 3C 产品依然是主流，这在自营产品尤为突出。在目前~8%的物流成本下，自营业务想凭借~8%毛利率去盈利基本是天方夜谭，因此京东必须走向开放物流，从京东开始自建物流的第一天，就应该是注定的。</p>
     <p><strong>京东物流的独立会带来更多决策权的灵活性，提供未来上市的想象空间</strong></p>
     <p>首先，运营上获取更多的灵活性。京东不仅仅想把物流推给商城上的 pop 商家（虽然目前的少量用户全部为 pop 商家和部分品牌商），京东想做的是把物流推给全社会的商家，线上线下，当然也包括天猫的商家。如果要达成这一目的，物流就必须从商城完全独立出来。对于物流团队来说，有了更多决策权和经营的灵活性，也是好事。</p>
     <p>第二，不排除为未来做上市准备。独立之后，财务也完全分离，这些都会为未来上市扫清障碍。今年快递公司轮番上市，从长远来说，拥有 6-7 万物流人员的京东，其实也完全可以去资本市场一展身手。</p>
     <p><strong>京东物流做大的难度很高</strong></p>
     <p>一句话来总结就是：必然选择，但困难重重。归根到底还是受到阿里在流量上的挤压。Amazon 之所以能把 FBA 做好，一个重要的原因是它在全球范围内电商领域的一只独秀，独孤求败的老大位置。一家在北美的企业，如果做电商，除了自家网站，首先想到的肯定是 Amazon，那么也会很自然的去考虑使用 Amazon 的物流。另外 Amazon 的 FBA 物流也受益于它庞大的 prime 业务，不用 FBA 的商家就享受不到 prime 的流量。反观京东，首先在国内目前仅仅屈尊老二，受到阿里压制。另外从商家的角度来说，使用了京东物流后的价值其实也并不明显，更不用说比起其他的物流服务提供商，价格还较贵。如果销售的增量无法弥补商家在物流上的额外支出，正常的商家都不会轻易的改变固有的相对廉价但体验稍逊的物流模式。</p>
     <p><strong>综上所述，自己的看法，未来京东物流还将侧重发展自家商城的 pop 商家。这会对利润率的提高起到帮助，并受到股东欢迎。</strong>但是什么&ldquo;5 年之内收入千亿&rdquo;之类的豪言壮语，基本不会实现。</p>
     </div>
     </div>


     <div class="view-more"><a href="http://www.zhihu.com/question/58998679">查看知乎讨论<span class="js-question-holder"></span></a></div>

     </div>


     </div>
     </div>
     * image_source : Yestone.com 版权图片库
     * title : 京东物流独立了，但「5 年千亿营收」，嗯，有点难
     * image : https://pic3.zhimg.com/v2-f40f5b3d5ce449b5d5c822626895115e.jpg
     * share_url : http://daily.zhihu.com/story/9384470
     * js : []
     * ga_prefix : 042707
     * images : ["https://pic4.zhimg.com/v2-72d05b3e8a1a25288b71a7653cdbf08f.jpg"]
     * type : 0
     * id : 9384470
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
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

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
