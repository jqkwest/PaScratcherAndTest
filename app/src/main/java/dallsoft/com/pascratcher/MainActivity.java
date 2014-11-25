package dallsoft.com.pascratcher;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


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
                        else if (name.equalsIgnoreCase("pubDate")) {
                            currentRSSItem.setPubDate(parser.nextText());
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
