package dallsoft.com.pascratcher;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.*;
import android.widget.*;
import android.view.*;
import android.view.ContextMenu.*;
import android.content.*;


public class MainActivity extends Activity {

    public ListView newsListView;
    public ArrayAdapter<RssItem> adapter;
    public List<RssItem> rssItemList = new ArrayList<RssItem>();
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsListView = (ListView) findViewById(R.id.newsListView);
       // adapter = new ArrayAdapter<RssItem>(this,
       //         android.R.layout.simple_list_item_1,
       //         rssItemList);
        adapter = new RssItemAdapter(this, android.R.layout.simple_list_item_1, rssItemList);
        newsListView.setAdapter(adapter);

      //  String siteURL = "http://www.npr.org/rss/rss.php?id=1001";
        String siteURL = "http://xmlhost.x10host.com/xmllottery.xml";
        new RetrieveFeedTask().execute(siteURL);
		
		
		
	
		newsListView.setOnItemClickListener(new OnItemClickListener()
			{

			

				@Override
				public void onItemClick(AdapterView<?> adapter, View v, int position,
										long arg3) 
				{
					

					
					Object listPosition = adapter.getItemAtPosition(position);
				//	String listLink = adapter.link;
				//    Object listLink = adapter.
				
				    String test =(String)rssItemList.toString();
					
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
			
			
	}
	
	
	
	

    public List<RssItem> parseRSS(URL feedURL)
            throws XmlPullParserException, IOException {

        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(feedURL.openStream(), null);

        int eventType = parser.getEventType();

        boolean done = false;

        RssItem currentRSSItem= new RssItem();

        while (eventType != XmlPullParser.END_DOCUMENT && !done) {
            String name = null;
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("game")) {
                        // a new item element
                        currentRSSItem = new RssItem();
                    } else if (currentRSSItem != null) {
                        if (name.equalsIgnoreCase("link")) {
                            currentRSSItem.setLink(parser.nextText());
                        } else if (name.equalsIgnoreCase("prizesLeft")) {
                            currentRSSItem.setDescription(parser.nextText());

                        }
                        else if (name.equalsIgnoreCase("prizeValue")){
                            currentRSSItem.setDescription(parser.nextText());
                        }
                        else if (name.equalsIgnoreCase("gamenumber")) {
                            currentRSSItem.setGameNumber(parser.nextText());
                        } else if (name.equalsIgnoreCase("name")) {
                            currentRSSItem.setTitle(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("game") && currentRSSItem != null) {
                        rssItemList.add(currentRSSItem);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      
		switch (item.getItemId()) {
			case R.id.action_newfirst:
				Toast.makeText(getApplicationContext(), "you selected new first sorting" , Toast.LENGTH_LONG).show();
				// search action
				return true;
			case R.id.action_abc:
				// alphabetically
				
				
				
				Toast.makeText(getApplicationContext(), "you selected alphabetical sorting" , Toast.LENGTH_LONG).show();
				
				//	LocationFound();
				return true;
			case R.id.action_cost:
				
				Toast.makeText(getApplicationContext(), "you selected cost sorting" , Toast.LENGTH_LONG).show();
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
	
}
