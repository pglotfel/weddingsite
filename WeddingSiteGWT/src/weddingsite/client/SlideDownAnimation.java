package weddingsite.client;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.Panel;

public class SlideDownAnimation extends Animation {
	
    private final Panel element;
    private Integer x;
    private Integer startY;
    private Integer finalY;
 
    public SlideDownAnimation(Panel element)
    {
        this.element = element;
    }
 
    public void expandTo(int x, int y, int milliseconds)
    {
        this.finalY = y;
        this.x = x;
 
        startY = 0;
 
        run(milliseconds);
    }
 
    @Override
    protected void onUpdate(double progress)
    {
        Double positionY = startY + (progress * (this.finalY - startY));
 
        this.element.setSize(x.toString() + "px", positionY.toString() + "px");
    }
 
    @Override
    protected void onComplete()
    {
        super.onComplete();
        this.element.setSize(x.toString() + "px", finalY.toString() + "px");
    }

}
