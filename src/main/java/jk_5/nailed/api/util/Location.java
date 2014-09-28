package jk_5.nailed.api.util;

import com.google.gson.JsonObject;

import jk_5.nailed.api.math.Vector2d;
import jk_5.nailed.api.math.Vector3d;
import jk_5.nailed.api.math.Vector3f;
import jk_5.nailed.api.math.Vector3i;
import jk_5.nailed.api.world.World;

/**
 * No description given
 *
 * @author jk-5
 */
public final class Location implements Vector3d, Cloneable {

    private final World world;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    public Location(Location location) {
        this(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public Location(World world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Location(double x, double y, double z, float yaw, float pitch) {
        this(null, x, y, z, yaw, pitch);
    }

    public Location(double x, double y, double z) {
        this(null, x, y, z, 0, 0);
    }

    public Location(World world, double x, double y, double z) {
        this(world, x, y, z, 0, 0);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public long getFloorX() {
        return (long) Math.floor(x);
    }

    @Override
    public long getFloorY() {
        return (long) Math.floor(y);
    }

    @Override
    public long getFloorZ() {
        return (long) Math.floor(z);
    }

    @Override
    public Location add(Vector3d v) {
        return this.add(v.getX(), v.getY(), v.getZ());
    }

    @Override
    public Location add(double x, double y, double z) {
        return Location.builder().copy(this).setX(this.x + x).setY(this.y + y).setZ(this.z + z).build();
    }

    @Override
    public Location sub(Vector3d v) {
        return this.sub(v.getX(), v.getY(), v.getZ());
    }

    @Override
    public Location sub(double x, double y, double z) {
        return Location.builder().copy(this).setX(this.x - x).setY(this.y - y).setZ(this.z - z).build();

    }

    @Override
    public Location mul(double a) {
        return this.mul(a, a, a);
    }

    @Override
    public Location mul(Vector3d v) {
        return this.mul(v.getX(), v.getY(), v.getZ());
    }

    @Override
    public Location mul(double x, double y, double z) {
        return Location.builder().copy(this).setX(this.x * x).setY(this.y * y).setZ(this.z * z).build();
    }

    @Override
    public Location div(double a) {
        return this.div(a, a, a);
    }

    @Override
    public Location div(Vector3d v) {
        return this.div(v.getX(), v.getY(), v.getZ());
    }

    @Override
    public Location div(double x, double y, double z) {
        return Location.builder().copy(this).setX(this.x / x).setY(this.y / y).setZ(this.z / z).build();
    }

    @Override
    public double dot(Vector3d v) {
        return 0;
    }

    @Override
    public double dot(double x, double y, double z) {
        return 0;
    }

    @Override
    public Location cross(Vector3d v) {
        return null;
    }

    @Override
    public Location cross(double x, double y, double z) {
        return null;
    }

    @Override
    public Location pow(double power) {
        return null;
    }

    @Override
    public Location ceil() {
        return Location.builder().copy(this).setX(Math.ceil(x)).setY(Math.ceil(y)).setZ(Math.ceil(z)).build();
    }

    @Override
    public Location floor() {
        return Location.builder().copy(this).setX(Math.floor(x)).setY(Math.floor(y)).setZ(Math.floor(z)).build();
    }

    @Override
    public Location round() {
        return Location.builder().copy(this).setX(Math.round(x)).setY(Math.round(y)).setZ(Math.round(z)).build();
    }

    @Override
    public Location abs() {
        return Location.builder().copy(this).setX(Math.abs(x)).setY(Math.abs(y)).setZ(Math.abs(z)).build();
    }

    @Override
    public Location negate() {
        return null;
    }

    @Override
    public Location min(Vector3d v) {
        return null;
    }

    @Override
    public Location min(double x, double y, double z) {
        return null;
    }

    @Override
    public Location max(Vector3d v) {
        return null;
    }

    @Override
    public Location max(double x, double y, double z) {
        return null;
    }

    @Override
    public double distanceSquared(Vector3d v) {
        return distanceSquared(v.getX(), v.getY(), v.getZ());
    }

    @Override
    public double distanceSquared(double x, double y, double z) {
        return square(this.x - x) + square(this.y - y) + square(this.z - z);
    }

    @Override
    public double distance(Vector3d v) {
        return distance(v.getX(), v.getY(), v.getZ());
    }

    @Override
    public double distance(double x, double y, double z) {
        return Math.sqrt(distanceSquared(x, y, z));
    }

    @Override
    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    @Override
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public Location normalize() {
        return null;
    }

    @Override
    public int getMinAxis() {
        return 0;
    }

    @Override
    public int getMaxAxis() {
        return 0;
    }

    @Override
    public Vector2d toVector2() {
        return null;
    }

    @Override
    public Vector2d toVector2(boolean useZ) {
        return null;
    }

    @Override
    public double[] toArray() {
        return new double[]{x, y, z};
    }

    @Override
    public Vector3i toInt() {
        return null;
    }

    @Override
    public Vector3f toFloat() {
        return null;
    }

    @Override
    public int compareTo(Vector3d v) {
        return 0;
    }

    @Override
    public Location clone() {
        return builder().copy(this).build();
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public World getWorld() {
        return world;
    }

    private double square(double in){
        return in * in;
    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", yaw=" + yaw +
                ", pitch=" + pitch +
                (world != null ? ", world=" + world : "") +
                '}';
    }

    public static final class Builder {

        private World world;
        private double x;
        private double y;
        private double z;
        private float yaw;
        private float pitch;

        public Builder copy(Location location){
            this.world = location.world;
            this.x = location.x;
            this.y = location.y;
            this.z = location.z;
            this.yaw = location.yaw;
            this.pitch = location.pitch;
            return this;
        }

        public World getWorld() {
            return world;
        }

        public Builder setWorld(World world) {
            this.world = world;
            return this;
        }

        public double getX() {
            return x;
        }

        public Builder setX(double x) {
            this.x = x;
            return this;
        }

        public double getY() {
            return y;
        }

        public Builder setY(double y) {
            this.y = y;
            return this;
        }

        public double getZ() {
            return z;
        }

        public Builder setZ(double z) {
            this.z = z;
            return this;
        }

        public float getYaw() {
            return yaw;
        }

        public Builder setYaw(float yaw) {
            this.yaw = yaw;
            return this;
        }

        public float getPitch() {
            return pitch;
        }

        public Builder setPitch(float pitch) {
            this.pitch = pitch;
            return this;
        }

        public Location build(){
            return new Location(world, x, y, z, yaw, pitch);
        }

        @Override
        public String toString() {
            return "Location.Builder{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    ", yaw=" + yaw +
                    ", pitch=" + pitch +
                    (world != null ? ", world=" + world : "") +
                    '}';
        }
    }

    public static Location read(JsonObject obj){
        Builder b = Location.builder().setWorld(null);
        if(obj.has("x")) b.setX(obj.get("x").getAsDouble());
        if(obj.has("y")) b.setY(obj.get("y").getAsDouble());
        if(obj.has("z")) b.setZ(obj.get("z").getAsDouble());
        if(obj.has("yaw")) b.setYaw(obj.get("yaw").getAsFloat());
        if(obj.has("pitch")) b.setPitch(obj.get("pitch").getAsFloat());
        return b.build();
    }
}
