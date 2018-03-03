
package simple.fortests.Json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fetchdata {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("feed")
    @Expose
    private List<Feed> feed = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public  List<Feed> getFeed() {
        return feed;
    }

    public void setFeed(List<Feed> feed) {
        this.feed = feed;
    }

}
