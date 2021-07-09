package com.example.toasterlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

public class Gifview {
    public static class GifView extends View {
        private InputStream gifInputStream;

        private Movie gifMovie;
        private long movieDuration;
        public static String raawname;

        private int movieHeight;
        private long movieStart;
        private int movieWidth;



        public static void setraw(String rawnamex) {
            raawname=rawnamex;
        }



        public GifView(Context paramContext) {
            super(paramContext);
            init(paramContext);
        }

        public GifView(Context paramContext, AttributeSet paramAttributeSet) {
            super(paramContext, paramAttributeSet);
            init(paramContext);
        }

        public GifView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
            super(paramContext, paramAttributeSet, paramInt);
            init(paramContext);
        }

        private void init(Context paramContext) {
            setFocusable(true);
            int resId = paramContext.getResources().getIdentifier(""+raawname+"", null, paramContext.getPackageName());

            InputStream inputStream = paramContext.getResources().openRawResource(resId);
            this.gifInputStream = inputStream;
            Movie movie = Movie.decodeStream(inputStream);
            this.gifMovie = movie;
            this.movieWidth = movie.width();
            this.movieHeight = this.gifMovie.height();
            this.movieDuration = this.gifMovie.duration();
        }

        public long getMovieDuration() {
            return this.movieDuration;
        }

        public int getMovieHeight() {
            return this.movieHeight;
        }

        public int getMovieWidth() {
            return this.movieWidth;
        }

        protected void onDraw(Canvas paramCanvas) {
            long l = SystemClock.uptimeMillis();
            if (this.movieStart == 0L)
                this.movieStart = l;
            Movie movie = this.gifMovie;
            if (movie != null) {
                int j = movie.duration();
                int i = j;
                if (j == 0)
                    i = 1000;
                i = (int) ((l - this.movieStart) % i);
                this.gifMovie.setTime(i);
                this.gifMovie.draw(paramCanvas, 1.0F, 1.0F);
                invalidate();
            }
        }

        protected void onMeasure(int paramInt1, int paramInt2) {
            setMeasuredDimension(this.movieWidth, this.movieHeight);
        }
    }


}
