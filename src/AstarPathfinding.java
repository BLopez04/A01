import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AstarPathfinding implements Runnable{
    private static final long SLEEPTIME = 500;
    private static int size;
    private static Point empty = new Point(-1, -1);

    @Override
    public void run() {
        ArrayList<Point> path = AstarPathfinding.computePath();

        // draw path using list
        for (Point p : path){
            GridModel.getInstance().set(p.x, p.y, GridCell.PATH);
        }
    }
    public static ArrayList<Point> computePath(){

        // get the size of the blackboard to determine the
        // bounds of the grid
        size = GridModel.getInstance().getSize();

        // get the start and endpoints from the blackboard
        Point start = empty;
        Point end = empty;
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                GridCell curCell = GridModel.getInstance().get(i, j);
                switch (curCell){
                    case START:
                        start = new Point(i, j);
                        break;
                    case END:
                        end = new Point(i, j);
                        break;
                }
            }
        }

        // if there wasn't a start and end point,
        // return with an empty list
        if(start.equals(empty) || end.equals(empty)){
            return new ArrayList<>();
        }


        // initialize open and close sets and helper functions
        List<AstarNode> openSet = new ArrayList<>();
        List<Point> closedSet = new ArrayList<>();
        Predicate<Point> canPassThrough = p -> withinBounds(p) && !GridModel.getInstance().get(p.x, p.y).equals(GridCell.OBSTACLE);
        Function<Point, Stream<Point>> potentialNeighbors = p -> Stream.of(new Point(p.x + 1, p.y),
                new Point(p.x, p.y - 1),
                new Point(p.x - 1, p.y),
                new Point(p.x, p.y + 1));

        // add the 1st node using the start point and start searching
        // for the end node until the openSet is empty
        openSet.add(new AstarNode(start, 0, Math.abs(end.x-start.x) + Math.abs(end.y-start.y), null));
        while(!openSet.isEmpty()){

            // gets the node with lowest fVal
            AstarNode curPoint = openSet.get(0);
            for(int i = 1; i < openSet.size(); i++){
                if(curPoint.fVal > openSet.get(i).fVal){
                    curPoint = openSet.get(i);
                }
            }

            if(withinReach(curPoint.pos, end)){
                ArrayList<Point> endPath = new ArrayList<>();
                Optional<AstarNode> backTrace = Optional.of(curPoint);
                while(!backTrace.get().pos.equals(start)){
                    endPath.addFirst(backTrace.get().pos);
                    backTrace = backTrace.get().previous;
                }
                return endPath;
            }

            List<Point> neighbors = potentialNeighbors.apply(curPoint.pos)
                    .filter(canPassThrough)
                    .filter(p -> !closedSet.contains(p))
                    .toList();
            for(Point p : neighbors){
                AstarNode newNeighbor = new AstarNode(p, curPoint.gVal + 1, Math.abs(end.x-p.x) + Math.abs(end.y-p.y), Optional.of(curPoint));
                int curSize = openSet.size();
                for(AstarNode s : openSet){
                    if(s.equals(newNeighbor)) {
                        if(s.fVal < newNeighbor.fVal){
                            break;
                        }else{
                            openSet.remove(s);
                            openSet.add(newNeighbor);
                            break;
                        }
                    }
                }
                if(curSize == openSet.size() && !openSet.contains(newNeighbor)){
                    try{
                        GridModel.getInstance().set(newNeighbor.pos.x, newNeighbor.pos.y, GridCell.FRONTIER);
                        Thread.sleep(SLEEPTIME);
                    }catch (Exception e){}
                    openSet.add(newNeighbor);
                }
            }

            try{
                if(!curPoint.pos.equals(start) && !curPoint.pos.equals(end)) GridModel.getInstance().set(curPoint.pos.x, curPoint.pos.y, GridCell.VISITED);
                Thread.sleep(SLEEPTIME);
            }catch (Exception e){}
            openSet.remove(curPoint);
            closedSet.add(curPoint.pos);
        }
        return new ArrayList<>();
    }

    private static boolean withinBounds(Point p){
        return (p.y >= 0 && p.y < size) && (p.x >= 0 && p.x < size);
    }

    private static boolean withinReach(Point cur, Point end) {
        return (Math.abs(cur.x - end.x) + Math.abs(cur.y - end.y)) == 1;
    }
}
