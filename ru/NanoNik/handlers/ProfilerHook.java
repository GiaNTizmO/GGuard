// 
// Decompiled by Procyon v0.5.36
// 

package ru.NanoNik.handlers;

public class ProfilerHook extends qi
{
    private boolean doScreen;
    private int delay;
    private int shouldDelay;
    
    public ProfilerHook() {
        this.doScreen = true;
        this.delay = 0;
        this.shouldDelay = 8000;
    }
    
    public void a(final String name) {
        if (name.equals("tick") && bao.B().h != null && this.doScreen && !bao.B().G()) {
            ++this.delay;
            if (this.delay == this.shouldDelay) {
                ScreenMaster.saveScreenshot(bao.B().w, bao.B().d, bao.B().e, bao.B().a());
                this.doScreen = false;
            }
        }
        super.a(name);
    }
}
