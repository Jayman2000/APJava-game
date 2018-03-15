public interface Collidable extends Entity
{
    public boolean isColliding(Circle collidableOther);
    public void onCollision(Collidable other);
}
