package dallsoft.com.pascratcherTwo;

/**
 * Created by jkwest on 11/21/2014.
 */ import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TicketItemAdapter extends ArrayAdapter<TicketItem> {

    private Context context;



    public TicketItemAdapter(Context context, int textViewResourceId,
                             List<TicketItem> items) {
        super(context, textViewResourceId, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.ticket_list_view, null);
        }

        TicketItem item = getItem(position);

	    
		
		// have a drop down boolen if sort == by cost || by alphabet
		
        if (item != null) {
            // our layout has two TextView elements
            TextView gameNameView = (TextView) view.findViewById(R.id.gameNameText);
          //  TextView prizeInfoView = (TextView) view.findViewById(R.id.prizeInfoText);

            TextView prizeATV = (TextView) view.findViewById(R.id.prizeATV);
            TextView prizeBTV = (TextView) view.findViewById(R.id.prizeBTV);
            TextView prizeCTV = (TextView) view.findViewById(R.id.prizeCTV);
            TextView valueATV = (TextView) view.findViewById(R.id.valueATV);
            TextView valueBTV = (TextView) view.findViewById(R.id.valueBTV);
            TextView valueCTV = (TextView) view.findViewById(R.id.valueCTV);


            TextView prizeDTV = (TextView) view.findViewById(R.id.prizeDTV);
            TextView prizeETV = (TextView) view.findViewById(R.id.prizeETV);
            TextView prizeFTV = (TextView) view.findViewById(R.id.prizeFTV);
            TextView valueDTV = (TextView) view.findViewById(R.id.valueDTV);
            TextView valueETV = (TextView) view.findViewById(R.id.valueETV);
            TextView valueFTV = (TextView) view.findViewById(R.id.valueFTV);




            //	TextView linkView = (TextView) view.findViewById(R.id.linkText);
					
		//	TextView gameNumberTV = (TextView) view.findViewById(R.id.gameNumberTV);
 
            gameNameView.setText(item.getTitle());
         //   descView.setText((item.getPrizeInfo()).replaceAll("[\n\r]", ""));
         //   prizeInfoView.setText((item.getPrizeInfo()));

            prizeATV.setText(item.getPrizeA());
            prizeBTV.setText(item.getPrizeB());
            prizeCTV.setText(item.getPrizeC());
            valueATV.setText(item.getValueA());
            valueBTV.setText(item.getValueB());
            valueCTV.setText(item.getValueC());

            prizeDTV.setText(item.getPrizeD());
            prizeETV.setText(item.getPrizeE());
            prizeFTV.setText(item.getPrizeF());
            valueDTV.setText(item.getValueD());
            valueETV.setText(item.getValueE());
            valueFTV.setText(item.getValueF());

			
		//	linkView.setText((item.getCost()));
			
		//	gameNumberTV.setText((item.getGameNumber()));
			
		
        }
		
		
		
		

        return view;
    }
	
	
	
	
	
	
	
	
	
	
	
}
