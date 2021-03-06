package com.crania.side.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.crania.side.R;

/**
 * @ProjectName: SIDE
 * @Package: com.crania.side.widget
 * @ClassName: VerificationCodeInputView
 * @Description: 自定义验证码输入框
 * @Author: 鹿鸿祥
 * @CreateDate: 2022/2/8 14:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/2/8 14:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class VerificationCodeInputView extends ViewGroup {
    private final static String TYPE_NUMBER = "number";
    private final static String TYPE_TEXT = "text";
    private final static String TYPE_PASSWORD = "password";
    private final static String TYPE_PHONE = "phone";
    private static final String TAG = "VerificationCodeInput";
    private int box = 4;
    private int boxWidth = 120;
    private int boxHeight = 120;
    private int childHPadding = 14;
    private int childVPadding = 14;
    private String inputType = TYPE_PASSWORD;
    private Drawable boxBgFocus = null;
    private Drawable boxBgNormal = null;
    private Listener listener;

    public VerificationCodeInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VerificationCodeInputView);
        box = a.getInt(R.styleable.VerificationCodeInputView_box, 4);
        childHPadding = (int) a.getDimension(R.styleable.VerificationCodeInputView_child_h_padding, 0);
        childVPadding = (int) a.getDimension(R.styleable.VerificationCodeInputView_child_v_padding, 0);
        boxBgFocus =  a.getDrawable(R.styleable.VerificationCodeInputView_box_bg_focus);
        boxBgNormal = a.getDrawable(R.styleable.VerificationCodeInputView_box_bg_normal);
        inputType = a.getString(R.styleable.VerificationCodeInputView_inputType);
        boxWidth = (int) a.getDimension(R.styleable.VerificationCodeInputView_child_width, boxWidth);
        boxHeight = (int) a.getDimension(R.styleable.VerificationCodeInputView_child_height, boxHeight);
        initViews();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @SuppressLint("NewApi")
    private void initViews() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    focus();
                    checkAndCommit();
                }
            }
        };

        View.OnKeyListener onKeyListener = new View.OnKeyListener() {
            @Override
            public synchronized boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    backFocus();
                }
                return false;
            }
        };

        for (int i = 0; i < box; i++) {
            EditText editText = new EditText(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(boxWidth, boxHeight);
            layoutParams.bottomMargin = childVPadding;
            layoutParams.topMargin = childVPadding;
            layoutParams.leftMargin = childHPadding;
            layoutParams.rightMargin = childHPadding;
            layoutParams.gravity = Gravity.CENTER;

            editText.setOnKeyListener(onKeyListener);
            setBg(editText, false);
            editText.setTextColor(Color.BLACK);
            editText.setLayoutParams(layoutParams);
            editText.setGravity(Gravity.CENTER);
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});

//            if (TYPE_NUMBER.equals(inputType)) {
//                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//            } else if (TYPE_PASSWORD.equals(inputType)){
//                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
//            } else if (TYPE_TEXT.equals(inputType)){
//                editText.setInputType(InputType.TYPE_CLASS_TEXT);
//            } else if (TYPE_PHONE.equals(inputType)){
//                editText.setInputType(InputType.TYPE_CLASS_PHONE);
//            }
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setId(i);
            editText.setEms(1);
            editText.addTextChangedListener(textWatcher);
            addView(editText,i);

            //设置光标颜色
            editText.setTextCursorDrawable(R.drawable.cursor_color);
            //设置输入框背景
            editText.setBackgroundResource(R.drawable.shape_gray_half_2);
            /**EditText的焦点监听事件**/
            editText.setOnFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    editText.setBackgroundResource(R.drawable.shape_black_half_2);
                } else {
                    editText.setBackgroundResource(R.drawable.shape_gray_half_2);
                }
            });
        }
    }

    private void backFocus() {
        int count = getChildCount();
        EditText editText ;
        for (int i = count-1; i>= 0; i--) {
            editText = (EditText) getChildAt(i);
            if (editText.getText().length() == 1) {
                editText.requestFocus();
                editText.setSelection(1);
                return;
            }
        }
    }

    private void focus() {
        int count = getChildCount();
        EditText editText ;
        for (int i = 0; i< count; i++) {
            editText = (EditText) getChildAt(i);
            if (editText.getText().length() < 1) {
                editText.requestFocus();
                return;
            }
        }
    }

    private void setBg(EditText editText, boolean focus) {
        if (boxBgNormal != null && !focus) {
            editText.setBackground(boxBgNormal);
        } else if (boxBgFocus != null && focus) {
            editText.setBackground(boxBgFocus);
        }
    }

    /**
     * 设置密码错误提示
     *
     */
    public void setBackgroundError() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.setBackgroundResource(R.drawable.shape_red_half_2);
        }
    }

    private void checkAndCommit() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean full = true;
        for (int i = 0 ;i < box; i++){
            EditText editText = (EditText) getChildAt(i);
            String content = editText.getText().toString();
            if ( content.length() == 0) {
                full = false;
                break;
            } else {
                stringBuilder.append(content);
            }
        }
        Log.d(TAG, "checkAndCommit:" + stringBuilder.toString());
        if (full){

            if (listener != null) {
                listener.onComplete(stringBuilder.toString());
//                setEnabled(false);
            }
        }
    }

    /**
     * 设置按钮是否可点击
     *
     * @param enabled
     */
    @Override
    public void setEnabled(boolean enabled) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.setEnabled(enabled);
        }
    }

    public void setOnCompleteListener(Listener listener){
        this.listener = listener;
    }

    @Override

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LinearLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int parentWidth = getMeasuredWidth();
        if (parentWidth == LayoutParams.MATCH_PARENT) {
            parentWidth = getScreenWidth();
        }
        Log.d(getClass().getName(), "onMeasure width " + parentWidth);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
        if (count > 0) {
            View child = getChildAt(0);
            int cWidth = child.getMeasuredWidth();
            if (parentWidth != LayoutParams.WRAP_CONTENT) {
                // 重新计算padding
                childHPadding = (parentWidth - cWidth * count) / (count + 1);
            }

            int cHeight = child.getMeasuredHeight();

            int maxH = cHeight + 2 * childVPadding;
            int maxW = (cWidth) * count + childHPadding * (count + 1);
            setMeasuredDimension(resolveSize(maxW, widthMeasureSpec),
                    resolveSize(maxH, heightMeasureSpec));
        }
    }

    private int getScreenWidth() {
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(getClass().getName(), "onLayout width = " +  getMeasuredWidth());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            child.setVisibility(View.VISIBLE);
            int cWidth = child.getMeasuredWidth();
            int cHeight = child.getMeasuredHeight();
            int cl = childHPadding + (i) * (cWidth + childHPadding);
            int cr = cl + cWidth;
            int ct = childVPadding;
            int cb = ct + cHeight;
            child.layout(cl, ct, cr, cb);
        }
    }

    public interface Listener {
        void onComplete(String content);
    }
}
