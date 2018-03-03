package simple.fortests;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rajat on 2/8/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> nameCommentsString;
    private List<String> textCommentsString;
    private List<String> createdCommentsString;
    private List<String> createdthumbnailString;
    Context context;
    public RecyclerAdapter(List<String> nameComments, List<String> textComments, List<String> createdComments,  List<String> thumbnailComments,Context context) {
        this.nameCommentsString = nameComments;
        this.textCommentsString = textComments;
        this.createdCommentsString = createdComments;
        this.createdthumbnailString=thumbnailComments;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameComments.setText(nameCommentsString.get(position));
        holder.textComments.setText(textCommentsString.get(position));
        holder.createdComments.setText(createdCommentsString.get(position));
        Picasso.with(context).load(createdthumbnailString.get(position))
                .into(holder.imageComments);
    }

    @Override
    public int getItemCount() {
        return nameCommentsString.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameComments, textComments, createdComments;
        ImageView imageComments;

        public ViewHolder(View itemView) {
            super(itemView);
            nameComments = (TextView) itemView.findViewById(R.id.nameComments);
            textComments = (TextView) itemView.findViewById(R.id.textComments);
            createdComments = (TextView) itemView.findViewById(R.id.timeComments);
            imageComments=(ImageView) itemView.findViewById(R.id.imageComments);

        }


    }
}
