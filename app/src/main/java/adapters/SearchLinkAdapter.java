package adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thiagoyf.links.R;

import java.util.List;

import models.Link;

/**
 * Created by thiagoyf on 1/11/17.
 */

public class SearchLinkAdapter extends BaseAdapter {
    private Context context;
    private List<Link> links;

    public SearchLinkAdapter(Context context, List<Link> links){
        this.context = context;
        this.links = links;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Link link = links.get(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.search_link_item, null);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.search_link_title);
        String title = context.getString(R.string.create_link_title) + link.getTitle();
        txtTitle.setText(title);

        TextView txtCategory = (TextView) view.findViewById(R.id.search_link_category);
        String category = context.getString(R.string.create_link_category) + link.getTitle();
        txtCategory.setText(category);

        TextView txtUrl = (TextView) view.findViewById(R.id.search_link_url);
        String url = context.getString(R.string.create_link_url) + link.getUrl();
        txtUrl.setText(url);

        return view;
    }
}
