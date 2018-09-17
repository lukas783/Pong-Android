/*****
 * PongView.java
 *
 * The main view for the game, handles the view components and passes off to the logic for every actual event.
 *
 *
 *
 */
package edu.sdsmt.carpenter.pong.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static edu.sdsmt.carpenter.pong.Game.GameLogic.GameState;

public class PongView extends SurfaceView implements Runnable {

    private Context context;

    private boolean isRunning = false;
    private Thread gameThread;
    private SurfaceHolder holder;

    public PongView(Context c) {
        super(c);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                GameLogic.screenHeight = height;
                GameLogic.screenWidth = width;
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });
        context = c;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        GameState = 1;
        GameLogic.touchEvent(e);
        return true;
    }

    /** Handles resume**/
    public void resume() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        GameState = 0;
        isRunning = false;
        boolean retry = true;
        while(retry) {
            try {
                gameThread.join();
                retry = false;
            } catch(InterruptedException e) {
            }
        }
    }

    /**
     * The main game loop, follows the Get input, Update positions, clear screen, and draw pattern
    **/
    @Override
    public void run() {
        // loop the thread while we are running
        while(isRunning) {
            //validate our surface
            if(!holder.getSurface().isValid()) {
                continue;
            }

            // record time
            long started = System.currentTimeMillis();

            // update
                GameLogic.updateBallLocation();
            //draw
            Canvas canvas = holder.lockCanvas();
            if(canvas != null) {
                GameLogic.draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            // calculate any needed sleep time
            float deltaTime = System.currentTimeMillis() - started;
            int sleepTime = (int) (33.33-deltaTime);
            // handle sleeping
            if(sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch(InterruptedException e) {

                }
            // handle skipping frames
            } else {
                while(sleepTime < 0) {
                    GameLogic.updateBallLocation();
                    sleepTime += 33.33;
                }
            }
        }
    }
}
