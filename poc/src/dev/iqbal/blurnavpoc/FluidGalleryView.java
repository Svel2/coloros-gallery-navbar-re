package dev.iqbal.blurnavpoc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * Pager + scroll sederhana tanpa AndroidX:
 *  - 2 halaman sesuai Gallery asli: Photos (timeline) dan Albums (grid album)
 *  - drag horizontal = pindah halaman dengan spring settle (fluid)
 *  - drag vertikal = scroll, fling dengan spring
 *  - tiap gerakan memberi callback supaya blur bar ikut update
 */
public class FluidGalleryView extends View {

    public interface Listener {
        void onPageSettled(int page);

        void onMotion(float vy);

        void onPageProgress(float pagePos);
    }

    private static final int PAGES = 2;
    private static final String[] TITLES = {"Photos", "Albums"};
    private static final String[] SUBS = {"Januari 2025   \u00b7   1.240 item",
            "24 album   \u00b7   1.260 item"};
    private static final String[] ALBUMS = {"Camera", "Screenshots", "WhatsApp Images",
            "Downloads", "Videos", "Recent", "Telegram", "Instagram", "Edited",
            "Favorites", "Trash", "Shared album"};

    private final Spring pageSpring = new Spring();
    private final Spring scrollSpring = new Spring();
    private final Paint bgPaint = new Paint();
    private final Paint tilePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint subPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint iconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Listener listener;
    private boolean dark;

    private int page = 0;
    private float pagePos = 0f;
    private float scroll = 0f;
    private float maxScroll = 0f;

    private float downX, downY, startPage, startScroll;
    private int axis = 0; // 0 belum, 1 horizontal, 2 vertikal
    private VelocityTracker vt;

    private boolean looping = false;
    private long lastFrame;
    private float timeScale = 1f;

    public FluidGalleryView(Context c) {
        super(c);
        pageSpring.configure(0.40, 0.32);
        scrollSpring.configure(0.40, 0.22);
        textPaint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        subPaint.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
        iconPaint.setStyle(Paint.Style.STROKE);
        iconPaint.setStrokeCap(Paint.Cap.ROUND);
        setFocusable(true);
    }

    public void setListener(Listener l) {
        listener = l;
    }

    public void setTimeScale(float f) {
        timeScale = Math.max(0.05f, f);
    }

    public void setDark(boolean d) {
        dark = d;
        invalidate();
    }

    public int getPage() {
        return page;
    }

    public void snapToPage(int p) {
        pageSpring.snapTo(pagePos);
        pageSpring.setTarget(p);
        startAnim();
    }

    // ---------------- ukuran konten ----------------

    private int cellSize(int w) {
        return (w - dp(6) * 2 - dp(6) * 2) / 3;
    }

    private float albumCardH(int w) {
        int cw = (w - dp(18) * 2 - dp(12)) / 2;
        return cw * 0.72f + dp(48);
    }

    private float contentHeight(int page, int w) {
        if (page == 0) {
            return dp(156) + 60 * (cellSize(w) + dp(6)) + dp(80);
        }
        return dp(156) + (ALBUMS.length / 2 + 1) * (albumCardH(w) + dp(16)) + dp(60);
    }

    private void updateMaxScroll(int w, int h) {
        maxScroll = Math.max(0, contentHeight(page, w) - h);
        if (scroll > maxScroll) scroll = maxScroll;
    }

