package com.example.helloworld8;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.MashapeHello.R;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    public void sendMessage(View view) {

    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	
    	TextView txtView = (TextView) findViewById(R.id.textView1);
    	txtView.setText(message);

    	new CallMashapeAsync().execute(message);
    }
    
    private class CallMashapeAsync extends AsyncTask<String, Integer, HttpResponse<JsonNode>> {

    	protected HttpResponse<JsonNode> doInBackground(String... msg) {

    		HttpResponse<JsonNode> request = null;
			try {
				request = Unirest.get("https://webknox-question-answering.p.mashape.com/questions/answers?question=" + msg[0] + "&answerLookup=true&answerSearch=true")
						  .header("X-Mashape-Authorization", "<Insert Mashape key here>")
						  .asJson();
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		return request;
    	}

    	protected void onProgressUpdate(Integer...integers) {
    	}

    	protected void onPostExecute(HttpResponse<JsonNode> response) {
    		String answer = response.getBody().toString();
        	TextView txtView = (TextView) findViewById(R.id.textView1);
        	txtView.setText(answer);
    	}
    }
}
    



