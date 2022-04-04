import processing.core.PApplet;

public abstract class MovingObject extends GameObject {
    private int[] pos;

    public MovingObject(int[] pos, int id, PApplet p) {
        super(id,p);
        if (pos.length == 2) {
            this.pos = pos;
        } else {
            throw new IllegalArgumentException("Not a coordinate");
        }
    }

    public MovingObject(int x, int y, int id, PApplet p) {
        super(id,p);
        pos = new int[2];
        this.pos[0] = x;

        this.pos[1] = y;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        if (pos.length == 2) {
            this.pos = pos;
        } else {
            throw new IllegalArgumentException("Not a coordinate");
        }
    }

    public int getX() {
        return pos[0];
    }

    public int getY() {
        return pos[1];
    }


    public void move(String direction) {
        char[] moves = direction.toCharArray();
        for (int i = 0; i < moves.length; i++) {
            switch (moves[i]) {
                case 'N':
                    if (pos[0] > 0) {
                        pos[0]--;
                    }
                    break;
                case 'S':
                    // if (pos[0]<Map.Legth-1)
                    // {
                    pos[0]++;
                    // }
                    break;
                case 'W':
                    if (pos[1] > 0) {
                        pos[1]--;
                    }
                    break;
                case 'E':
                    // if(pos[1]<Map[0].length-1)
                    // {
                    pos[1]++;
                    // }
            }
        }

    }

}
