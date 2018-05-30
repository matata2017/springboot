package com.xxy.springboot.entity;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by xxy on 2018/1/4.
 */
@Component
public class SpiderMan implements PageProcessor {
    private Site site = Site.me().setCycleRetryTimes(1000).setRetrySleepTime(6000);

    @Override
    public void process(Page page) {
        List<String> links = page.getHtml().links().regex("http://my\\.oschina\\.net/flashsword/blog/\\d+").all();
        page.addTargetRequests(links);
        page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());
        page.putField("content", page.getHtml().$("div.content").toString());
        page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
    }



    @Override
    public Site getSite() {
        return site;

    }

//    public static void main(String[] args) {
//        Spider.create(new OschinaBlogPageProcesser()).addUrl("http://my.oschina.net/flashsword/blog")
//                .addPipeline(new ConsolePipeline()).run();
//    }

    /**
     * Sets new site.
     *
     * @param site New value of site.
     */
    public void setSite(Site site) {
        this.site = site;
    }
}
