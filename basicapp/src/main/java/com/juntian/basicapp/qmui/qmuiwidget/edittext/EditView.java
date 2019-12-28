package com.juntian.basicapp.qmui.qmuiwidget.edittext;

import android.content.Context;
import android.content.res.TypedArray;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.juntian.basicapp.R;

/**
 * @作者: Liufan
 * @时间: 2017/5/15
 * @功能描述:
 */


public class EditView extends FrameLayout {
    public EditView(@NonNull Context context) {
        this(context, null);
    }

    public EditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public EditView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.view_edittext, this);
        initViews();
        setAttrs(context, attrs);
    }

    private void setAttrs(Context context, AttributeSet attrs) {
        TypedArray att = context.obtainStyledAttributes(attrs, R.styleable.EditView);
        final String hintText = att.getString(R.styleable.EditView_hintText);
        final String text = att.getString(R.styleable.EditView_text);
        final int maxLength = att.getInt(R.styleable.EditView_maxLength, -1);
        final boolean editable = att.getBoolean(R.styleable.EditView_editable, true);
        final int leftImg = att.getResourceId(R.styleable.EditView_leftImg, -1);
        final int rightImg = att.getResourceId(R.styleable.EditView_rightImg, -1);
        final String inputType = att.getString(R.styleable.EditView_inputType);
        final String rightAction = att.getString(R.styleable.EditView_rightAction);

        input.setLines(1);

        setTextHint(hintText)
                .setText(text)
                .setMaxLength(maxLength)
                .setEditable(editable)
                .setLeftHintIcon(leftImg)
                .setRightActionImage(rightImg);

        if (!TextUtils.isEmpty(inputType)) {
            input.setInputType(Integer.parseInt(inputType));
        }

        setRightActionInner(rightAction);
        att.recycle();
        att = null;
    }

    private void setRightActionInner(String rightAction) {
        if (TextUtils.equals("0", rightAction)) {
            handlePassword();
        } else if (TextUtils.equals("1", rightAction)) {
            handleClearAll();
        }
    }

    private void handleClearAll() {
        rightAction.setVisibility(View.GONE);
        input.addTextChangedListener(new ShowOrHideWatcher(input, rightAction));
        rightAction.setOnClickListener(new ClearAllAction(input));
    }

    static class ClearAllAction implements OnClickListener {
        private EditText editText;

        public ClearAllAction(EditText editView) {
            this.editText = editView;
        }

        @Override
        public void onClick(View v) {
            editText.setText("");
        }
    }

    static class ShowOrHideWatcher implements TextWatcher {

        private EditText editView;
        private ImageView icon;

        public ShowOrHideWatcher(EditText editView, ImageView icon) {
            this.editView = editView;
            this.icon = icon;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            final String content = s.toString().trim();
            icon.setVisibility(TextUtils.isEmpty(content) ? View.GONE : View.VISIBLE);
        }
    }

    private void handlePassword() {
        rightAction.setImageResource(R.mipmap.icon_eye_close);
        rightAction.setOnClickListener(new ShowOrHidePasswordAction(input, rightAction));

    }

    static class ShowOrHidePasswordAction implements OnClickListener {

        private final ImageView icon;
        private EditText input;
        private boolean isShowingPassword = false;

        public ShowOrHidePasswordAction(EditText input, ImageView icon) {
            this.input = input;
            this.icon = icon;
        }

        @Override
        public void onClick(View v) {
            if (isShowingPassword) {
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                icon.setImageResource(R.mipmap.icon_eye_close);
            } else {
                input.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                icon.setImageResource(R.mipmap.icon_eye_open);
            }
            isShowingPassword = !isShowingPassword;
            input.setSelection(input.getText().toString().length());
        }
    }

    public EditView setEditable(boolean editable) {
        input.setEnabled(editable);
        return this;
    }

    private ImageView leftHint;
    private EditText input;
    private ImageView rightAction;

    private void initViews() {
        this.leftHint = get(R.id.imgLeftHint);
        this.input = get(R.id.editInput);
        this.rightAction = get(R.id.imgRightAction);
    }


    private <T extends View> T get(@IdRes int res) {
        return (T) findViewById(res);
    }

    public EditView setMaxLength(int maxLength) {
        if (maxLength <= 0) return this;
        input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        return this;
    }

    public EditView setLeftHintIcon(@DrawableRes int res) {
        if (res == -1) {
            this.leftHint.setVisibility(View.GONE);
            return this;
        }
        this.leftHint.setVisibility(View.VISIBLE);
        this.leftHint.setImageResource(res);
        return this;
    }

    public EditView setTextHint(String hint) {
        if (TextUtils.isEmpty(hint)) return this;
        this.input.setHint(hint);
        return this;
    }

    public EditView setText(String text) {
        if (TextUtils.isEmpty(text)) return this;
        this.input.setText(text);
        return this;
    }

    public String getText() {
        return input.getText().toString().trim();
    }

    public EditText EditText() {
        return input;
    }

    public EditView setRightActionImage(@DrawableRes int res) {
        if (res == -1) {
            this.rightAction.setVisibility(View.GONE);
            return this;
        }
        this.rightAction.setVisibility(View.VISIBLE);
        this.rightAction.setImageResource(res);
        return this;
    }

    public EditView setRightAction(OnClickListener onClick) {
        if (onClick == null) return this;
        this.rightAction.setOnClickListener(onClick);
        return this;
    }


}
