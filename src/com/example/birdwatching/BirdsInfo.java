package com.example.birdwatching;



import java.util.Locale;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class BirdsInfo extends Activity {
	//create the widgets to use
	Button button1,page,add,godb,delete,update,home,report;
	Spinner  spinner1,spinner2;
	TextView group;
	EditText et1,et2,edit;
	//create the ImageView to show the pictures of the birds, and TextToSpeech to speak 
	ImageView imageView1;	
	TextToSpeech talker;
	
	DBAdapter myDb;				//create an instance of the DBAdapter 
	String bname,bgroup;

	int q;
	public static final String PREFS_NAME ="MyPreferencesFile";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_birds_info);
		
		openDB();
		
		 spinner1=(Spinner)this.findViewById(R.id.spinner1);
		 spinner2=(Spinner)this.findViewById(R.id.spinner2);
		 group=(TextView)this.findViewById(R.id.group);
		 et1=(EditText)this.findViewById(R.id.editText1);
		 et2=(EditText)this.findViewById(R.id.editText2);
		 edit=(EditText)this.findViewById(R.id.edit);
		 
		 String quality=et1.getText().toString();
		// notes=et2.getText().toString();
		 imageView1=(ImageView)this.findViewById(R.id.imageView1);	
		 button1=(Button)this.findViewById(R.id.button1);
		 page=(Button)this.findViewById(R.id.page);
		 add=(Button)this.findViewById(R.id.add);
		 godb=(Button)this.findViewById(R.id.godb);
		 delete=(Button)this.findViewById(R.id.delete);
		 update=(Button)this.findViewById(R.id.update);
		 home=(Button)this.findViewById(R.id.home);
		 report=(Button)this.findViewById(R.id.report);
		//set the new activity to text 
		 report.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					Intent newIntent= new Intent (BirdsInfo.this,SMSActivity.class);
					startActivity(newIntent);
					finish();
				}
			});
		//Delete a row 
		 delete.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					
					int i=Integer.parseInt(edit.getText().toString());
					myDb.deleteRow(i);
					
				}
			});
		 //Update a specific row 
		 update.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					int i=Integer.parseInt(edit.getText().toString());
					int j=Integer.parseInt(et1.getText().toString());
			//		String notes= et2.getText().toString();
					myDb.updateRow(i, bname, j, bgroup);
					
				}
			});
		 // goes back to MainActivity
		 home.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					Intent newIntent= new Intent (BirdsInfo.this,MainActivity.class);
					startActivity(newIntent);
					finish();
				}
			});
	// the use of text to speech object: talker	 
		 talker = new TextToSpeech(BirdsInfo.this,new TextToSpeech.OnInitListener(){
			   public void onInit(int status){
				   if(status!=TextToSpeech.ERROR){
					   spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
						    @Override
						    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				
						    	switch(position){		    	
						    	case 0: 
						    		talker.setLanguage(Locale.US);
						    			break;
						    	case 1: 
						    		
						    		talker.setLanguage(Locale.GERMAN);
						    			break;
						    	case 2:
						    		
						    		talker.setLanguage(Locale.FRANCE);
						    			break;
						    	}
						    }

						    @Override
						    public void onNothingSelected(AdapterView<?> parentView) {
						    	talker.setLanguage(Locale.US);
						    }
						});
					   
				   }
			   }
			   
		   });
		  	 
		spinner1.setPrompt("Tap to Select");
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	   // different cases on select items from the spinner
		    	imageView1.setImageResource(R.drawable.ic_launcher);
		    	switch(position){		    	
		    	case 0: 
		    		bname="bittern";
		    		bgroup="siege";
		    		group.setText("siege");		
		    		button1.setOnClickListener(new OnClickListener() {
	    				   
	    				   @Override
	    				   public void onClick(View v) {
	    					   //set different voices and pictures
	    					   imageView1.setImageResource(R.drawable.eurasian_bittern_1);	    
	    					   talker.speak("bittern of group siege", TextToSpeech.QUEUE_FLUSH, null);

	    				   }
			   
	    				  });
		    		page.setOnClickListener(new OnClickListener(){
		    			
		    			@Override
		    			public void onClick(View v){
// pass the url of the webpage to the WebActivity so it can be seen
		    				SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
		    				SharedPreferences.Editor editor=settings.edit();
		    				editor.putString("link","http://en.m.wikipedia.org/wiki/Bittern");
		    				editor.commit();
		    				Intent newIntent= new Intent (BirdsInfo.this,WebActivity.class);
		    				startActivity(newIntent);
		    				finish();
		    			}
		    		});
		    			break;
		    	case 1: 
		    		bname="chicken";
		    		bgroup="peep";
		    		group.setText("peep");	
		    		
		    		button1.setOnClickListener(new OnClickListener() {
	    				   
	    				   @Override
	    				   public void onClick(View v) {
	    					   imageView1.setImageResource(R.drawable.chickens);	
	    					   
	    					   
	    					   talker.speak("chicken of group peep", TextToSpeech.QUEUE_FLUSH, null);
	    				   }
	    				  });
		    		page.setOnClickListener(new OnClickListener(){
		    			
		    			@Override
		    			public void onClick(View v){

		    				SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
		    				SharedPreferences.Editor editor=settings.edit();
		    				editor.putString("link","http://en.m.wikipedia.org/wiki/Chicken");
		    				editor.commit();
		    				Intent newIntent= new Intent (BirdsInfo.this,WebActivity.class);
		    				startActivity(newIntent);
		    				finish();
		    			}
		    		});
		    			break;
		    	case 2:
		    		bname="coot";
		    		bgroup="cover";
		    		group.setText("cover");		
		    		button1.setOnClickListener(new OnClickListener() {
	    				   
	    				   @Override
	    				   public void onClick(View v) {
	    					   imageView1.setImageResource(R.drawable.coots);	
	    					  
	    					   
	    					   talker.speak("coots of group cover", TextToSpeech.QUEUE_FLUSH, null);
	    				   }
	    				  });
		    		page.setOnClickListener(new OnClickListener(){
		    			
		    			@Override
		    			public void onClick(View v){

		    				SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
		    				SharedPreferences.Editor editor=settings.edit();
		    				editor.putString("link","http://en.m.wikipedia.org/wiki/Coot");
		    				editor.commit();
		    				Intent newIntent= new Intent (BirdsInfo.this,WebActivity.class);
		    				startActivity(newIntent);
		    				finish();
		    			}
		    		});
		    			break;
		    	case 3:
		    		bname="cormorant";
		    		bgroup="flight";
		    		group.setText("flight");		    
		    		button1.setOnClickListener(new OnClickListener() {
	    				   
	    				   @Override
	    				   public void onClick(View v) {
	    					   imageView1.setImageResource(R.drawable.cormorants);	
	    					   
	    					   
	    					   talker.speak("cormorants of group flight", TextToSpeech.QUEUE_FLUSH, null);
	    				   }
	    				  });
		    		page.setOnClickListener(new OnClickListener(){
		    			
		    			@Override
		    			public void onClick(View v){

		    				SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
		    				SharedPreferences.Editor editor=settings.edit();
		    				editor.putString("link","http://en.m.wikipedia.org/wiki/Coot");
		    				editor.commit();
		    				Intent newIntent= new Intent (BirdsInfo.this,WebActivity.class);
		    				startActivity(newIntent);
		    				finish();
		    			}
		    		});
		    			break;
		    	case 4:
		    		bname="crane";
		    		bgroup="herd";
		    		group.setText("herd");
		    		button1.setOnClickListener(new OnClickListener() {
	    				   
	    				   @Override
	    				   public void onClick(View v) {
	    					   imageView1.setImageResource(R.drawable.cranes);	
	    					  
	    					   talker.speak("cranes of group herd", TextToSpeech.QUEUE_FLUSH, null);
	    				   }
	    				  });
		    		page.setOnClickListener(new OnClickListener(){
		    			
		    			@Override
		    			public void onClick(View v){

		    				SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
		    				SharedPreferences.Editor editor=settings.edit();
		    				editor.putString("link","http://en.m.wikipedia.org/wiki/Crane_(bird)");
		    				editor.commit();
		    				Intent newIntent= new Intent (BirdsInfo.this,WebActivity.class);
		    				startActivity(newIntent);
		    				finish();
		    			}
		    		});
		    			break;
		    	case 5:  	
		    		bname="dotterel";
		    		bgroup="trip";
		    		group.setText("trip");		  
		    		button1.setOnClickListener(new OnClickListener() {
	    				   
	    				   @Override
	    				   public void onClick(View v) {
	    					   imageView1.setImageResource(R.drawable.dotterel);	
	    					   
	    					   
	    					   talker.speak("dotterel of group trip", TextToSpeech.QUEUE_FLUSH, null);
	    				   }
	    				  });
		    		page.setOnClickListener(new OnClickListener(){
		    			
		    			@Override
		    			public void onClick(View v){

		    				SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
		    				SharedPreferences.Editor editor=settings.edit();
		    				editor.putString("link","http://en.m.wikipedia.org/wiki/Dotterel");
		    				editor.commit();
		    				Intent newIntent= new Intent (BirdsInfo.this,WebActivity.class);
		    				startActivity(newIntent);
		    				finish();
		    			}
		    		});
		    			break;	
		    	case 6:
		    		bname="dove";
		    		bgroup="dule";
		    		group.setText("dule");
		    		button1.setOnClickListener(new OnClickListener() {
	    				   
	    				   @Override
	    				   public void onClick(View v) {
	    					   imageView1.setImageResource(R.drawable.doves);	
	    					  
	    					   
	    					   talker.speak("doves of group dule", TextToSpeech.QUEUE_FLUSH, null);
	    				   }
	    				  });
		    		page.setOnClickListener(new OnClickListener(){
		    			
		    			@Override
		    			public void onClick(View v){

		    				SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
		    				SharedPreferences.Editor editor=settings.edit();
		    				editor.putString("link","http://en.m.wikipedia.org/wiki/Dove");
		    				editor.commit();
		    				Intent newIntent= new Intent (BirdsInfo.this,WebActivity.class);
		    				startActivity(newIntent);
		    				finish();
		    			}
		    		});
		    			break;
		    	case 7:
		    		bname="eagle";
		    		bgroup="convocation";
		    		group.setText("convocation");
		    		button1.setOnClickListener(new OnClickListener() {
	    				   
	    				   @Override
	    				   public void onClick(View v) {
	    					   imageView1.setImageResource(R.drawable.eagles);	
	    					   
	    					   
	    					   talker.speak("eagles of group convocation", TextToSpeech.QUEUE_FLUSH, null);
	    				   }
	    				  });
		    		page.setOnClickListener(new OnClickListener(){
		    			
		    			@Override
		    			public void onClick(View v){		    				
		    				SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
		    				SharedPreferences.Editor editor=settings.edit();
		    				editor.putString("link","http://en.m.wikipedia.org/wiki/Eagle");
		    				editor.commit();
		    				Intent newIntent= new Intent (BirdsInfo.this,WebActivity.class);
		    				startActivity(newIntent);
		    				finish();
		    			}
		    		});
		    			break;
		    	case 8:
		    		bname="falcon";
		    		group.setText("cast");
		    		button1.setOnClickListener(new OnClickListener() {
	    				   
	    				   @Override
	    				   public void onClick(View v) {
	    					   imageView1.setImageResource(R.drawable.falcons);	    
	    					  
	    					   
	    					   talker.speak("falcons of group cast", TextToSpeech.QUEUE_FLUSH, null);
	    				   }
	    				  });
		    		page.setOnClickListener(new OnClickListener(){
		    			
		    			@Override
		    			public void onClick(View v){

		    				SharedPreferences settings=getSharedPreferences(PREFS_NAME, 0);
		    				SharedPreferences.Editor editor=settings.edit();
		    				editor.putString("link","http://en.m.wikipedia.org/wiki/Falcon");
		    				editor.commit();
		    				Intent newIntent= new Intent (BirdsInfo.this,WebActivity.class);
		    				startActivity(newIntent);
		    				finish();
		    			}
		    		});
		    			break;
		    	}
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        spinner1.setPrompt("Tap to Select");// your code here
		    }

		});

		

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		closeDB();
		
	}

	private void closeDB() {
		myDb.close();
	}

	private void openDB() {
		myDb= new DBAdapter	(this);
		myDb.open();
	}
	//Display the text in database
	private void displayText(String message) {
        TextView textView = (TextView) findViewById(R.id.textDisplay);
        textView.setText(message);
	}
	
	public void onClick_AddRecord(View v) {
		displayText("Clicked add record!");
		q=Integer.parseInt(et1.getText().toString());
	
		long newId = myDb.insertRow(bname,q, bgroup);
		
		// Query for the record we just added.
		// Use the ID:
		Cursor cursor = myDb.getRow(newId);
		displayRecordSet(cursor);
	}
	
	public void onClick_DisplayRecords(View v) {
		displayText("Clicked display record!");
		
		Cursor cursor = myDb.getAllRows();
		displayRecordSet(cursor);
	}
	
	
	
	private void displayRecordSet(Cursor cursor) {
		String message = "";
		// populate the message from the cursor
		
		// Reset cursor to start, checking to see if there's data:
		if (cursor.moveToFirst()) {
			do {
				// Process the data:
				int id = cursor.getInt(DBAdapter.COL_ROWID);
				String name = cursor.getString(DBAdapter.COL_NAME);
				int quality = cursor.getInt(DBAdapter.COL_QUAL);
				String group = cursor.getString(DBAdapter.COL_GR);
				
				// Append data to the message:
				message += "id=" + id
						   +", name=" + name
						   +", quality=" + quality
						   +", group=" + group
					//	   +", notes=" +notes
						   +"\n";
			} while(cursor.moveToNext());
		}
		
		// Close the cursor to avoid a resource leak.
		cursor.close();
		
		displayText(message);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.birds_info, menu);
		return true;
	}

}
