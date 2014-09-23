package com.example.test2;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author KanKan todo 
 */

public class LevelupDetailActivity extends Activity implements
		OnClickListener  {

 

	private TextView reformStandard, reformName, reformDescription,
			reformSuitable, takePhoto, reformSubmit, reformPass;
	private EditText reformResult;
	private RadioButton reformC0, reformC1, reformC2, reformC3, reformC4;
	private int centResult;
	private ImageView img1, img2, img3, back;

	private boolean pass = false, suitable = false;

	private int index = 0;

 
	private RadioGroup radioGroup;
	private LinearLayout ratingLayout, levelImgLayout,takePhotoLayout,levelOkLayout;
	private RelativeLayout suitableLayout;
	// 预警的流水id，预警id，预警类型，这由预警传来的数据
	private String flowId, prewardId, prewarnType, SHOWID, MSID, TASKID,
			BOOKID, GAPID, imgStr1, imgStr2, imgStr3, FIXKEY, JUMPKEY,
			isCentOrSwitch;
	// 当前照片文件
	private File mCurrentPhotoFile;

	private ImageView imageview;
	
    private LayoutTransition mTransitioner;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_levelupdetail);
 		
		// 预警的流水id，预警id，预警类型，这由预警传来的数据
		flowId = getIntent().getStringExtra("FLOWID");
		prewardId = getIntent().getStringExtra("PREWARN");
		prewarnType = getIntent().getStringExtra("PREWARNTYPE");

		// 页面关系的4是质量追溯 3是天然乳验证
		JUMPKEY = getIntent().getStringExtra("JUMPKEY");
		JUMPKEY = "2";
  
		initLayout();
 

	}
	private void initLayout() {
		int pageInt = 0;

		switch (pageInt) {
		case 4:
			((TextView) findViewById(R.id.v_title_text)).setText("璐ㄩ噺杩芥函璇︽儏");

			break;

		case 2:
			((TextView) findViewById(R.id.v_title_text)).setText("椤甸潰鏍囬");

			break;
		case 3:

			break;

		}

		back = (ImageView) findViewById(R.id.v_title_left_img);
		

		ratingLayout = (LinearLayout) findViewById(R.id.rating_layout);
		suitableLayout = (RelativeLayout) findViewById(R.id.suitable_layout);
		levelImgLayout = (LinearLayout) findViewById(R.id.level_imgLayout);
		takePhotoLayout=(LinearLayout)findViewById(R.id.takephoto_layout);
        levelOkLayout=(LinearLayout)findViewById(R.id.level_ok_layout);
		
		
		reformStandard = (TextView) findViewById(R.id.reform_standard);
		reformName = (TextView) findViewById(R.id.reform_name);
		reformDescription = (TextView) findViewById(R.id.reform_description);

		reformC0 = (RadioButton) findViewById(R.id.reform_c0);

		reformC1 = (RadioButton) findViewById(R.id.reform_c1);
		reformC2 = (RadioButton) findViewById(R.id.reform_c2);
		reformC3 = (RadioButton) findViewById(R.id.reform_c3);
		reformC4 = (RadioButton) findViewById(R.id.reform_c4);
		reformPass = (TextView) findViewById(R.id.reform_pass);
		reformResult = (EditText) findViewById(R.id.reform_result);
		reformSuitable = (TextView) findViewById(R.id.reform_issuitable);
		takePhoto = (TextView) findViewById(R.id.reform_takephoto);
		reformSubmit = (TextView) findViewById(R.id.reform_submit);
		radioGroup = (RadioGroup) findViewById(R.id.level_radiogroup);
//		radioGroup.setOnCheckedChangeListener(this);

		img1 = (ImageView) findViewById(R.id.reform_img1);
		img2 = (ImageView) findViewById(R.id.reform_img2);
		img3 = (ImageView) findViewById(R.id.reform_img3);
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
		img3.setOnClickListener(this);
		back.setOnClickListener(this);

		reformC1.setOnClickListener(this);
		reformC2.setOnClickListener(this);
		reformC3.setOnClickListener(this);
		reformC4.setOnClickListener(this);
		reformResult.setOnClickListener(this);
		reformPass.setOnClickListener(this);
		reformSuitable.setOnClickListener(this);
		takePhoto.setOnClickListener(this);
		reformSubmit.setOnClickListener(this);

	 
		if (JUMPKEY.equals("4")) {
			ratingLayout.setVisibility(View.GONE);
		}
        if(JUMPKEY.equals("8")){
        	takePhotoLayout.setVisibility(View.GONE);
        }
	 //声明动画
		resetTransition();
 
	//初始化动画	
		initAnim();
		
		
//		initData();

	}
	
	  private void resetTransition() {
	        mTransitioner = new LayoutTransition();
	        levelOkLayout.setLayoutTransition(mTransitioner);
	    }
	  
	  private void  initAnim(){
          mTransitioner.setStagger(LayoutTransition.CHANGE_APPEARING, 30);
          mTransitioner.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);
          setupCustomAnimations();
          long duration; 
          duration = 500;
          mTransitioner.setDuration(duration);
	  }
	  //设置自定义动画的方法
	  private void setupCustomAnimations() {
	        // Changing while Adding
	        PropertyValuesHolder pvhLeft =
	                PropertyValuesHolder.ofInt("left", 0, 1);
	        PropertyValuesHolder pvhTop =
	                PropertyValuesHolder.ofInt("top", 0, 1);
	        PropertyValuesHolder pvhRight =
	                PropertyValuesHolder.ofInt("right", 0, 1);
	        PropertyValuesHolder pvhBottom =
	                PropertyValuesHolder.ofInt("bottom", 0, 1);
	        PropertyValuesHolder pvhScaleX =
	                PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
	        PropertyValuesHolder pvhScaleY =
	                PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
	        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
	                        this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY).
	                setDuration(mTransitioner.getDuration(LayoutTransition.CHANGE_APPEARING));
	        mTransitioner.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
	        changeIn.addListener(new AnimatorListenerAdapter() {
	            public void onAnimationEnd(Animator anim) {
	                View view = (View) ((ObjectAnimator) anim).getTarget();
	                view.setScaleX(1f);
	                view.setScaleY(1f);
	            }
	        });

	        // Changing while Removing
	        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
	        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
	        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
	        PropertyValuesHolder pvhRotation =
	                PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
	        final ObjectAnimator changeOut = ObjectAnimator.ofPropertyValuesHolder(
	                        this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).
	                setDuration(mTransitioner.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
	        mTransitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeOut);
	        changeOut.addListener(new AnimatorListenerAdapter() {
	            public void onAnimationEnd(Animator anim) {
	                View view = (View) ((ObjectAnimator) anim).getTarget();
	                view.setRotation(0f);
	            }
	        });

	        // Adding
	        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f).
	                setDuration(mTransitioner.getDuration(LayoutTransition.APPEARING));
	        mTransitioner.setAnimator(LayoutTransition.APPEARING, animIn);
	        animIn.addListener(new AnimatorListenerAdapter() {
	            public void onAnimationEnd(Animator anim) {
	                View view = (View) ((ObjectAnimator) anim).getTarget();
	                view.setRotationY(0f);
	            }
	        });

	        // Removing
	        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f).
	                setDuration(mTransitioner.getDuration(LayoutTransition.DISAPPEARING));
	        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);
	        animOut.addListener(new AnimatorListenerAdapter() {
	            public void onAnimationEnd(Animator anim) {
	                View view = (View) ((ObjectAnimator) anim).getTarget();
	                view.setRotationX(0f);
	            }
	        });

	    }
	  
 

	 

	/*// 淇敼鏁版嵁鎴栬?鏌ョ湅棰勮鏃跺?锛岄噸鏂板啓鍏ユ暟鎹?

	private void setData() {
		setOldData();
if(gapEntity!=null){
		reformStandard.setText(gapEntity.referenceBookName);
		reformName.setText(gapEntity.GAPName);
		reformDescription.setText(gapEntity.GDestription);
}
		// 璁＄畻鍥剧墖鏁伴噺
		List<ImageEntity> imgs = gapEntity.images;
		
		 * int imgCount=0; imgCount = (imgs == null || imgs.size() == 0) ?
		 * 0:imgs.size()>3?3:imgs.size(); for(int i=0 ; i<imgCount; i++){
		 * ImageView imgView = (ImageView)
		 * levelImgLayout.findViewWithTag("img"+i);
		 * MApplication.get().getImageLoader().displayImage(imgs.get(i).url,
		 * imgView); }
		 

		int imgCount = (imgs == null || imgs.size() == 0) ? 0
				: (imgs.size() > 3 ? 3 : imgs.size());

		if (imgCount == 3) {
			MApplication.get().getImageLoader()
					.displayImage(imgs.get(0).url, img1);
			MApplication.get().getImageLoader()
					.displayImage(imgs.get(1).url, img1);

			MApplication.get().getImageLoader()
					.displayImage(imgs.get(2).url, img1);

		} else if (imgCount == 2) {
			MApplication.get().getImageLoader()
					.displayImage(imgs.get(0).url, img1);
			MApplication.get().getImageLoader()
					.displayImage(imgs.get(1).url, img1);
		} else if (imgCount == 1) {
			MApplication.get().getImageLoader()
					.displayImage(imgs.get(0).url, img1);
		}*/

		/*
		 * for (int i = 0; i < imgCount; i++) { ImageView ivshow = (ImageView)
		 * levelImgLayout .findViewWithTag("img" + i);
		 * MApplication.get().getImageLoader().displayImage(imgs.get(i).url,
		 * ivshow);
		 * 
		 * }
		 */

		// ? imgCount=3:images.size();

		/*
		 * if (images != null) { if (images.get(0) != null) {
		 * MApplication.get().getImageLoader() .displayImage(images.get(0).url,
		 * img1); } if (images.get(1) != null) {
		 * MApplication.get().getImageLoader() .displayImage(images.get(1).url,
		 * img2);
		 * 
		 * } if (images.get(2) != null) { MApplication.get().getImageLoader()
		 * .displayImage(images.get(2).url, img3); }
		 */
	 

	// set the button state as checkecd or uncheked
	// 璁剧疆鎸夐挳閫変腑鎴栨湭閫変腑鐨勬柟娉?
	private void setCent(TextView tv) {
		reformC1.setBackgroundResource(R.drawable.levelup_udlr);
		reformC2.setBackgroundResource(R.drawable.levelup_udlr);
		reformC3.setBackgroundResource(R.drawable.levelup_udlr);
		reformC4.setBackgroundResource(R.drawable.levelup_udlr);
		int green = getResources().getColor(R.color.GreenButton);
		tv.setBackgroundColor(green);
	}

	private void disable(View v) {
		v.setEnabled(false);
	}

	// set the button state as checkecd or uncheked
	// 璁剧疆鎸夐挳鏈?涓殑鏂规硶
	private void setBorder(TextView tv) {
		tv.setBackgroundResource(R.drawable.levelup_udlr);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.v_title_left_img:
			this.finish();
			break;
		case R.id.reform_c0:
			centResult = 0;

		case R.id.reform_c1:
			centResult = 1;

			break;
		case R.id.reform_c2:
			centResult = 2;

			break;
		case R.id.reform_c3:
			centResult = 3;
			break;

		case R.id.reform_c4:
			centResult = 4;
			break;

		case R.id.reform_img1:
			index = 1;
	//		showSelectItems();

			break;
		case R.id.reform_img2:
			index = 2;
	//		showSelectItems();
			break;

		case R.id.reform_img3:
			index = 3;
	//		showSelectItems();
			break;
		// 鍝佹帶鍒ゆ柇鏄惁绗﹀悎
		case R.id.reform_pass:
			if (!pass) {
				reformPass.setBackgroundResource(R.drawable.switch_on);
				pass = !pass;

			} else {
				reformPass.setBackgroundResource(R.drawable.switch_off);
				pass = !pass;
			}
			break;
		// 楠岃瘉銆佹暣鏀圭粨鏋?
		case R.id.reform_result:
			break;
		// 鏄惁閫傜敤鏈ザ绔?
		case R.id.reform_issuitable:
			if (!suitable) {
				reformSuitable.setBackgroundResource(R.drawable.swich_on);
				suitable = !suitable;
				levelOkLayout.setVisibility(View.VISIBLE);
			} else {
				reformSuitable.setBackgroundResource(R.drawable.switch_off);
				suitable = !suitable;
				levelOkLayout.setVisibility(View.GONE);

			}
			break;
		// 鎷嶇収
		case R.id.reform_takephoto:
			if (index < 4) {
	//			showSelectItems();
			} else {
			}
			;

			break;
		// 鎻愪氦
		case R.id.reform_submit:

//			sendJson();

			break;

		}


	 
 

