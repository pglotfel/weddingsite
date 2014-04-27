package weddingsite.client;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlowPanel;
 
public class PracticeAnimation extends Animation
{
    private final FlowPanel element;
    private Double startX;
    private Double startY;
    private Double finalX;
    private Double finalY;
 
    public PracticeAnimation(FlowPanel element)
    {
        this.element = element;
    }
 
    public void expandTo(double x, double y, int milliseconds)
    {
        this.finalX = x;
        this.finalY = y;
 
        startX = 0.0;
        startY = 0.0;
 
        run(milliseconds);
    }
 
    @Override
    protected void onUpdate(double progress)
    {
        Double positionX = startX + (progress * (this.finalX - startX));
        Double positionY = startY + (progress * (this.finalY - startY));
 
        this.element.setSize(new Integer(positionX.intValue()).toString() + "%", new Integer(positionY.intValue()).toString() + "%");
    }
 
    @Override
    protected void onComplete()
    {
        super.onComplete();
        this.element.setSize(finalX.toString() + "%", finalY.toString() + "%");
    }
}
