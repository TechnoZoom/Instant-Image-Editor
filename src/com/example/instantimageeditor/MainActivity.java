package com.example.instantimageeditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.PorterDuff.Mode;
import android.text.TextPaint;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private static final int REQUEST_CODE = 1;
	private static final String TAG = MainActivity.class.getSimpleName();
	private Bitmap mBitmap;
	private Bitmap mRainPenguin;
	private Bitmap sp;
	private Bitmap yel;
	private Bitmap li;
	
	private Bitmap text;
	public Bitmap[] bp;//=new Bitmap[]{BitmapFactory.decodeResource( getResources(), R.drawable.heartfire),BitmapFactory.decodeResource( getResources(), R.drawable.light),BitmapFactory.decodeResource( getResources(), R.drawable.purple),BitmapFactory.decodeResource( getResources(), R.drawable.spider),BitmapFactory.decodeResource( getResources(), R.drawable.spsk),BitmapFactory.decodeResource( getResources(), R.drawable.tex),BitmapFactory.decodeResource( getResources(), R.drawable.yellow)};
	public String s;
	public boolean ch =false;
	private Canvas c;
	private Paint p;
	public Bitmap first;
	public InputStream stream;
	private ImageView v;
	public String[] effects=new String[]{"light","purple","spsk","yellow"};
	private Paint q;
	private Bitmap bm;
	private Bitmap sp2;
	private Bitmap bat;
	private Bitmap fr;
	private Bitmap gl;
	private Bitmap mul;
	private Bitmap pl;
	private Bitmap st;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate!");
		setContentView(R.layout.activity_main);
		
		//mRainPenguin = BitmapFactory.decodeResource( getResources(), R.drawable.light);
		
		v = (ImageView) findViewById(R.id.imageView1);
		q= new Paint();
		q.setAlpha(0);
		
		mBitmap=Bitmap.createBitmap(100, 70,
				Bitmap.Config.ARGB_8888);
		
		c = new Canvas(mBitmap);
		
		
		
		bat=rtr_comp(R.drawable.batman2);
	
		fr=rtr_comp(R.drawable.firedesign);
		
		gl=rtr_comp(R.drawable.gold2);
		
		li=rtr_comp(R.drawable.light);
		
		mul=rtr_comp(R.drawable.multi);
		
		pl=rtr_comp(R.drawable.purple);
		
		sp = rtr_comp(R.drawable.spider);
		
		sp2=rtr_comp(R.drawable.spider2);
		
		
		st=rtr_comp(R.drawable.spsk);
		
		
		text=rtr_comp(R.drawable.tex);
		
		
		
		
		
		c.drawBitmap(text, 0, 0, q);
		
		v.setImageBitmap(mBitmap);
		
		int i,j,j1;
		
		
		/*j1=0;
		 * 
		 * 
		
		Field[] ID_Fields = R.drawable.class.getFields();
		
        int[] resArray = new int[ID_Fields.length];
        
        outer:for(int i1 = 0; i1 < ID_Fields.length; i1++)
       
        while(j1<ID_Fields.length-1)
        {
        	
            try {
                try {
                	if(ID_Fields[i1].getName().startsWith("ic_launcher"))
                		
                	{
                		continue outer;
                	}
					resArray[j1] = ID_Fields[i1].getInt(null);
					j1++;
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/
    
		/*bp=new Bitmap[6];
		
		//bp[0]=BitmapFactory.decodeResource( getResources(), R.drawable.heartfire);
		bp[0]=BitmapFactory.decodeResource( getResources(), R.drawable.light);
		bp[1]=BitmapFactory.decodeResource( getResources(), R.drawable.purple);
		//bp[2]=BitmapFactory.decodeResource( getResources(), R.drawable.spider);
		bp[2]=BitmapFactory.decodeResource( getResources(), R.drawable.spsk);
		bp[3]=BitmapFactory.decodeResource( getResources(), R.drawable.tex);
		bp[4]=BitmapFactory.decodeResource( getResources(), R.drawable.yellow);
		*/
		
		
			
			//bp=new Bitmap[]{BitmapFactory.decodeResource( getResources(), R.drawable.heartfire),BitmapFactory.decodeResource( getResources(), R.drawable.light),BitmapFactory.decodeResource( getResources(), R.drawable.purple),BitmapFactory.decodeResource( getResources(), R.drawable.spider),BitmapFactory.decodeResource( getResources(), R.drawable.spsk),BitmapFactory.decodeResource( getResources(), R.drawable.tex),BitmapFactory.decodeResource( getResources(), R.drawable.yellow)};
		
		

		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Do some Intent magic to open the Gallery? Yes!
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(
						Intent.createChooser(intent, "Select..."), REQUEST_CODE);
					ch = true;
			}
		};
		findViewById(R.id.button1).setOnClickListener(listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

			Uri uri = data.getData();

			Log.d(TAG, uri.toString());
			Toast.makeText(getApplicationContext(), uri.toString(),
					Toast.LENGTH_LONG).show();
			try {
					stream = getContentResolver().openInputStream(uri);
					
					

				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;

				BitmapFactory.decodeStream(stream, null, options);
				stream.close();

				int w = options.outWidth;
				int h = options.outHeight;
				Log.d(TAG, "Bitmap raw size:" + w + " x " + h);

				int displayW = getResources().getDisplayMetrics().widthPixels;
				int displayH = getResources().getDisplayMetrics().heightPixels;
				
				

				int sample = 1;
				
				

				while (w > displayW * sample || h > displayH * sample) {
					sample = sample * 2;
				}
				Log.d(TAG, "Sampling at " + sample);

				options.inJustDecodeBounds = false;
				options.inSampleSize = sample;

				stream = getContentResolver().openInputStream(uri);
				 bm = BitmapFactory.decodeStream(stream, null, options);
				
				if (mBitmap != null) {
					mBitmap.recycle();
				}
				// Make a mutable bitmap...
				mBitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
						Bitmap.Config.ARGB_8888);
				
				 p = new Paint();
				 //q= new Paint();
				p.setAlpha(87);
				
				c=new Canvas(mBitmap);
				
				c.drawColor(0, Mode.CLEAR);
				 //c = new Canvas(mBitmap);
				c.drawBitmap(bm, 0, 0, null);
				//c.drawBitmap(sp, 0, 0, p);
				
				
				
				TextPaint tp = new TextPaint();
				tp.setTextSize(bm.getHeight() / 2);
				tp.setColor(0x800000ff); // AARRGGBB
				// 0xff....... Fully opaque
				// 0x00....... Fully transparent (useless!)
				//c.drawText("Gotcha", 0, bm.getHeight() / 2, tp);

				

				 //v = (ImageView) findViewById(R.id.imageView1);
				v.setImageBitmap(mBitmap);
			} catch (Exception e) {
				Log.e(TAG, "Decoding Bitmap", e);
			}

		}
	}

	public void saveAndShare(View v) {
		if (mBitmap == null) {
			return;
		}
		File path = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		Log.d(TAG, "saveAndShare path = " + path);
		path.mkdirs();

		// Note, for display purposes
		// SimpleDateFormat.getTimeInstance()
		// getDateTimeInstance() or getDateIntance
		// are more appropriate.
		// For filenames we can use the following specification
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		
		

		String filename = "Imagen_" + timestamp + ".jpg";
		// Alternatively ... use System.currentTimeMillis()

		// Creating a new File object in Java does not create a new
		// file on the device. The file object just represents
		// a location or path that may or may not exist
		File file = new File(path, filename);
		FileOutputStream stream_out;
		try {
			// This can fail if the external storage is mounted via USB
			stream_out = new FileOutputStream(file);
			mBitmap.compress(CompressFormat.JPEG, 100, stream_out);
			stream_out.close();
		} catch (Exception e) {
			Log.e(TAG, "saveAndShare (compressing):", e);
			return; // Do not continue
		}
		
		Uri uri = Uri.fromFile(file);
		
		// Tell Android that a new public picture exists
		
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		intent.setData(uri);
		sendBroadcast(intent);

		// Send the public picture file to my friend... 
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("image/jpeg");
		share.putExtra(Intent.EXTRA_STREAM, uri);
		startActivity(Intent.createChooser(share, "Share using..."));
	}
	
	public void ba(View t)
	{
		if(ch)
		{
		c.drawColor(0, Mode.CLEAR);
		
		c.drawBitmap(bm, 0, 0, null);
		c.drawBitmap(Bitmap.createScaledBitmap(bat, bm.getWidth(), bm.getHeight(), false), 0, 0, p);
		
		v.setImageBitmap(mBitmap);
		}
		
	}
	
	
	public void fr(View t)
	{
		if(ch)
		{
		c.drawColor(0, Mode.CLEAR);
		
		c.drawBitmap(bm, 0, 0, null);
		//c.drawBitmap(fr, 0, 0, p);
		c.drawBitmap(Bitmap.createScaledBitmap(fr, bm.getWidth(), bm.getHeight(), false), 0, 0, p);
		
		v.setImageBitmap(mBitmap);
		}
		
	}
	
	
	public void go(View t)
	{
		if(ch)
		{
		c.drawColor(0, Mode.CLEAR);
		
		c.drawBitmap(bm, 0, 0, null);
		//c.drawBitmap(gl, 0, 0, p);
		c.drawBitmap(Bitmap.createScaledBitmap(gl, bm.getWidth(), bm.getHeight(), false), 0, 0, p);
		
		v.setImageBitmap(mBitmap);
		}
		
	}
	
	public void li(View t)
	{
		if(ch)
		{
		c.drawColor(0, Mode.CLEAR);
		
		c.drawBitmap(bm, 0, 0, null);
		//c.drawBitmap(li, 0, 0, p);
		c.drawBitmap(Bitmap.createScaledBitmap(li, bm.getWidth(), bm.getHeight(), false), 0, 0, p);
		
		
		v.setImageBitmap(mBitmap);
		}
		
	}
	
	
	public void ml(View t)
	{
		if(ch)
		{
		c.drawColor(0, Mode.CLEAR);
		
		c.drawBitmap(bm, 0, 0, null);
		//c.drawBitmap(mul, 0, 0, p);
		c.drawBitmap(Bitmap.createScaledBitmap(mul, bm.getWidth(), bm.getHeight(), false), 0, 0, p);
		
		v.setImageBitmap(mBitmap);
		}
		
	}
	
	public void pu(View t)
	{
		if(ch)
		{
		c.drawColor(0, Mode.CLEAR);
		
		c.drawBitmap(bm, 0, 0, null);
		//c.drawBitmap(pl, 0, 0, p);
		c.drawBitmap(Bitmap.createScaledBitmap(pl, bm.getWidth(), bm.getHeight(), false), 0, 0, p);
		
		v.setImageBitmap(mBitmap);
		}
		
	}
	
	public void sp(View t)
	{
		if(ch)
		{
		c.drawColor(0, Mode.CLEAR);
		
		c.drawBitmap(bm, 0, 0, null);
		//c.drawBitmap(sp, 0, 0, p);
		c.drawBitmap(Bitmap.createScaledBitmap(sp, bm.getWidth(), bm.getHeight(), false), 0, 0, p);
		
		v.setImageBitmap(mBitmap);
		}
		
	}
	
	public void sp2(View t)
	{
		if(ch)
		{
		c.drawColor(0, Mode.CLEAR);
		
		c.drawBitmap(bm, 0, 0, null);
		//c.drawBitmap(sp2, 0, 0, p);
		c.drawBitmap(Bitmap.createScaledBitmap(sp2, bm.getWidth(), bm.getHeight(), false), 0, 0, p);
		
		v.setImageBitmap(mBitmap);
		}
		
	}
	
	public void st(View t)
	{
		if(ch)
		{
		c.drawColor(0, Mode.CLEAR);
		
		c.drawBitmap(bm, 0, 0, null);
		//c.drawBitmap(st, 0, 0, p);
		c.drawBitmap(Bitmap.createScaledBitmap(st, bm.getWidth(), bm.getHeight(), false), 0, 0, p);
		
		v.setImageBitmap(mBitmap);
		}
		
	}
	
	public void tx(View t)
	{
		if(ch)
		{
		c.drawColor(0, Mode.CLEAR);
		
		c.drawBitmap(bm, 0, 0, null);
		//c.drawBitmap(text, 0, 0, p);
		c.drawBitmap(Bitmap.createScaledBitmap(text, bm.getWidth(), bm.getHeight(), false), 0, 0, p);
		
		v.setImageBitmap(mBitmap);
		}
		
	}
	
	
	
	public Bitmap rtr_comp(int id_1)
	{
		
		
		try {
			InputStream stream_2 = getResources().openRawResource(id_1);
			
			

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;

		BitmapFactory.decodeStream(stream_2, null, options);
		stream_2.close();

		int w = options.outWidth;
		int h = options.outHeight;
		Log.d(TAG, "Bitmap raw size:" + w + " x " + h);

		int displayW = getResources().getDisplayMetrics().widthPixels;
		int displayH = getResources().getDisplayMetrics().heightPixels;
		
		

		int sample = 1;
		
		

		while (w > displayW * sample || h > displayH * sample) {
			sample = sample * 2;
		}
		Log.d(TAG, "Sampling at " + sample);

		options.inJustDecodeBounds = false;
		options.inSampleSize = sample;

		stream_2 = getResources().openRawResource(id_1);
		Bitmap bm_comp = BitmapFactory.decodeStream(stream_2, null, options);
		 
		 return bm_comp;
		 
		} catch (Exception e) {
			Log.e(TAG, "Decoding Bitmap", e);
			return null;
		}
					
		
		
		
	
	}
	
}	
	
	
	

