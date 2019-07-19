// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.arexperiments.justaline.view;

import android.animation.Animator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.arexperiments.justaline.AppSettings;
import com.arexperiments.justaline.R;

import javax.vecmath.Vector3f;

/**
 * Created by msk on 07/17/19.
 * Custom view for selecting color
 */

public class ColorSelector extends ConstraintLayout implements View.OnClickListener {

    private static final String TAG = "ColorSelector";

    private static final int WHITE = 0;

    private static final int RED = 1;

    private static final int GREEN = 2;

    private static final int BLUE = 3;

    private static final int YELLOW = 4;

    private static final int PURPLE = 5;


   /* private static final Pair<Integer, AppSettings.LineWidth> defaultBrush = new Pair<>(MEDIUM_BRUSH,
            AppSettings.LineWidth.MEDIUM);*/

    private View mColorButton;

    private View mWhiteButton, mRedButton, mGreenButton, mBlueButton, mYellowButton, mPurpleButton;

    private View mSelectedColorIndicator;

    private int mSelectedColor = WHITE;
    Vector3f mSelectedColorValue = AppSettings.WHITE;


    private boolean mIsOpen = true;

    //the locations of the buttons
    private int mWhiteButtonLoc[] = new int[2];

    private int mRedButtonLoc[] = new int[2];

    private int mGreenButtonLoc[] = new int[2];

    private int mBlueButtonLoc[] = new int[2];

    private int mYellowButtonLoc[] = new int[2];

    private int mPurpleButtonLoc[] = new int[2];

    public ColorSelector(Context context) {
        super(context);
        init();
    }

