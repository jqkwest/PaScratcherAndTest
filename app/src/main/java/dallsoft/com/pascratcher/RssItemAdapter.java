package dallsoft.com.pascratcher;

/**
 * Created by jkwest on 11/21/2014.
 */ import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.view.ContextMenu;  
import android.view.ContextMenu.ContextMenuInfo;  
import android.view.Menu;  
import android.view.MenuItem;  

import java.util.List;
import android.widget.*;

public class RssItemAdapter extends ArrayAdapter<RssItem> {

    private Context context;
	private String sort;
	public String link;


    public RssItemAdapter(Context context, int textViewResourceId,
                          List<RssItem> items) {
        super(context, textViewResourceId, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
       
		View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_layout, null);
        }

        RssItem item = getItem(position);
		this.link = item.getLink();
		
		// have a drop down boolen if sort == by cost || by alphabet
		
        if (item != null) {
            // our layout has two TextView elements
            TextView titleView = (TextView) view.findViewById(R.id.titleText);
            TextView descView = (TextView) view
                    .findViewById(R.id.descriptionText);
			TextView linkView = (TextView) view.findViewById(R.id.linkText);
					
			TextView gameNumberTV = (TextView) view.findViewById(R.id.gameNumberTV);
 
            titleView.setText(item.getTitle());
         //   descView.setText((item.getDescription()).replaceAll("[\n\r]", ""));
            descView.setText((item.getDescription()));
			
			linkView.setText((item.getLink()));
			
			gameNumberTV.setText((item.getGameNumber()));
			
		
        }
		
		
		
		

        return view;
    }
	
	public void setSort(String sort){
		
		
		this.sort = sort;
	}
	
	
	
	
	
	
	
	
}
