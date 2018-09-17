package edu.sdsmt.carpenter.pong.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;

public class GameLogic {

    public static int GameState = 0;

    /** Some variables for holding game area constants **/
    private static float gameWidth = 1024.0f, gameHeight = 768.0f;
    public static float screenWidth = 0.0f, screenHeight = 0.0f;

    /** Some variables for entity information **/
    /** NOTE: Speed is 26 because 30 fps, 30*26 = ~gameHeight **/
    private static float ballX = gameWidth/2, ballY = gameHeight/2;
    private static int ballDirX = 1, ballDirY = 1, speed = 26;
    public static int ballColor = Color.WHITE;

    private static float paddleX = 780, paddleY = 740.0f;
    private static float paddleWidth = 275, paddleHeight = 25;
    public static int paddleColor = Color.WHITE;

    /** Motion Controlling Information **/
    private static boolean movingPaddle = false;
    private static int score = 0, highScore = 0;

    /** Some functions for converting from screen coordinates to game coordinates **/
    public static float toGameX(float w) {
        return (w/screenWidth)*gameWidth;
    }
    public static float toGameY(float h) {
        return (h/screenHeight)*gameHeight;
    }

    /** Some functions for converting from game coordinates to screen coordinates **/
    public static float toScreenX(float w) {
        return (w/gameWidth)* screenWidth;
    }
    public static float toScreenY(float h) {
        return (h/gameHeight)* screenHeight;
    }


    public static void updateBallLocation() {
        if(GameState != 1) { return; }
        ballX += ballDirX*speed;
        ballY += ballDirY*speed;
        if(ballX > gameWidth || ballX < 0) { ballDirX = ballDirX * -1; }
        if(ballY > gameHeight || ballY < 0) { ballDirY = ballDirY * -1; }

        if(ballY > (paddleY+paddleHeight/2)) {
            ballX = gameWidth/2;
            ballY = gameHeight/2;
            if(score > highScore)
                highScore = score;
            score = 0;
            GameState = 0;
            if(ballDirX > 2)
                ballDirX = 2;
            if(ballDirX < -2) {
                ballDirX = -2;
            }
        }
        if(ballY > (paddleY-(paddleHeight/2))) {
            float distFromMiddle = ballX - paddleX;
            float limit = paddleWidth/2;
            if(distFromMiddle > -limit && distFromMiddle < limit) {
                ballDirY *= -1;
                ballDirX += (int)(distFromMiddle/23);
                if(ballDirX > 6) { ballDirX = 6; }
                if(ballDirX < -6) { ballDirX = -6; }
                score += 1;
            }
        }
    }


    public static void touchEvent(MotionEvent e) {
        if(toGameX(e.getX()) > (paddleX-(paddleWidth/4)) && toGameX(e.getX()) < (paddleX+(paddleWidth/4))
                && toGameY(e.getY()) > (paddleY-(paddleHeight/2))) {
            if(e.getAction() == 0)
                movingPaddle = true;
            if(e.getAction() == 1) {
                movingPaddle = false;
            }
        }
        if(movingPaddle)
            paddleX = toGameX(e.getX());
    }

    public static void draw(Canvas c) {
        c.drawColor(Color.BLACK);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(ballColor);
        p.setStrokeWidth(4.0f);
        c.drawCircle(toScreenX(ballX), toScreenY(ballY), 30.0f, p);
        p.setColor(paddleColor);
        c.drawRect(toScreenX(paddleX-paddleWidth/2), toScreenY(paddleY-paddleHeight/2),
                toScreenX(paddleX+paddleWidth/2), toScreenY(paddleY+paddleHeight/2), p);

        p.setTypeface(Typeface.create("Arial", Typeface.BOLD));
        p.setTextAlign(Paint.Align.LEFT);
        p.setTextSize(50);
        p.setColor(Color.WHITE);
        c.drawText("Current Score: "+score, toScreenX(70), toScreenY(30), p);
        c.drawText("High Score: "+highScore, toScreenX(70), toScreenY(50), p);

        if(GameState == 0) {
            p.setTypeface(Typeface.create("Arial", Typeface.BOLD));
            p.setTextAlign(Paint.Align.CENTER);
            p.setTextSize(150);
            c.drawText("Tap to begin", toScreenX(gameWidth/2), toScreenY(gameWidth/4), p);
        }
    }
}