    public ColorSelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_color_selector, this);

        mColorButton = findViewById(R.id.color_button);
        mColorButton.setOnClickListener(this);

        mSelectedColorIndicator = findViewById(R.id.selected_size_indicator1);

        mWhiteButton = findViewById(R.id.color_selection_white);
        mRedButton = findViewById(R.id.color_selection_red);
        mGreenButton = findViewById(R.id.color_selection_green);
        mBlueButton = findViewById(R.id.color_selection_blue);
        mYellowButton = findViewById(R.id.color_selection_yellow);
        mPurpleButton = findViewById(R.id.color_selection_purple);

        mWhiteButton.setOnClickListener(this);
        mRedButton.setOnClickListener(this);
        mGreenButton.setOnClickListener(this);
        mBlueButton.setOnClickListener(this);
        mYellowButton.setOnClickListener(this);
        mPurpleButton.setOnClickListener(this);

        mColorButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    performClick();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE && mIsOpen) {
                    //get the point where we let go
                    float yloc = event.getRawY();
                    int colorIndex;
                    Vector3f colorValue;

                    //determine which button was released over
                    if (mWhiteButtonLoc[1] < yloc && yloc < (mWhiteButtonLoc[1] + mWhiteButton
                            .getHeight())) {
                        //prevent calling an update when not needed
                        if (mSelectedColor != WHITE) {
                            colorValue = AppSettings.WHITE;
                            colorIndex = WHITE;
                            onColorSelected(colorValue, colorIndex);
                        }
                    } else if (mRedButtonLoc[1] < yloc && yloc < (mRedButtonLoc[1]
                            + mRedButton.getHeight())) {
                        if (mSelectedColor != RED) {
                            colorValue = AppSettings.RED;
                            colorIndex = RED;
                            onColorSelected(colorValue, colorIndex);
                        }
                    } else if (mGreenButtonLoc[1] < yloc && yloc < (mGreenButtonLoc[1]
                            + mGreenButton.getHeight())) {
                        if (mSelectedColor != GREEN) {
                            colorValue = AppSettings.GREEN;
                            colorIndex = GREEN;
                            onColorSelected(colorValue, colorIndex);
                        }
                    } else if (mBlueButtonLoc[1] < yloc && yloc < (mBlueButtonLoc[1]
                            + mBlueButton.getHeight())) {
                        if (mSelectedColor != BLUE) {
                            colorValue = AppSettings.BLUE;
                            colorIndex = BLUE;
                            onColorSelected(colorValue, colorIndex);
                        }
                    } else if (mYellowButtonLoc[1] < yloc && yloc < (mYellowButtonLoc[1]
                            + mYellowButton.getHeight())) {
                        if (mSelectedColor != YELLOW) {
                            colorValue = AppSettings.YELLOW;
                            colorIndex = YELLOW;
                            onColorSelected(colorValue, colorIndex);
                        }
                    } else if (mPurpleButtonLoc[1] < yloc && yloc < (mPurpleButtonLoc[1]
                            + mPurpleButton.getHeight())) {
                        if (mSelectedColor != PURPLE) {
                            colorValue = AppSettings.PURPLE;
                            colorIndex = PURPLE;
                            onColorSelected(colorValue, colorIndex);
                        }
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //toggle if over a button
                    float yloc = event.getRawY();
                    if (mWhiteButtonLoc[1] < yloc && yloc < (mWhiteButtonLoc[1] + mWhiteButton
                            .getHeight())) {
                        toggleColorSelectorVisibility();
                    } else if (mRedButtonLoc[1] < yloc && yloc < (mRedButtonLoc[1] + mRedButton
                            .getHeight())) {
                        toggleColorSelectorVisibility();
                    } else if (mGreenButtonLoc[1] < yloc && yloc < (mGreenButtonLoc[1] + mGreenButton
                            .getHeight())) {
                        toggleColorSelectorVisibility();
                    } else if (mBlueButtonLoc[1] < yloc && yloc < (mBlueButtonLoc[1] + mBlueButton
                            .getHeight())) {
                        toggleColorSelectorVisibility();
                    } else if (mYellowButtonLoc[1] < yloc && yloc < (mYellowButtonLoc[1] + mYellowButton
                            .getHeight())) {
                        toggleColorSelectorVisibility();
                    } else if (mPurpleButtonLoc[1] < yloc && yloc < (mPurpleButtonLoc[1] + mPurpleButton
                            .getHeight())) {
                        toggleColorSelectorVisibility();
                    }
                }
                return true;
            }
        });

        this.post(new Runnable() {
            @Override
            public void run() {
                //the navigation bar is visible here at this point and throws off my location capture....
                //have to get the height to fix this
                mWhiteButton.getLocationInWindow(mWhiteButtonLoc);
                mRedButton.getLocationInWindow(mRedButtonLoc);
                mGreenButton.getLocationInWindow(mGreenButtonLoc);
                mBlueButton.getLocationInWindow(mBlueButtonLoc);
                mYellowButton.getLocationInWindow(mYellowButtonLoc);
                mPurpleButton.getLocationInWindow(mPurpleButtonLoc);

            }
        });

        onColorSelected(AppSettings.WHITE, WHITE);
        toggleColorSelectorVisibility();
    }

    @Override
    public void onClick(View view) {

        Vector3f colorValue = null;
        int colorIndex = 0;

        switch (view.getId()) {
            case R.id.color_button:
                toggleColorSelectorVisibility();
                return;
            case R.id.color_selection_white:
                colorValue = AppSettings.WHITE;
                colorIndex = WHITE;
                break;
            case R.id.color_selection_red:
                colorValue = AppSettings.RED;
                colorIndex = RED;
                break;
            case R.id.color_selection_green:
                colorValue = AppSettings.GREEN;
                colorIndex = GREEN;
                break;
            case R.id.color_selection_blue:
                colorValue = AppSettings.BLUE;
                colorIndex = BLUE;
                break;
            case R.id.color_selection_yellow:
                colorValue = AppSettings.YELLOW;
                colorIndex = YELLOW;
                break;
            case R.id.color_selection_purple:
                colorValue = AppSettings.PURPLE;
                colorIndex = PURPLE;
                break;


        }

        onColorSelected(colorValue, colorIndex);

        toggleColorSelectorVisibility();
    }

    @Override
    public boolean performClick() {
        toggleColorSelectorVisibility();
        return super.performClick();
    }

    private void onColorSelected(Vector3f colorValue, int colorIndex) {

        TypedValue outValue = new TypedValue();

        getResources().getValue(R.dimen.brush_scale_small, outValue, true);
        mSelectedColor = colorIndex;
        mSelectedColorValue = colorValue;

        AppSettings.color = mSelectedColorValue;

        float scale = outValue.getFloat();

        mSelectedColorIndicator.animate().scaleX(scale).scaleY(scale);
    }

    private void toggleColorSelectorVisibility() {
        if (mIsOpen) {
            float y = mSelectedColorIndicator.getY();
            Animator.AnimatorListener hideListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mWhiteButton.setVisibility(GONE);
                    mRedButton.setVisibility(GONE);
                    mGreenButton.setVisibility(GONE);
                    mBlueButton.setVisibility(GONE);
                    mYellowButton.setVisibility(GONE);
                    mPurpleButton.setVisibility(GONE);

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };
            mWhiteButton.animate().alpha(0).setListener(hideListener).translationY(y);
            mRedButton.animate().alpha(0).translationY(y);
            mGreenButton.animate().alpha(0).translationY(y);
            mBlueButton.animate().alpha(0).translationY(y);
            mYellowButton.animate().alpha(0).translationY(y);
            mPurpleButton.animate().alpha(0).translationY(y);

            mWhiteButton.setEnabled(false);
            mRedButton.setEnabled(false);
            mGreenButton.setEnabled(false);
            mBlueButton.setEnabled(false);
            mYellowButton.setEnabled(false);
            mPurpleButton.setEnabled(false);

            mColorButton.setAccessibilityTraversalBefore(R.id.record_button);
        } else {
            Animator.AnimatorListener showListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mWhiteButton.setVisibility(VISIBLE);
                    mRedButton.setVisibility(VISIBLE);
                    mGreenButton.setVisibility(VISIBLE);
                    mBlueButton.setVisibility(VISIBLE);
                    mYellowButton.setVisibility(VISIBLE);
                    mPurpleButton.setVisibility(VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };
            mWhiteButton.animate().alpha(1).setListener(showListener).translationY(0);
            mRedButton.animate().alpha(1).translationY(0);
            mGreenButton.animate().alpha(1).translationY(0);
            mBlueButton.animate().alpha(1).translationY(0);
            mYellowButton.animate().alpha(1).translationY(0);
            mPurpleButton.animate().alpha(1).translationY(0);

            mWhiteButton.setEnabled(true);
            mRedButton.setEnabled(true);
            mGreenButton.setEnabled(true);
            mBlueButton.setEnabled(true);
            mYellowButton.setEnabled(true);
            mPurpleButton.setEnabled(true);

            mColorButton.setAccessibilityTraversalBefore(R.id.color_selection_white);
            mWhiteButton.setAccessibilityTraversalBefore(R.id.color_selection_red);
            mRedButton.setAccessibilityTraversalBefore(R.id.color_selection_green);
            mGreenButton.setAccessibilityTraversalBefore(R.id.color_selection_blue);
            mYellowButton.setAccessibilityTraversalBefore(R.id.color_selection_yellow);
            mPurpleButton.setAccessibilityTraversalBefore(R.id.color_selection_purple);

        }
        mIsOpen = !mIsOpen;
    }


    public boolean isOpen() {
        return mIsOpen;
    }

    public void close() {
        if (mIsOpen) {
            toggleColorSelectorVisibility();
        }
    }

}
