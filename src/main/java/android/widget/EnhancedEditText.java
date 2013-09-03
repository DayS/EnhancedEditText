package android.widget;

import android.content.Context;
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

	public void setPrefixIcon(Iconify.IconValue iconValue) {
		if (iconValue != null) {
			prefixIconDrawable = new IconDrawable(getContext(), iconValue).sizePx((int) getTextSize()).color(Color.GRAY);
			updateDrawables();
		}
	}

	public void setPrefixText(String unitTextPrefix) {
		if (unitTextPrefix != null) {
			prefixTextDrawable = new TextDrawable(getContext(), unitTextPrefix).sizePx(getTextSize()).color(Color.GRAY).typeface(getTypeface());
			updateDrawables();
		}
	}

	public void setSuffixIcon(Iconify.IconValue iconValue) {
		if (iconValue != null) {
			suffixIconDrawable = new IconDrawable(getContext(), iconValue).sizePx((int) getTextSize()).color(Color.GRAY);
			updateDrawables();
		}
	}

	public void setSuffixText(String unitTextSuffix) {
		if (unitTextSuffix != null) {
			suffixTextDrawable = new TextDrawable(getContext(), unitTextSuffix).sizePx(getTextSize()).color(Color.GRAY).typeface(getTypeface());
			updateDrawables();
		}
	}

	private void updateDrawables() {
		Drawable leftDrawable = prefixIconDrawable != null ? prefixIconDrawable : prefixTextDrawable;
		setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, suffixTextDrawable, null);
	}

}
