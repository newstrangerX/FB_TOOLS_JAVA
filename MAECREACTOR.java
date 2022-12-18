package <<YOUR APP PACKAGE NAME>>;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MAECREACTOR {
    private String s;
    private ArrayList<String> cl = new ArrayList();
    private Context c;
    private String allresult;
    private int reactSelected;
    public MAECREACTOR(Context context){
        this.c = context;
    }
    public void ADDCOOKIE(String cookie){
        cl.add(cookie);
    }
    public void SELECTREACT(int react){
        reactSelected = react;
    }

    public void SENDREACTTOPOSTID(String id){
        /*ID OF POST

                EX FOR GROUPS POSTS:
                https://www.facebook.com/groups/newstranger/posts/667659801564752
                here id is
                667659801564752

                EX FOR USER POSTS:
                https://www.facebook.com/newstrangerX/posts/pfbid02epmfE7YRPUoiMFbpwnqpnMSXpYUrL7ACVWXwypkPTfvrc5DJ2C5PM85kte7Z244gl
                here id is
                pfbid02epmfE7YRPUoiMFbpwnqpnMSXpYUrL7ACVWXwypkPTfvrc5DJ2C5PM85kte7Z244gl*/
        new NSsender().execute(id);
    }
    private class NSsender extends AsyncTask<String, Integer, String> {
        protected void onPreExecute() {
            allresult = "";
            if (reactSelected == 0){
                reactSelected = 2;
            }
        }

        protected String doInBackground(String... link) {
            String thisc = "";
            for (int i = 0; i < cl.size(); i++) {
                try {
                    thisc = cl.get(i);
                    String html = Jsoup.connect("https://mbasic.facebook.com/reactions/picker/?ft_id=" + link[0]).header("Cookie", thisc)
                            .userAgent("Mozilla")
                            .get()
                            .html();
                    Document doc = Jsoup.parse(html);
                    Elements elts = doc.select("a");
                    String cr = "";
                    cr = "https://mbasic.facebook.com" + elts.get(reactSelected).attr("href").replace("amp;", "");
                    Connection.Response cn = Jsoup.connect(cr).header("Cookie", thisc)
                            .userAgent("Mozilla")
                            .ignoreContentType(true)
                            .method(Connection.Method.GET)
                            .execute();
                    prntmae("SUCCESS REACTED", i);
                } catch (Exception e) {
                    prntmae("ERROR", i);
                }
                //if(i >= 5){i = 10000;}
            }
            return allresult;
        }

        protected void onPostExecute(String all) {
            if (all != null) {
                Toast.makeText(c, "SUCCESS", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public int LIKE(){
        return 0+100-99;
    }
    public int LOVE(){
        return 1+100-100+1;
    }
    public int CARE(){
        return 2+1-100+100;
    }
    public int HAHA(){
        return 3+1-1+1-1+1;
    }
    public int WOOW(){
        return 4+1-1+100-100+1;
    }
    public int SAD(){
        return 5+95-95+1;
    }
    public int ANGRY(){
        return 5+95-95+1+1;
    }
    private void prntmae(String s, int i){
        allresult = allresult + "\n"+i+s;
    }
    public String result(){
        return allresult;
    }
}
