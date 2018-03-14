
public class Tile extends Circle
{
	private final Object sprite;
	
	public Tile(double x, double y, double width)
	{
		super(x, y, (width/2.0));
		sprite = Game.loadSprite("tile.png");
	}
	
	public RenderInfo[] getRenderInfo()
	{
		RenderInfo[] ret = new RenderInfo[1];
		ret[0] = Game.newRenderInfo(sprite, getX()-getRadius(), getY()-getRadius());
		return ret;
	}
}