/*	@Override
	public void onResultHandler(LMessage msg, int requestId) {
		// TODO Auto-generated method stub
		super.onResultHandler(msg, requestId);
		if (msg != null) {

			switch (msg.getWhat()) {
			case 0:
				T.ss("鎻? 浜?澶辫触");

				break;
			case 1:
				T.ss("鎻? 浜?鎴? 鍔?);
				//杩斿洖涓?釜淇敼杩囩殑id缁欏墠涓?釜activity
				Intent intent = new Intent();
				intent.putExtra("FIXID", gapEntity.fixId);
				setResult(321, intent);

				this.finish();
				break;

 
			case 8:
				L.e("------Prewarn-----");
				gapEntity = (GapEntity) msg.getObj();
				if (gapEntity != null) {
					// 淇敼鏁版嵁鎴栬?鏌ョ湅棰勮鏃跺?锛岄噸鏂板啓鍏ユ暟鎹?
					setData();
				}
				break;
			case 7:
				L.e("------TakePhoton-----");

				ImageEntity img = (ImageEntity) msg.getObj();
				dismissProgressDialog();

				// img1.setImageBitmap(bitmap);
				if (index == 1) {

					img1.setImageBitmap(bitmap);
					imgStr1 = img.url;
					index++;
				} else if (index == 2) {
					img2.setImageBitmap(bitmap);
					imgStr2 = img.url;
					index++;
				} else if (index == 3) {
					img3.setImageBitmap(bitmap);
					imgStr3 = img.url;
					index++;
				}
				break;

			}

		}
	}

	// 鎻愪氦棰勮娴佹按id锛?
	private void sendPrewarnJson(String prewarnId) {

		LevelupPrewarnHandler handler = new LevelupPrewarnHandler(this);
		String url = MApplication.get().getAppServiceUrl() + "routine/prewarn"
				+ "?A=" + flowId + "&B=" + prewarnId;
		LReqEntity entity = new LReqEntity();
		entity.setUrl(url);
		entity.setReqMode(LReqMothed.GET);
		handler.request(entity);
	}

	// 浠庢爣鍑嗗寲璺宠繃鏉ワ紝鎻愪氦鏁版嵁
	private void sendJson() {
		LevelupSubmitHandler handler = new LevelupSubmitHandler(this);
		LReqEntity entity = new LReqEntity();
		String url = MApplication.get().getAppServiceUrl()
				+ "routine/levelupdetail";

		Map<String, String> params = new HashMap<String, String>();
		params.put("A", levelEntity.taskId);
		params.put("B", levelEntity.msId);
		params.put("C", levelEntity.referenceBookId);
		params.put("D", levelEntity.referenceBookName);
		params.put("FB", gapEntity.GAPName);
		params.put("FA", gapEntity.id);
		params.put("FD", System.currentTimeMillis() + "");
		
		 * if(isCentOrSwitch!=null){ if(isCentOrSwitch.equals("1")){
		 
		params.put("FG", (suitable == true ? 1 : 0) + "");
		params.put("FE", centResult + "");
		
		 * }else if (isCentOrSwitch.equals("2")){ } }
		 

		params.put("FGG", (pass == true ? 1 : 0) + "");
		params.put("FH", reformResult.getText().toString());
		String imgStrs = imgStr1 + "," + imgStr2 + "," + imgStr3;
		params.put("FI", FIXKEY);
		params.put("FK", imgStrs);
		entity.setParams(params);
		entity.setUrl(url);
		entity.setReqMode(LReqMothed.POST);
		handler.request(entity);

	}

	// 鏌ョ湅宸插畬鎴愮鍚堥」鐨勮鎯咃紝鍙互杩涜淇敼
	private void sendShowJson(String prewarnId) {

		LevelupShowHandler handler = new LevelupShowHandler(this);
		String url = MApplication.get().getAppServiceUrl()
				+ "routine/levelupshow" + "?A=" + TASKID + "&B=" + MSID + "&C="
				+ BOOKID + "&D=" + GAPID + "&E=" + gapEntity.fixId;
		LReqEntity entity = new LReqEntity();
		entity.setUrl(url);
		entity.setReqMode(LReqMothed.GET);
		handler.request(entity);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.reform_c1:
			L.e(checkedId + "-----------");

			break;
		case R.id.reform_c2:
			L.e(checkedId + "-----------");

			break;
		case R.id.reform_c3:
			L.e(checkedId + "-----------");

			break;
		case R.id.reform_c4:
			L.e(checkedId + "-----------");

			break;

		}

	}

	private Bitmap bitmap;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK)
			return;
		switch (requestCode) {
		case CAMERA_WITH_DATA: // 鐓х浉鏈虹▼搴忚繑鍥炵殑,鍐嶆璋冪敤鍥剧墖鍓緫绋嬪簭鍘讳慨鍓浘鐗?
			doCropPhoto(mCurrentPhotoFile);
			break;
		case PHOTO_PICKED_WITH_DATA: // 鐩稿唽
			// 涓婁紶鐓х墖鐨勬柟娉?

			uploadPhoto(data);

		}
	}

	// 涓婁紶鐓х墖鐨勬柟娉?
	private void uploadPhoto(Intent data) {

		Bundle extras = null;
		if (data != null)
			extras = data.getExtras();
		if (extras != null)
			bitmap = (Bitmap) extras.get("data");
		if (bitmap != null) {
			showProgressDialog("姝ｅ湪涓婁紶鍥惧儚");
			String filename = "mImage.jpg";

			String catchUrl = MApplication.get().getCacheUrl();
			String url = LBitmap.saveBmpToSd(bitmap, catchUrl, filename, 99);
			File file = new File(url);
			if (!file.exists()) {
				return;
			}
			// sendRequest(file);
			UploadHandler handler = new UploadHandler(this);
			LReqEntity entity = new LReqEntity();
			String reqUrl = MApplication.get().getAppServiceUrl() + "upload";
			entity.setUrl(reqUrl);
			entity.setReqMode(LReqMothed.POST);
			Map<String, String> params = new HashMap<String, String>();

			entity.setParams(params);
			// 澶т簬3寮狅紝鏃跺?锛岀户缁媿鎽勶紝鍒欒鐩栫涓?紶
			if (index > 3) {
				index = 0;
			}
			LReqFile LFile = new LReqFile(url, file, LReqFileType.JPEG);
			List<LReqFile> files = new ArrayList<LReqFile>();
			files.add(LFile);
			entity.setFileParamsList(files);
			handler.request(entity);
		} else {
			T.ss("鏁版嵁鍔犺浇鍑洪敊锛岃绋嶅悗閲嶈瘯");
			return;
		}
	}

	// 鏄剧ず鎷嶇収瀵硅瘽妗?
	public void showSelectItems() {
		String[] items = { "鎷嶇収", "鐩稿唽" };
		Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("璇烽?鎷?);
		builder.setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int which) {
				switch (which) {
				case 0:
					getPicFromCapture();
					break;
				case 1:
					getPicFromContent();
					break;
				}
			}
		});
		builder.setPositiveButton("鍙栨秷", null);
		builder.show();
	}

	private void getPicFromCapture() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File dir = MApplication.get().getPhotoCache();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			mCurrentPhotoFile = new File(dir, getPhotoFileName()); // 鍛藉悕
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE"); // 瀛樺偍
			intent.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(mCurrentPhotoFile));
			this.startActivityForResult(intent, CAMERA_WITH_DATA);
		}
	}

	// 鐩稿唽
	private void getPicFromContent() {
		try {
			Intent intent = getPhotoPickIntent();
			startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
		} catch (Exception e) {
			T.ss("鎿嶄綔澶辫触");
		}
	}

	@SuppressLint("SimpleDateFormat")
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

	// 瀵瑰浘鐗囪繘琛屽壀瑁?
	private static Intent getPhotoPickIntent() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 80);
		intent.putExtra("outputY", 80);
		intent.putExtra("return-data", true);
		return intent;
	}

	// 璋冪敤鍥剧墖鍓緫绋嬪簭 鍓鍚庣殑鍥剧墖璺宠浆鍒版柊鐨勭晫闈?
	private static Intent getCropImageIntent(Uri photoUri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(photoUri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 80);
		intent.putExtra("outputY", 80);
		intent.putExtra("return-data", true);
		return intent;
	}

	protected void doCropPhoto(File f) {
		try {
			// 鍚姩gallery鍘诲壀杈戣繖涓収鐗?
			final Intent intent = getCropImageIntent(Uri.fromFile(f));
			startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
		} catch (Exception e) {
			T.ss("鎿嶄綔澶辫触");
		}
	}*/
	/*  private int numButtons = 1;
	    ViewGroup container = null;
	    Animator defaultAppearingAnim, defaultDisappearingAnim;
	    Animator defaultChangingAppearingAnim, defaultChangingDisappearingAnim;
	    Animator customAppearingAnim, customDisappearingAnim;
	    Animator customChangingAppearingAnim, customChangingDisappearingAnim;
	    Animator currentAppearingAnim, currentDisappearingAnim;
	    Animator currentChangingAppearingAnim, currentChangingDisappearingAnim;

	    
	    private void showLayout(View v){
        final LayoutTransition transitioner = new LayoutTransition();
        container.setLayoutTransition(transitioner);
        defaultAppearingAnim = transitioner.getAnimator(LayoutTransition.APPEARING);
        defaultDisappearingAnim =
                transitioner.getAnimator(LayoutTransition.DISAPPEARING);
        defaultChangingAppearingAnim =
                transitioner.getAnimator(LayoutTransition.CHANGE_APPEARING);
        defaultChangingDisappearingAnim =
                transitioner.getAnimator(LayoutTransition.CHANGE_DISAPPEARING);
        currentAppearingAnim = defaultAppearingAnim;
        currentDisappearingAnim = defaultDisappearingAnim;
        currentChangingAppearingAnim = defaultChangingAppearingAnim;
        currentChangingDisappearingAnim = defaultChangingDisappearingAnim;

        ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
        parent.addView(container);
        parent.setClipChildren(false);
        Button addButton = (Button) findViewById(R.id.addNewButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button newButton = new Button(LayoutAnimations.this);
                newButton.setText(String.valueOf(numButtons++));
                newButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        container.removeView(v);
                    }
                });
                container.addView(newButton, Math.min(1, container.getChildCount()));
            }
        });
}*/
	}
}
