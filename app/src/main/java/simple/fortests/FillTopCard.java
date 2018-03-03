package simple.fortests;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import simple.fortests.Holders.TextHolderTop;
import simple.fortests.Holders.ViewHolder;
import simple.fortests.Json.Feed;
import simple.fortests.Json.Fetchdata;
import simple.fortests.Json.Item;

/**
 * Created by starzhinskij on 31.01.2018.
 */

public class FillTopCard {
    List<String> imageLinks;
    List<String> textTopCard;
    TextView title, caption, likes, downloads;
    List<String> nameComments, textComments, createdComments,thumbnailComments;
    View cardTop, cardRecomend, openCard, cardComments, mainConstraint;
    ViewHolder viewHolder;
    TextHolderTop textHolderTop;
    ImageView openCardImageMain;
    String URLopenCardImageMain, TextTitle, TextCaption, TextLikes, TextDownloads;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    LinearLayout parent;
    private String FullUrl = "";
    public String VIDEO_ID = "None";
    public static Fetchdata fetchdata;


    public FillTopCard(View cardTop, View cardRecomend, View openCard, View mainConstraint) {
        imageLinks = new ArrayList<>();
        textTopCard = new ArrayList<>();
        nameComments = new ArrayList<>();
        textComments = new ArrayList<>();
        createdComments = new ArrayList<>();
        thumbnailComments=new ArrayList<>();

        this.cardTop = cardTop;
        this.cardRecomend = cardRecomend;
        this.openCard = openCard;
        this.mainConstraint = mainConstraint;

        Gsonx json = new Gsonx(this);
        json.execute();

    }

    public void setVideoId(String video_id) {
        this.VIDEO_ID = video_id;
    }

    public String getVideoId() {
        return VIDEO_ID;
    }


    public void initialize() {
        viewHolder = new ViewHolder();
        viewHolder.setImageViews((ImageView) cardTop.findViewById(R.id.CardTopImageLeft));
        viewHolder.setImageViews((ImageView) cardTop.findViewById(R.id.CardTopImageCenter));
        viewHolder.setImageViews((ImageView) cardTop.findViewById(R.id.CardTopImageRight));
        viewHolder.setImageViews((ImageView) cardRecomend.findViewById(R.id.recomendCardFirst));
        viewHolder.setImageViews((ImageView) cardRecomend.findViewById(R.id.recomendCardSecond));
        viewHolder.setImageViews((ImageView) cardRecomend.findViewById(R.id.recomendCardThird));
        viewHolder.setImageViews((ImageView) cardRecomend.findViewById(R.id.recomendCardFour));

        textHolderTop = new TextHolderTop();
        textHolderTop.setTextViews((TextView) cardTop.findViewById(R.id.CardTopTextLeft));
        textHolderTop.setTextViews((TextView) cardTop.findViewById(R.id.CardTopTextCenter));
        textHolderTop.setTextViews((TextView) cardTop.findViewById(R.id.CardTopTextRight));

        title = (TextView) openCard.findViewById(R.id.Header);
        caption = (TextView) openCard.findViewById(R.id.MainText);
        likes = (TextView) openCard.findViewById(R.id.openCardIconTextleft);
        downloads = (TextView) openCard.findViewById(R.id.openCardIconTextRight);
        openCardImageMain = (ImageView) openCard.findViewById(R.id.openCardImageMain);


    }


    void asyncResult(Fetchdata result) {

        fetchdata = result;
        List<Feed> feed = fetchdata.getFeed();


        for (Feed feed1 : feed) {
            int count_images = 0;

            if (feed1.getType().equals("youtube-video-cta")) {
                FullUrl = feed1.getUrl();
                FullUrl = FullUrl.substring(FullUrl.indexOf('=') + 1, FullUrl.length());
                VIDEO_ID = FullUrl;
                setVideoId(FullUrl);
            }


            if (feed1.getType().equals("horizontal-list")) {
                for (Item item : feed1.getItems()) {
                    imageLinks.add(count_images, item.getThumbnail());
                    count_images++;
                }

            }
            if (feed1.getType().equals("horizontal-list-featuring")) {
                for (Item item : feed1.getItems()) {
                    textTopCard.add(item.getName());
                }

            }
            if (feed1.getType().equals("hero")) {
                TextTitle = feed1.getTitle();
                TextCaption = feed1.getCaption();
                TextLikes = feed1.getLikes().toString();
                TextDownloads = feed1.getDownloads().toString();
                URLopenCardImageMain = feed1.getThumbnail();

            }
            if (feed1.getType().equals("comments")) {
                for (Item item : feed1.getItems()) {
                    nameComments.add(item.getAuthor().getName());
                    textComments.add(item.getText());
                    createdComments.add(item.getCreatedAt());
                    thumbnailComments.add(item.getSubject().getThumbnail());
                }

            }

        }

        Handler uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= viewHolder.imageViews.size() - 1; i++) {
                    Picasso.with(cardTop.getContext())
                            .load(imageLinks.get(i))
                            .into(viewHolder.imageViews.get(i));
                }

                for (int i = 0; i <= textHolderTop.textViews.size() - 1; i++) {
                    textHolderTop.textViews.get(i).setText(textTopCard.get(i));
                }
                Picasso.with(openCard.getContext())
                        .load(URLopenCardImageMain)
                        .into(openCardImageMain);

                title.setText(TextTitle);
                caption.setText(TextCaption);
                likes.setText(TextLikes);
                downloads.setText(TextDownloads);

                recyclerView = (RecyclerView) mainConstraint.findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(mainConstraint.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new RecyclerAdapter(nameComments, textComments, createdComments,thumbnailComments,mainConstraint.getContext());
                recyclerView.setAdapter(adapter);
            }
        });


    }

}