    @Override
    protected void onSizeChanged(int w, int h, int ow, int oh) {
        super.onSizeChanged(w, h, ow, oh);
        updateMaxScroll(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();
        if (w == 0 || h == 0) return;
        canvas.drawColor(dark ? 0xFF111214 : 0xFFF7F7F9);
        for (int p = 0; p < PAGES; p++) {
            float x = (p - pagePos) * w;
            if (x <= -w || x >= w * 2) continue;
            int save = canvas.save();
            canvas.translate(x, 0);
            drawPage(canvas, p, w, h);
            canvas.restoreToCount(save);
        }
    }

    private void drawPage(Canvas canvas, int p, int w, int h) {
        bgPaint.setColor(dark ? 0xFF111214 : 0xFFF7F7F9);
        canvas.drawRect(0, 0, w, h, bgPaint);

        float titleY = dp(96) - scroll;
        float subY = dp(116) - scroll;
        if (titleY > -dp(40) && titleY < h + dp(40)) {
            textPaint.setColor(dark ? 0xFFEDEDED : 0xFF111111);
            textPaint.setTextSize(dp(28));
            textPaint.setTextAlign(Paint.Align.LEFT);
            textPaint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
            canvas.drawText(TITLES[p], dp(18), titleY, textPaint);

            subPaint.setColor(dark ? 0x99FFFFFF : 0x99000000);
            subPaint.setTextSize(dp(12));
            subPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(SUBS[p], dp(18), subY, subPaint);

            drawHeaderIcons(canvas, w, titleY - dp(10));
        }

        if (p == 0) drawTimeline(canvas, w, h);
        else drawAlbums(canvas, w, h);
    }

    private void drawHeaderIcons(Canvas canvas, int w, float cy) {
        int col = dark ? 0xFFEDEDED : 0xFF111111;
        iconPaint.setColor(col);
        iconPaint.setStrokeWidth(dp(1.8f));

        float sx = w - dp(46);
        canvas.drawCircle(sx - dp(1), cy - dp(1), dp(7), iconPaint);
        canvas.drawLine(sx + dp(4), cy + dp(4), sx + dp(9), cy + dp(9), iconPaint);

        float mx = w - dp(22);
        for (int i = -1; i <= 1; i++) {
            canvas.drawCircle(mx, cy + i * dp(5), dp(1.5f), iconPaint);
        }
    }

    private void drawTimeline(Canvas canvas, int w, int h) {
        int pad = dp(6);
        int gap = dp(6);
        int cell = cellSize(w);
        int step = cell + gap;
        int top = dp(156);

        String[] dates = {"Hari ini", "Kemarin", "8 Nov", "7 Nov", "6 Nov"};
        int rows = 60;
        int rowStart = Math.max(0, (int) ((scroll - top) / step));
        int rowEnd = Math.min(rows, (int) ((scroll + h - top) / step) + 2);

        subPaint.setColor(dark ? 0xCCFFFFFF : 0xCC000000);
        subPaint.setTextSize(dp(13));
        subPaint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        subPaint.setTextAlign(Paint.Align.LEFT);

        int lastDateBlock = -1;
        for (int row = rowStart; row < rowEnd; row++) {
            float t = top + (float) row * step - scroll;
            if (t > h || t + cell < -dp(40)) continue;

            int dateBlock = row / 12;
            if (dateBlock != lastDateBlock) {
                lastDateBlock = dateBlock;
                float dy = t - dp(22);
                if (dy > dp(60) && dy < h) {
                    canvas.drawText(dates[dateBlock % dates.length], dp(10), dy, subPaint);
                }
            }

            for (int col = 0; col < 3; col++) {
                int i = row * 3 + col;
                float l = pad + (float) col * (cell + gap);
                drawTile(canvas, l, t, cell, i, 0);
            }
        }
    }

    private void drawAlbums(Canvas canvas, int w, int h) {
        int pad = dp(18);
        int gap = dp(12);
        int cw = (w - pad * 2 - gap) / 2;
        float ch = albumCardH(w);
        int top = dp(156);

        subPaint.setTextAlign(Paint.Align.LEFT);

        for (int i = 0; i < ALBUMS.length; i++) {
            int row = i / 2;
            int col = i % 2;
            float t = top + row * (ch + dp(16)) - scroll;
            if (t > h || t + ch < -dp(40)) continue;

            float l = pad + col * (cw + gap);
            float hue = (i * 41f + 20f) % 360f;
            float[] hsv = {hue, 0.35f + (i % 4) * 0.09f, 0.93f - (i % 3) * 0.08f};
            tilePaint.setStyle(Paint.Style.FILL);
            tilePaint.setColor(Color.HSVToColor(hsv));
            RectF card = new RectF(l, t, l + cw, t + ch - dp(40));
            canvas.drawRoundRect(card, dp(14), dp(14), tilePaint);

            // potongan mini tile di dalam kartu album
            tilePaint.setColor(0x38FFFFFF);
            float inner = dp(8);
            canvas.drawRoundRect(new RectF(card.left + inner, card.top + inner,
                    card.left + cw * 0.46f, card.top + (card.height()) * 0.46f),
                    dp(8), dp(8), tilePaint);
            canvas.drawRoundRect(new RectF(card.right - cw * 0.46f, card.bottom - card.height() * 0.46f,
                    card.right - inner, card.bottom - inner),
                    dp(8), dp(8), tilePaint);

            textPaint.setTextAlign(Paint.Align.LEFT);
            textPaint.setColor(dark ? 0xFFEDEDED : 0xFF111111);
            textPaint.setTextSize(dp(14));
            textPaint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
            canvas.drawText(ALBUMS[i], l + dp(2), t + ch - dp(20), textPaint);

            subPaint.setColor(dark ? 0x99FFFFFF : 0x99000000);
            subPaint.setTextSize(dp(11));
            subPaint.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
            canvas.drawText((12 + i * 7) + " item", l + dp(2), t + ch - dp(4), subPaint);
        }
    }

    private void drawTile(Canvas canvas, float l, float t, int size, int i, int page) {
        float hue = (page * 53f + i * 29f) % 360f;
        float[] hsv = {hue, 0.40f + (i % 5) * 0.09f, 0.92f - (i % 3) * 0.10f};
        tilePaint.setColor(Color.HSVToColor(hsv));
        tilePaint.setStyle(Paint.Style.FILL);
        RectF r = new RectF(l, t, l + size, t + size);
        canvas.drawRoundRect(r, dp(10), dp(10), tilePaint);

        tilePaint.setColor(0x33FFFFFF);
        canvas.drawCircle(l + size * 0.72f, t + size * 0.26f, size * 0.13f, tilePaint);

        Path path = new Path();
        path.moveTo(l + size * 0.08f, t + size * 0.88f);
        path.lineTo(l + size * 0.40f, t + size * 0.46f);
        path.lineTo(l + size * 0.60f, t + size * 0.72f);
        path.lineTo(l + size * 0.76f, t + size * 0.54f);
        path.lineTo(l + size * 0.94f, t + size * 0.88f);
        path.close();
        tilePaint.setColor(0x66FFFFFF);
        canvas.drawPath(path, tilePaint);

        tilePaint.setColor(0x25000000);
        canvas.drawRoundRect(new RectF(l, t + size - dp(18), l + size, t + size), dp(10), dp(10), tilePaint);
        subPaint.setTextSize(dp(9));
        subPaint.setTextAlign(Paint.Align.LEFT);
        subPaint.setColor(0xDDFFFFFF);
        canvas.drawText("IMG_" + (1024 + i) + ".jpg", l + dp(6), t + size - dp(6), subPaint);
    }

    // ---------------- gesture ----------------

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                downX = e.getX();
                downY = e.getY();
                startPage = pagePos;
                startScroll = scroll;
                axis = 0;
                pageSpring.active = false;
                scrollSpring.active = false;
                if (vt == null) vt = VelocityTracker.obtain();
                else vt.clear();
                vt.addMovement(e);
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                if (vt != null) vt.addMovement(e);
                float dx = e.getX() - downX;
                float dy = e.getY() - downY;
                if (axis == 0 && (Math.abs(dx) > dp(7) || Math.abs(dy) > dp(7))) {
                    axis = Math.abs(dx) > Math.abs(dy) ? 1 : 2;
                }
                if (axis == 1) {
                    pagePos = clamp(startPage - dx / getWidth(), 0f, PAGES - 1f);
                    if (listener != null) listener.onPageProgress(pagePos);
                } else if (axis == 2) {
                    float s = startScroll - dy;
                    if (s < 0) s *= 0.35f;
                    if (s > maxScroll) s = maxScroll + (s - maxScroll) * 0.35f;
                    scroll = s;
                }
                if (listener != null) listener.onMotion(0f);
                invalidate();
                return true;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                if (vt != null) {
                    vt.addMovement(e);
                    vt.computeCurrentVelocity(1000);
                }
                float vx = vt != null ? vt.getXVelocity() : 0f;
                float vy = vt != null ? vt.getYVelocity() : 0f;
                if (axis == 1) {
                    float projected = pagePos - clamp(vx / 5000f, -1.2f, 1.2f);
                    int target = clamp(Math.round(projected), 0, PAGES - 1);
                    pageSpring.snapTo(pagePos);
                    pageSpring.setTarget(target);
                    startAnim();
                } else if (axis == 2) {
                    float target = clamp(scroll - vy * 0.12f, 0f, maxScroll);
                    scrollSpring.snapTo(scroll);
                    scrollSpring.setTarget(target);
                    startAnim();
                }
                if (listener != null) listener.onMotion(vy);
                axis = 0;
                return true;
            }
        }
        return super.onTouchEvent(e);
    }

    private void startAnim() {
        if (looping) return;
        looping = true;
        lastFrame = System.nanoTime();
        Choreographer.getInstance().postFrameCallback(frame);
    }

    private final Choreographer.FrameCallback frame = new Choreographer.FrameCallback() {
        @Override
        public void doFrame(long frameTimeNanos) {
            long now = System.nanoTime();
            double dt = ((now - lastFrame) / 1e9) / timeScale;
            lastFrame = now;

            boolean more = false;
            if (pageSpring.update(dt)) more = true;
            pagePos = (float) pageSpring.value;
            if (scrollSpring.update(dt)) more = true;
            scroll = (float) scrollSpring.value;

            if (listener != null) listener.onPageProgress(pagePos);
            int settled = clamp(Math.round(pagePos), 0, PAGES - 1);
            if (!pageSpring.active) {
                boolean changed = page != settled;
                pagePos = settled;
                page = settled;
                updateMaxScroll(getWidth(), getHeight());
                if (changed) {
                    scroll = 0;
                    scrollSpring.snapTo(0);
                    if (listener != null) listener.onPageSettled(settled);
                }
            }

            if (listener != null) listener.onMotion(0f);
            invalidate();

            if (more) {
                Choreographer.getInstance().postFrameCallback(this);
            } else {
                looping = false;
            }
        }
    };

    private int dp(float v) {
        return Math.round(v * getResources().getDisplayMetrics().density);
    }

    private static int clamp(int v, int min, int max) {
        return v < min ? min : (v > max ? max : v);
    }

    private static float clamp(float v, float min, float max) {
        return v < min ? min : (v > max ? max : v);
    }
}
