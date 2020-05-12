package home.nigel.innovativecinemalistingsappv01;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class FilmListAdapter extends ArrayAdapter<FilmIdentity> //Listview adapter class
{
    private Context mContext;
    int mResource;
    public FilmListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FilmIdentity> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)   //Get listview of Films with relevant information
    {
        String name = getItem(position).getName();
        String top_info = getItem(position).getTop_info();
        String bottom_info = getItem(position).getBottom_info();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = convertView.findViewById(R.id.textView1);
        TextView tvTop = convertView.findViewById(R.id.textView2);
        TextView tvBottom = convertView.findViewById(R.id.textView3);

        tvName.setText(name);
        tvTop.setText(top_info);
        tvBottom.setText(bottom_info);

        return convertView;
    }
}
