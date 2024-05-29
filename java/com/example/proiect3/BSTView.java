package com.example.proiect3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BSTView extends View {
    private BST bst;
    private Paint paint;
    private Paint textPaint;

    public BSTView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setBST(BST bst) {
        this.bst = bst;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bst != null && bst.root != null) {
            drawTree(canvas, bst.root, getWidth() / 2, 100, getWidth() / 4);
        }
    }

    private void drawTree(Canvas canvas, BST.Node node, float x, float y, float xOffset) {
        if (node != null) {
            if (node.left != null) {
                canvas.drawLine(x, y, x - xOffset, y + 100, paint);
                drawTree(canvas, node.left, x - xOffset, y + 100, xOffset / 2);
            }
            if (node.right != null) {
                canvas.drawLine(x, y, x + xOffset, y + 100, paint);
                drawTree(canvas, node.right, x + xOffset, y + 100, xOffset / 2);
            }
            canvas.drawCircle(x, y, 30, paint);
            canvas.drawText(String.valueOf(node.key), x, y + 15, textPaint);
        }
    }
}
