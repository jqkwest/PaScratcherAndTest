package dallsoft.com.pascratcherTwo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends Activity {

    public ListView newsListView;
    public ArrayAdapter<TicketItem> adapter;
    public List<TicketItem> rssItemList = new ArrayList<TicketItem>();
	
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsListView = (ListView) findViewById(R.id.newsListView);

        adapter = new TicketItemAdapter(this, android.R.layout.simple_list_item_1, rssItemList);
        newsListView.setAdapter(adapter);
     //  String siteURL = "http://xmlhost.x10host.com/xmllottery.xml";

        String siteURL = "http://xmlhost.savethis.net/xmllottery.xml";
        new RetrieveFeedTask().execute(siteURL);

       // adapter.notifyDataSetChanged();

		
	
	/*	newsListView.setOnItemClickListener(new OnItemClickListener()
			{

			

				@Override
				public void onItemClick(AdapterView<?> adapter, View v, int position,
										long arg3) 
				{
					

					
					Object listPosition = adapter.getItemAtPosition(position);
				//	String listLink = adapter.link;
				//    Object listLink = adapter.
				

					
					Toast.makeText(getApplicationContext(), "You selected item "+ position + ":" + listPosition, Toast.LENGTH_LONG).show();
					//Toast.makeText(getApplication(), rssItemList. , Toast.LENGTH_LONG).show();
					//rssItemList.toString();
					//String value = (String)adapter.getItemAtPosition(position); 
					//Toast.makeText(MainActivity.this, 
					//		 "Test Pop up", Toast.LENGTH_LONG).show();
					
					// assuming string and if you want to get the value on click of list item
					// do what you intend to do on click of listview row
				}
			});     
*/

    }
	
	
	
	

    public List<TicketItem> parseRSS(URL feedURL)
            throws XmlPullParserException, IOException {

        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(feedURL.openStream(), null);

        int eventType = parser.getEventType();

        boolean done = false;

        TicketItem currentTicketItem= new TicketItem();

        ////////////////////////////////////////////////////////////// attempt to slow down a bit to keep from crashing
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (eventType != XmlPullParser.END_DOCUMENT && !done) {
            String name = null;
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("game")) {
                        // a new item element
                        currentTicketItem = new TicketItem();
                    } else if (currentTicketItem != null) {
                        if (name.equalsIgnoreCase("link")) {
                            currentTicketItem.setLink(parser.nextText());
                        } else if (name.equalsIgnoreCase("prizesLeft")){

                              currentTicketItem.setPrizeInfo(parser.nextText() + "  prizes at ");
                        }
                        else if (name.equalsIgnoreCase("prizesLeftA")){
                            currentTicketItem.setPrizeA(parser.nextText().replaceAll("/n","").trim());

                        }
                        else if (name.equalsIgnoreCase("prizesLeftB")){
                            currentTicketItem.setPrizeB(parser.nextText().replaceAll("/n","").trim());
                        }
                        else if (name.equalsIgnoreCase("prizesLeftC")){
                            currentTicketItem.setPrizeC(parser.nextText().replaceAll("/n","").trim());
                        }
                        else if (name.equalsIgnoreCase("prizeValueA")){
                            currentTicketItem.setValueA(parser.nextText().replaceAll("/n","").trim());
                        }
                        else if (name.equalsIgnoreCase("prizeValueB")){
                            currentTicketItem.setValueB(parser.nextText().replaceAll("/n","").trim());

                        }
                        else if (name.equalsIgnoreCase("prizeValueC")){
                            currentTicketItem.setValueC(parser.nextText().replaceAll("/n","").trim());

                        }
                        //////////////////////////////////////////////////////// PRO Version
                        else if (name.equalsIgnoreCase("prizesLeftD")){
                            currentTicketItem.setPrizeD(parser.nextText().replaceAll("/n", "").trim());

                        }
                        else if (name.equalsIgnoreCase("prizesLeftE")){
                            currentTicketItem.setPrizeE(parser.nextText().replaceAll("/n", "").trim());
                        }
                        else if (name.equalsIgnoreCase("prizesLeftF")){
                            currentTicketItem.setPrizeF(parser.nextText().replaceAll("/n", "").trim());
                        }
                        else if (name.equalsIgnoreCase("prizeValueD")){
                            currentTicketItem.setValueD(parser.nextText().replaceAll("/n", "").trim());
                        }
                        else if (name.equalsIgnoreCase("prizeValueE")){
                            currentTicketItem.setValueE(parser.nextText().replaceAll("/n", "").trim());

                        }
                        else if (name.equalsIgnoreCase("prizeValueF")){
                            currentTicketItem.setValueF(parser.nextText().replaceAll("/n", "").trim());

                        }



                        else if (name.equalsIgnoreCase("cost")) {
									currentTicketItem.setCost(parser.nextText());
						}
                        else if (name.equalsIgnoreCase("prizeValue")){
                            currentTicketItem.setPrizeInfo(parser.nextText());
                        }
                        else if (name.equalsIgnoreCase("gamenumber")) {
                            currentTicketItem.setGameNumber(parser.nextText());
                        } else if (name.equalsIgnoreCase("name")) {
                            currentTicketItem.setTitle(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("game") && currentTicketItem != null) {
                        rssItemList.add(currentTicketItem);
                    } else if (name.equalsIgnoreCase("ticket")) {
                        done = true;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return rssItemList;
    }

	
	
    //------- AsyncTask ------------//
    class RetrieveFeedTask extends AsyncTask<String, Integer, Integer> {

        protected Integer doInBackground(String... urls) {
            try {
                URL feedURL = new URL(urls[0]);
                rssItemList = parseRSS(feedURL);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block

                e.printStackTrace();
            } catch (XmlPullParserException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
          //  adapter.notifyDataSetChanged();   changing the adapter from the background thread not the UI thread.... handler perhaps?
            return 0;

        }

        protected void onPostExecute(Integer result) {
            adapter.notifyDataSetChanged();
        }

    }


	
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
       // adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      
		switch (item.getItemId()) {
			case R.id.action_newfirst:
				Toast.makeText(getApplicationContext(), "Sorting by Newest Tickets first" , Toast.LENGTH_LONG).show();
				
				Collections.sort(rssItemList, TicketItem.GameNumberComparator);
                newsListView.smoothScrollToPosition(0);
				adapter.notifyDataSetChanged();
				// search action
				return true;
			case R.id.action_abc:
				// alphabetically

				
				Collections.sort(rssItemList, TicketItem.GameNameComparator );
                newsListView.smoothScrollToPosition(0);

                adapter.notifyDataSetChanged();
				
				
				
				
				
				Toast.makeText(getApplicationContext(), "Sorting Alphabetically" , Toast.LENGTH_LONG).show();
				
				//	LocationFound();
				return true;
			case R.id.action_cost:
				
				
				Collections.sort(rssItemList, TicketItem.CostComparator );
                newsListView.smoothScrollToPosition(0);

                adapter.notifyDataSetChanged();
				
				Toast.makeText(getApplicationContext(), "Sorting by Cost" , Toast.LENGTH_LONG).show();
				// refresh
				return true;
			default:
				return super.onOptionsItemSelected(item);
        }
    
	
		
		

     //   return super.onOptionsItemSelected(item);
    }
	
	
	
	
	
        // Take appropriate action for each action item click
        
    /**
     * Launching new activity
     * */
   /* private void LocationFound() {
        Intent i = new Intent(MainActivity.this, LocationFound.class);
        startActivity(i);
    }
	*/
  /*@Override
    protected void onResume(Bundle savedInstanceState){
        super.onResume();
        adapter.notifyDataSetChanged();
    }*/

    protected void onPause(Bundle savedInstanceState){

      //  adapter.notifyDataSetChanged();
    }

    protected void onStart(Bundle savedInstanceState){

      //  adapter.notifyDataSetChanged();
    }


}
