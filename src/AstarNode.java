import java.awt.*;
import java.util.Optional;

public class AstarNode {
    public Point pos;
    public int gVal;
    public int hVal;
    public int fVal;

    public Optional<AstarNode> previous;

    public AstarNode(Point pos, int gVal, int hVal, Optional<AstarNode> prev){
        this.pos = pos;
        this.gVal = gVal;
        this.hVal = hVal;
        this.fVal = gVal + hVal;
        this.previous = prev;
    }

    @Override
    public boolean equals(Object obj) {
        if(pos == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(obj.getClass().equals(this.getClass())){
            AstarNode o = (AstarNode) obj;
            return pos.equals(o.pos);
        }else{
            return false;
        }
    }
}
