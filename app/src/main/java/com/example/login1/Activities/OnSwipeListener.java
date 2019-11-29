package com.example.login1.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import static com.example.login1.Activities.ActivityVendedor.selectedProd;

public class OnSwipeListener implements View.OnTouchListener
{
    Context context;
    View v;
    int position;
    MotionEvent ev;
    OnSwipeListener(Context context,View v,int position) {
        this.context=context;
        this.v=v;
        this.position=position;
    }
    private final GestureDetector gestureDetector = new GestureDetector(new GestureListener(context,v,position));

    public boolean onTouch(final View v, final MotionEvent event)
    {
        ev=event;
        return gestureDetector.onTouchEvent(event);
    }


    private final class GestureListener extends GestureDetector.SimpleOnGestureListener
    {
        Context context;
        View v;
        int position;
        GestureListener(Context context,View v,int p){
            this.context=context;
            this.v=v;
            this.position=p;

        }

        private static final int SWIPE_THRESHOLD = 70;
        private static final int SWIPE_VELOCITY_THRESHOLD = 70;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            boolean result = false;
            try
            {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY))
                {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
                    {
                        if (diffX > 0)
                        {
                            result = onSwipeRight();
                        }
                        else
                        {
                            result = onSwipeLeft();
                        }
                    }
                }
                else
                {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD)
                    {
                        if (diffY > 0)
                        {
                            result = onSwipeBottom();
                        }
                        else
                        {
                            result = onSwipeTop();
                        }
                    }
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public boolean onSwipeRight()
    {
        //Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
         EditText text = v.findViewById(position);
         int cant=Integer.parseInt(text.getText().toString())+1;
         int stock = selectedProd.get(position).getStock();
         if (cant<=stock){
             text.setText(String.valueOf(cant));
             selectedProd.get(position).setPedidos(Integer.parseInt(text.getText().toString()));
             ActivityVendedor.txTotal.setText("Total: $"+String.valueOf(ActivityVendedor.calcularTotal(ActivityVendedor.productos, selectedProd)));
         }

        return false;
    }

    public boolean onSwipeLeft()
    {
        //Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
        EditText text = v.findViewById(position);
        int cant=Integer.parseInt(text.getText().toString());
        if (cant>1){
            cant=Integer.parseInt(text.getText().toString())-1;
            text.setText(String.valueOf(cant));
            selectedProd.get(position).setPedidos(Integer.parseInt(text.getText().toString()));
            ActivityVendedor.txTotal.setText("Total: $"+String.valueOf(ActivityVendedor.calcularTotal(ActivityVendedor.productos, selectedProd)));
        }

        return false;
    }

    public boolean onSwipeTop()
    {
        return false;
    }

    public boolean onSwipeBottom()
    {
        return false;
    }
}
