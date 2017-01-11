package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thiagoyf.links.R;

import java.util.List;

import models.Link;

/**
 * Created by thiagoyf on 1/11/17.
 */

public class LinkAdapter extends BaseAdapter {
    private Context context;
    private List<Link> links;

    public LinkAdapter(Context context, List<Link> links) {
        this.context = context;
        this.links = links;
    }

    @Override
    public int getCount() {
        return links.size();
    }

    @Override
    public Object getItem(int i) {
        return links.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Link link = links.get(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.link, null);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.link_title);
        String title = context.getString(R.string.create_link_title) + link.getTitle();
        txtTitle.setText(title);

        TextView txtCategory = (TextView) view.findViewById(R.id.link_category);
        String category = context.getString(R.string.create_link_category) + link.getTitle();
        txtCategory.setText(category);

        TextView txtUrl = (TextView) view.findViewById(R.id.link_url);
        String url = context.getString(R.string.create_link_url) + link.getUrl();
        txtUrl.setText(url);

        return view;
    }
}
