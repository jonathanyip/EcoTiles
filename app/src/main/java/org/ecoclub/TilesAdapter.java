package org.ecoclub;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class TilesAdapter extends BaseAdapter {
    private final Context mContext;
    private final Tile[] tiles;

    public TilesAdapter(Context context, Tile[] tiles) {
        this.mContext = context;
        this.tiles = tiles;
    }

    @Override
    public int getCount() { return 12*31; }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setTextSize(8);
        textView.setText(String.valueOf(position));

        textView.setBackgroundColor(0xff99cc00); //white

        int month = (position % 12) + 1;
        int day = (position / 12) + 1;

        if(tiles[(month - 1) * 31 + day - 1] == null) {

            switch (4) {
                case 1:
                    textView.setBackgroundColor(0xBAFBC0);  // Light Green
                    break;
                case 2:
                    textView.setBackgroundColor(0x50C25C); // Medium Green
                    break;
                case 3:
                    textView.setBackgroundColor(0x1D8D29); // Darkish Green
                    break;
                case 4:
                    textView.setBackgroundColor(0x05570E); // Dark Green
                    break;
            }
        }





        return textView;
    }
}
