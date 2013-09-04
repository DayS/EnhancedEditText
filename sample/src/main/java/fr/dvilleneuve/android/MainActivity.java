package fr.dvilleneuve.android;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.widget.EnhancedEditText;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.joanzapata.android.iconify.Iconify;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@ViewById
	EnhancedEditText edittext1, edittext2, edittext3;

	@AfterViews
	void init() {
		edittext1.setPrefixColor(Color.LTGRAY);
		edittext3.setPrefixColors(R.drawable.text_color);
		edittext3.setSuffixColorRes(R.color.text_color_red);
	}

	@Click
	void buttonToggleDrawablesClicked() {
		if (edittext3.getPrefixDrawable() == null)
			edittext3.setPrefixTextRes(R.string.activity_main_edittext3_prefix);
		else
			edittext3.setPrefixText(null);

		if (edittext3.getSuffixDrawable() == null)
			edittext3.setSuffixIcon(Iconify.IconValue.icon_circle_arrow_right);
		else
			edittext3.setSuffixIcon(null);
	}

}