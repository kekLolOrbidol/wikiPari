package at.sunilson.wiki_quiz.repository.retrofitGsonEntities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Linus Weiss
 */

public class WikiArticles {

    @SerializedName("query")
    @Expose
    private WikiQuery query;


    public WikiQuery getQuery() {
        return query;
    }

    public void setQuery(WikiQuery query) {
        this.query = query;
    }


}
