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
package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import fr.dvilleneuve.android.DrawablePosition;
import fr.dvilleneuve.android.OnClickDrawableListener;
import fr.dvilleneuve.android.R;
import fr.dvilleneuve.android.TextDrawable;

public class EnhancedEditText extends EditText implements View.OnTouchListener {

	public static final int DRAWABLE_CLICK_PADDING = 10;
	private String prefixIcon;
	private String prefixText;
	private ColorStateList prefixColors;
	private String suffixIcon;
	private String suffixText;
	private ColorStateList suffixColors;
	private TextDrawable prefixTextDrawable;
	private IconDrawable prefixIconDrawable;
	private TextDrawable suffixTextDrawable;
	private IconDrawable suffixIconDrawable;
	private OnClickDrawableListener onClickDrawableListener;

	public EnhancedEditText(Context context) {
		super(context);
		init();
	}

	public EnhancedEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(context, attrs, R.style.enhancedEditText);
		init();
	}

	public EnhancedEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(context, attrs, defStyle);
		init();
	}

	private void initAttrs(Context context, AttributeSet attrs, int defStyle) {
		TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.EnhancedEditText, defStyle, 0);
		prefixIcon = attr.getString(R.styleable.EnhancedEditText_prefixIcon);
		prefixText = attr.getString(R.styleable.EnhancedEditText_prefixText);
		if (attr.hasValue(R.styleable.EnhancedEditText_prefixColor)) {
			prefixColors = attr.getColorStateList(R.styleable.EnhancedEditText_prefixColor);
		} else {
			prefixColors = getTextColors();
		}

		suffixIcon = attr.getString(R.styleable.EnhancedEditText_suffixIcon);
		suffixText = attr.getString(R.styleable.EnhancedEditText_suffixText);
		if (attr.hasValue(R.styleable.EnhancedEditText_suffixColor)) {
			suffixColors = attr.getColorStateList(R.styleable.EnhancedEditText_suffixColor);
		} else {
			suffixColors = getTextColors();
		}
		attr.recycle();
	}

	private void init() {
		setCompoundDrawablePadding(16);

		if (!isInEditMode()) {
			if (prefixIcon != null) {
				setPrefixIcon(Iconify.IconValue.valueOf(prefixIcon));
			}
			if (suffixIcon != null) {
				setSuffixIcon(Iconify.IconValue.valueOf(suffixIcon));
			}
		}
		setPrefixText(prefixText);
		setSuffixText(suffixText);

		updateTextColor();
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
			if (onClickDrawableListener != null) {
				Drawable prefixDrawable = getPrefixDrawable();
				if (touchesDrawable(motionEvent, prefixDrawable, DrawablePosition.PREFIX)) {
					onClickDrawableListener.onClickDrawable(prefixDrawable, DrawablePosition.PREFIX);
					return true;
				}
				Drawable suffixDrawable = getSuffixDrawable();
				if (touchesDrawable(motionEvent, suffixDrawable, DrawablePosition.SUFFIX)) {
					onClickDrawableListener.onClickDrawable(suffixDrawable, DrawablePosition.SUFFIX);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void setTextSize(int unit, float size) {
		super.setTextSize(unit, size);
		updateTextSize();
	}

	@Override
	public void drawableStateChanged() {
		super.drawableStateChanged();
		updateTextColor();
	}

	public Drawable getPrefixDrawable() {
		return prefixTextDrawable != null ? prefixTextDrawable : prefixIconDrawable;
	}

	public void setPrefixIcon(Iconify.IconValue prefixIcon) {
		prefixIconDrawable = getIconDrawable(prefixIcon, prefixColors);
		updateCompoundDrawables();
	}

	public void setPrefixText(String prefix) {
		prefixTextDrawable = getTextDrawable(prefix, prefixColors);
		updateCompoundDrawables();
	}

	public void setPrefixTextRes(int prefix) {
		setPrefixText(getContext().getString(prefix));
	}

	public void setPrefixColors(ColorStateList prefixColors) {
		this.prefixColors = prefixColors;
		updateTextColor();
	}

	public void setPrefixColors(int prefixColorsRes) {
		setPrefixColors(getContext().getResources().getColorStateList(prefixColorsRes));
	}

	public void setPrefixColor(int prefixColor) {
		setPrefixColors(ColorStateList.valueOf(prefixColor));
	}

	public void setPrefixColorRes(int prefixColorRes) {
		setPrefixColor(getContext().getResources().getColor(prefixColorRes));
	}

	public Drawable getSuffixDrawable() {
		return suffixTextDrawable != null ? suffixTextDrawable : suffixIconDrawable;
	}

	public void setSuffixIcon(Iconify.IconValue suffixIcon) {
		suffixIconDrawable = getIconDrawable(suffixIcon, suffixColors);
		updateCompoundDrawables();
	}

	public void setSuffixText(String suffix) {
		suffixTextDrawable = getTextDrawable(suffix, suffixColors);
		updateCompoundDrawables();
	}

	public void setSuffixTextRes(int suffixRes) {
		setSuffixText(getContext().getString(suffixRes));
	}

	public void setSuffixColors(ColorStateList suffixColors) {
		this.suffixColors = suffixColors;
		updateTextColor();
	}

	public void setSuffixColors(int suffixColorsRes) {
		setSuffixColors(getContext().getResources().getColorStateList(suffixColorsRes));
	}

	public void setSuffixColor(int suffixColor) {
		setSuffixColors(ColorStateList.valueOf(suffixColor));
	}

	public void setSuffixColorRes(int suffixColorRes) {
		setSuffixColor(getContext().getResources().getColor(suffixColorRes));
	}

	public void setOnClickDrawableListener(OnClickDrawableListener onClickDrawableListener) {
		this.onClickDrawableListener = onClickDrawableListener;

		if (onClickDrawableListener != null) {
			setOnTouchListener(this);
		} else {
			setOnTouchListener(null);
		}
	}

	private IconDrawable getIconDrawable(Iconify.IconValue iconValue, ColorStateList colors) {
		if (isInEditMode()) return null;
		if (iconValue == null) return null;

		return new IconDrawable(getContext(), iconValue) //
				.sizePx((int) getTextSize()) //
				.color(getCurrentDrawablColor(colors));
	}

	private TextDrawable getTextDrawable(String value, ColorStateList colors) {
		if (value == null || value.isEmpty()) return null;

		return new TextDrawable(getContext(), value) //
				.sizePx(getTextSize()).typeface(getTypeface()) //
				.color(getCurrentDrawablColor(colors));
	}

	private int getCurrentDrawablColor(ColorStateList colors) {
		return colors.getColorForState(getDrawableState(), getCurrentTextColor());
	}

	private void updateCompoundDrawables() {
		Drawable leftDrawable = prefixIconDrawable != null ? prefixIconDrawable : prefixTextDrawable;
		Drawable rightDrawable = suffixIconDrawable != null ? suffixIconDrawable : suffixTextDrawable;
		setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, rightDrawable, null);
	}

	private void updateTextSize() {
		float textSize = getTextSize();
		if (prefixIconDrawable != null) prefixIconDrawable.sizePx((int) textSize);
		if (prefixTextDrawable != null) prefixTextDrawable.sizePx((int) textSize);
		if (suffixIconDrawable != null) suffixIconDrawable.sizePx((int) textSize);
		if (suffixTextDrawable != null) suffixTextDrawable.sizePx((int) textSize);
	}

	private void updateTextColor() {
		int prefixColor = getCurrentDrawablColor(prefixColors);
		if (prefixIconDrawable != null) prefixIconDrawable.color(prefixColor);
		if (prefixTextDrawable != null) prefixTextDrawable.color(prefixColor);

		int suffixColor = getCurrentDrawablColor(suffixColors);
		if (suffixIconDrawable != null) suffixIconDrawable.color(suffixColor);
		if (suffixTextDrawable != null) suffixTextDrawable.color(suffixColor);
	}

	private boolean touchesDrawable(MotionEvent motionEvent, Drawable drawable, DrawablePosition drawablePosition) {
		if (drawable != null) {
			final int x = (int) motionEvent.getX();
			final int y = (int) motionEvent.getY();
			final Rect bounds = drawable.getBounds();

			if (y >= (getHeight() - bounds.height()) / 2 - DRAWABLE_CLICK_PADDING && y <= (getHeight() + bounds.height()) / 2 + DRAWABLE_CLICK_PADDING) {
				if (drawablePosition == DrawablePosition.PREFIX && x <= bounds.width() + DRAWABLE_CLICK_PADDING) {
					return true;
				} else if (drawablePosition == DrawablePosition.SUFFIX && x >= getWidth() - bounds.width() - DRAWABLE_CLICK_PADDING) {
					return true;
				}
			}
		}
		return false;
	}
}
