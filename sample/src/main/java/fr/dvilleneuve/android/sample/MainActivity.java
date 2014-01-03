/**
 * Copyright 2013 Damien Villeneuve
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * It uses Iconify by Joan Zapata, licensed under Apache License version 2, which is compatible
 * with this library's license.
 */
package fr.dvilleneuve.android.sample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.EnhancedEditText;
import android.widget.Toast;
import com.joanzapata.android.iconify.Iconify;
import fr.dvilleneuve.android.DrawablePosition;
import fr.dvilleneuve.android.OnClickDrawableListener;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements OnClickDrawableListener {

	@ViewById
	EnhancedEditText edittext1, edittext2, edittext3;

	@AfterViews
	void init() {
		edittext1.setPrefixColor(Color.LTGRAY);
		edittext3.setPrefixColors(R.drawable.text_color);
		edittext3.setSuffixColorRes(R.color.text_color_red);

		edittext1.setOnClickDrawableListener(this);
		edittext2.setOnClickDrawableListener(this);
		edittext3.setOnClickDrawableListener(this);
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

	@Click
	void buttonToggleEnableClicked() {
		edittext1.setEnabled(!edittext1.isEnabled());
		edittext2.setEnabled(!edittext2.isEnabled());
		edittext3.setEnabled(!edittext3.isEnabled());
	}

	@Override
	public void onClickDrawable(Drawable drawable, DrawablePosition position) {
		switch (position) {
		case PREFIX:
			Toast.makeText(this, "Clicked on prefix drawable", Toast.LENGTH_SHORT).show();
			break;
		case SUFFIX:
			Toast.makeText(this, "Clicked on suffix drawable", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}