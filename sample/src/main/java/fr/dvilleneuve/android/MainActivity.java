package fr.dvilleneuve.android;

import android.app.Activity;
import android.widget.EnhancedEditText;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.joanzapata.android.iconify.Iconify;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@ViewById
	EnhancedEditText edittext2, edittext3;

	@AfterViews
	void init() {
		edittext2.setTextColor(R.color.text_color);
		edittext3.setSuffixIcon(Iconify.IconValue.icon_circle_arrow_right);
	}

}