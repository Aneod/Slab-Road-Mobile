package com.example.veritablejeu.Tools.LayoutParams;

public class ConstraintParams {

    private final int width;
    private final int height;
    private final int leftMargin;
    private final int topMargin;

    public ConstraintParams(int width, int height, int leftMargin, int topMargin) {
        this.width = width;
        this.height = height;
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public int getTopMargin() {
        return topMargin;
    }
}
