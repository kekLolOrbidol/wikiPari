package at.sunilson.wiki_quiz.repository.retrofitGsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by linus on 24.02.2018.
 */

public class WikiQuery {

    @SerializedName("pages")
    @Expose
    private Map<String, WikiArticle> pages;

    public Map<String, WikiArticle> getPages() {
        return pages;
    }

    public void setPages(Map<String, WikiArticle> pages) {
        this.pages = pages;
    }

}
