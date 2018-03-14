public interface Controllable extends Entity
{
    public Bind[] getBinds();
    public void sendInputs(Object[] signals);
}
