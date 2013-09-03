package android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import fr.dvilleneuve.android.R;
import fr.dvilleneuve.android.TextDrawable;

public class EnhancedEditText extends EditText {

	private String prefixIcon;
	private String prefixText;
	private String suffixIcon;
	private String suffixText;
	private TextDrawable prefixTextDrawable;
	private IconDrawable prefixIconDrawable;
	private TextDrawable suffixTextDrawable;
	private IconDrawable suffixIconDrawable;

	public EnhancedEditText(Context context) {
		super(context);
		init();
	}

	public EnhancedEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(context, attrs);
		init();
	}

	public EnhancedEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(context, attrs);
		init();
	}

	private void initAttrs(Context context, AttributeSet attrs) {
		TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.EnhancedEditText);
		prefixIcon = attr.getString(R.styleable.EnhancedEditText_prefixIcon);
		prefixText = attr.getString(R.styleable.EnhancedEditText_prefixText);
		suffixIcon = attr.getString(R.styleable.EnhancedEditText_suffixIcon);
		suffixText = attr.getString(R.styleable.EnhancedEditText_SuffixText);
		attr.recycle();
	}

	void init() {
		setCompoundDrawablePadding(16);

		if (prefixIcon != null) {
			setPrefixIcon(Iconify.IconValue.valueOf(prefixIcon));
		}
		if (suffixIcon != null) {
			setSuffixIcon(Iconify.IconValue.valueOf(suffixIcon));
		}
		setPrefixText(prefixText);
		setSuffixText(suffixText);
	}

	@Override
	public void setTextSize(int unit, float size) {
		super.setTextSize(unit, size);
		updateTextSize();
	}

	@Override
	public void setTextColor(int color) {
		super.setTextColor(color);
		updateTextColor();
	}

	@Override
	public void setTextColor(ColorStateList colors) {
		super.setTextColor(colors);
		updateTextColor();
	}

	public void setPrefixIcon(Iconify.IconValue iconValue) {
		if (iconValue != null) {
			prefixIconDrawable = new IconDrawable(getContext(), iconValue).sizePx((int) getTextSize()).color(Color.GRAY);
			updateCompoundDrawables();
		}
	}

	public void setPrefixText(String unitTextPrefix) {
		if (unitTextPrefix != null && !unitTextPrefix.isEmpty()) {
			prefixTextDrawable = new TextDrawable(getContext(), unitTextPrefix).sizePx(getTextSize()).color(Color.GRAY).typeface(getTypeface());
			updateCompoundDrawables();
		}
	}

	public void setSuffixIcon(Iconify.IconValue iconValue) {
		if (iconValue != null) {
			suffixIconDrawable = new IconDrawable(getContext(), iconValue).sizePx((int) getTextSize()).color(Color.GRAY);
			updateCompoundDrawables();
		}
	}

	public void setSuffixText(String unitTextSuffix) {
		if (unitTextSuffix != null && !unitTextSuffix.isEmpty()) {
			suffixTextDrawable = new TextDrawable(getContext(), unitTextSuffix).sizePx(getTextSize()).color(Color.GRAY).typeface(getTypeface());
			updateCompoundDrawables();
		}
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
		int textColor = getCurrentTextColor();
		if (prefixIconDrawable != null) prefixIconDrawable.color(textColor);
		if (prefixTextDrawable != null) prefixTextDrawable.color(textColor);
		if (suffixIconDrawable != null) suffixIconDrawable.color(textColor);
		if (suffixTextDrawable != null) suffixTextDrawable.color(textColor);
	}

}
